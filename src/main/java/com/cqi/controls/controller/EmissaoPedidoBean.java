package com.cqi.controls.controller;

import java.io.Serializable;

import javax.enterprise.event.Event;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.cqi.controls.model.Pedido;
import com.cqi.controls.service.EmissaoPedidoService;
import com.cqi.controls.util.jsf.FacesUtil;

/**
 * @author cqfb
 *
 */

@Named
@ViewScoped
public class EmissaoPedidoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EmissaoPedidoService emissaoPedidoService;

	@Inject
	@PedidoEdicao
	private Pedido pedido;

	/**
	 * Classe Event que é parametrizada com o tipo do evento que estamos lidando,
	 * neste caso "PedidoAlteradoEvent". Esse objeto tem uma utilidade 
	 * disparar o evento no momento certo e 
	 * anunciar o objeto do evento para quem estiver escutando (pedidoAlteradoEvent.fire)
	 * existente no metodo emitirPedido()
	 */
	@Inject
	private Event<PedidoAlteradoEvent> pedidoAlteradoEvent;

	public void emitirPedido() {
		this.pedido.removerItemVazio();

		try {
			this.pedido = this.emissaoPedidoService.emitir(this.pedido);
			this.pedidoAlteradoEvent.fire(new PedidoAlteradoEvent(this.pedido));

			FacesUtil.addInfoMessage("Pedido emitido com sucesso!");
		} finally {
			this.pedido.adicionarItemVazio();
		}
	}

}
