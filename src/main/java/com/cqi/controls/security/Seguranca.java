package com.cqi.controls.security;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

/**
 * @author cqfb
 *
 */

@Named
@RequestScoped
public class Seguranca {

	public String getNomeUsuario() {
		String nome = null;

		UsuarioSistema usuarioLogado = getUsuarioLogado();

		if (usuarioLogado != null) {
			nome = usuarioLogado.getUsuario().getNome();
		}

		return nome;
	}

	/**
	 * Internamente, a chamada do método ExternalContext.getUserPrincipal vai
	 * ser delegada para HttpServletRequest.getUserPrincipal. O Spring Security sobrescreve
	 * getUserPrincipal para retornar o objeto que representa o usuário logado.
	 * "getPrincipal()" retorna uma instancia de UsuarioSistema
	 * 
	 * @return usuario
	 */
	private UsuarioSistema getUsuarioLogado() {
		UsuarioSistema usuario = null;

		UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) FacesContext
				.getCurrentInstance().getExternalContext().getUserPrincipal();

		if (auth != null && auth.getPrincipal() != null) {
			usuario = (UsuarioSistema) auth.getPrincipal();
		}

		return usuario;
	}

}