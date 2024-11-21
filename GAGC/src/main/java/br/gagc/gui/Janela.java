package br.gagc.gui;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Janela extends JFrame {
	public Janela(int altura, int largura) {
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		this.setBounds(0,0,altura,largura);
		this.setResizable(false);
		this.setTitle("Gerenciador Automático de Grupo de Calouros");
		
		//Criando um painel
		TelaPanel telaPanel = new TelaPanel();
		this.add(telaPanel);
		
		ImageIcon imageIcon = new ImageIcon(Janela.class.getResource("/imagens/icon_estacio.png"));
		Image image = imageIcon.getImage();
		this.setIconImage(image);
		
		this.setVisible(true);
	}
}
