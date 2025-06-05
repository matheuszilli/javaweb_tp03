import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Exercicio01 {

    public static void main(String[] args) {
        String urlString = "https://apichallenges.eviltester.com/sim/entities";

        try {
            URL url = new URL(urlString);

            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();

            conexao.setRequestMethod("GET");

            conexao.setConnectTimeout(5000);
            conexao.setReadTimeout(5000);

            int codigoStatus = conexao.getResponseCode();
            System.out.println("Código de status da resposta: " + codigoStatus);

            if (codigoStatus == HttpURLConnection.HTTP_OK) {
                BufferedReader leitor = new BufferedReader(
                        new InputStreamReader(conexao.getInputStream())
                );

                String linha;
                StringBuilder resposta = new StringBuilder();

                while ((linha = leitor.readLine()) != null) {
                    resposta.append(linha).append("\n");
                }

                leitor.close();

                System.out.println("Resposta do servidor:");
                System.out.println(resposta.toString());

            } else {
                System.out.println("Erro na requisição. Código de status: " + codigoStatus);
            }

            conexao.disconnect();

        } catch (IOException e) {
            System.out.println("Erro ao conectar ou ler a resposta: " + e.getMessage());
        }
    }
}