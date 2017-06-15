package com.hz.school.controller;

import com.avaje.ebean.Ebean;
import com.hz.school.model.Student;
import com.hz.school.util.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
  
public class HelloController {
    private static Logger log=Logger.getLogger(HelloController.class);
    @RequestMapping(value="/",method=RequestMethod.GET)
    public String index(ModelMap model){
        log.info("----》》》。日志输出");
        model.addAttribute("message","Hello Spring mvc Framework");
        return "index";
    }
    @RequestMapping(value="/index",method=RequestMethod.GET)
    public String index2(ModelMap model){
        model.addAttribute("message","Hello Spring mvc Framework");
        return "index";
    }

    /**
     * 返回字符串
     */
	@ResponseBody
	@RequestMapping(value="/getString",method=RequestMethod.GET)
	public String getString(){
		return "wo shi String";
	}
  
   @RequestMapping("/hello")
   public ModelAndView hello(){
     ModelAndView mv =new ModelAndView();
     mv.addObject("spring", "spring mvc");
     mv.setViewName("hello");
     return mv;
   }

    /**
     * 表单处理
     */
    @RequestMapping(value = "/student", method = RequestMethod.GET)
    public ModelAndView student() {
        return new ModelAndView("student", "command", new Student());
    }
    @RequestMapping(value = "/addStudent", method = RequestMethod.POST)
    public String addStudent(@ModelAttribute("SpringWeb")Student student,
                             ModelMap model) {
        model.addAttribute(student);
//        model.addAttribute("name", student.getName());
//        model.addAttribute("age", student.getAge());
//        model.addAttribute("id", student.getId());
        return "result";
    }
    @RequestMapping(value="/addStudent2",method=RequestMethod.GET)
    public String addStudent2(ModelMap model){
        Student student= Ebean.find(Student.class,2l);
        model.addAttribute(student);
        return "result";
    }
    /**
     * 重定向例子
     */
    @RequestMapping(value = "/redirect", method = RequestMethod.GET)
    public String redirect() {
        return "redirect:finalPage";
    }
    @RequestMapping(value = "/finalPage", method = RequestMethod.GET)
    public String finalPage() {
        return "final";
    }
    /**
     * 静态页面例子
     */
    @RequestMapping(value = "/staticPage", method = RequestMethod.GET)
    public String staticPage() {
        return "redirect:/pages/final.html";
    }
    @RequestMapping(value = "/photo", method = RequestMethod.GET)
    public String staticPhoto() {
        return "redirect:/images/myLogo.jpg";
    }
}