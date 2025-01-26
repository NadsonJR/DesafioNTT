# CEP Search Web Application

## Sobre o Projeto

Este projeto é uma aplicação web desenvolvida em **Java** que permite a busca de informações de um CEP (Código de Endereçamento Postal) utilizando a API pública do [ViaCEP](https://viacep.com.br/). O projeto é dividido em duas partes principais:

1. **Back-end:** Desenvolvido em Java, utilizando Servlets para gerenciar as requisições HTTP.
2. **Front-end:** Ainda em desenvolvimento

## Funcionalidades

- Recebe requisições HTTP do tipo `GET` para a rota `/buscarCep`.
- Busca informações de um CEP na API do ViaCEP.
- Retorna as informações no formato JSON.
- Lida com erros de requisição e CEPs inválidos.

## Tecnologias Utilizadas

### Back-end

- **Java**: Linguagem principal para lógica e gerenciamento do servidor.
- **Jakarta Servlet API**: Para implementar a aplicação web.
- **HttpURLConnection**: Para realizar a comunicação com a API externa (ViaCEP).

### Front-end (Sugestão)

## Como Executar

1. Certifique-se de ter o **JDK 11** (ou superior) instalado.
2. Configure um servidor de aplicação como **Apache Tomcat** ou equivalente.
3. Clone este repositório:
   ```bash
   git clone https://github.com/NadsonJR/DesafioNTT.git
   ```
4. Importe o projeto em sua IDE favorita (Eclipse, IntelliJ, etc.).
5. Compile o projeto e inicie o servidor.
6. Acesse a aplicação via navegador ou ferramenta como Postman:
   ```
   http://localhost:8080/nome-do-contexto/buscarCep?cep=01001000
   ```

## Estrutura do Código

- `CepSearchServlet`: Classe principal que gerencia a lógica de requisição e resposta.
- `buscarCep`: Método que realiza a comunicação com a API do ViaCEP.

## Possíveis Melhorias

- Adicionar validação mais robusta para o formato do CEP.
- Implementar um front-end para facilitar o uso.
- Criar testes unitários para o back-end.
- Adicionar suporte a mais idiomas.

## Licença

Este projeto está licenciado sob a [MIT License](LICENSE).

---

**Autor:** Seu Nome\
**Contato:** [seu-email@example.com](mailto\:seu-email@example.com)

