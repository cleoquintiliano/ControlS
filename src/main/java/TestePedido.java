import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.cqi.controls.model.Cliente;
import com.cqi.controls.model.EnderecoEntrega;
import com.cqi.controls.model.FormaPagamento;
import com.cqi.controls.model.ItemPedido;
import com.cqi.controls.model.Pedido;
import com.cqi.controls.model.Produto;
import com.cqi.controls.model.StatusPedido;
import com.cqi.controls.model.Usuario;

public class TestePedido {

	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("ControlsPU");
		EntityManager manager = factory.createEntityManager();
		
		EntityTransaction trx = manager.getTransaction();
		trx.begin();
		
		Cliente cliente = manager.find(Cliente.class, 1L);
		Produto produto = manager.find(Produto.class, 1L);
		Usuario vendedor = manager.find(Usuario.class, 1L);
		
		EnderecoEntrega enderecoEntrega = new EnderecoEntrega();
		enderecoEntrega.setLogradouro("Rua Florida");
		enderecoEntrega.setNumero("240");
		enderecoEntrega.setCidade("Londrina");
		enderecoEntrega.setUf("PR");
		enderecoEntrega.setCep("86040-123");
		
		Pedido pedido = new Pedido();
		pedido.setCliente(cliente);
		pedido.setDataCriacao(new Date());
		pedido.setDataEntrega(new Date());
		pedido.setFormaPagamento(FormaPagamento.CARTAO_CREDITO);
		pedido.setObservacao("Aberto das 08 às 18h");
		pedido.setStatus(StatusPedido.ORCAMENTO);
		pedido.setValorDesconto(BigDecimal.ZERO);
		pedido.setValorFrete(BigDecimal.ZERO);
		pedido.setValorTotal(new BigDecimal(10000.10));
		pedido.setVendedor(vendedor);
		pedido.setEnderecoEntrega(enderecoEntrega);
		
		ItemPedido item = new ItemPedido();
		item.setProduto(produto);
		item.setQuantidade(10);
		item.setValorUnitario(new BigDecimal(1000.1));
		item.setPedido(pedido);
		
		pedido.getItens().add(item);
		
		manager.persist(pedido);
		
		trx.commit();
	}
	
}
