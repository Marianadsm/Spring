package com.helloworld.helloworld.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController /*diz que a classe se trata de um Controller*/
@RequestMapping("/hello-world") 
public class HelloWorldController {

	@GetMapping //no caso, sempre que eu chamar este GetMapping, virá o método que criei abaixo
	public String helloWorld() {
		return "Hello World!!!";
		}
	
}
