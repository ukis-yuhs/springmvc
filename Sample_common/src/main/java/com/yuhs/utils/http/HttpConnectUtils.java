package com.yuhs.utils.http;

import cn.hutool.json.JSONArray;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by yuhaisheng on 2019/7/4.
 */
public class HttpConnectUtils {

    /**
     * Get方式请求
     *
     * @param url 请求路径
     * @return
     */
    public static String doGet(String url) {
        String responseMsg = "";
        HttpClient httpClient = new HttpClient();
        GetMethod getMethod = new GetMethod(url);
        getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        InputStream inputStream = null;

        try {
            httpClient.executeMethod(getMethod);
            inputStream = getMethod.getResponseBodyAsStream();

            int len = 0;
            byte[] buf = new byte[1024];
            while ((len = inputStream.read(buf)) != -1) {
                outputStream.write(buf, 0, len);
            }
            responseMsg = outputStream.toString("UTF-8");

        } catch (HttpException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭流
            if (inputStream != null || outputStream != null) {
                try {
                    inputStream.close();
                    outputStream.close();
                    inputStream = null;
                    outputStream = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            //释放连接
            getMethod.releaseConnection();
            httpClient.getHttpConnectionManager().closeIdleConnections(0);
        }
        return responseMsg;
    }

    /**
     * Post方式请求
     *
     * @param url 请求路径
     * @return
     */
    public static String doPost(String url) {
        //post请求返回结果
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost method = new HttpPost(url);
        method.setHeader("Connection", "close");

        try {
            HttpResponse result = httpClient.execute(method);
            url = URLDecoder.decode(url, "UTF-8");

            /**请求发送成功，并得到响应**/
            String str = getResultMessage(result);

            //关闭链接
            httpClient.close();

            if (str != null) return str;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            method.releaseConnection();
        }
        return null;
    }

    /**
     * Post方式请求
     *
     * @param url       请求路径
     * @param jsonParam 请求参数
     * @return
     */
    public static String doPost(String url, String jsonParam) {
        //post请求返回结果
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost method = new HttpPost(url);
        method.setHeader("Connection", "close");

        try {
            if (null != jsonParam) {
                //解决中文乱码问题
                setFileEncoding(method, jsonParam);
            }
            HttpResponse result = httpClient.execute(method);
            url = URLDecoder.decode(url, "UTF-8");

            /**请求发送成功，并得到响应**/
            String str = getResultMessage(result);

            //关闭链接
            httpClient.close();

            if (str != null) return str;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            method.releaseConnection();
        }

        return null;
    }

    /**
     * Post方式请求
     *
     * @param url       请求路径
     * @param jsonParam 请求参数
     * @return
     */
    public static String doPost(String url, JSONArray jsonParam) {
        //post请求返回结果
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost method = new HttpPost(url);
        method.setHeader("Connection", "close");

        try {
            if (null != jsonParam) {
                //解决中文乱码问题
                setFileEncoding(method, jsonParam.toString());
            }
            HttpResponse result = httpClient.execute(method);
            url = URLDecoder.decode(url, "UTF-8");

            /**请求发送成功，并得到响应**/
            String str = getResultMessage(result);

            //关闭链接
            httpClient.close();

            if (str != null) return str;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            method.releaseConnection();
        }

        return null;
    }

    /**
     * Post方式请求
     *
     * @param url        请求路径
     * @param formParams 请求参数
     * @return
     */
    public static String doPost(String url, Map<String, Object> formParams) {
        if (MapUtils.isEmpty(formParams)) {
            return null;
        }

        CloseableHttpClient httpClient = null;
        HttpPost httpPost = new HttpPost(url);
        String result = null;

        httpPost.setHeader("Connection", "close");
        try {
            httpClient = HttpClients.createDefault();

            //设置参数
            List<BasicNameValuePair> list = new ArrayList<BasicNameValuePair>();
            Iterator iterator = formParams.entrySet().iterator();

            while (iterator.hasNext()) {
                Map.Entry<String, String> elem = (Map.Entry<String, String>) iterator.next();
                list.add(new BasicNameValuePair(elem.getKey(), elem.getValue()));
            }

            if (list.size() > 0) {
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, "utf-8");
                httpPost.setEntity(entity);
            }

            HttpResponse response = httpClient.execute(httpPost);

            if (response != null) {
                org.apache.http.HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, "utf-8");
                }
            }

            httpClient.close();

            return result;

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            httpPost.releaseConnection();
        }

        return "";
    }

    /**
     * 解决中文乱码问题
     *
     * @param method
     * @param string
     */
    private static void setFileEncoding(HttpPost method, String string) {
        StringEntity entity = new StringEntity(string, "utf-8");
        entity.setContentEncoding("UTF-8");
        entity.setContentType("application/json");
        method.setEntity(entity);
    }

    /**
     * 请求成功返回结果
     *
     * @param result
     * @return
     */
    private static String getResultMessage(HttpResponse result) {
        if (result.getStatusLine().getStatusCode() == 200) {
            String str = "";
            try {
                /**读取服务器返回过来的json字符串数据**/
                str = EntityUtils.toString(result.getEntity());
                return str;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
