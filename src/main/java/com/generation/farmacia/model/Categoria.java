package com.generation.farmacia.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_categoria")
public class Categoria {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	 @NotNull(message = "O Atributo categoria é obrigatório")
	    private String categoria;
	
	@NotBlank(message = "O atributo nome deve ser obrigatório")
	@Size(min = 4, max = 50, message = "O O Nome deve ter entre 4 e 50 caracteres.")
	private String nome; 
	
	@NotBlank(message = "O atributo categoria deve seer obrigatório")
	@Size(min = 4, max = 50, message = "O O Nome deve ter entre 4 e 50 caracteres.")
	private String descricao;

	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
