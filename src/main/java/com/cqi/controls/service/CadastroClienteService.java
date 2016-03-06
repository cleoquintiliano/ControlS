package com.cqi.controls.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.cqi.controls.model.Cliente;
import com.cqi.controls.repository.Clientes;
import com.cqi.controls.util.jpa.Transactional;

/**
 * @author cqfb
 */

public class CadastroClienteService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Clientes clientes;
	
	/**	
	 * Metodo que salva apenas se SKU ainda não existe (SKU deve ser único) ou se for uma edição
	 * @param produto
	 * @return 
	 */
	@Transactional
	public Cliente salvar(Cliente cliente) {
		Cliente clienteExistente = clientes.porDocumentoReceitaFederal(cliente.getDocumentoReceitaFederal());

		if (clienteExistente != null && !clienteExistente.equals(cliente))  {
			throw new NegocioException("Já existe um cliente com o CPF/CNPJ informado.");
		}

		return clientes.guardar(cliente);

	}

}
