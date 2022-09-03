package YTPlaylistMaintainer;


import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;

public class YTRequest {

    public static CompletableFuture<String> getPlaylist(String playlistId, String apiKey, String nextPageToken) throws Exception {

        // Form URL for HttpRequest
        final int maxResults = 50;
        nextPageToken = (nextPageToken == null)  ? "" : nextPageToken ;
        String requestURL = "https://youtube.googleapis.com/youtube/v3/playlistItems" +
                "?part=contentDetails&part=id&part=snippet&part=status" +
                "&maxResults=" + maxResults + "&pageToken=" + nextPageToken +"&playlistId=" +
                playlistId + "&key=" + apiKey;

        // Form a http request
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("accept", "application/json")
                .uri(new URI(requestURL))
                .version(HttpClient.Version.HTTP_2)
                .timeout(Duration.ofSeconds(15))
                .build();

        // Create new http client
        HttpClient client = HttpClient.newHttpClient();

       // Client request
         return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                                     .thenApply(HttpResponse<String>::body);
    }


}
