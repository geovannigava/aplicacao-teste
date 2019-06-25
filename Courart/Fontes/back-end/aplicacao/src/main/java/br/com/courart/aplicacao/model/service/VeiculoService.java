package br.com.courart.aplicacao.model.service;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.courart.aplicacao.model.Veiculo;
import br.com.courart.aplicacao.model.dao.VeiculoDao;
import br.com.courart.aplicacao.model.enums.AtivoEnum;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class VeiculoService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private VeiculoDao veiculoDao;
	
	/**
	 * Buscar Veículo por placa.
	 * 
	 * @param placa
	 * @return List<Veiculo>
	 */
	public List<Veiculo> buscarPorPlaca(String placa){
		return veiculoDao.buscarPorPlaca(placa);
	}
	
	/**
	 * Buscar Veículo por modelo.
	 * 
	 * @param modelo
	 * @return List<Veiculo>
	 */
	public List<Veiculo> buscarPorModelo(String modelo){
		return veiculoDao.buscarPorModelo(modelo);
	}

	/**
	 * Salva Veículo.
	 * 
	 * @param veiculo
	 * @return Veiculo
	 */
	public Veiculo salvar(Veiculo veiculo) {
		veiculo.setDataCadastro(LocalDate.now());
		//Verifica se é uma desativação, caso seja atualiza o campo Data da Desativação
		if(veiculo.getAtivoEnum() == AtivoEnum.NAO) {
			veiculo.setDataDesativacao(LocalDate.now());
		}
		veiculo.setPlaca(veiculo.getPlaca().toUpperCase());
		veiculo.setCor(veiculo.getCor().toUpperCase());
		veiculo.setModelo(veiculo.getModelo().toUpperCase());
		return veiculoDao.salvar(veiculo);
	}

	/**
	 * Listar Todos os Veículo.
	 * 
	 * @return List<Veiculo>
	 */
	public List<Veiculo> listarTodos() {
		return veiculoDao.listarTodos();
	}

	/**
	 * Buscar Veículo por Id.
	 * 
	 * @param id
	 * @return Veiculo
	 */
	public Veiculo buscarPorId(Long id) {
		return veiculoDao.buscarPorId(id);
	}

	/**
	 * Atualizar dados do Veículo por Id.
	 * 
	 * @param id
	 * @param veiculo
	 * @return Veiculo
	 */
	public Veiculo atualizar(Veiculo veiculo, Long id) {
		Veiculo veiculoDb = this.buscarPorId(id);
		if(veiculoDb != null) {
			BeanUtils.copyProperties(veiculo, veiculoDb, "idVeiculo");
			
			//Verifica se é uma desativação, caso seja atualiza o campo Data da Desativação
			if(veiculoDb.getAtivoEnum() == AtivoEnum.NAO) {
				veiculoDb.setDataDesativacao(LocalDate.now());
			} else {
				veiculoDb.setDataDesativacao(null);
			}
			
			return veiculoDao.atualizar(veiculoDb);
		} else {
			throw new EmptyResultDataAccessException(1);
		}
	}

	/**
	 * Remover Veiculo.
	 * 
	 * @param id
	 * @return Boolean
	 */
	public Boolean remover(Long id) {
		try {
			veiculoDao.remover(id);
			return true;
		} catch (Exception e) {
			throw new EmptyResultDataAccessException(1);
		}
	}

}
