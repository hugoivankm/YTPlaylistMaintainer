package Models.Playlist;
import java.util.List;

public class YTPlaylistResponse {
	public String kind;
	public String etag;
	public String nextPageToken;
	public List<PlayListItem> items;
	public PageInfo pageInfo;

    public YTPlaylistResponse(String kind, String etag, String nextPageToken, List<PlayListItem> items, PageInfo pageInfo) {
        this.kind = kind;
        this.etag = etag;
        this.nextPageToken = nextPageToken;
        this.items = items;
        this.pageInfo = pageInfo;
    }
}
