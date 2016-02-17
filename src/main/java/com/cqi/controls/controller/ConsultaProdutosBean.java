package com.cqi.controls.controller;

import java.io.Serializable;
/**
 * @author cqfb
 */
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
//Um managed bean é capaz de passivação (e ativação) SE E SOMENTE SE a classe de bean é serializável
public class ConsultaProdutosBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<Integer> produtosFiltrados;

	public ConsultaProdutosBean() {
		produtosFiltrados = new ArrayList<>();
		for (int i = 0; i < 50; i++) {
			produtosFiltrados.add(i);
		}
	}

	public List<Integer> getProdutosFiltrados() {
		return produtosFiltrados;
	}

}
