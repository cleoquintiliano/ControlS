package com.cqi.controls.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import com.cqi.controls.model.Categoria;
import com.cqi.controls.model.Produto;

/**
 * @author cqfb
 */
@Named
@ViewScoped
public class CadastroProdutoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	private Produto produto;

	private List<Categoria> categoriasRaizes;

	public CadastroProdutoBean() {
		produto = new Produto();
	}

	public void inicializar() {
		System.out.println("Inicializando...");

		categoriasRaizes = manager.createQuery("from Categoria", Categoria.class).getResultList();

	}

	public void salvar() {
	}

	public Produto getProduto() {
		return produto;
	}

	public List<Categoria> getCategoriasRaizes() {
		return categoriasRaizes;
	}

}
