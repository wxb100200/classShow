package com.hz.school.controller;

import com.hz.school.dao.GSCourseDao;
import com.hz.school.dao.xuejun.GoClassCourseDao;
import com.hz.school.dao.xuejun.StudentCourseDao;
import com.hz.school.util.ExcelUtil;
import com.hz.school.util.FileUtil;
import com.hz.school.util.Logger;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value="/course",produces="text/html;charset=UTF-8")
public class CourseController {
    private static Logger log= Logger.getLogger(ApiResource.class);

    @RequestMapping(value = "/uploadCourse")
    public String uploadCourse(HttpServletRequest request){
        return "uploadCourse";
    }

    /**
     * 工商学校上传全课表
     */
    @RequestMapping(value = "/uploadExcel")
    public String uploadExcel(HttpServletRequest request,String username,ModelMap model){
        try{
            log.info("----->>>>>>>>start import excel data<<<<<<<<<<-----------------");
            FileItem fileItem=parseFormFileItem(request).get(0);
            Workbook workbook= ExcelUtil.openExcel(fileItem.getInputStream(),false);
            Sheet sheet=workbook.getSheetAt(0);
            GSCourseDao.parseSheet(sheet,2,0,43);
            System.out.println("end import excel data");
            model.addAttribute("message","import excel data success");
            log.info("----->>>>>>>>close import excel data<<<<<<<<<<-----------------");
            return "hello";
        }catch (Exception e){
            log.error("-------->>>>>>>>CourseController generateExcelList error ",e);
            return null;
        }
    }
    /**
     * 学军中学上传走班课表
     */
    @RequestMapping(value = "/uploadGoClassExcel")
    public String uploadGoClassExcel(HttpServletRequest request,ModelMap model){
        try{
            log.info("----->>>>>>>>uploadGoClassExcel method start import excel data<<<<<<<<<<-----------------");
            FileItem fileItem=parseFormFileItem(request).get(0);
            Workbook workbook= ExcelUtil.openExcel(fileItem.getInputStream(),true);
            Sheet sheet=workbook.getSheetAt(0);
            GoClassCourseDao.parseSheet(sheet,3,0,43);
            System.out.println("end import excel data");
            model.addAttribute("message","import excel data success");
            log.info("----->>>>>>>>uploadGoClassExcel method  close import excel data<<<<<<<<<<-----------------");
            return "hello";
        }catch (Exception e){
            log.error("-------->>>>>>>>CourseController uploadGoClassExcel error ",e);
            return null;
        }
    }
    /**
     * 学军中学上传学生课表
     */
    @RequestMapping(value = "/uploadStudentCourseExcel")
    public String uploadStudentCourseExcel(HttpServletRequest request,ModelMap model){
        try{
            log.info("----->>>>>>>>uploadStudentCourseExcel method start import excel data<<<<<<<<<<-----------------");
            FileItem fileItem=parseFormFileItem(request).get(0);
            Workbook workbook=ExcelUtil.openExcel(fileItem.getInputStream(),false);
            Sheet sheet=workbook.getSheetAt(0);
            StudentCourseDao.parseSheet(sheet,0,0,5);
            System.out.println("end import excel data");
            model.addAttribute("message","import excel data success");
            log.info("----->>>>>>>>uploadStudentCourseExcel method  close import excel data<<<<<<<<<<-----------------");
            return "hello";
        }catch (Exception e){
            log.error("-------->>>>>>>>CourseController uploadStudentCourseExcel error ",e);
            return null;
        }
    }

    /**
     * 解析上传文件的FileItem
     */
    private static List<FileItem> parseFormFileItem(HttpServletRequest request) throws FileUploadException {
        try {
            List<FileItem> fileItems= FileUtil.parseFileItems(request);
            List<FileItem> fileItemList=new ArrayList<FileItem>();
            for(FileItem item:fileItems){
                if(!item.isFormField()){
                    //得到上传的文件名称，
                    String filename = item.getName();
                    if (filename == null || filename.trim().equals("")) {
                        continue;
                    }
                    fileItemList.add(item);
                }
            }
            return fileItemList;
        } catch (FileUploadException e) {
            log.error("CourseController parseFormFileItem error",e);
            throw  e;
        }
    }

    /**
     * 解析上传文件的获得输入流
     */
    private static List<InputStream> parseFormFile(HttpServletRequest request) throws Exception {
        try {
            List<FileItem> fileItems=FileUtil.parseFileItems(request);
            List<InputStream> inputStreams=new ArrayList<InputStream>();
            for(FileItem item:fileItems){
                if(!item.isFormField()){
                    //得到上传的文件名称，
                    String filename = item.getName();
                    if (filename == null || filename.trim().equals("")) {
                        continue;
                    }
                    //注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的，如： c:\a\b\1.txt，而有些只是单纯的文件名，如：1.txt
                    //处理获取到的上传文件的文件名的路径部分，只保留文件名部分
                    filename = filename.substring(filename.lastIndexOf("\\") + 1);
                    //获取item中的上传文件的输入流
                    InputStream in = item.getInputStream();
                    inputStreams.add(in);
                }
            }
            return inputStreams;
        } catch (Exception e) {
            log.error("CourseController parseFormFields error",e);
            throw  e;
        }
    }



}
