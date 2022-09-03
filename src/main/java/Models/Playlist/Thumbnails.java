package Models.Playlist;

public class Thumbnails {
	private Default _default;
	private Medium medium;
	private High high;
	private Standard standard;

    public Thumbnails(Default _default, Medium medium, High high, Standard standard) {
        this.medium = medium;
        this.high = high;
        this.standard = standard;
        this._default = _default;
    }
}