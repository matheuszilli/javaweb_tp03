import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Exercicio09 {

    public static void main(String[] args) throws Exception {

        // 1. DELETE da entidade 9
        URL urlDelete = new URL("https://apichallenges.eviltester.com/sim/entities/9");
        HttpURLConnection conexaoDelete = (HttpURLConnection) urlDelete.openConnection();

        conexaoDelete.setRequestMethod("DELETE");

        System.out.println("Status do DELETE: " + conexaoDelete.getResponseCode());
        conexaoDelete.disconnect();

        // 2. GET para confirmar que foi deletada
        URL urlGet = new URL("https://apichallenges.eviltester.com/sim/entities/9");
        HttpURLConnection conexaoGet = (HttpURLConnection) urlGet.openConnection();

        int statusGet = conexaoGet.getResponseCode();
        System.out.println("Status do GET após DELETE: " + statusGet);

        if (statusGet == 404) {
            System.out.println("Entidade foi deletada!");
        }
        conexaoGet.disconnect();

    }
}