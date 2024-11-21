package br.gagc.automacao;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import br.gagc.automacao.components.Contato;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Automacao {

	AutomacaoZap automacaoZap = new AutomacaoZap();
    /**
     * Método responsável por ler os dados de uma planilha Excel e passar para a automação do WhatsApp.
     *
     * @param caminho     O caminho do arquivo Excel.
     * @param nomeDoGrupo O nome do grupo que será usado posteriormente.
     * @return Uma lista de objetos Contato, contendo nomes e números extraídos da planilha.
     */
    public List<Contato> iniciarAutomacao(String caminho, String nomeDoGrupo) {
        List<Contato> listaContatos = new ArrayList<>();

        try (
            FileInputStream file = new FileInputStream(caminho);
            Workbook workbook = new XSSFWorkbook(file)
        ) {
            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue;

                Cell cellNome = row.getCell(0);
                Cell cellNumero = row.getCell(1);

                if (cellNome != null && cellNumero != null) {
                    String nome = getCellValueAsString(cellNome);
                    String numero = getCellValueAsString(cellNumero);

                    listaContatos.add(new Contato(nome, numero));
                }
            }

            automacaoZap.IniciarAutomacaoZap(listaContatos, nomeDoGrupo);
            
            // Exibir os contatos salvos no console
            if (listaContatos.isEmpty()) {
                System.out.println("Nenhum contato encontrado na planilha.");
            } else {
                System.out.println("Contatos salvos:");
                for (Contato contato : listaContatos) {
                    System.out.println("Nome: " + contato.getNome() + ", Número: " + contato.getNumero());
                }
            }

        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }


        return listaContatos; // Retorna a lista de contatos
       
    }
    /**
     * Método auxiliar para converter valores de células em String, independente do tipo da célula.
     *
     * @param cell A célula a ser lida.
     * @return O valor da célula como String.
     */
    private String getCellValueAsString(Cell cell) {
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf((long) cell.getNumericCellValue());
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            case BLANK:
                return "";
            default:
                return "Tipo de célula não suportado";
        }

    }

}
