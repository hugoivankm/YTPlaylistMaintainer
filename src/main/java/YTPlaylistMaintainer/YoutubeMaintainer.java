package YTPlaylistMaintainer;

import Models.Playlist.PlayListItem;
import Models.Playlist.YTPlaylistResponse;
import Services.JSON.JSONService;
import io.github.cdimascio.dotenv.Dotenv;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class YoutubeMaintainer {

    public static void main(String[] args) {
        String nextPageToken = null;
        List<PlayListItem> itemList = new ArrayList<>();
        YTPlaylistResponse playListObj = null;
        String jsonValue = "";
        boolean hasNextPageToken = false;

        Path root = Paths.get(".").normalize().toAbsolutePath();
        Path dotenvPath = Paths.get(root.toString(), ".env");

        Dotenv dotenv =  Dotenv.configure()
                               .directory(dotenvPath.toString())
                               .load();

        String PLAYLIST_ID = dotenv.get("PLAYLIST_ID");
        String API_KEY = dotenv.get("API_KEY");;


        try {
            do {
                if (API_KEY == null || PLAYLIST_ID == null) {
                    System.out.println("Unable to acquire secrets");
                    return;
                }
                // Make a request to YT and store the playlist
                jsonValue = YTRequest.GetRequest(PLAYLIST_ID, API_KEY, nextPageToken).join();

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
        } catch (Exception e) {
            System.out.println("EXCEPTION");
            System.out.println(e.getStackTrace());
        }
    }

    private static boolean isStringFalsy(String stringParameter) {
        if (stringParameter == null || stringParameter == "") {
            return false;
        }
        stringParameter = stringParameter.trim();
        if (stringParameter.equals("null") || stringParameter.equals("")) {
            return false;
        }
        return true;
    }
}
