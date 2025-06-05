import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Exercicio02 {

    public static void main(String[] args) {

        for (int id = 2; id <= 8; id++) {
            System.out.println("\n Buscando entidade com ID: " + id + "\n");
            buscarEntidadePorId(id);
        }
    }

        public static void buscarEntidadePorId(int id) {
            String urlString = "https://apichallenges.eviltester.com/sim/entities/" + id;

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
                        resposta.append(linha);
                    }

                    leitor.close();

                    System.out.println("Resposta do servidor:" + resposta.toString());
                } else if (codigoStatus == HttpURLConnection.HTTP_NOT_FOUND) {
                    System.out.println(("Entidade com ID " + id + " não encontrada."));
                } else {
                    System.out.println("Erro na requisição. Código de status: " + codigoStatus);
                }

                conexao.disconnect();

            } catch (IOException e) {
                System.out.println("Erro ao conectar ou ler a resposta: " + e.getMessage());
            }
        }
    }


