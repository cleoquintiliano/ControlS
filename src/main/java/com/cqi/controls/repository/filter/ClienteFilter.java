package com.cqi.controls.repository.filter;

import java.io.Serializable;

//import com.cqi.controls.validation.DocumentoFederal;

/**
 * 
 * @author cqfb
 *
 */
public class ClienteFilter implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String documentoReceitaFederal;
	private String nome;

	//@DocumentoFederal
	public String getDocumentoReceitaFederal() {
		return documentoReceitaFederal;
	}

	/**
	 * Metodo com operador condicional ternário
	 * @param documentoReceitaFederal
	 */
	public void setDocumentoReceitaFederal(String documentoReceitaFederal) {
		this.documentoReceitaFederal = documentoReceitaFederal == null ? null : documentoReceitaFederal.toUpperCase();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
