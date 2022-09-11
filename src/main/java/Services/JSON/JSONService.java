package Services.JSON;

import Models.Playlist.YTPlaylistResponse;
import com.google.gson.Gson;

import java.io.IOException;

public class JSONService {
    public static YTPlaylistResponse JSON2Object(String data) throws IOException {
        Gson gson = new Gson();
        return gson.fromJson(data, YTPlaylistResponse.class);
    }
}


