package br.com.courart.aplicacao.model.dao;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import br.com.courart.aplicacao.model.Veiculo;
import br.com.courart.aplicacao.model.dao.page.PaginacaoDao;


@Component
public class VeiculoDao extends PaginacaoDao {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private IVeiculoDao iVeiculoDao;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	/**
	 * Buscar Veículo por placa.
	 * 
	 * @param placa
	 * @param pageable 
	 * @return List<Veiculo>
	 */
	public Page<Veiculo> buscarPorPlaca(String placa, Pageable pageable){		
		String jpql = new String("SELECT veiculo FROM Veiculo veiculo WHERE veiculo.placa = '"+placa+"'");
		System.out.println(jpql);
		return paginar(jpql, Veiculo.class, pageable);
	}
	
	/**
	 * Buscar Veículo por modelo.
	 * 
	 * @param modelo
	 * @return List<Veiculo>
	 */
	public Page<Veiculo> buscarPorModelo(String modelo, Pageable pageable){
		String jpql = new String("SELECT veiculo FROM Veiculo veiculo WHERE veiculo.modelo LIKE '%"+modelo+"%'");
		return paginar(jpql, Veiculo.class, pageable);
	}

	/**
	 * Salva Veículo.
	 * 
	 * @param veiculo
	 * @return Veiculo
	 */
	public Veiculo salvar(Veiculo veiculo) {
		return iVeiculoDao.save(veiculo);
	}
	
	/**
	 * Listar Todos os Veículo ordenados por ID.
	 * 
	 * @return List<Veiculo>
	 */
	public Page<Veiculo> listarTodos(Pageable pageable) {
		String jpql = new String("SELECT veiculo FROM Veiculo veiculo");
		return paginar(jpql, Veiculo.class, pageable);
	}
	
	/**
	 * Buscar Veículo por Id.
	 * 
	 * @param id
	 * @return Veiculo
	 */
	public Veiculo buscarPorId(Long id) {
		return iVeiculoDao.findOne(id);
	}

	/**
	 * Atualizar dados do Veículo por Id.
	 * 
	 * @param veiculo
	 * @return Veiculo
	 */
	public Veiculo atualizar(Veiculo veiculo) {
		return entityManager.merge(veiculo);
	}
	
	/**
	 * Remover Veiculo.
	 * 
	 * @param id
	 * @return void
	 */
	public void remover(Long id) {
		 iVeiculoDao.delete(id);
	}
	
	/**
	 * Buscar Veículos ativos por período
	 * 
	 * @param inicio
	 * @param fim
	 * @return List<Veiculo>
	 */
	public List<Veiculo> buscarVeiculosAtivos(LocalDate inicio, LocalDate fim) {
		return iVeiculoDao.buscarVeiculosAtivos(inicio, fim);
	}

}
