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

import com.generation.lojajogos.model.Genero;
import com.generation.lojajogos.repository.GeneroRepository;

@RestController
@RequestMapping("/genero")
public class GeneroController {

	@Autowired
	private GeneroRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Genero>> getAll(){
		return ResponseEntity.ok(repository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Genero> getById(@PathVariable Long id){
		return repository.findById(id)
				.map(resp -> ResponseEntity.ok (resp))
	.orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/descricao/{descricao}")
	public ResponseEntity<List<Genero>> getByName(@PathVariable String descricao){
		return ResponseEntity.ok(repository.findAllByDescricaoContainingIgnoreCase(descricao));
	}

	@PostMapping
	public ResponseEntity<Genero> post (@RequestBody Genero genero){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body (repository.save(genero));
	}

	@PutMapping
	public ResponseEntity<Genero> atualizarPostagem(@Valid @RequestBody Genero genero){
	    return repository.findById(genero.getId())
	            .map(resposta -> ResponseEntity.status(HttpStatus.OK) 
	            .body(repository.save(genero)))
	            .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}") 
	public void delete (@PathVariable Long id) {
		Optional <Genero> genero = repository.findById(id); 
		if (genero.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
			repository.deleteById(id);
		
	}
	
}
