package br.com.courart.aplicacao.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.courart.aplicacao.model.converters.LocalDateConverter;
import br.com.courart.aplicacao.model.converters.LocalDateDeserializer;
import br.com.courart.aplicacao.model.converters.LocalDateSerializer;
import br.com.courart.aplicacao.model.enums.AtivoEnum;

@Entity
@Table(name = "tb_veiculos", schema = "courart")
public class Veiculo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_VEICULO", updatable=false)
	private Long idVeiculo;
    
	@Column(name = "PLACA")
	@NotNull(message = "O campo Placa é Obrigatorio.")
	@Size(max = 10, min = 0, message = "Tamanho máximo do campo Placa é 10")
	private String placa;
	
	@Column(name = "ATIVO")
	@Enumerated(EnumType.ORDINAL)
	@NotNull(message = "O campo AtivoEnum é Obrigatorio.")
	private AtivoEnum ativoEnum;

	@Column(name = "ANO_FABRICACAO")
	@NotNull(message = "O campo Ano de Fabricação é Obrigatorio.")
	@Size(max = 4, min = 0, message = "Tamanho máximo do campo Ano de Fabricação é 4")
	private String anoFabricacao;
	
	@Column(name = "ANO_MODELO")
	@NotNull(message = "O campo Ano do Modelo é Obrigatorio.")
	@Size(max = 4, min = 0, message = "Tamanho máximo do campo Ano do Modelo é 4")
	private String anoModelo;
	
	@Column(name = "CHASSI")
	@NotNull(message = "O campo Chassi é Obrigatorio.")
	@Size(max = 40, min = 0, message = "Tamanho máximo do campo Chassi é 40")
	private String chassi;
	
	@Column(name = "DATA_CADASTRO")
	@Convert(converter = LocalDateConverter.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataCadastro;
	
	@Column(name = "DATA_DESATIVACAO")
	@Convert(converter = LocalDateConverter.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataDesativacao;
	
	@Column(name = "MODELO")
	@NotNull(message = "O campo Modelo é Obrigatorio.")
	@Size(max = 30, min = 0, message = "Tamanho máximo do campo Modelo é 30")
	private String modelo;
	
	@Column(name = "COR")
	@Size(max = 20, min = 0, message = "Tamanho máximo do campo Cor é 20")
	private String cor;
	
	@Column(name = "CONSUMO_MEDIO")
	@NotNull(message = "O campo Consumo Médio é Obrigatorio.")
	private BigDecimal consumoMedio;
	
	@Column(name = "QUANTIDADE_PASSAGEIROS")
	@NotNull(message = "O campo Quantidade de Passageiros é Obrigatorio.")
	private Integer quantidadePassageiros;

	public Veiculo() {
		
	}

	public Long getIdVeiculo() {
		return idVeiculo;
	}

	public void setIdVeiculo(Long idVeiculo) {
		this.idVeiculo = idVeiculo;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public AtivoEnum getAtivoEnum() {
		return ativoEnum;
	}

	public void setAtivoEnum(AtivoEnum ativoEnum) {
		this.ativoEnum = ativoEnum;
	}

	public String getAnoFabricacao() {
		return anoFabricacao;
	}

	public void setAnoFabricacao(String anoFabricacao) {
		this.anoFabricacao = anoFabricacao;
	}

	public String getAnoModelo() {
		return anoModelo;
	}

	public void setAnoModelo(String anoModelo) {
		this.anoModelo = anoModelo;
	}

	public String getChassi() {
		return chassi;
	}

	public void setChassi(String chassi) {
		this.chassi = chassi;
	}

	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public LocalDate getDataDesativacao() {
		return dataDesativacao;
	}

	public void setDataDesativacao(LocalDate dataDesativacao) {
		this.dataDesativacao = dataDesativacao;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public BigDecimal getConsumoMedio() {
		return consumoMedio;
	}

	public void setConsumoMedio(BigDecimal consumoMedio) {
		this.consumoMedio = consumoMedio;
	}

	public Integer getQuantidadePassageiros() {
		return quantidadePassageiros;
	}

	public void setQuantidadePassageiros(Integer quantidadePassageiros) {
		this.quantidadePassageiros = quantidadePassageiros;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idVeiculo == null) ? 0 : idVeiculo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Veiculo other = (Veiculo) obj;
		if (idVeiculo == null) {
			if (other.idVeiculo != null)
				return false;
		} else if (!idVeiculo.equals(other.idVeiculo))
			return false;
		return true;
	}

}
