package com.cqi.controls.util.report;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;

import org.hibernate.jdbc.Work;

/**
 * interface "Map" é um objeto que mapeia valores para chaves, ou seja, 
 * através da chave consegue ser acessado o valor configurado, 
 * sendo que a chave não pode ser repetida ao contrário do valor, 
 * mas se caso tiver uma chave repetida é sobrescrito pela última chamada. 

 * @author cqfb
 *
 */

public class ExecutorRelatorio implements Work {

	private String caminhoRelatorio;
	private HttpServletResponse response;
	private Map<String, Object> parametros;
	private String nomeArquivoSaida;
	
	private boolean relatorioGerado;
	
	public ExecutorRelatorio(String caminhoRelatorio, 
			HttpServletResponse response, Map<String, Object> parametros, 
			String nomeArquivoSaida) {
		this.caminhoRelatorio = caminhoRelatorio;
		this.response = response;
		this.parametros = parametros;
		this.nomeArquivoSaida = nomeArquivoSaida;
		
		this.parametros.put(JRParameter.REPORT_LOCALE, new Locale("pt", "BR"));
	}

	@Override
	public void execute(Connection connection) throws SQLException {
		try {
			//"pega" o fluxo de entrada (bits) do arquivo de relatório
			InputStream relatorioStream = this.getClass().getResourceAsStream(this.caminhoRelatorio);
			//Classe JasperPrint gera os dados do relatório (sem formato)
			JasperPrint print = JasperFillManager.fillReport(relatorioStream, this.parametros, connection);
			//Verifica se relatorio contem dados
			this.relatorioGerado = print.getPages().size() > 0;
			
			if (this.relatorioGerado) {
				//JRExporter exporta para formato da implementação (PDF)
				JRExporter exportador = new JRPdfExporter();
				//"pega" o fluxo de saida e entrega o relatório pro response da requisição HTTP
				exportador.setParameter(JRExporterParameter.OUTPUT_STREAM, response.getOutputStream());
				//informa o objeto (neste caso JasperPrint) que será usado para gerar o arquivo (PDF)
				exportador.setParameter(JRExporterParameter.JASPER_PRINT, print);
				//informa ao response o tipo de arquivo que será enviado
				response.setContentType("application/pdf");
				//para abrir/salvar fora do navegador
				response.setHeader("Content-Disposition", "attachment; filename=\"" 
						+ this.nomeArquivoSaida  + "\"");
				//executa o JRExporter exportando o relatório
				exportador.exportReport();
			}
		} catch (Exception e) {
			throw new SQLException("Erro ao executar relatório " + this.caminhoRelatorio, e);
		}
	}

	public boolean isRelatorioGerado() {
		return relatorioGerado;
	}

}
