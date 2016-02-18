package com.cqi.controls.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 * @author cqfb
 */
@Named
@RequestScoped
public class CadastroProdutoBean {
	
	public void salvar() {
		throw new RuntimeException("Teste de exceção.");
	}
	
}
