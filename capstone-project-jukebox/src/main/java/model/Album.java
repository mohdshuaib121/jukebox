package model;

public class Album {
    private int albumId;
    private String albumName;
    private String releaseYear;

    public Album() {
    }

    public Album(int albumId, String name, String releaseDate) {
        this.albumId = albumId;
        this.albumName = name;
        this.releaseYear = releaseDate;
    }

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public String getName() {
        return albumName;
    }

    public void setName(String name) {
        this.albumName = name;
    }

    public String getReleaseDate() {
        return releaseYear;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseYear = releaseDate;
    }

   /* @Override
    public String toString() {
        return "Album{" +
                "albumId=" + albumId +
                ", albumName='" + albumName + '\'' +
                ", releaseYear='" + releaseYear + '\'' +
                '}';
    }*/

}
