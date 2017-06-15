package com.hz.school.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

	public static String convertToNotNullStr(Object obj){
		if(obj==null){
			return "";
		}else{
			return obj.toString();
		}
	}

	public static boolean isNumber(String string){
		try{
			Double.parseDouble(string);
		}catch(Exception e){
			return false;
		}

		return true;
	}


    public static boolean isDate(String date,String pattern){
        if (isEmpty(date)){
            return false;
        }
        try{
//            DateUtil.parseDate(date, pattern);
        }catch(Exception e){
            return false;
        }
        return true;

    }

    public static boolean isEmpty(String string){

        if ( string==null || "".equals(string.trim())){
            return true;
        }

        return false;

    }
    public static boolean isEmpty(Object object){
        if ( object==null || "".equals((object.toString().trim()))){
            return true;
        }

        return false;

    }

    /**
     * 校验是否为邮箱
     * @param email
     * @return
     */
     public static boolean isEmail(String email) {
          if (isEmpty(email)) {
               return false;
          }
          String str = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

          Pattern p = Pattern.compile(str);
          Matcher m = p.matcher(email);
          return m.matches();
     }

     /**
     * 校验是否为手机号
     * @param mobiles
     * @return
     */
     public static boolean isMobileNO(String mobiles) {
          if (isEmpty(mobiles))
               return false;
          Pattern p = Pattern.compile("^1(3[0-9]|4[57]|5[^4,\\D]|8[023456789])\\d{8}$");
          Matcher m = p.matcher(mobiles);
          return m.matches();
     }

    public static String inputStream2String(InputStream is){
		   BufferedReader in = new BufferedReader(new InputStreamReader(is));
		   StringBuffer buffer = new StringBuffer();
		   String line = "";
		   try {
			while ((line = in.readLine()) != null){
			     buffer.append(line);
			   }
		} catch (IOException e) {
			e.printStackTrace();
		}
		   return buffer.toString();
	}

     /**
     *
     * 对输入数据库中的回车换行进行处理
     */
    public static String replaceoperationChar(String message){
    	if(message == null){
    		return null;
    	}
    	char content[] = new char[message.length()];
    	message.getChars(0, message.length(), content, 0);
    	StringBuffer result = new StringBuffer();
    	for(int i = 0; i < content.length;i++){
    		switch(content[i]){
    		case '\'' :
    			result.append("/");
    			break;
    		default  :
    			result.append(content[i]);
    		}
    	}
    	return result.toString();
    }

     public static String replaceoperationWebChar(String message){
    	if(message == null){
    		return null;
    	}
    	char content[] = new char[message.length()];
    	message.getChars(0, message.length(), content, 0);
    	StringBuffer result = new StringBuffer();
    	for(int i = 0; i < content.length;i++){
    		switch(content[i]){
    		case '/' :
    			result.append(File.separator);
    			break;
    		default  :
    			result.append(content[i]);
    		}
    	}
    	return result.toString();
    }


    /**
     *
     * 对输入数据库中的回车换行进行处理
     */
    public static String operationChar(String message){
    	if(message == null){
    		return null;
    	}
    	char content[] = new char[message.length()];
    	message.getChars(0, message.length(), content, 0);
    	StringBuffer result = new StringBuffer(content.length + 50);
    	for(int i = 0; i < content.length;i++){
    		switch(content[i]){
    		case '\'' :
    			result.append("&#039;");
    			break;
    		case '\"' :
    			result.append("&#034;");
    			break;
    		case '<' :
    			result.append("&lt;");
    			break;
    		case '>' :
    			result.append("&gt;");
    			break;
    		case '&' :
    			result.append("&amp;");
    			break;
    		case ' ' :
    			result.append("&nbsp;");
    			break;
    		case '\n':
    			result.append("<br>");
    			break;
    		default  :
    			result.append(content[i]);
    		}
    	}
    	return result.toString();
    }

    /**
     *
     */
   public static String deOperationChar(String message){
	   if(message == null){
		   return null;
	   }
	  // StringBuffer result = new StringBuffer();
	  /**
	   * 输出到页面对换行的处理
	   */
	   message.replaceAll("&lt;", "<");
	   message.replaceAll("&gt;", ">");
	   message.replaceAll("&amp;", "&");
	   message.replaceAll("&quot;","\"");
	   message.replaceAll("&nbsp;", " ");
	   /**
	    * 下面的两个while语句也可以对输出到页面的回车换行进行处理
	    * 若用下面的方法则应该屏蔽上面的operationChar(String str)方法
	    * 不然会导致有时输出有误
	    */
//	   while(message.indexOf("\n")!=-1){
//   		message = message.substring(0,message.indexOf("\n"))+"<br>"+message.substring(message.indexOf("\n")+1);
//			}
//		while(message.indexOf(" ")!=-1){
//		message = message.substring(0,message.indexOf(" "))+"&nbsp;"+message.substring(message.indexOf(" ")+1);
//			}
//		message.replaceAll("<br&nbsp;/>", "<br>");
	   //message.replaceAll("\r\n", "<br />");

	   return message;
   }

   /**
    * 产生固定长度的字符串。
    * 如果src的长度比length参数大，返回原始src，否则将在前（或后）填补padding字符。
    * @param src 源字符串
    * @param length 期望的长度
    * @param padding 填补的字符
    * @param leadingPad 在最前面填补还是在最后面填补。
    * @return 填补以后的字符串
    */
   public static String fixLength(String src, int length, char padding, boolean leadingPad) {
       if (src == null) {
           src = "";
       }
       if (length <= src.length()) {
           return src;
       }
       StringBuilder sb = new StringBuilder(src);
       for (int i = src.length(), j = length; i < j; i++) {
           if (leadingPad) {
               sb.insert(0, padding);
           } else {
               sb.append(padding);
           }
       }
       return sb.toString();
   }

   /**
   * url解密
   *
   * @param encodedUrl
   * @return String
   * @throws IOException
   */
   public final static String decodeUri(String encodedUrl) throws IOException {
      String[] url = encodedUrl.replace("|", ",").split(",");
      byte[] urlByte = new byte[url.length];
//      for (int i = 0; i < url.length; i++) {
//      }

      for (int i = 0; i < url.length; i++) {
       if (!url[i].equals(""))
        urlByte[i] = Byte.parseByte(url[i]);
      }
      ByteArrayInputStream bin = new ByteArrayInputStream(urlByte);
      DataInputStream din = new DataInputStream(bin);
      String oriStr = din.readUTF();
      din.close();
      bin.close();
      return oriStr;
   }

   /**
   * url加密
   *
   * @param url
   * @return String
   * @throws IOException
   */
   public final static String encodeUri(String url) throws IOException {
      ByteArrayOutputStream bout = new ByteArrayOutputStream();
      DataOutputStream dout = new DataOutputStream(bout);
      dout.writeUTF(url);
      byte[] byteUrl = bout.toByteArray();
      StringBuffer buf = new StringBuffer();
      for (int i = 0; i < byteUrl.length; i++) {
          buf.append(byteUrl[i] + "|");
      }
      dout.close();
      bout.close();
      return buf.toString();
   }

   /**
    * 从c:\\\ddd\\t.txt 或者 /dfd/t.txt类似的路径中解析文件名。
    */
    public static String parseFileName(String path){
		String name = new File(path).getName();
		int a = name.lastIndexOf("/");
		if(a != -1){
		    return name.substring(a+1);
		}
        int b = name.lastIndexOf("\\");
        if (b != -1){
		    return name.substring(b+1);
		}
		return name;
   }
   //给出一个文件名  返回是否是web文件
   public static String isWebFile(String fileName){
       if(fileName == null || fileName.lastIndexOf('.')<0){
           return null;
       }
       String extension = fileName.substring(fileName.lastIndexOf('.')).toLowerCase();
       String prohibitExtension = ",.exe,.com,.jsp,.php,.php3,.asp,.aspx,.html,.htm,.hta,.js,.css,";
       if(prohibitExtension.indexOf(","+extension + ",")!=-1){
           return "文件后缀不能为:.exe,.com,.jsp,.php,.php3,.asp,.aspx,.html,.htm,.hta,.js,.css";
       }
       return null;
   }
    //给出一个filter的map
    public static Map<String,String> getFilter(String filter){
        if(StringUtil.isEmpty(filter)){
            return null;
        }
        JSONArray arr = JSON.parseArray(filter);
        if(arr==null || arr.size()==0){
            return null;
        }
        Map<String,String> filterMap = new HashMap<String, String>();
        for(int i=0,j=arr.size(); i<j; i++){
            JSONObject obj = (JSONObject) arr.get(i);
            String name = obj.getString("property");
            String value = obj.getString("value");
            name = name.trim();
            value = value.trim();
            filterMap.put(name,value);
        }
        return filterMap;
    }
   public static void main(String[] arg)
   {
       System.out.println(parseFileName("extl.txt"));
//	   StringUtil.isDate("2009-09-09 12:00:00", DateUtil.C_TIME_PATTON_DEFAULT);
   }
}
