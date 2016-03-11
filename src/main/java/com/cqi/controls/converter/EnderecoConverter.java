package com.cqi.controls.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.cqi.controls.model.Endereco;
import com.cqi.controls.repository.Enderecos;
import com.cqi.controls.util.cdi.CDIServiceLocator;

/**
 * Um converter é uma classe que implementa a interface javax.faces.convert.Converter, 
 * implementando os dois métodos desta interface, o getAsObject e o getAsString.
 * @author cqfb
 */

@FacesConverter(forClass = Endereco.class)
public class EnderecoConverter implements Converter {

	//@Inject (não funciona em conversores para essa versão)
	private Enderecos enderecos;
	
	public EnderecoConverter() {
		enderecos = CDIServiceLocator.getBean(Enderecos.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Endereco retorno = null;
		
		if (value != null) {
			Long id = new Long(value);
			retorno = enderecos.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			return ((Endereco) value).getId().toString();
		}
		
		return "";
	}

}
