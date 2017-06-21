package com.hz.school.filter;


import com.hz.school.model.Student;
import com.hz.school.util.EbeanUtil;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by wxb on 2016/11/14.
 */
public class CharEncodingFilter implements Filter{
    String encoding;
    public void init(FilterConfig cfg) throws ServletException {
        String e=cfg.getInitParameter("encoding");
        if(e==null||"".equals(e.trim())){
            encoding="UTF-8";
        }else {
            encoding=e;
        }
        EbeanUtil.find(Student.class).findRowCount();
    }
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding(encoding);
        chain.doFilter(request,response);
    }

    public void destroy() {



    }
}
