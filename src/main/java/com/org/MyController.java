package com.org;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MyController {
	
	@RequestMapping("/add") //way 1
	public void service1(ServletRequest req, ServletResponse res) throws IOException {
		
		double num1 = Double.parseDouble(req.getParameter("n1"));
		double num2 = Double.parseDouble(req.getParameter("n2"));
		
		PrintWriter p = res.getWriter();
		p.write("<h1>Sum is: "+(num1+num2)+"</h1>");
		
	}

	@RequestMapping("/sub") // way 2
	@ResponseBody
	public String service2(@RequestParam double n1, @RequestParam double n2) throws IOException {
		
		return "Sub is: "+(n1-n2);
	}
	
	@RequestMapping("/mul") // way 3
	public ModelAndView service3(@ModelAttribute Numbers n) {
		
		double n1 = n.getN1();
		double n2 = n.getN2();
		
		ModelAndView mav = new ModelAndView("display.jsp");
		mav.addObject("res", "MUL is: "+(n1*n2));
		return mav;
		
	}
	
	@RequestMapping("/div") // way 4
	public String service(@RequestParam double n1, @RequestParam double n2, Model m) throws IOException {
		
		m.addAttribute("res", "DIV is: "+(n1/n2));
		return "display.jsp";
		
	}

}
