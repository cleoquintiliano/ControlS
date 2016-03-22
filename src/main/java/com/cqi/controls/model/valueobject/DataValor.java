package com.cqi.controls.model.valueobject;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Classe que representa a list retornada pelo "criteria" do repositorio de pedidos que retorna os dados p/ 
 * popular o gráfico (tal list retornada contem uma lista de vetores, como um dicionario de data e valor).
 * Nomes dos atributos devem ser os mesmos dos alias usados na consulta com criteria.
 * 
 * @author cqfb
 *
 */
public class DataValor implements Serializable {

	private static final long serialVersionUID = 1L;

	private Date data;
	private BigDecimal valor;

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

}
