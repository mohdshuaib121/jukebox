package model;

public class Song {
    private int songId;
    private String songName;
    private String singerName;
    private String genre;
    private String duration;
    private String url;
    private Album album;

    public Song() {
    }

    public Song(int songId, String songName, String singerName, String genre, String duration, String url, Album album) {
        this.songId = songId;
        this.songName = songName;
        this.singerName = singerName;
        this.genre = genre;
        this.duration = duration;
        this.url = url;
        this.album = album;
    }

    public int getSongId() {
        return songId;
    }

    public String getSongName() {
        return songName;
    }

    public String getSingerName() {
        return singerName;
    }

    public String getGenre() {
        return genre;
    }

    public String getDuration() {
        return duration;
    }

    public String getUrl() {
        return url;
    }

    public Album getAlbum() {
        return album;
    }


}
