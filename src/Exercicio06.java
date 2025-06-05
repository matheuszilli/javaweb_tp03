import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Exercicio06 {

    public static void main(String[] args) throws Exception {
        URL url = new URL("https://apichallenges.eviltester.com/sim/entities/11");
        HttpURLConnection conexao = (HttpURLConnection) url.openConnection();

        BufferedReader br = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
        String resposta = br.readLine();

        System.out.println("Entidade recuperada: " + resposta);

        conexao.disconnect();
    }
}
