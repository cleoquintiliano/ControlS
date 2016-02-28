package com.cqi.controls.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

import com.cqi.controls.model.Categoria;
import com.cqi.controls.model.Produto;
import com.cqi.controls.repository.Categorias;

/**
 * @author cqfb
 */

@Named
@ViewScoped
public class CadastroProdutoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Categorias categorias;

	private Produto produto;
	private Categoria categoriaPai;

	private List<Categoria> categoriasRaizes;

	public CadastroProdutoBean() {
		produto = new Produto();
	}

	public void inicializar() {
		System.out.println("Inicializando...");

		categoriasRaizes = categorias.raizes();

	}

	public void salvar() {
		System.out.println("Categoria pai selecionada: " + categoriaPai.getDescricao());
	}

	public Produto getProduto() {
		return produto;
	}

	public List<Categoria> getCategoriasRaizes() {
		return categoriasRaizes;
	}
	
	@NotNull
	public Categoria getCategoriaPai() {
		return categoriaPai;
	}

	public void setCategoriaPai(Categoria categoriaPai) {
		this.categoriaPai = categoriaPai;
	}

}
