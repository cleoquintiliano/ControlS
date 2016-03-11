package com.cqi.controls.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import com.cqi.controls.model.Endereco;
import com.cqi.controls.service.NegocioException;
import com.cqi.controls.util.jpa.Transactional;

/**
 * @author cqfb
 */
public class Enderecos implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;
	
	/**
	 * "porId" conecta para persistencia / "remove" apenas seleciona para
	 * remoção /"flush" executa pendencias de execução (remove) / em caso de
	 * erro na persistencia (flush) como FK por exemplo lança NegocioException
	 * 
	 * @param usuario
	 */
	@Transactional
	public void remover(Endereco endereco) {
		try {
			endereco = porId(endereco.getId());
			manager.remove(endereco);
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Endereco não pode ser excluído.");
		}
	}

	public Endereco porId(Long id) {
		return manager.find(Endereco.class, id);
	}

	//  
	/**
	 * Metodo com Query JPQL (Java Persistence Query Language)
	 * @return lista de Endereco (não retorna colunas mas sim atributos de classe)
	 */
	public List<Endereco> listarEnderecos() {
		return manager.createQuery("from Endereco", Endereco.class).getResultList();
	}

}
