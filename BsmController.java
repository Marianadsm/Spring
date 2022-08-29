package com.helloworld.helloworld.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController /*diz que a classe se trata de um Controller*/
@RequestMapping("/bsmGen") //como devo procura-lo
public class BsmController {

	
	@GetMapping
	public String BsmGen() {
		return  "BSMs Generation: \n"
				+"Orientação ao futuro\n"
				+ "Responsabilidade Pessoal \n"
				+ "Mentalidade de crescimento \n"
				+"Trabalho em equipe \n"
				+"Orientacao ao detalhe \n"
				+"Pro Atividade\n"
				+"Comunicacao";
	}
	
}
