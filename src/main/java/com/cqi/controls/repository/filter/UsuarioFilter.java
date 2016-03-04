package com.cqi.controls.repository.filter;

import java.io.Serializable;

import org.hibernate.validator.constraints.Email;


/**
 * 
 * @author cqfb
 *
 */
public class UsuarioFilter implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String email;
	private String nome;

	@Email
	public String getEmail() {
		return email;
	}

	/**
	 * Metodo com operador condicional ternário
	 * @param sku
	 */
	public void setEmail(String email) {
		this.email = email == null ? null : email.toUpperCase();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
