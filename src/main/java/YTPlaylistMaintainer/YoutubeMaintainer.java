package YTPlaylistMaintainer;

import Models.Playlist.PlayListItem;
import Models.Playlist.YTPlaylistResponse;
import Services.JSON.JSONService;
import Services.Persistence.TextFileHandler;
import io.github.cdimascio.dotenv.Dotenv;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class YoutubeMaintainer {

    public static void main(String[] args) {
        String nextPageToken = null;
        List<PlayListItem> itemList = new ArrayList<>();
        YTPlaylistResponse playListObj;
        String jsonValue;
        boolean hasNextPageToken;

        Path root = Paths.get(".").normalize().toAbsolutePath();
        Path dotenvPath = Paths.get(root.toString(), ".env");

        Dotenv dotenv = Dotenv.configure()
                .directory(dotenvPath.toString())
                .load();

        String PLAYLIST_ID = dotenv.get("PLAYLIST_ID");
        String API_KEY = dotenv.get("API_KEY");

        try {
            do {
                if (API_KEY == null || PLAYLIST_ID == null) {
                    System.out.println("Unable to acquire secrets");
                    return;
                }
                // Make a request to YT and store the playlist
                jsonValue = YTRequest.getPlaylist(PLAYLIST_ID, API_KEY, nextPageToken).join();

                // Parse that JSON that we got to objects and extract next page token
                playListObj = JSONService.JSON2Object(jsonValue);

                itemList.addAll(playListObj.items);

                nextPageToken = playListObj.nextPageToken;
                hasNextPageToken = isStringFalsy(nextPageToken);

            } while (hasNextPageToken);
            //Print the item list
            for (var item : itemList) {
                System.out.println(item.snippet.position + " " + item.snippet.title);
            }

            String path = Paths.get("./test.txt").toAbsolutePath().toString();
            TextFileHandler textFile = new TextFileHandler();
            List<String> data = new ArrayList<>();

            for (var item : itemList) {
                data.add(item.snippet.position + " " + item.snippet.title);
            }

            textFile.save(data, path);

        } catch (Exception e) {
            System.out.println("EXCEPTION");
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }

    private static boolean isStringFalsy(String stringParameter) {
        if (stringParameter == null || stringParameter.isEmpty()) {
            return false;
        }
        stringParameter = stringParameter.trim();
        return !stringParameter.equals("null") && !stringParameter.isEmpty();
    }
}
