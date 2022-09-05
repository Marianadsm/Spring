package com.generation.farmacia.controller;

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

import com.generation.farmacia.model.Categoria;
import com.generation.farmacia.repository.CategoriaRepository;


@RestController
@RequestMapping("/categoria")
public class CategoriaController {
	@Autowired
	private CategoriaRepository catRepository;

	@GetMapping
	public ResponseEntity<List<Categoria>> getAll(){
		return ResponseEntity.ok(catRepository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Categoria> getById(@PathVariable Long id){
		return catRepository.findById(id)
				.map(resp -> ResponseEntity.ok (resp))
	.orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/descricao/{descricao}")
	public ResponseEntity<List<Categoria>> getByName(@PathVariable String descricao){
		return ResponseEntity.ok(catRepository.findAllByDescricaoContainingIgnoreCase(descricao));
	}

	@PostMapping
	public ResponseEntity<Categoria> post (@RequestBody Categoria categoria){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body (catRepository.save(categoria));
	}

	@PutMapping
	public ResponseEntity<Categoria> atualizarPostagem(@Valid @RequestBody Categoria categoria){
	    return catRepository.findById(categoria.getId())
	            .map(resposta -> ResponseEntity.status(HttpStatus.OK) 
	            .body(catRepository.save(categoria)))
	            .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}


	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}") //posts/o numero do ID e delete
	public void delete (@PathVariable Long id) {
		Optional <Categoria> categoria = catRepository.findById(id); 
		if (categoria.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
			catRepository.deleteById(id);
		
	}
	

}
