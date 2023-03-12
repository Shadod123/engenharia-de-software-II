package com.jcg.hibernate.crud.operations.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CONTATOS_110068")
public class Contato implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="codigo")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="nome")
	private String Nome;

	@Column(name="endereco")
	private String Endereco;

	@Column(name="telefone")
	private String Telefone;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return Nome;
	}

	public void setNome(String nome) {
		this.Nome = nome;
	}

	public String getEndereco() {
		return Endereco;
	}

	public void setEndereco(String endereco) {
		this.Endereco = endereco;
	}

	public String getTelefone() {
		return Telefone;
	}

	public void setTelefone(String telefone) {
		this.Telefone = telefone;
	}

	@Override
	public String toString() {
		return "Contato Details?= Id: " + this.id + ", Nome: " + this.Nome + ", REndereco: " + this.Endereco + ", Telefone: " + this.Telefone;
	}
}