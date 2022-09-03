package Models.Playlist;

public class PlayListItem {
    public String kind;
    public String etag;
    public String id;
    public Snippet snippet;
    public ContentDetails contentDetails;
    public Status status;

    public PlayListItem(String kind, String etag, String id, Snippet snippet, ContentDetails contentDetails, Status status) {
        this.kind = kind;
        this.etag = etag;
        this.id = id;
        this.snippet = snippet;
        this.contentDetails = contentDetails;
        this.status = status;
    }
}
