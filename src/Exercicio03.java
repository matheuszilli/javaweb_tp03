import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Exercicio03 {

    public static void main(String[] args) {
        String urlString = "https://apichallenges.eviltester.com/sim/entities/13";

        try {
            URL url = new URL(urlString);
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();

            conexao.setRequestMethod("GET");
            conexao.setConnectTimeout(5000);
            conexao.setReadTimeout(5000);

            int codigoStatus = conexao.getResponseCode();

            System.out.println("Tentando buscar entidade ID 13...");
            System.out.println("Código de Status: " + codigoStatus);
            if (codigoStatus == 404) {
                System.out.println("Entidade com ID 13 não encontrada.");
                return;
            }


            conexao.disconnect();

        } catch (IOException e) {
            System.err.println("Erro na conexão: " + e.getMessage());
        }
    }
}