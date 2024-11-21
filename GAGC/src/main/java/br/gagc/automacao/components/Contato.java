package br.gagc.automacao.components;

public class Contato {
	private String nome;
	private String telefone;
	
	public Contato(String nome, String numero) {
		this.nome = nome;
		this.telefone = numero;
	}
	
	public void setNome(String nome){
		this.nome = nome;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public void setNumero(String numero) {
		this.telefone = numero;
	}
	
	public String getNumero() {
		return this.telefone;
	}
}
