package br.gagc.automacao.components;

import java.io.File;
import java.io.FileInputStream;

public class Planilha {
	private FileInputStream caminho;
	private boolean selecionou = false;
	public Planilha() {
		selecionou = false;
	}
	
	public void setCaminho(FileInputStream file) {
		this.caminho = file;
	}
	
	public FileInputStream getCaminho() {
		return this.caminho;
	}
	
	public void setSelecionou(boolean status) {
		this.selecionou = status;
	}
	
	public boolean getSelecionou() {
		return this.selecionou;
	}
}
