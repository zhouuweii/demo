package com.zw.admin.framework.common.utils.verification;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 验证码工具类
 * @author: ZhouWei
 * @create: 2021-01
 **/
public class RandomValidateCodeUtil {

    private static final Logger logger = LoggerFactory.getLogger(RandomValidateCodeUtil.class);

    /**随机产生只有数字的字符串*/
//    private String randString = "0123456789";
    /**随机产生只有字母的字符串*/
//    private String randString = "abcdefghijklmnopqrstuvwxyz";
    /**随机产生数字与字母组合的字符串*/
    private String randString = "0123456789abcdefghijklmnopqrstuvwxyz";
    /**图片宽*/
    private int width = 75;
    /**图片高*/
    private int height = 25;
    /**干扰线数量*/
    private int lineSize = 40;
    /**随机产生字符数量*/
    private int stringNum = 4;

    private Random random = new Random();

    /**
     * 获取验证图片
     * @param response 响应浏览器为图片
     * @param isStream 返回图片为流
     * @return java.lang.String
     **/
    public Map<String, String> getRandCode(HttpServletResponse response, Boolean isStream) {
        Map result = new HashMap();
        //BufferedImage类是具有缓冲区的Image类,Image类是用于描述图像信息的类
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
        //产生Image对象的Graphics对象,改对象可以在图像上进行各种绘制操作
        Graphics graphics = image.getGraphics();
        //图片大小
        graphics.fillRect(0, 0, width, height);
        //字体大小
        graphics.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE, 18));
        //字体颜色
        graphics.setColor(getRandColor(110, 133));
        //绘制干扰线
        for (int i = 0; i <= lineSize; i++) {
            drawLine(graphics);
        }
        //绘制随机字符
        String randomString = "";
        for (int i = 1; i <= stringNum; i++) {
            randomString = drawString(graphics, randomString, i);
        }
        result.put("code", randomString);
//        System.out.println(randomString);
        graphics.dispose();
        try {
            if (isStream) {
                //BufferedImage转换为Base64
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                ImageIO.write(image, "JPEG", stream);
                final BASE64Encoder encoder = new BASE64Encoder();
                final String encoded = encoder.encode(stream.toByteArray());
                String encodedText = encoded.replaceAll("\r|\n", "");
                result.put("encoded", encodedText);
                return result;
            } else {
                //将内存中的图片通过流动形式输出到客户端
                ImageIO.write(image, "JPEG", response.getOutputStream());
            }
        } catch (Exception e) {
            logger.error("将内存中的图片通过流动形式输出到客户端失败", e);
        }
        return result;
    }

    /**
     * 获得颜色
     * @param fc
     * @param bc
     * @return java.awt.Color
     **/
    private Color getRandColor(int fc, int bc) {
        if (fc > 255) {
            fc = 255;
        }
        if (bc > 255) {
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc - 16);
        int g = fc + random.nextInt(bc - fc - 14);
        int b = fc + random.nextInt(bc - fc - 18);
        return new Color(r, g, b);
    }

    /**
     * 绘制干扰线
     * @param graphics
     * @return void
     **/
    private void drawLine(Graphics graphics) {
        int x = random.nextInt(width);
        int y = random.nextInt(height);
        int xl = random.nextInt(13);
        int yl = random.nextInt(15);
        graphics.drawLine(x, y, x + xl, y + yl);
    }

    /**
     * 绘制字符串
     * @param graphics
     * @param randomString
     * @param i
     * @return java.lang.String
     **/
    private String drawString(Graphics graphics, String randomString, int i) {
        graphics.setFont(getFont());
        graphics.setColor(new Color(random.nextInt(101), random.nextInt(111), random.nextInt(121)));
        String rand = String.valueOf(getRandomString(random.nextInt(randString.length())));
        randomString += rand;
        graphics.translate(random.nextInt(3), random.nextInt(3));
        graphics.drawString(rand, 13 * i, 16);
        return randomString;
    }

    /**
     * 获得字体
     * @return java.awt.Font
     **/
    private Font getFont() {
        return new Font("Fixedsys", Font.CENTER_BASELINE, 18);
    }

    /**
     * 获取随机的字符
     * @param num
     * @return java.lang.String
     **/
    private String getRandomString(int num) {
        return String.valueOf(randString.charAt(num));
    }
}
