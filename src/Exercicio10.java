import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Exercicio10 {

    public static void main(String[] args) throws Exception {
        URL url = new URL("https://apichallenges.eviltester.com/sim/entities/2");
        HttpURLConnection conexao = (HttpURLConnection) url.openConnection();

        conexao.setRequestMethod("DELETE");

        int codigoStatus = conexao.getResponseCode();
        System.out.println("Status do DELETE: " + codigoStatus);

        // Ler mensagem de erro
        BufferedReader br = new BufferedReader(
                new InputStreamReader(conexao.getErrorStream())  // ← errorStream para erros
        );

        String linha;
        while ((linha = br.readLine()) != null) {
            System.out.println("Resposta: " + linha);
        }

        conexao.disconnect();
    }
}