package com.cqi.controls.controller;

import com.cqi.controls.model.Pedido;

/**
 * Classe que representa o evento de alteração de um pedido
 * @author cqfb
 *
 */

public class PedidoAlteradoEvent {
	
private Pedido pedido;
	
	public PedidoAlteradoEvent(Pedido pedido) {
		this.pedido = pedido;
	}

	public Pedido getPedido() {
		return pedido;
	}

}
