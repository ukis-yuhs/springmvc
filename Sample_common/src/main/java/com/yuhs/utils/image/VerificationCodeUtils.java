package com.yuhs.utils.image;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * Created by yuhaisheng on 2019/7/19.
 */
public class VerificationCodeUtils {

    /**
     * 生成验证码图片
     * @param uuid
     * @param outPutStream
     * @return
     */
    public static BufferedImage getVerificationCode(String uuid, int outPutStream) {
        byte width = 85;
        byte height = 28;
        BufferedImage image = new BufferedImage(width, height, 2);
        Graphics2D g = image.createGraphics();
        g.setComposite(AlphaComposite.getInstance(3, 1.0F));
        Random random = new Random();
        g.setColor(new Color(231, 231, 231));
        g.fillRect(0, 0, width, height);
        g.setFont(new Font("Microsoft YaHei", 2, 24));
        String sRand = "";

        for (int responseOutputStream = 0; responseOutputStream < outPutStream; responseOutputStream++) {
            String rand = String.valueOf(random.nextInt(10));
            sRand = sRand + rand;
            g.setColor(new Color(121, 143, 96));
            g.drawString(rand, 13 * responseOutputStream + 16, 23);
        }

        //保存验证码，以对比用
        // session.setAttribute(GlobalConstant.RAND_CODE, sRand);
        //redisService.set(uuid, sRand);
        g.dispose();
        return image;
    }
}
