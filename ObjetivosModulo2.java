package com.helloworld.helloworld.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController /*diz que a classe se trata de um Controller*/
@RequestMapping("/objetivos") //como devo procura-lo
public class ObjetivosModulo2 {

	@GetMapping
	public String objetivos() {
		return "Meus objetivos neste módulo são: \n"
				+"Ser uma desenvolvedora Back-end\n"
				+"Aprender a utilizar novas aplicacoes"
				+"\nContinuar desenvolvendo minhas BSMs"
				+"\nDesenvolver meu blog pessoal"
				+"\nDesenvolver o projeto integrador Gen.";
	}
}
