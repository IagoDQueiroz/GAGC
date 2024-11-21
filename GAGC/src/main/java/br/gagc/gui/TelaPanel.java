package br.gagc.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import br.gagc.automacao.Automacao;
import br.gagc.automacao.components.Planilha;

public class TelaPanel extends JPanel {
	
	String filePath;
	public TelaPanel() {	
		//Automatizador 
		Automacao automacao = new Automacao();
		
		//Obj da planilha selecionada
		Planilha planilhaSelecionada = new Planilha();
		
		//Criando Layouts, que são usados para organizar os componentes no TelaPanel
		BoxLayout layoutBox = new BoxLayout(this, BoxLayout.Y_AXIS); //Definindo que a caixa é no eixo Y, de cima pra baixo
		FlowLayout layoutFlow = new FlowLayout();

		//Definindo o Layout Padrão e a cor de fundo
		this.setLayout(layoutBox);
		this.setBackground(Color.decode("#202028"));

		//Criando primeiro texto que chamei de Titulo "GAG"
		JLabel tituloGAGC = new JLabel("GAGC");
		tituloGAGC.setAlignmentX(Component.CENTER_ALIGNMENT);	// Definindo o alinhamento
		tituloGAGC.setForeground(Color.WHITE); 					// Definindo a cor do texto
		tituloGAGC.setFont(new Font("Arial", Font.BOLD, 24));	// Definindo a fonte e tamanho

		//Criando segundo texto que chamei ide subtitulo "Gerenciador automatico de grupo de calouros"
		JLabel subtitulo = new JLabel("Gerenciador Automático de Grupos de Calouros");
		subtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);	
		subtitulo.setForeground(Color.WHITE);					
		subtitulo.setFont(new Font("Arial", Font.BOLD, 10));	
		
		//textinho em cima do TextField de nome do grupo
		JLabel textoNomeGrupo = new JLabel("Insira o nome do grupo");
		textoNomeGrupo.setAlignmentX(Component.CENTER_ALIGNMENT);
		textoNomeGrupo.setForeground(Color.WHITE);
		textoNomeGrupo.setFont(new Font("Arial", Font.PLAIN, 10)); // Fonte menor para o rótulo
        
		//Botão de Inserir o nome
		JTextField nomeDoGrupo = new JTextField(20); 
        nomeDoGrupo.setMaximumSize(nomeDoGrupo.getPreferredSize()); // Definindo o tamanho máximo como o tamanho preferido
        nomeDoGrupo.setAlignmentX(Component.CENTER_ALIGNMENT); // Centralizando o campo de texto
        nomeDoGrupo.setFont(new Font("Arial", Font.PLAIN, 14)); // Definindo a fonte e tamanho
        nomeDoGrupo.setForeground(Color.BLACK); // Cor do texto
        nomeDoGrupo.setBackground(Color.WHITE); // Cor de fundo
		
		//Criando uma "Imagem" a Logo da Estácio, ps: É um texto vazio que a imagem é um icone 
		JLabel imagem = new JLabel(""); 
		ImageIcon icon = new ImageIcon(TelaPanel.class.getResource("/imagens/logo_estacio.png")); 	//Selecionando o caminho da imagem
		Image novaImagem = icon.getImage().getScaledInstance(200, 150, Image.SCALE_AREA_AVERAGING); //Usando a awt.image para editar o tamanho da imagem sem precisar editar por fora
		imagem.setIcon(new ImageIcon(novaImagem));													//Definindo a imagem editada como o novo icone
		imagem.setAlignmentX(Component.CENTER_ALIGNMENT);											// Definindo o alinhamento

		// Criando painel para os botões
		JPanel painelBotoes = new JPanel(); //Criando um JPanel para conter os Botões Selecionar e Iniciar
		painelBotoes.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0)); //Definindo o layout como FlowLayout
		painelBotoes.setBackground(Color.decode("#202028"));			  //Definido a cor do painel

		//texto de status de seleção
		JLabel statusPlanilha = new JLabel("Não selecionou");
		statusPlanilha.setForeground(Color.RED);
		statusPlanilha.setFont(new Font("Arial", Font.PLAIN, 10));
		statusPlanilha.setAlignmentX(Component.LEFT_ALIGNMENT);
		        
        // Criando botão "Selecionar Planilha"
        JButton btnSelecionarPlanilha = new JButton("Selecionar Planilha");
        btnSelecionarPlanilha.setBackground(new Color(0, 122, 204));      // Cor de fundo (azul)
        btnSelecionarPlanilha.setForeground(Color.WHITE);                 
        btnSelecionarPlanilha.setFont(new Font("Arial", Font.BOLD, 14));  
        btnSelecionarPlanilha.setFocusPainted(false);                      // Tirando o destaque automático quando inicia o programa, o botão ficava destacado como se tivesse selecionado
        btnSelecionarPlanilha.addActionListener(new ActionListener() {      // Função que "escuta" uma ação que é clicar no botão e executa o que está dentro da action performed
            @Override
            public void actionPerformed(ActionEvent e) {    
                
                System.out.println("Apertou o botão"); // Mensagem de Debug
                JFileChooser selecionadorArquivo = new JFileChooser();    // Criando um FileChooser para selecionar o arquivo
                selecionadorArquivo.setDialogTitle("Selecione a Planilha do Excel"); // Definindo o nome da janela do FileChooser
                FileNameExtensionFilter filtro = new FileNameExtensionFilter("Arquivos Excel", "xlsx"); // Criando o filtro para arquivos .xlsx
                selecionadorArquivo.setFileFilter(filtro); // Adicionando o filtro ao FileChooser
                int userSelection = selecionadorArquivo.showOpenDialog(null); // Abre o FileChooser, O null passado como argumento significa que a caixa de diálogo não tem uma janela pai explícita, então ela será exibida no centro da tela.

                if (userSelection == JFileChooser.APPROVE_OPTION) { // Verifica se o usuário selecionou um arquivo e clicou no botão "Abrir" ou "Ok".
                    // Definindo a planilha selecionada
                	planilhaSelecionada.setSelecionou(true);
                	File planilhaSelecionadas = selecionadorArquivo.getSelectedFile();
                	filePath = planilhaSelecionadas.getAbsolutePath();
                    // Texto de status de seleção
                    statusPlanilha.setText("Planilha Selecionada!");
                    statusPlanilha.setForeground(Color.GREEN);
                } else if(userSelection == JFileChooser.CANCEL_OPTION) { // Mensagem de alerta caso o usuário cancele a seleção de arquivos
                    JOptionPane.showMessageDialog(null, "Por favor, selecione um arquivo .xlsx", "Selecione um arquivo", JOptionPane.WARNING_MESSAGE);
                    statusPlanilha.setText("Planilha não Selecionada!");
                    statusPlanilha.setForeground(Color.RED);
                    planilhaSelecionada.setCaminho(null);
                    planilhaSelecionada.setSelecionou(false);
                }
            }
        });
		
       
		JButton btnIniciarAutomacao = new JButton("Iniciar Automação"); //Criando botão de Inicir Automação
		btnIniciarAutomacao.setBackground(new Color(0,122,204)); 
		btnIniciarAutomacao.setForeground(Color.WHITE);
		btnIniciarAutomacao.setFont(new Font("Arial", Font.BOLD, 14));
		btnIniciarAutomacao.setFocusPainted(false);
		btnIniciarAutomacao.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(planilhaSelecionada.getSelecionou() && !(nomeDoGrupo.getText().isEmpty())) {
                    automacao.iniciarAutomacao(filePath, nomeDoGrupo.getText());
				}else
				{
					if(!(planilhaSelecionada.getSelecionou())){
						JOptionPane.showMessageDialog(null, "Por favor, selecione um arquivo .xlsx", "Sem planilha selecionada!", JOptionPane.WARNING_MESSAGE);
					}
					
					if(nomeDoGrupo.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Por favor, insira um nome para o grupo", "Grupo sem nome!", JOptionPane.WARNING_MESSAGE);
					}
				}
				
			}

		});
		
		//Adicionando os botões no painel de botões
		painelBotoes.add(btnIniciarAutomacao);	
		painelBotoes.add(btnSelecionarPlanilha);		
		
		//Adicionando os componentes no Painel Principal, montando ele básicamente
		this.add(Box.createVerticalStrut(20)); // Adiciona um espaço fixo de 20 pixels entre os rótulos
		this.add(tituloGAGC);
		this.add(subtitulo);
		this.add(Box.createVerticalStrut(20));
		this.add(textoNomeGrupo);
		this.add(nomeDoGrupo);
		this.add(Box.createVerticalStrut(20));
		this.add(painelBotoes);
		 this.add(statusPlanilha);
		this.add(imagem);
		this.add(Box.createVerticalStrut(20));

	}
}
