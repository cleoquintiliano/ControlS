package com.cqi.controls.controller;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 * @author cqfb
 */

@Named
@RequestScoped
public class ConsultaEnderecosBean {
	
	private List<Integer> enderecosFiltrados;

	public ConsultaEnderecosBean() {
		enderecosFiltrados = new ArrayList<>();
		for (int i = 0; i < 2; i++) {
			enderecosFiltrados.add(i);
		}
	}

	public List<Integer> getEnderecosFiltrados() {
		return enderecosFiltrados;
	}

}
