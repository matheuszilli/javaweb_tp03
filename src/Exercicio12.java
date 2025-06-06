import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Exercicio12 {

    public static void main(String[] args) throws Exception {
        // 1. GET todos os itens
        System.out.println("1. LISTANDO TODOS OS ITENS");
        URL url1 = new URL("https://apichallenges.eviltester.com/simpleapi/items");
        HttpURLConnection con1 = (HttpURLConnection) url1.openConnection();

        BufferedReader br1 = new BufferedReader(new InputStreamReader(con1.getInputStream()));
        System.out.println("Itens: " + br1.readLine());
        con1.disconnect();

        // 2. Gerar ISBN aleatório
        System.out.println("\n2. GERANDO ISBN");
        URL url2 = new URL("https://apichallenges.eviltester.com/simpleapi/randomisbn");
        HttpURLConnection con2 = (HttpURLConnection) url2.openConnection();

        BufferedReader br2 = new BufferedReader(new InputStreamReader(con2.getInputStream()));
        String isbnResponse = br2.readLine();
        System.out.println("ISBN gerado: " + isbnResponse);

        // Extrair apenas o número do ISBN
        String isbn = isbnResponse.replaceAll("[^0-9]", "");
        con2.disconnect();

        // 3. POST - Criar item
        System.out.println("\n3. CRIANDO ITEM");
        URL url3 = new URL("https://apichallenges.eviltester.com/simpleapi/items");
        HttpURLConnection con3 = (HttpURLConnection) url3.openConnection();

        con3.setRequestMethod("POST");
        con3.setRequestProperty("Content-Type", "application/json");
        con3.setDoOutput(true);

        String jsonCriar = String.format(
                "{\"type\":\"book\",\"isbn13\":\"%s\",\"price\":5.99,\"numberinstock\":5}",
                isbn
        );

        OutputStream os3 = con3.getOutputStream();
        os3.write(jsonCriar.getBytes());
        os3.flush();

        System.out.println("Status POST: " + con3.getResponseCode());
        BufferedReader br3 = new BufferedReader(new InputStreamReader(con3.getInputStream()));
        System.out.println("Item criado: " + br3.readLine());
        con3.disconnect();

        // 4. PUT - Atualizar item
        System.out.println("\n4. ATUALIZANDO ITEM");
        URL url4 = new URL("https://apichallenges.eviltester.com/simpleapi/items");
        HttpURLConnection con4 = (HttpURLConnection) url4.openConnection();

        con4.setRequestMethod("PUT");
        con4.setRequestProperty("Content-Type", "application/json");
        con4.setDoOutput(true);

        String jsonAtualizar = String.format(
                "{\"type\":\"book\",\"isbn13\":\"%s\",\"price\":9.99,\"numberinstock\":10}",
                isbn
        );

        OutputStream os4 = con4.getOutputStream();
        os4.write(jsonAtualizar.getBytes());
        os4.flush();

        System.out.println("Status PUT: " + con4.getResponseCode());
        con4.disconnect();

        // 5. DELETE - Remover item
        System.out.println("\n5. DELETANDO ITEM");
        URL url5 = new URL("https://apichallenges.eviltester.com/simpleapi/items/" + isbn);
        HttpURLConnection con5 = (HttpURLConnection) url5.openConnection();

        con5.setRequestMethod("DELETE");
        System.out.println("Status DELETE: " + con5.getResponseCode());
        con5.disconnect();

        System.out.println("\nCiclo chegou no fim");
    }
}