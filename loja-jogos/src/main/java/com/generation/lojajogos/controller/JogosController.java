package com.generation.lojajogos.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.generation.lojajogos.model.Jogos;
import com.generation.lojajogos.repository.JogosRepository;

@RestController
@RequestMapping ("/jogos")
public class JogosController {

	@Autowired
private JogosRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Jogos>> GetAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Jogos> getById(@PathVariable Long id){
		return repository.findById(id)
				.map(resposta -> ResponseEntity.ok (resposta))
						.orElse (ResponseEntity.status(HttpStatus.NOT_FOUND).build());		
	}
	@GetMapping ("/nome/{nome}")
	public ResponseEntity<List<Jogos>> GetByTitulo (@PathVariable String nome) {
		return ResponseEntity.ok(repository.findAllByNomeContainingIgnoreCase(nome));
		
	}
	@PostMapping 
	public ResponseEntity<Jogos> post (@RequestBody Jogos jogos){ 
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(repository.save(jogos));
	}
	
	@PutMapping
    public ResponseEntity<Jogos> atualizarProduto(@Valid @RequestBody Jogos jogos){
        return repository.findById(jogos.getId())
                .map(resposta -> ResponseEntity.status(HttpStatus.OK)
                .body(repository.save(jogos)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}") 
	public void delete (@PathVariable Long id) {
		Optional <Jogos> jogos = repository.findById(id); 
		if (jogos.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
			repository.deleteById(id);
		
	}
}
	

