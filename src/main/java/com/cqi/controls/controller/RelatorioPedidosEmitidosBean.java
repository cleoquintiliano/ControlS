package com.cqi.controls.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;

import org.hibernate.Session;

import com.cqi.controls.util.jsf.FacesUtil;
import com.cqi.controls.util.report.ExecutorRelatorio;

/**
 * @author cqfb
 *
 */

@Named
@RequestScoped
public class RelatorioPedidosEmitidosBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Date dataInicio;
	private Date dataFim;

	@Inject
	private FacesContext facesContext;

	@Inject
	private HttpServletResponse response;

	@Inject
	private EntityManager manager;

	/**
	 *  Para usar JDBC Connection, a solução é converter Session do Hibernate para JDBC Connection.
	 *  DoWork() é utilizado para operações CRUD
	 */
	public void emitir() {
		Map<String, Object> parametros = new HashMap<>();
		//String do "Map" devem estar com nome de acordo com o relatorio
		parametros.put("data_inicio", this.dataInicio);
		parametros.put("data_fim", this.dataFim);
		
		ExecutorRelatorio executor = new ExecutorRelatorio("/relatorios/relatorio_pedidos_emitidos.jasper",
				this.response, parametros, "Relatorio_pedidos_emitidos.pdf");
		
		Session session = manager.unwrap(Session.class);
		session.doWork(executor);
		
		if (executor.isRelatorioGerado()) {
			//finaliza ciclo de vida do JSF (evitando erros)
			facesContext.responseComplete();
		} else {
			FacesUtil.addErrorMessage("O relatório não retornou dados.");
		}
	}

	@NotNull
	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	@NotNull
	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

}
