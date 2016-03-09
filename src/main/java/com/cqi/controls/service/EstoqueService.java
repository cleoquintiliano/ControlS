package com.cqi.controls.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.cqi.controls.model.ItemPedido;
import com.cqi.controls.model.Pedido;
import com.cqi.controls.repository.Pedidos;
import com.cqi.controls.util.jpa.Transactional;

/**
 * @author cqfb
 *
 */

public class EstoqueService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Pedidos pedidos;
	
	@Transactional
	public void baixarItensEstoque(Pedido pedido) {
		pedido = this.pedidos.porId(pedido.getId());
		
		for (ItemPedido item : pedido.getItens()) {
			item.getProduto().baixarEstoque(item.getQuantidade());
		}
	}

	public void retornarItensEstoque(Pedido pedido) {
		pedido = this.pedidos.porId(pedido.getId());
		
		for (ItemPedido item : pedido.getItens()) {
			item.getProduto().adicionarEstoque(item.getQuantidade());
		}
	}
	
}
