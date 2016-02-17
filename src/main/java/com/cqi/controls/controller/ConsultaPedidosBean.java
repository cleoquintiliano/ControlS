package com.cqi.controls.controller;

/**
 * @author cqfb
 */
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class ConsultaPedidosBean {

	private List<Integer> pedidosFiltrados;
	
	public ConsultaPedidosBean() {
		pedidosFiltrados = new ArrayList<>();
		for (int i = 0; i < 50; i++) {
			pedidosFiltrados.add(i);
		}
	}

	public List<Integer> getPedidosFiltrados() {
		return pedidosFiltrados;
	}
	
}
