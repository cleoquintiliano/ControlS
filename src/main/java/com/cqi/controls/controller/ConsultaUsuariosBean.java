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
public class ConsultaUsuariosBean {
	
	private List<Integer> usuariosFiltrados;

	public ConsultaUsuariosBean() {
		usuariosFiltrados = new ArrayList<>();
		for (int i = 0; i < 50; i++) {
			usuariosFiltrados.add(i);
		}
	}

	public List<Integer> getUsuariosFiltrados() {
		return usuariosFiltrados;
	}

}
