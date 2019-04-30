package com.yuhs.web;

import com.yuhs.exception.CustomException;
import com.yuhs.service.SysService;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by yuhaisheng on 2019/4/25.
 */
@Controller
@RequestMapping("user")
public class LoginController {
    @Autowired
    private SysService sysService;

    /**
     * 登录提交地址，和applicationContext-shiro.xml中配置的loginurl一致
     * @return
     */
    @RequestMapping(value = "login")
    public String login(HttpServletRequest request) throws Exception {
        //如果登录失败从request中获取认证异常信息
        //shiroLoginFailure就是shiro异常类到全限定名
        String exceptionClassName = (String)request.getAttribute("shiroLoginFailure");
        //根据shiro返回到异常类路径判断，抛出指定异常信息
        if (exceptionClassName != null) {
            if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
                throw new CustomException("账号不存在");
            } else if (IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
                throw new CustomException("用户名/密码错误");
            } else if ("randomcodeError".equals(exceptionClassName)) {
                throw new CustomException("验证码错误");
            }else {
                throw new Exception();//最终在异常处理器生成未知错误
            }
        }

        //此方法不出来登录成功，shiro认证成功会自动跳转到上一个路径
        //登录失败还到login页面
        return "login";
    }

    /**
     * 验证码生成
     * @param request
     * @return
     */
    @RequestMapping(value="getCheckImg",method= RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getCheckImg(HttpServletRequest request) {
        //在内存中创建图象
        int width = 80;
        int height = 35;
        BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);

        //创建图象
        Graphics g = image.getGraphics();
        //生成随机对象
        Random random = new Random();
        //设置背景色
        g.setColor(getRandColor(200,250));
        g.fillRect(0,0,width,height);
        //设置字体
        g.setFont(new Font("Tines Nev Roman",Font.ITALIC,22));
        //随机产生干扰线
        for(int i = 0; i < 5; i++){
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(60);
            int yl = random.nextInt(20);
            g.setColor(getRandColor(1,255));
            g.drawLine(x, y, x + xl, y + yl);
        }
        //随机产生认证码,4位数字
        String sRand = "";
        StringBuilder sRandBuff = new StringBuilder();
        Random r = new Random();
//		String words = "asdfghjklqwertyuiopzxcvb1234567890ASDFGHJKLQWERTYUIOPZXCVB";
        String words = "asdfghkqwertyupzxcvb123456789ASDFGHJKLQWERTYUPZXCVB";
        for(int i = 0; i < 4; i++){
            int idx = r.nextInt(words.length());
            //根据位置得到具体的字符
            char ch = words.charAt(idx);
            sRandBuff.append(ch);
//	     //旋转+- 30度
//	     int jiaodu = r.nextInt(60)-30;
//	     double hudu = jiaodu*Math.PI/180;
//	     //旋转的效果
//	     g.rotate(hudu, 5, height);

            //将认证码显示到图象中
            g.setColor(new Color(20 + random.nextInt(110),20 + random.nextInt(110),20 + random.nextInt(110)));
//	 		g.drawString(rand,13*i+6,16);
            g.drawString(ch+"",13*i + random.nextInt(6), 18 - random.nextInt(4));

//	 		String rand = String.valueOf(random.nextInt(10));
//	 		sRand  += rand;
//	 		//将认证码显示到图象中
//	 		g.setColor(new Color(20 + random.nextInt(110),20 + random.nextInt(110),20 + random.nextInt(110)));
            // // 		g.drawString(rand,13*i+6,16);
//	 		g.drawString(rand,13*i + random.nextInt(12),20 - random.nextInt(8));
        }
        request.getSession().setAttribute("validateCode",sRandBuff.toString());
        //图像生效
        g.dispose();

        String base64String = null;
        ByteArrayOutputStream baos = null;
        try {
            baos = new ByteArrayOutputStream(1024);
            ImageIO.write(image, "jpg", baos);
            baos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null) {
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        base64String = com.yuhs.utils.secure.Base64Utils.getBase64(baos.toByteArray());

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("errCode", "0"); // 0：正常。1：异常。
        map.put("errInfo", null); // null：正常。"xxxx错误"：异常。
        map.put("data", base64String);

        return map;
    }

    /**
     * 随机颜色
     * @param fc
     * @param bc
     * @return
     */
    private Color getRandColor(int fc,int bc){
        Random random = new Random();
        if(fc > 255){
            fc = 255;
        }
        if(bc < 255){
            bc = 255;
        }
        int r = fc +random.nextInt(bc-fc);
        int g = fc +random.nextInt(bc-fc);
        int b = fc +random.nextInt(bc-fc);
        return new Color(r,g,b);
    }
}
