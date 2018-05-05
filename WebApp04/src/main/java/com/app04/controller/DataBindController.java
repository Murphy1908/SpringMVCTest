package com.app04.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/bind")
public class DataBindController {

	@RequestMapping(value="/baseType")
	public ModelAndView baseType( @RequestParam("xage") int age ) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("Agepage");
		mv.addObject("age", age);
		return mv;
	}
	
}
