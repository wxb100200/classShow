<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
    <h2>上传ExcelDemo01</h2>
    <form action="../course/uploadExcel" enctype="multipart/form-data" method="post">
      上传用户：<input type="text" name="username"><br/>
      上传用户2：<input type="text" name="username2"><br/>
      上传文件1：<input type="file" name="file1"><br/>
      上传文件2：<input type="file" name="file2"><br/>
      <input type="submit" value="提交">
     </form>
    <h2>工商学校上传全校课表</h2>
    <form action="../course/uploadExcel" enctype="multipart/form-data" method="post">
      上传文件：<input type="file" name="file"><br/>
      <input type="submit" value="提交">
     </form>
    <h2>学军中学上传走班课表</h2>
    <form action="../course/uploadGoClassExcel" enctype="multipart/form-data" method="post">
      上传文件：<input type="file" name="file"><br/>
      <input type="submit" value="提交">
     </form>
    <h2>学军中学上传学生课表</h2>
    <form action="../course/uploadStudentCourseExcel" enctype="multipart/form-data" method="post">
      上传文件：<input type="file" name="file"><br/>
      <input type="submit" value="提交">
     </form>
</body>
</html>