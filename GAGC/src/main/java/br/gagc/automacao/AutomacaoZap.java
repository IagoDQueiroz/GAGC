package br.gagc.automacao;

import br.gagc.automacao.components.Contato;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.JOptionPane;
import java.time.Duration;
import java.util.List;

public class AutomacaoZap {

    private WebDriver driver;

    public AutomacaoZap() {
        try {
            // Configura o caminho do ChromeDriver
            System.setProperty("webdriver.chrome.driver", "D:\\Users\\Popyl\\Downloads\\chromedriver-win64 (1)\\chromedriver-win64\\chromedriver.exe");
            
            // Configurações do WebDriver
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
            driver = new ChromeDriver(options);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Método que realiza a automação para criar o grupo no WhatsApp e adicionar os contatos.
     *
     * @param listaContatos Lista de contatos a ser adicionada ao grupo.
     * @param nomeDoGrupo   Nome do grupo a ser criado.
     */
    public void IniciarAutomacaoZap(List<Contato> listaContatos, String nomeDoGrupo) {
        try {
            // Abrir o WhatsApp Web
            driver.get("https://web.whatsapp.com/");
            System.out.println("Acesse o WhatsApp Web e faça login.");

            // Exibir um aviso para o usuário esperar até que faça o login
            JOptionPane.showMessageDialog(null, "Faça login no WhatsApp Web e clique em OK para continuar.", 
                                          "Aviso", JOptionPane.INFORMATION_MESSAGE);

            // Espera o usuário fazer login manualmente
            System.out.println("Esperando você fazer login...");

            // Usar WebDriverWait para aguardar a página carregar
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span[data-icon='menu']")));

            // Após o login, o código segue automaticamente
            System.out.println("Login realizado. Iniciando a criação do grupo.");

            // Chama o método que cria o grupo no WhatsApp e adiciona os contatos
            criarGrupoWhatsApp(driver, nomeDoGrupo, listaContatos);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void criarGrupoWhatsApp(WebDriver driver, String nomeGrupo, List<Contato> contatos) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Espera de até 10 segundos

            WebElement menu = driver.findElement(By.cssSelector("span[data-icon='menu']"));
            menu.click(); // clica nos 3 pontos (menu)

            WebElement novoGrupo = driver.findElement(By.cssSelector("div[class='_aj-z _aj-t _alxo']"));
            novoGrupo.click(); // clica no elemento novo grupo

            for (Contato contato : contatos) {
                WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[type='text']")));
                
                // Formata o número de telefone antes de fazer a pesquisa
                String telefoneFormatado = formatarNumero(contato.getNumero());
                searchBox.sendKeys(telefoneFormatado);
                
                try {
                    // Aguarda até que o elemento com classe e número apareça na lista de contatos
                    WebElement contatoElemento = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@title='" + telefoneFormatado + "']")));
                    contatoElemento.click();
                    
                    // Adiciona um pequeno atraso para garantir que o contato seja adicionado
                    Thread.sleep(1000); // 1 segundo de pausa
                    
                } catch (Exception e) {
                    System.out.println("Erro ao adicionar contato: " + e.getMessage());
                }
                
                searchBox.clear();  // Limpa a caixa de pesquisa para o próximo contato
            }

            driver.findElement(By.cssSelector("span[data-icon='arrow-forward']")).click();  // Prosseguir para tela de nomear grupo

            WebElement nomeGrupoElemento = driver.findElement(By.cssSelector("div[contenteditable='true']"));
            nomeGrupoElemento.sendKeys(nomeGrupo); // Inserir o nome do grupo

            // Clica em finalizar a criação do grupo
            driver.findElement(By.cssSelector("span[data-icon='checkmark']")).click(); // --> Trocar para "span[data-icon='checkmark-medium']" se necessário

            System.out.println("Grupo criado com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String formatarNumero(String numero) {
        // Verifica se o número possui o comprimento esperado
        if (numero.length() != 11) {
            throw new IllegalArgumentException("O número deve ter 11 dígitos.");
        }

        // Remove o dígito '9' do início, após o código da área
        String numeroSemNove;
        if (numero.charAt(2) == '9') {
            numeroSemNove = numero.substring(0, 2) + numero.substring(3);
        } else {
            numeroSemNove = numero;
        }

        // Adiciona o '+' no início do número, os espaços e hífen para a formatação correta
        String formatado = "+55 " + numeroSemNove.substring(0, 2);  // Código da área
        formatado += " " + numeroSemNove.substring(2, 6);           // Primeiro bloco do número local
        formatado += "-" + numeroSemNove.substring(6);              // Segundo bloco do número local

        return formatado;
    }

    public void fechar() {
        if (driver != null) {
            driver.quit();  // Fechar o navegador
        }
    }
}
