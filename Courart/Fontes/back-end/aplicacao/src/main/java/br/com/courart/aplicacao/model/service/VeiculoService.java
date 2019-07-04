package br.com.courart.aplicacao.model.service;

import java.io.InputStream;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.courart.aplicacao.model.Veiculo;
import br.com.courart.aplicacao.model.dao.VeiculoDao;
import br.com.courart.aplicacao.model.enums.AtivoEnum;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

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
	public Page<Veiculo> buscarPorPlaca(String placa, Pageable pageable){
		return veiculoDao.buscarPorPlaca(placa.toUpperCase(), pageable);
	}
	
	/**
	 * Buscar Veículo por modelo.
	 * 
	 * @param modelo
	 * @return List<Veiculo>
	 */
	public Page<Veiculo> buscarPorModelo(String modelo, Pageable pageable){
		return veiculoDao.buscarPorModelo(modelo.toUpperCase(), pageable);
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
	 * @param pageable 
	 * 
	 * @return List<Veiculo>
	 */
	public Page<Veiculo> listarTodos(Pageable pageable) {
		return veiculoDao.listarTodos(pageable);
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
	
	/**
	 * Gerar relatório de todos os Veiculos ativos por período
	 * 
	 * @param inicio
	 * @param fim
	 * @return List<Funcionario>
	 */
	@Transactional(readOnly=true)
	public byte[] buscarVeiculosAtivos(LocalDate inicio, LocalDate fim) throws JRException {
		List<Veiculo> dados = veiculoDao.buscarVeiculosAtivos(inicio, fim);
		
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("DT_INICIO", Date.valueOf(inicio));
		parametros.put("DT_FIM", Date.valueOf(fim));
		parametros.put("REPORT_LOCALE", new Locale("pt", "BR"));
		//Colocando traço na placa de acordo com o formato AAA-9999
		for (Veiculo veiculo : dados) {
			veiculo.setPlaca(veiculo.getPlaca().substring(0, 3) + "-" + veiculo.getPlaca().substring(3, 7));
		}
		InputStream inputStream = this.getClass().getResourceAsStream(
				"/relatorios/veiculos-ativados.jasper");
		
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parametros,
				new JRBeanCollectionDataSource(dados));
		
		return JasperExportManager.exportReportToPdf(jasperPrint);

	}

}
