package com.cqi.controls.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
	
	private Endereco endereco;
	
	//private Enderecos enderecos;
	
	private Cliente cliente;
	
	private Endereco enderecoSelecionado;
	
	//private List<Endereco> listaEnderecos;
	
	public CadastroClienteBean() {
		limpar();
	}
	
	public void inicializar() {
//		// if (FacesUtil.isNotPostback()) {
//		if (isEditando()) { // Se estiver editando, a lista de endereços do
//			// cliente editado é carregada.
//			this.listaEnderecos = cliente.getEnderecos();
//		}

		// }
	}

	private void limpar() {
		cliente = new Cliente();
		endereco = new Endereco();
		enderecoSelecionado = new Endereco();
		//listaEnderecos = new ArrayList<>(); 
		

	}

	public void salvar() {
		this.cliente = cadastroClienteService.salvar(this.cliente);
		limpar();
		
		FacesUtil.addInfoMessage("Cliente salvo com sucesso!");
		
	}
	
	public void adicionarEndereco() {
		cliente.getEnderecos().add(this.endereco);
		endereco.setCliente(cliente);
		FacesUtil.addInfoMessage("Endereço adicionado com sucesso!");
			
		this.endereco = new Endereco();
		
	}
	
	public void removerEndereco() {
		this.cliente.getEnderecos().remove(enderecoSelecionado);
		//enderecos.remover(enderecoSelecionado);
		FacesUtil.addInfoMessage("Endereco excluído com sucesso!");
			
		this.enderecoSelecionado = new Endereco();
		
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
		
		/*if (this.cliente != null){
		this.enderecos = this.usuario.getEndereco();
	}*/
	}

	public Endereco getEndereco() {
		return endereco;
	}
	
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Endereco getEnderecoSelecionado() {
		return enderecoSelecionado;
	}

	public void setEnderecoSelecionado(Endereco enderecoSelecionado) {
		this.enderecoSelecionado = enderecoSelecionado;
	}

	public boolean isEditando() {
		return this.cliente.getId() != null;
	}

}
