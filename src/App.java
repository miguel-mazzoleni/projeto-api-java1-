import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularTVs.json";
        URI end = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(end).GET().build();
        var response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        // extraindo os dados dos filmes
        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);
        // System.out.println(listaDeFilmes.size());
        // System.out.println(listaDeFilmes.get(0));

        // Exibir e manipular os dados

        for (Map<String, String> filme : listaDeFilmes) {
            System.out.println("\u001b[1mTitulo:\u001b[m " + filme.get("title"));
            System.out.println("\u001b[1mAno de lançamento:\u001b[m " + filme.get("year"));
            System.out.println("\u001b[1mURL da Imagem:\u001b[m " + filme.get("image"));
            System.out.print("\u001b[97m\u001b[38;5;214mClassificação:\u001b[m ");
            double classificacao = Double.parseDouble(filme.get("imDbRating"));
            for (int star = 1; star <= classificacao; star++) {
                System.out.print("⭐");


            }
            System.out.printf("  " + filme.get("imDbRating"));
            System.out.println("\n");
        }

    }
}
