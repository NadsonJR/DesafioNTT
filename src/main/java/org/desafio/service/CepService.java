package org.desafio.service;

import lombok.extern.log4j.Log4j2;
import org.desafio.model.CepResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Log4j2
@Service
public class CepService {

    private final RestTemplate restTemplate;

    public CepService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public CepResponse buscarCep(String cep) {
        String url = "https://viacep.com.br/ws/"+cep+"/json/";
        log.info(url);
        return restTemplate.getForObject(url, CepResponse.class);
    }

    public Object fetchCep(String cep) {
        String url = "https://viacep.com.br/ws/" + cep + "/json/";
        log.info(url);
        try {

            // Faz a chamada para a API externa
            return restTemplate.getForObject(url, Object.class);
        } catch (HttpClientErrorException e) {
            // Trata erros de requisição (ex.: 404)
            throw new RuntimeException("CEP não encontrado ou inválido: " + e.getStatusCode());
        } catch (Exception e) {
            // Trata outros erros genéricos
            throw new RuntimeException("Erro ao buscar CEP: " + e.getMessage());
        }
    }
}
