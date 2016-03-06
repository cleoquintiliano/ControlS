package com.cqi.controls.controller;

import java.io.Serializable;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.cqi.controls.model.Cliente;
import com.cqi.controls.model.Endereco;
import com.cqi.controls.service.CadastroClienteService;
import com.cqi.controls.util.jsf.FacesUtil;

/**
 * @author cqfb
 */

@Named
@ViewScoped
public class CadastroClienteBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CadastroClienteService cadastroClienteService;

	private Cliente cliente;
	private Endereco endereco;

	public CadastroClienteBean() {
		limpar();
	}
	
	/*public void inicializar() {
		// if (FacesUtil.isNotPostback()) {
		listaEnderecos = enderecos.listarEnderecos();

		// }
	}*/

	private void limpar() {
		cliente = new Cliente();

	}

	public void salvar() {
		this.cliente = cadastroClienteService.salvar(this.cliente);
		limpar();

		FacesUtil.addInfoMessage("Cliente salvo com sucesso!");
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
		
		/*if (this.cliente != null){
		this.endereco = this.usuario.getEndereco();
	}*/
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void adicionarEndereco() {
		cliente.getEnderecos().add(endereco);
	}
	
	public boolean isEditando() {
		return this.cliente.getId() != null;
	}

}
