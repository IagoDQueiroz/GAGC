
# Gerenciador Automático de Grupos de Calouros
O Gerenciar automático de grupos de calouros também conhecido como GAGC, Automatiza a criação de grupos de Whatsapp, A aplicação lê dados de uma planilha com nomes e números de contato, cria grupos personalizados e adiciona automaticamente os alunos, agilizando a integração e comunicação de forma eficiente e organizada.
Este projeto automatiza a criação de grupos no WhatsApp usando dados de uma planilha Excel.

## Requisitos

- **Eclipse IDE**
- **Java Development Kit (JDK)** (Baixe e instale)
- **Maven** para gerenciamento de dependências (https://dlcdn.apache.org/maven/maven-3/3.9.9/binaries/apache-maven-3.9.9-bin.zip)
- **ChromeDriver** para automação do Chrome (https://storage.googleapis.com/chrome-for-testing-public/128.0.6613.86/win64/chromedriver-win64.zip)
- **Planilha de Teste**

## Preparando o Ambiente de Trabalho

1. Adicione os caminhos para Maven e ChromeDriver nas variáveis de ambiente:
   - Pesquise "Variáveis de Ambiente" no Windows (você pode adicioná-las para o usuário ou para o sistema; variáveis de usuário são válidas apenas para o usuário atual, enquanto variáveis de sistema são para todos os usuários do PC).
   - Crie uma variável chamada `MAVEN_HOME` e defina seu valor como o diretório de `apache-maven-3.9.9'.
   - Vá até a variável `Path`, clique em "Editar", depois clique em "Novo" (cuidado para não excluir ou sobrepor entradas existentes). Adicione `%MAVEN_HOME%\bin` como uma nova entrada.
   - Na variável `Path`, adicione outra entrada para o diretório da pasta do ChromeDriver `chromedriver-win64`.

2. Dentro do projeto:
   - No arquivo `WhatsAppGroupAutomationUI.java`, vá para a linha 127 e especifique o diretório do arquivo `chromedriver.exe` (localizado dentro da pasta `chromedriver-win64`).
   - No final do código, o código do `cssSelector` foi alterado propositalmente para evitar o envio em massa de criação de grupos, mas o código correto está comentado no arquivo.

## Como Rodar

1. **Escolha a Planilha:**
   - Selecione a planilha desejada. Note que a primeira linha será ignorada.
   - A tabela A deve conter os nomes e a tabela B, os números. Os numeros tem que ser formatados assim:"82988884444" (11 digitos)

2. **Inicie a Automação:**
   - Escolha o nome do grupo.
   - Clique em "Iniciar Automação".

3. **Abra o WhatsApp Web:**
   - Uma nova página será aberta com o QR Code do WhatsApp Web.
   - Um popup com um aviso aparecerá. Clique em "OK" somente após fazer login com o QR Code e aguardar o carregamento completo da página.

4. **Finalização:**
   - A automação será realizada conforme configurado.
   - Ao final, o nome do grupo será adicionado.

