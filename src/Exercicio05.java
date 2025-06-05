import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Exercicio05 {

    public static void main(String[] args) throws Exception {
        URL url = new URL("https://apichallenges.eviltester.com/sim/entities");
        HttpURLConnection conexao = (HttpURLConnection) url.openConnection();

        conexao.setRequestMethod("POST");
        conexao.setRequestProperty("Content-Type", "application/json");
        conexao.setDoOutput(true);

        String json = "{\"name\": \"aluno\"}";
        OutputStream os = conexao.getOutputStream();
        os.write(json.getBytes());
        os.flush();

        BufferedReader br = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
        String resposta = br.readLine();

        System.out.println("Resposta: " + resposta);
        System.out.println("ID gerado: " + resposta.substring(resposta.indexOf(":")+1, resposta.indexOf(",")));

        conexao.disconnect();
    }
}