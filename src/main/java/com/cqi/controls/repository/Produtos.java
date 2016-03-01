package com.cqi.controls.repository;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import com.cqi.controls.model.Produto;

/**
 * @author cqfb
 */

public class Produtos implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;
	
			
	/**
	 * Metodo "merge" do EntityManager insere ou atualiza;
	 * @param produto
	 * @return instancia do produto persistido
	 */
	public Produto guardar(Produto produto) {
		// 
		return manager.merge(produto);

	}
	/** 
	 * Metodo verifica se o SKU já existe
	 * @param sku
	 * @return
	 */
	public Produto porSku(String sku) {
		try {
			return manager.createQuery("from Produto where upper(sku) = :sku", Produto.class)
					.setParameter("sku", sku.toUpperCase()).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

}
