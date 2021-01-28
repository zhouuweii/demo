package com.zw.admin.system.service.impl;

import com.alibaba.fastjson.JSON;
import com.zw.admin.framework.common.constant.CacheConstants;
import com.zw.admin.framework.common.exception.ExceptionCast;
import com.zw.admin.framework.common.response.CommonCode;
import com.zw.admin.framework.common.utils.IdUtils;
import com.zw.admin.framework.common.utils.PhoneFormatCheckUtils;
import com.zw.admin.framework.common.utils.verification.RandomValidateCodeUtil;
import com.zw.admin.framework.core.service.RedisTemplateUtil;
import com.zw.admin.system.config.RabbitMqConfig;
import com.zw.admin.system.service.VerifyService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 验证码相关
 * @author: ZhouWei
 * @create: 2021-01
 **/
@Service
public class VerifyServiceImpl implements VerifyService {

    @Autowired
    private RedisTemplateUtil redisTemplateUtil;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 获取验证码
     * @param type 验证码类型
     * @return java.util.Map<java.lang.String,java.lang.String>
     **/
    @Override
    public Map<String, String> getCodeChar(String type) {
        RandomValidateCodeUtil randomValidateCode = new RandomValidateCodeUtil();
        Map<String, String> randCode = randomValidateCode.getRandCode(null, Boolean.TRUE);
        String code = randCode.get("code");
        String encoded = randCode.get("encoded");
        // 保存验证码信息
        String uuid = IdUtils.simpleUUID();
        redisTemplateUtil.setCacheObject("register:" + uuid, code, 2L, TimeUnit.MINUTES);
        Map<String, String> result = new HashMap<>();
        result.put("image", encoded);
        result.put("uuid", code);
        return result;
    }

    /**
     * 获取手机短信验证码
     * @param phone 手机号码
     * @return 验证码唯一标识
     **/
    @Override
    public String getSmsCode(String phone) {
        //校验手机号格式
        if (!PhoneFormatCheckUtils.isPhoneLegal(phone)) {
            ExceptionCast.cast(CommonCode.PARAM_PHONE_ERROR);
        }
        //短信验证码唯一标识
        String smsId = (long) ((Math.random() * 9 + 1) * 1000) + "";
        String key = CacheConstants.SMS_CODE_KEY + phone + "-" + smsId;
        //生成一个6位随机数
        String code = (long) ((Math.random() * 9 + 1) * 100000) + "";
        //将手机号、验证码存入Map
        Map<String, String> map = new HashMap<String, String>();
        map.put("phone", phone);
        map.put("code", code + "");
        //将消息对象转成json串
        String message = JSON.toJSONString(map);
        //发送消息
        rabbitTemplate.convertAndSend(RabbitMqConfig.EXCHANGE_TOPICS_INFORM, "inform.sms", message);
        //将验证码存入缓存
        redisTemplateUtil.setCacheObject(key, code, CacheConstants.SMS_CODE_TIME);
        System.out.println("手机号码:" + phone + "，验证码：" + code);
        return smsId;
    }

    /**
     * 校验手机短信验证码
     * @param phone 手机号码
     * @param code 手机验证码
     * @param smsId 验证码唯一标识
     * @return java.lang.Boolean
     **/
    @Override
    public Boolean checkSmsCode(String phone, String code, String smsId) {
        String key = CacheConstants.SMS_CODE_KEY + phone + "-" + smsId;
        Object redisKey = redisTemplateUtil.getCacheObject(key);
        if (redisKey == null) {
            return Boolean.FALSE;
        }
        if (!redisKey.equals(code)) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

}
