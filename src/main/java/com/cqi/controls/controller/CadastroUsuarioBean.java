package com.cqi.controls.controller;

import java.io.Serializable;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.cqi.controls.model.Grupo;
import com.cqi.controls.model.Usuario;
import com.cqi.controls.service.CadastroUsuarioService;
import com.cqi.controls.util.jsf.FacesUtil;

/**
 * @author cqfb
 */

@Named
@ViewScoped
public class CadastroUsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private CadastroUsuarioService cadastroUsuarioService;

	private Usuario usuario;
	private Grupo grupo;
	
	//private List<Grupo> listaGrupos;

	public CadastroUsuarioBean() {
		limpar();
	}
	
	private void limpar() {
		usuario = new Usuario();
		grupo = new Grupo();
		//listaGrupos = new ArrayList<>();
	}

	public void salvar() {
		this.usuario = cadastroUsuarioService.salvar(this.usuario);
		limpar();

		FacesUtil.addInfoMessage("Usuario salvo com sucesso!");
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public Grupo getGrupo() {
		return grupo;
	}

}
