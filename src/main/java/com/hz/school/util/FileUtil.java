package com.hz.school.util;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 文件工具类
 */
public class FileUtil {
    private static Logger log=Logger.getLogger(FileUtil.class);
    public static final int MEMORY_FILE_SIZE = 1024*1024; // 1M以内的文件，不保存到磁盘，直接放内存
    public static final int SAVE_INTERVAL = 300; // 每隔100毫秒，更新一次数据库，避免更新过于频繁

    public static List<FileItem> parseFileItems(HttpServletRequest request) throws FileUploadException {
        String uploadTempDir = SSMConfig.getProperty("upload.temp"); // 所有上传的临时文件都保存到这里
        File folder = new File(uploadTempDir);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        //使用Apache文件上传组件处理文件上传步骤：
        //1、创建一个DiskFileItemFactory工厂
        DiskFileItemFactory factory = new DiskFileItemFactory(MEMORY_FILE_SIZE,folder);
        //2、创建一个文件上传解析器
        ServletFileUpload upload = new ServletFileUpload(factory);
        try {
            List<FileItem> fileItems = upload.parseRequest(request);
            return fileItems;
        } catch (FileUploadException e) {
            log.error("FileUtil parseFileItems error",e);
            throw e;
        }finally {

        }
    }

    /**
     * 解析带有文件上传的表单普通输入项的数据
     */
    public static Map<String,String> parseFormFields(HttpServletRequest request) throws Exception {
        try {
            List<FileItem> fileItems=parseFileItems(request);
            Map<String,String> map=new HashMap<String, String>();
            for(FileItem item:fileItems){
                if(item.isFormField()){
                    String name=item.getFieldName();
                    String value=item.getString("UTF-8");
                    map.put(name,value);
                }
            }
            return map;
        } catch (Exception e) {
            log.error("FileUtil parseFormFields error",e);
            throw  e;
        }
    }

}
