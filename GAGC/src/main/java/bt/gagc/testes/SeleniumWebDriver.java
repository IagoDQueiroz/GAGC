package bt.gagc.testes;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class SeleniumWebDriver {

    public static void main(String[] args) {
        try {
            String driverName = "chromedriver.exe";
            String driverURL = "https://storage.googleapis.com/chrome-for-testing-public/131.0.6778.85/win64/chromedriver-win64.zip"; // URL do ChromeDriver (ajuste conforme versão do Chrome)
            String driverFolder = "C:\\webdriver\\";

            // Verifica se o ChromeDriver já está instalado
            File driverFile = new File(driverFolder + driverName);
            if (!driverFile.exists()) {
                System.out.println("ChromeDriver não encontrado. Baixando e configurando...");
                downloadAndExtractDriver(driverURL, driverFolder);
                System.out.println("ChromeDriver instalado com sucesso em: " + driverFile.getAbsolutePath());
            } else {
                System.out.println("ChromeDriver já está instalado em: " + driverFile.getAbsolutePath());
            }

            // Configurar o ChromeDriver no PATH
            System.setProperty("webdriver.chrome.driver", driverFile.getAbsolutePath());

            // Iniciar o WebDriver
            WebDriver driver = new ChromeDriver();
            driver.get("https://web.whatsapp.com/");
            System.out.println("WhatsApp Web foi aberto com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Ocorreu um erro: " + e.getMessage());
        }
    }

    // Método para baixar e extrair o ChromeDriver
    public static void downloadAndExtractDriver(String driverURL, String destinationFolder) throws Exception {
        // Criar a pasta de destino, se não existir
        File folder = new File(destinationFolder);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        // Baixar o arquivo ZIP do ChromeDriver
        URL url = new URL(driverURL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setDoOutput(true);

        // Salvar o arquivo ZIP localmente
        File zipFile = new File(destinationFolder + "chromedriver.zip");
        try (InputStream in = connection.getInputStream();
             FileOutputStream out = new FileOutputStream(zipFile)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
        }

        // Extrair o arquivo ZIP
        unzip(zipFile, destinationFolder);

        // Excluir o arquivo ZIP após extração
        zipFile.delete();
    }

    // Método para descompactar o ZIP do ChromeDriver
    public static void unzip(File zipFile, String destinationFolder) throws Exception {
        try (java.util.zip.ZipInputStream zis = new java.util.zip.ZipInputStream(new java.io.FileInputStream(zipFile))) {
            java.util.zip.ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                File newFile = new File(destinationFolder, entry.getName());
                try (FileOutputStream fos = new FileOutputStream(newFile)) {
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = zis.read(buffer)) > 0) {
                        fos.write(buffer, 0, length);
                    }
                }
            }
        }
    }
}
