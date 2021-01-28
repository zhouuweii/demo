package com.zw.admin.framework.common.constant;

/**
 * 缓存的key 常量
 * @author: ZhouWei
 * @create: 2021-01
 */
public class CacheConstants {

    /**用户Token缓存前缀*/
    public final static String LOGIN_TOKEN_KEY = "user_token:";

    /**令牌自定义标识*/
    public static final String HEADER = "Authorization";

    /**令牌前缀*/
    public static final String TOKEN_PREFIX = "Bearer ";

    /**用户ID字段*/
    public static final String DETAILS_USER_ID = "user_id";

    /**用户名字段*/
    public static final String DETAILS_USERNAME = "username";

    /**授权信息字段*/
    public static final String AUTHORIZATION_HEADER = "authorization";

    /**字典管理 cache key*/
    public static final String SYS_DICT_KEY = "sys_dict:";

    /**字典管理 缓存时间*/
    public static final Long SYS_DICT_KEY_TIME = 60*2L;

    /**短信验证码 key*/
    public static final String SMS_CODE_KEY = "smsCode:";

    /**短信验证码 缓存时间*/
    public static final Long SMS_CODE_TIME = 60*5L;

}
