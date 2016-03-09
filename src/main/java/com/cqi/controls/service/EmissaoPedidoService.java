package com.cqi.controls.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.cqi.controls.model.Pedido;
import com.cqi.controls.model.StatusPedido;
import com.cqi.controls.repository.Pedidos;

/**
 * @author cqfb
 *
 */
public class EmissaoPedidoService implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Inject
	private CadastroPedidoService cadastroPedidoService;
	
	@Inject
	private EstoqueService estoqueService;
	
	@Inject
	private Pedidos pedidos;
	
	public Pedido emitir(Pedido pedido) {
		pedido = this.cadastroPedidoService.salvar(pedido);
		
		if (pedido.isNaoEmissivel()) {
			throw new NegocioException("Pedido não pode ser emitido com status "
					+ pedido.getStatus().getDescricao() + "!");
		}
		
		this.estoqueService.baixarItensEstoque(pedido);
		
		pedido.setStatus(StatusPedido.EMITIDO);
		
		pedido = this.pedidos.guardar(pedido);
		
		return pedido;
	}
}
