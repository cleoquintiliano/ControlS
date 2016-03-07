package com.cqi.controls.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.cqi.controls.model.Pedido;
import com.cqi.controls.model.StatusPedido;
import com.cqi.controls.repository.Pedidos;
import com.cqi.controls.repository.filter.PedidoFilter;

/**
 * @author cqfb
 *
 */

@Named
@ViewScoped
public class ConsultaPedidosBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Pedidos pedidos;

	private PedidoFilter filtro;
	private List<Pedido> pedidosFiltrados;

	public ConsultaPedidosBean() {
		filtro = new PedidoFilter();
		pedidosFiltrados = new ArrayList<>();
	}

	public void pesquisar() {
		pedidosFiltrados = pedidos.filtrados(filtro);
	}

	/**
	 * "values" retorna um array contendo todos os valores da enumeração na
	 * ordem em que são declaradas
	 * 
	 * @return descrições do Enum StatusPedido
	 */
	public StatusPedido[] getStatuses() {
		return StatusPedido.values();
	}

	public List<Pedido> getPedidosFiltrados() {
		return pedidosFiltrados;
	}

	public PedidoFilter getFiltro() {
		return filtro;
	}

}
