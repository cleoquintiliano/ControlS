package com.cqi.controls.service;

import java.io.Serializable;
import java.util.Date;

import javax.inject.Inject;

import com.cqi.controls.model.Pedido;
import com.cqi.controls.model.StatusPedido;
import com.cqi.controls.repository.Pedidos;
import com.cqi.controls.util.jpa.Transactional;

/**
 * @author cqfb
 *
 */
public class CadastroPedidoService implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Inject
	private Pedidos pedidos;
	
	@Transactional
	public Pedido salvar(Pedido pedido) {
		if (pedido.isNovo()) {
			pedido.setDataCriacao(new Date());
			pedido.setStatus(StatusPedido.ORCAMENTO);
		}
		
		pedido = this.pedidos.guardar(pedido);
		return pedido;
	}

}
