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


import br.com.courart.aplicacao.model.Funcionario;
import br.com.courart.aplicacao.model.dao.FuncionarioDao;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;


@Service
@Transactional(propagation = Propagation.REQUIRED)
public class FuncionarioService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private FuncionarioDao funcionarioDao;
	
	/**
	 * Buscar Funcionarios por nome.
	 * 
	 * @param nome
	 * @return List<Funcionario>
	 */
	public Page<Funcionario> buscarPorNome(String nome, Pageable pageable) {
		return funcionarioDao.buscarPorNome(nome.toUpperCase(), pageable);
	}
	
	/**
	 * Buscar Funcionarios por cpf.
	 * 
	 * @param cpf
	 * @return List<Funcionario>
	 */
	public Page<Funcionario> buscarPorCpf(String cpf, Pageable pageable) {
		return funcionarioDao.buscarPorCpf(cpf, pageable);
	}

	/**
	 * Salva Funcionario.
	 * 
	 * @param funcionario
	 * @return Funcionario
	 */
	public Funcionario salvar(Funcionario funcionario) {
		funcionario.setNome(funcionario.getNome().toUpperCase());
		return funcionarioDao.salvar(funcionario);
	}

	/**
	 * Listar Todos os Funcionarios.
	 * 
	 * @return List<Funcionario>
	 */
	public Page<Funcionario> listarTodos(Pageable pageable) {
		return funcionarioDao.listarTodos(pageable);
	}

	/**
	 * Buscar Funcionario por Id.
	 * 
	 * @param id
	 * @return Funcionario
	 */
	public Funcionario buscarPorId(Long id) {
		return funcionarioDao.buscarPorId(id);
	}

	/**
	 * Atualizar dados do Funcionario por Id.
	 * 
	 * @param funcionario
	 * @return Funcionario
	 */
	public Funcionario atualizar(Funcionario funcionario, Long id) {
		Funcionario funcionarioDb = this.buscarPorId(id);
		if(funcionarioDb != null) {
			BeanUtils.copyProperties(funcionario, funcionarioDb, "idFuncionario");
			funcionarioDb.setNome(funcionarioDb.getNome().toUpperCase());
			return funcionarioDao.atualizar(funcionarioDb);
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
			funcionarioDao.remover(id);
			return true;
		} catch (Exception e) {
			throw new EmptyResultDataAccessException(1);
		}
	}

	/**
	 * Gerar relatório de todos os Funcionarios aniversariantes por período
	 * 
	 * @param inicio
	 * @param fim
	 * @return List<Funcionario>
	 */
	public byte[] relatorioFuncionariosAniversariantes(LocalDate inicio, LocalDate fim) throws JRException {
		List<Funcionario> dados = funcionarioDao.buscarFuncionariosAniversariantes(inicio, fim);
		
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("DT_INICIO", Date.valueOf(inicio));
		parametros.put("DT_FIM", Date.valueOf(fim));
		parametros.put("REPORT_LOCALE", new Locale("pt", "BR"));
		
		InputStream inputStream = this.getClass().getResourceAsStream(
				"/relatorios/funcionarios-aniversariantes.jasper");
		
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parametros,
				new JRBeanCollectionDataSource(dados));
		
		return JasperExportManager.exportReportToPdf(jasperPrint);

	}

}
