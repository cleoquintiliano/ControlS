package com.cqi.controls.controller;

import java.io.Serializable;

import javax.faces.bean.ViewScoped;
import javax.inject.Named;

import com.cqi.controls.model.Grupo;
import com.cqi.controls.model.Usuario;

/**
 * @author cqfb
 */

@Named
@ViewScoped
public class CadastroUsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Usuario usuario;
	private Grupo grupo;

	public CadastroUsuarioBean() {
		usuario = new Usuario();
		grupo = new Grupo();
	}

	public void salvar() {
		this.usuario = new Usuario();
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public Grupo getGrupo() {
		return grupo;
	}

}
