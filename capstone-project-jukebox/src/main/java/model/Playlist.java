package model;

import java.util.List;
import java.util.Map;

public class Playlist {
    private int playlistId;
    private String playlistName;
    private User user;
    private Map<Integer,Song> songs;

    public Playlist() {
    }

    public Playlist(int playlistId, String playlistName, User user, Map<Integer,Song> songs) {
        this.playlistId = playlistId;
        this.playlistName = playlistName;
        this.user = user;
        this.songs = songs;
    }

    public int getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(int playlistId) {
        this.playlistId = playlistId;
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Map<Integer,Song> getSongs() {
        return songs;
    }

    public void setSongs(Map<Integer,Song> songs) {
        this.songs = songs;
    }
}
