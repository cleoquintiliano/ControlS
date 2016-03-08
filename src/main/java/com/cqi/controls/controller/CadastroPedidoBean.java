package com.cqi.controls.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.cqi.controls.model.Cliente;
import com.cqi.controls.model.EnderecoEntrega;
import com.cqi.controls.model.FormaPagamento;
import com.cqi.controls.model.Pedido;
import com.cqi.controls.model.Usuario;
import com.cqi.controls.repository.Clientes;
import com.cqi.controls.repository.Usuarios;
import com.cqi.controls.service.CadastroPedidoService;
import com.cqi.controls.util.jsf.FacesUtil;

/**
 * @author cqfb
 * 
 */

@Named
@ViewScoped
public class CadastroPedidoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Usuarios usuarios;
	
	@Inject
	private Clientes clientes;
	
	@Inject
	private CadastroPedidoService cadastroPedidoService;

	private Pedido pedido;
	private List<Usuario> vendedores;

	public CadastroPedidoBean() {
		limpar();
	}
	
	public void inicializar() {
		if (FacesUtil.isNotPostback()) {
			this.vendedores = this.usuarios.vendedores();
		}
	}

	private void limpar() {
		pedido = new Pedido();
		pedido.setEnderecoEntrega(new EnderecoEntrega());
	}

	public void salvar() {
		this.pedido = this.cadastroPedidoService.salvar(this.pedido);
		
		FacesUtil.addInfoMessage("Pedido salvo com sucesso!");
	}
	
	public FormaPagamento[] getFormasPagamento() {
		return FormaPagamento.values();
	}
	
	public List<Cliente> completarCliente(String nome) {
		return this.clientes.porNome(nome);
	}

	public Pedido getPedido() {
		return pedido;
	}
	
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public List<Usuario> getVendedores() {
		return vendedores;
	}
	
	public boolean isEditando() {
		return this.pedido.getId() != null;
	}

}
