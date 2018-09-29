package com.hidupbersahaja.learn.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("/foo")
public class FooController {

	@RequestMapping(value = "/saySomething", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public void saySomething() throws Exception {
		System.out.println("----> executed --");
	}
	
}
