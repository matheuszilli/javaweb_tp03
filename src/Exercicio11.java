import java.net.HttpURLConnection;
import java.net.URL;

public class Exercicio11 {

    public static void main(String[] args) throws Exception {
        URL url = new URL("https://apichallenges.eviltester.com/sim/entities");
        HttpURLConnection conexao = (HttpURLConnection) url.openConnection();

        conexao.setRequestMethod("OPTIONS");

        System.out.println("Status: " + conexao.getResponseCode());

        String metodosPermitidos = conexao.getHeaderField("Allow");
        System.out.println("Métodos permitidos: " + metodosPermitidos);

        System.out.println("\nTodos os cabeçalhos:");
        conexao.getHeaderFields().forEach((k, v) ->
                System.out.println(k + ": " + v)
        );

        conexao.disconnect();
    }
}