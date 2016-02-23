import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.cqi.controls.model.Categoria;
import com.cqi.controls.model.Produto;


public class TesteProduto {

	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("ControlsPU");
		EntityManager manager = factory.createEntityManager();
		
		EntityTransaction trx = manager.getTransaction();
		trx.begin();
		
		// instancia a categoria pai (Computadores)
		Categoria categoriaPai = new Categoria();
		categoriaPai.setDescricao("Computadores");
		
		// instancia a categoria filha (Notebooks)
		Categoria categoriaFilha = new Categoria();
		categoriaFilha.setDescricao("Notebooks");
		categoriaFilha.setCategoriaPai(categoriaPai);
		
		// adiciona a categoria Notebooks como filha de Computadores
		categoriaPai.getSubcategorias().add(categoriaFilha);
		
		// ao persistir a categoria pai (Computadores), a filha (Notebooks) 
		// deve ser persistida também
		manager.persist(categoriaPai);
		
		// instanciamos e persistimos um produto
		Produto produto = new Produto();
		produto.setCategoria(categoriaFilha);
		produto.setNome("Notebook Sony Vaio 2345");
		produto.setQuantidadeEstoque(10);
		produto.setSku("NTSONY2345");
		produto.setValorUnitario(new BigDecimal(2.21));
		
		manager.persist(produto);
		
		trx.commit();
	}
	
}
