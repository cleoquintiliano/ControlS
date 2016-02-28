package com.cqi.controls.controller;

import java.io.Serializable;

import javax.faces.bean.ViewScoped;
import javax.inject.Named;

import com.cqi.controls.model.Cliente;
import com.cqi.controls.model.Endereco;

/**
 * @author cqfb
 */

@Named
@ViewScoped
public class CadastroClienteBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Cliente cliente;
	private Endereco endereco;

	public CadastroClienteBean() {
		cliente = new Cliente();
		endereco = new Endereco();
	}

	public void salvar() {
		this.cliente = new Cliente();
		this.endereco = new Endereco();
	}

	public Cliente getCliente() {
		return cliente;
	}

	public Endereco getEndereco() {
		return endereco;
	}
	
	public void adicionarEndereco () {
		cliente.getEnderecos().add(endereco);
	}

}
