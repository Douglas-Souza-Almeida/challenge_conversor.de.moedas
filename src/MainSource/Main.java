package MainSource;

// Importação das classes necessárias
import Classes.Currencies;
import Classes.Request;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Criação do objeto Scanner para entrada do usuário
        int choice; // Variável para armazenar a escolha do usuário

        do {
            // Exibe o menu para o usuário
            System.out.println("==================================");
            System.out.println("         CONVERSOR DE MOEDAS      ");
            System.out.println("==================================");
            System.out.println("1. Converter valor");
            System.out.println("2. Exibir moedas e siglas");
            System.out.println("3. Sair");
            System.out.println("==================================");
            System.out.print("Escolha uma opção: ");

            // Verifica se o próximo token é um número
            while (!scanner.hasNextInt()) {
                System.out.println("Opção inválida. Por favor, escolha novamente.");
                scanner.next(); // Consome o token inválido
                System.out.print("Escolha uma opção: ");
            }

            choice = scanner.nextInt(); // Lê a escolha do usuário

            switch (choice) {
            case 1:
                // Opção para converter valor entre moedas
                System.out.print("Digite o valor a ser convertido: ");
                double amount = scanner.nextDouble();

                System.out.print("Digite a Currency de origem (ex: USD): ");
                String fromCurrency = scanner.next().toUpperCase();

                System.out.print("Digite a Currency de destino (ex: BRL): ");
                String toCurrency = scanner.next().toUpperCase();

                Request request = new Request(); // Criação de um objeto Request para lidar com a requisição de conversão

                try {
                    // Obtém a taxa de conversão e realiza a conversão
                    double conversionRate = request.getConversionRate(fromCurrency, toCurrency);
                    double convertedAmount = amount * conversionRate;

                    // Exibe os resultados
                    System.out.printf("Taxa de conversão: %.4f\n", conversionRate);
                    System.out.printf("%.2f %s = %.2f %s\n", amount, fromCurrency, convertedAmount, toCurrency);
                } catch (Exception e) {
                    // Exibe mensagem de erro caso ocorra uma exceção durante a conversão
                    System.err.println("Erro ao converter Currencys: " + e.getMessage());
                }
                break;

            case 2:
                // Opção para exibir moedas e siglas disponíveis
                Currencies.displayCurrencies();
                break;

            case 3:
                // Opção para sair do programa
                System.out.println("Encerrando a aplicação...");
                break;

            default:
                // Mensagem exibida quando a opção escolhida não é válida
                System.out.println("Opção inválida. Por favor, escolha novamente.");
                break;
            }

            System.out.println(); // Linha em branco para separar as iterações do menu

        } while (choice != 3); // Continua executando até que o usuário escolha a opção de sair

        scanner.close(); // Fecha o scanner ao finalizar a execução do programa
    }
}
