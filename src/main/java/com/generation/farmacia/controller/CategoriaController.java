package com.generation.farmacia.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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

import jakarta.validation.Valid;

@RestController
@RequestMapping("/categoria")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoriaController {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@GetMapping("/all")
	ResponseEntity<List<Categoria>> getAll() {
		return ResponseEntity.ok(categoriaRepository.findAll());
	}

	@GetMapping("/{id}")
	ResponseEntity<Categoria> getById(@PathVariable Long id) {
		return categoriaRepository.findById(id).map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

	@GetMapping("/categoria") // buscar por categoria
	public ResponseEntity<List<Categoria>> getByCategoria(@PathVariable String categoria) {
		return ResponseEntity.ok(categoriaRepository.findAllByCategoriaContainingIgnoreCase(categoria));
	}

	@PostMapping("/cadastrar")
	public ResponseEntity<Categoria> post(@RequestBody @Valid Categoria categoria) {
		Categoria categoriaSalva = categoriaRepository.save(categoria);
		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaSalva);
	}

	@PutMapping("/atualizar")
	public ResponseEntity<Categoria> putCategoria(@RequestBody @Valid Categoria categoria) {
		if (categoriaRepository.existsById(categoria.getId())) { // veriricar se o id com a categoria existe
			Categoria categoriaAtualizada = categoriaRepository.save(categoria); // atualizar no bd

			return ResponseEntity.status(HttpStatus.OK).body(categoriaAtualizada);
		} else {

			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria não encontrada");
		}
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		Optional<Categoria> categoria = categoriaRepository.findById(id);

		if (categoria.isEmpty()) // Verifica se a postagem existe ----> poderia usar o .map nesse caso tambem
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);

		categoriaRepository.deleteById(id);

	}
}