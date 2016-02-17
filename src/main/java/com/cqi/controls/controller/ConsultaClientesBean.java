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
public class ConsultaClientesBean {
	
	private List<Integer> clientesFiltrados;

	public ConsultaClientesBean() {
		clientesFiltrados = new ArrayList<>();
		for (int i = 0; i < 50; i++) {
			clientesFiltrados.add(i);
		}
	}

	public List<Integer> getClientesFiltrados() {
		return clientesFiltrados;
	}

}
