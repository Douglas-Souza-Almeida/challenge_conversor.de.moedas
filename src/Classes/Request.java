package Classes;

import com.google.gson.Gson;  // Importa a biblioteca Gson para manipulação de JSON
import java.net.URI;  // Importa a classe URI para manipulação de URIs
import java.net.http.HttpClient;  // Importa a classe HttpClient para realizar requisições HTTP
import java.net.http.HttpRequest;  // Importa a classe HttpRequest para construir requisições HTTP
import java.net.http.HttpResponse;  // Importa a classe HttpResponse para manipular respostas HTTP

public class Request {
    private static final String API_KEY = "b4da37f414806e3c2df9b7a6";  // Chave de API para autenticação no serviço de taxa de câmbio

    // Método para obter a taxa de conversão entre duas moedas
    public static double getConversionRate(String fromCurrency, String toCurrency) throws Exception {
        // Formata a URL para a requisição API com a chave de API e a moeda de origem
        String url = String.format("https://v6.exchangerate-api.com/v6/%s/latest/%s", API_KEY, fromCurrency);

        HttpClient client = HttpClient.newHttpClient();  // Cria um cliente HTTP
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();  // Constrói uma requisição HTTP para a URL especificada

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());  // Envia a requisição e recebe a resposta como string

        if (response.statusCode() != 200) {  // Verifica se o código de status da resposta não é 200 (OK)
            throw new Exception("Falha ao obter a taxa de câmbio");  // Lança uma exceção se a requisição falhou
        }

        Gson gson = new Gson();  // Cria uma instância do Gson para manipulação de JSON
        ExchangeRates exchangeRates = gson.fromJson(response.body(), ExchangeRates.class);  // Converte a resposta JSON para um objeto ExchangeRates

        // Verifica se a moeda de destino está presente no mapa de taxas de conversão
        if (!exchangeRates.conversionRates().containsKey(toCurrency)) {
            throw new Exception("Currency de destino não encontrada");  // Lança uma exceção se a moeda de destino não foi encontrada
        }

        // Retorna a taxa de conversão para a moeda de destino
        return exchangeRates.conversionRates().get(toCurrency);
    }
}
