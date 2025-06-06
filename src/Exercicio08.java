import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Exercicio08 {

    public static void main(String[] args) throws Exception {
        // PUT para atualizar
        URL url = new URL("https://apichallenges.eviltester.com/sim/entities/10");
        HttpURLConnection conexao = (HttpURLConnection) url.openConnection();

        conexao.setRequestMethod("PUT");  // ← Única diferença: PUT em vez de POST
        conexao.setRequestProperty("Content-Type", "application/json");
        conexao.setDoOutput(true);

        String json = "{\"name\": \"atualizado\"}";
        OutputStream os = conexao.getOutputStream();
        os.write(json.getBytes());
        os.flush();

        System.out.println("Status do PUT: " + conexao.getResponseCode());

        // Ler resposta do PUT
        BufferedReader br = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
        String resposta = br.readLine();
        System.out.println("Resposta do PUT: " + resposta);

        conexao.disconnect();
    }
}