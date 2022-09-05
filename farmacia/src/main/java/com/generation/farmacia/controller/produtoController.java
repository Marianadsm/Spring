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

import com.generation.farmacia.model.Produto;
import com.generation.farmacia.repository.ProdutoRepository;

@RestController
@RequestMapping("/produtos")
public class produtoController {

	@Autowired
	private ProdutoRepository Repository;
	
	@GetMapping 
	public ResponseEntity<List<Produto>> GetAll(){
		return ResponseEntity.ok(Repository.findAll());
	}
	@GetMapping("/{id}")
	public ResponseEntity<Produto> getById(@PathVariable Long id){
		return Repository.findById(id)
				.map(resposta -> ResponseEntity.ok (resposta))
						.orElse (ResponseEntity.status(HttpStatus.NOT_FOUND).build());		
	}
	@GetMapping ("/nome/{nome}")
	public ResponseEntity<List<Produto>> GetByTitulo (@PathVariable String nome) {
		return ResponseEntity.ok(Repository.findAllByNomeContainingIgnoreCase(nome));
		
	}
	@PostMapping 
	public ResponseEntity<Produto> post (@RequestBody Produto produto){ 
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(Repository.save(produto));
	}
	
	@PutMapping
    public ResponseEntity<Produto> atualizarProduto(@Valid @RequestBody Produto produto){
        return Repository.findById(produto.getId())
                .map(resposta -> ResponseEntity.status(HttpStatus.OK)
                .body(Repository.save(produto)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}") 
	public void delete (@PathVariable Long id) {
		Optional <Produto> produto = Repository.findById(id); 
		if (produto.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
			Repository.deleteById(id);
		
	}
}
