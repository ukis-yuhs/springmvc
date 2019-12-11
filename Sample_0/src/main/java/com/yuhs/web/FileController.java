package com.yuhs.web;

import com.alibaba.fastjson.JSONObject;
import com.yuhs.dto.ResponseJsonResult;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by yuhaisheng on 2019/4/23.
 */
@Controller
@RequestMapping("/file")
public class FileController {

    @Value("${FILE_UPLOAD_BSAE_PATH}")
    private String FILE_UPLOAD_BSAE_PATH;

    /**
     * 文件上传
     * @param files 上传文件列表
     * @return
     */
    @RequestMapping(value = "fileUpload", method = RequestMethod.POST)
    @ResponseBody
    public ResponseJsonResult fileUpload(@RequestParam(value = "file") List<MultipartFile> files) {
        ResponseJsonResult responseJsonResult = new ResponseJsonResult();
        if (!files.isEmpty()) {

            //判断上传文件夹是否存在
            File uploadFile = new File(FILE_UPLOAD_BSAE_PATH);
            if (!uploadFile.exists()) {
                uploadFile.mkdir();
            }
            //循环读取文件
            for (MultipartFile file : files) {
                String originalFileName = file.getOriginalFilename();
                if (originalFileName != null && !originalFileName.equals("")) {
                    try {
                        originalFileName = UUID.randomUUID() + "_" + originalFileName;
                        file.transferTo(new File(FILE_UPLOAD_BSAE_PATH + originalFileName));
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        responseJsonResult.setMsg("文件上传失败");
                    }
                } else {
                    responseJsonResult.setMsg("上传文件为空");
                }
            }
            responseJsonResult.setMsg("文件上传成功");
        } else {
            responseJsonResult.setMsg("没有上传文件");
        }
        return responseJsonResult;
    }

    /**
     * 超过文件大小限制处理
     * @param ex
     * @return
     */
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    @ResponseBody
    public ResponseJsonResult handExceprion(MaxUploadSizeExceededException ex) {
        ResponseJsonResult responseJsonResult = new ResponseJsonResult();
        responseJsonResult.setMsg("文件超过指定大小,上传失败");
        return responseJsonResult;
    }

    /**
     * 取上传文件夹中所有的文件名列表
     * @return
     */
    @RequestMapping(value = "fileList", method = RequestMethod.GET)
    @ResponseBody
    public ResponseJsonResult getFileList() {
        ResponseJsonResult responseJsonResult = new ResponseJsonResult();
        //获取upload文件夹
        File baseFile = new File(FILE_UPLOAD_BSAE_PATH);
        List<String> fileList = null;
        if (baseFile.exists()) {
            File[] files = baseFile.listFiles();
            fileList = new ArrayList<String>(files.length);
            for (File file : files) {
                fileList.add(file.getName());
            }
        }
        responseJsonResult.setList(fileList);
        return responseJsonResult;
    }

    /**
     * 文件下载
     * @param jsonpObject
     * @param request
     * @return
     * @throws IOException
     */
    //@RequestBody JSONObject jsonObj
    @RequestMapping(value = "download", method = RequestMethod.POST)
    //public ResponseEntity<byte[]> fileDownload(@RequestBody Map<string,string> map, HttpServletRequest request) throws IOException {
//            string filename = map.get("filename");
    public ResponseEntity<byte[]> fileDownload(@RequestBody JSONObject jsonpObject, HttpServletRequest request) throws IOException {
        String filename = jsonpObject.getString("filename");
        File file = new File(FILE_UPLOAD_BSAE_PATH + filename);
        System.out.println("转码前" + filename);
        filename = this.getFilename(request, filename);
        System.out.println("转码后" + filename);
        // 设置响应头通知浏览器下载
        HttpHeaders headers = new HttpHeaders();
        // 将对文件做的特殊处理还原
        filename = filename.substring(filename.indexOf("_") + 1);
        headers.setContentDispositionFormData("attachment", filename);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.OK);
    }

    /**
     * 根据不同的浏览器进行编码设置，返回编码后的文件名
     * @param request
     * @param filename
     * @return
     * @throws UnsupportedEncodingException
     */
    public String getFilename(HttpServletRequest request, String filename) throws UnsupportedEncodingException {
        String[] IEBrowerKeyWords = {"MSIE", "Trident", "Edge"};
        String userAgent = request.getHeader("User-Agent");
        System.out.print("userAgent= " +userAgent);
        for (String keyword : IEBrowerKeyWords) {
            if (userAgent.contains(keyword)) {
                return URLEncoder.encode(filename, "UTF-8");
            }
        }
        return new String(filename.getBytes("UTF-8"), "ISO-8859-1");
    }
}
