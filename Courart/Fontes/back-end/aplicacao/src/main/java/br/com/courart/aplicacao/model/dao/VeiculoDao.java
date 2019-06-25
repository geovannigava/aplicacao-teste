package br.com.courart.aplicacao.model.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.courart.aplicacao.model.Veiculo;


@Component
public class VeiculoDao implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private IVeiculoDao iVeiculoDao;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	/**
	 * Buscar Veículo por placa.
	 * 
	 * @param placa
	 * @return List<Veiculo>
	 */
	public List<Veiculo> buscarPorPlaca(String placa){
		return iVeiculoDao.buscarPorPlaca(placa);
	}
	
	/**
	 * Buscar Veículo por modelo.
	 * 
	 * @param modelo
	 * @return List<Veiculo>
	 */
	public List<Veiculo> buscarPorModelo(String modelo){
		return iVeiculoDao.buscarPorModelo(modelo);
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
	public List<Veiculo> listarTodos() {
		return (List<Veiculo>) iVeiculoDao.findAllByOrderByIdVeiculoAsc();
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

}
