import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Exercicio04 {

    public static void main(String[] args) {
        String urlBase = "https://apichallenges.eviltester.com/sim/entities";
        String parametros = "?categoria=teste&limite=5";
        String urlCompleta = urlBase + parametros;

        try {
            URL url = new URL(urlCompleta);
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();

            conexao.setRequestMethod("GET");
            conexao.setConnectTimeout(5000);
            conexao.setReadTimeout(5000);

            System.out.println("URL final: " + urlCompleta);

            int codigoStatus = conexao.getResponseCode();
            System.out.println("Código de resposta: " + codigoStatus);

            if (codigoStatus == HttpURLConnection.HTTP_OK) {
                BufferedReader leitor = new BufferedReader(
                        new InputStreamReader(conexao.getInputStream())
                );

                String linha;
                StringBuilder resposta = new StringBuilder();

                while ((linha = leitor.readLine()) != null) {
                    resposta.append(linha);
                }

                leitor.close();

                System.out.println("Resposta: " + resposta.toString());
            }

            conexao.disconnect();

        } catch (IOException e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }
}
