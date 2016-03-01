package com.cqi.controls.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.cqi.controls.model.Produto;
import com.cqi.controls.repository.Produtos;
import com.cqi.controls.util.jpa.Transactional;

/**
 * @author cqfb
 */

public class CadastroProdutoService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Produtos produtos;
	
	/**	
	 * Metodo que salva apenas se SKU ainda não existe (SKU deve ser único)
	 * @param produto
	 * @return 
	 */
	@Transactional
	public Produto salvar(Produto produto) {
		Produto produtoExistente = produtos.porSku(produto.getSku());

		if (produtoExistente != null) {
			throw new NegocioException("Já existe um produto com o SKU informado.");
		}

		return produtos.guardar(produto);

	}

}
