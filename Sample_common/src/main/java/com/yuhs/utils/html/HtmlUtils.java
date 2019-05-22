package com.yuhs.utils.html;

import com.yuhs.utils.string.StringUtils;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by yuhaisheng on 2019/5/21.
 */
public class HtmlUtils {
    /**
     * HTML字符转义
     * 对输入参数中的敏感字符进行过滤替换,防止用户利用JavaScript等方式输入恶意代码
     * String input = <img src='http://t1.baidu.com/it/fm=0&gp=0.jpg'/>
     * HtmlUtils.htmlEscape(input);         //from spring.jar
     * StringEscapeUtils.escapeHtml(input); //from commons-lang.jar
     * 尽管Spring和Apache都提供了字符转义的方法,但Apache的StringEscapeUtils功能要更强大一些
     * StringEscapeUtils提供了对HTML,Java,JavaScript,SQL,XML等字符的转义和反转义
     * 但二者在转义HTML字符时,都不会对单引号和空格进行转义,而本方法则提供了对它们的转义
     * @param input
     * @return String 过滤后的字符串
     */
    public static String htmlEscape(String input) {
        if(StringUtils.isEmpty(input) || StringUtils.isAllSpace(input)){
            return input;
        }
        input = input.replaceAll("&", "&amp;");
        input = input.replaceAll("<", "&lt;");
        input = input.replaceAll(">", "&gt;");
        input = input.replaceAll(" ", "&nbsp;");
        input = input.replaceAll("'", "&#39;");   //IE暂不支持单引号的实体名称,而支持单引号的实体编号,故单引号转义成实体编号,其它字符转义成实体名称
        input = input.replaceAll("\"", "&quot;"); //双引号也需要转义，所以加一个斜线对其进行转义
        input = input.replaceAll("\n", "<br/>");  //不能把\n的过滤放在前面，因为还要对<和>过滤，这样就会导致<br/>失效了
        return input;
    }

    /**
     * 通过url获取网站html
     * @param url
     * @return
     */
    public static String parseHtml(String url) {
        // 测试HttpClient用法
        HttpClient client = new HttpClient();
        //设置代理服务器地址和端口
        HttpMethod method = null;
        String html = "";
        try {
            method = new GetMethod(url);
            client.executeMethod(method);
            html = method.getResponseBodyAsString();//获取网页内容
        } catch (HttpException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //释放连接
            if (method != null) {
                method.releaseConnection();
            }
        }
        return html;
    }

    /**
     * 解析html，需根据业务需要进行自定义
     * @param html
     */
    public static void getHtmlEarthBean(String html) {
        if (html != null && !"".equals(html)) {
            Document doc = Jsoup.parse(html);
            Elements linksElements = doc.getElementsByAttributeValue("class", "news-table");//获取class名字为 news-table
            for (Element ele : linksElements) {
                Elements linksElements1 = ele.getElementsByTag("td");//获取网页td的标签元素
                for (Element ele1 : linksElements1) {
                    System.out.println(ele1.text());
                }
            }
        }
    }
}
