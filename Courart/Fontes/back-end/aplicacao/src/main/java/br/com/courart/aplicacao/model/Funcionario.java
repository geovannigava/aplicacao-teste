package br.com.courart.aplicacao.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
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

@Entity
@Table(name = "tb_funcionarios", schema = "courart")
public class Funcionario implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_FUNCIONARIO", updatable=false)
	private Long idFuncionario;
    
	@Column(name = "CPF")
	@NotNull(message = "O campo Cpf é Obrigatorio.")
	@Size(max = 14, min = 0, message = "Tamanho máximo do campo Cpf é 14")
	private String cpf;
	
	@Column(name = "NOME")
	@NotNull(message = "O campo Nome é Obrigatorio.")
	@Size(max = 40, min = 0, message = "Tamanho máximo do campo Nome é 40")
	private String nome;
	
	@Column(name = "DATA_NASCIMENTO")
	@Convert(converter = LocalDateConverter.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataNascimento;
	
	@Column(name = "LOGIN")
	@NotNull(message = "O campo Login é Obrigatorio.")
	@Size(max = 12, min = 0, message = "Tamanho máximo do campo Login é 12")
	private String login;
	
	@Column(name = "SENHA")
	@NotNull(message = "O campo Senha é Obrigatorio.")
	@Size(max = 12, min = 0, message = "Tamanho máximo do campo Senha é 12")
	private String senha;

	public Funcionario() {

	}

	public Long getIdFuncionario() {
		return idFuncionario;
	}

	public void setIdFuncionario(Long idFuncionario) {
		this.idFuncionario = idFuncionario;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idFuncionario == null) ? 0 : idFuncionario.hashCode());
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
		Funcionario other = (Funcionario) obj;
		if (idFuncionario == null) {
			if (other.idFuncionario != null)
				return false;
		} else if (!idFuncionario.equals(other.idFuncionario))
			return false;
		return true;
	}
	
	

}
