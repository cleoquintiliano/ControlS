package com.cqi.controls.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.cqi.controls.model.Pedido;
import com.cqi.controls.repository.Pedidos;
import com.cqi.controls.util.cdi.CDIServiceLocator;

/**
 * @author cqfb
 * Um converter é uma classe que implementa a interface javax.faces.convert.Converter, 
 * implementando os dois métodos desta interface, o getAsObject e o getAsString.
 */

@FacesConverter(forClass = Pedido.class)
public class PedidoConverter implements Converter {

	//@Inject (não funciona em conversores para essa versão)
	private Pedidos pedidos;
	
	public PedidoConverter() {
		pedidos = CDIServiceLocator.getBean(Pedidos.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Pedido retorno = null;
		
		if (value != null) {
			Long id = new Long(value);
			retorno = pedidos.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Pedido pedido = (Pedido) value;
			return pedido.getId() == null ? null : pedido.getId().toString();
		}
		
		return "";
	}

}
