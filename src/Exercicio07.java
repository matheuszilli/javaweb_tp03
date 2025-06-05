import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Exercicio07 {

    public static void main(String[] args) throws Exception {
        // 1. Atualizar entidade 10
        URL urlPost = new URL("https://apichallenges.eviltester.com/sim/entities/10");
        HttpURLConnection conexaoPost = (HttpURLConnection) urlPost.openConnection();

        conexaoPost.setRequestMethod("POST");
        conexaoPost.setRequestProperty("Content-Type", "application/json");
        conexaoPost.setDoOutput(true);

        String json = "{\"name\": \"atualizado\"}";
        System.out.println("Atualizando entidade 10 com: " + json);
        OutputStream os = conexaoPost.getOutputStream();
        os.write(json.getBytes());
        os.flush();

        System.out.println("Status da atualização: " + conexaoPost.getResponseCode());
        conexaoPost.disconnect();

        // 2. Verificar mudança
        URL urlGet = new URL("https://apichallenges.eviltester.com/sim/entities/10");
        HttpURLConnection conexaoGet = (HttpURLConnection) urlGet.openConnection();

        BufferedReader br = new BufferedReader(new InputStreamReader(conexaoGet.getInputStream()));
        String resposta = br.readLine();

        System.out.println("Entidade após atualização: " + resposta);

        conexaoGet.disconnect();
    }
}