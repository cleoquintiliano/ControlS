package com.cqi.controls.controller;

import java.io.Serializable;

import javax.faces.bean.ViewScoped;
import javax.inject.Named;

import com.cqi.controls.model.Produto;

/**
 * @author cqfb
 */
@Named
@ViewScoped
public class CadastroProdutoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Produto produto;

	public CadastroProdutoBean() {
		produto = new Produto();
	}

	public void salvar() {
	}

	public Produto getProduto() {
		return produto;
	}

}
