package com.cqi.controls.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.cqi.controls.model.Cliente;
import com.cqi.controls.repository.Clientes;
import com.cqi.controls.repository.filter.ClienteFilter;
import com.cqi.controls.util.jsf.FacesUtil;

/**
 * @author cqfb
 */

@Named
@ViewScoped
public class ConsultaClientesBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Clientes clientes;

	private ClienteFilter filtro;

	private List<Cliente> clientesFiltrados;

	private Cliente clienteSelecionado;

	public ConsultaClientesBean() {
		filtro = new ClienteFilter();
	}

	public void excluir() {
		clientes.remover(clienteSelecionado);
		clientesFiltrados.remove(clienteSelecionado);

		FacesUtil.addInfoMessage("Cliente " + clienteSelecionado.getNome() + " excluído com sucesso.");
	}

	public void pesquisar() {
		clientesFiltrados = clientes.filtrados(filtro);
	}

	public List<Cliente> getClientesFiltrados() {
		return clientesFiltrados;
	}

	public ClienteFilter getFiltro() {
		return filtro;
	}

	public Cliente getClienteSelecionado() {
		return clienteSelecionado;
	}

	public void setClienteSelecionado(Cliente clienteSelecionado) {
		this.clienteSelecionado = clienteSelecionado;
	}

}
