package dao;

import model.Album;
import model.Playlist;
import model.Song;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlaylistDao {
    Connection connection;

    public PlaylistDao() {
        this.connection = DbOperations.createConnection();
    }

    public Playlist createPlaylist(String playlistName, User user) throws SQLException {
        Playlist playlist = new Playlist();
        String insertQuery = "insert into playlist(playlist_name,user_id) values (?,?);";
        PreparedStatement statement = connection.prepareStatement(insertQuery, PreparedStatement.RETURN_GENERATED_KEYS);
        statement.setString(1, playlistName);
        statement.setInt(2,user.getUserId());
        int result = statement.executeUpdate();
        if(result > 0){
            ResultSet keys = statement.getGeneratedKeys();
            if(keys.next()){
                playlist.setPlaylistId(keys.getInt(1));
                playlist.setPlaylistName(playlistName);
                playlist.setUser(user);
                playlist.setSongs(new HashMap<Integer,Song>() {
                });
            }
        }
        return playlist;
    }

    public boolean addSongToPlaylist(int playlistId,int songId) throws SQLException {
        String query = "insert into playlist_track values(?,?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, playlistId);
        statement.setInt(2, songId);
        int result = statement.executeUpdate();
        return result > 0;
    }

    public boolean deleteSongFromPlaylist(int playlistId,int songId) throws SQLException {
        String query = "delete from playlist_track where playlist_id=? and song_id=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, playlistId);
        statement.setInt(2, songId);
        int result = statement.executeUpdate();
        return result > 0;
    }

    public boolean deleteAllSongFromPlaylist(int playlistId) throws SQLException {
        String query = "delete from playlist_track where playlist_id=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, playlistId);
        int result = statement.executeUpdate();
        return result > 0;
    }

    public boolean deletePlaylist(int playlistId) throws SQLException {
        deleteAllSongFromPlaylist(playlistId);
        String query = "delete from playlist where playlist_id=?;";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, playlistId);
        int result = statement.executeUpdate();
        return result > 0;
    }

    public boolean deleteAllPlaylist(int userId) throws SQLException {
        String query = "delete from playlist where user_id=?;";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, userId);
        int result = statement.executeUpdate();
        return result > 0;
    }

    public Map<Integer,Playlist> getAllPlaylist(User user) {
        Map<Integer,Playlist> playlists=new HashMap<>();

        List<Album> albumList=new ArrayList<>();
        String query = "select * from playlist where user_id="+user.getUserId()+";";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int playlistId=resultSet.getInt("playlist_id");
                String playlistName=resultSet.getString("playlist_name");
                Playlist playlist=new Playlist();
                playlist.setPlaylistId(playlistId);
                playlist.setPlaylistName(playlistName);
                playlist.setUser(user);
                Map<Integer,Song> songList=getPlaylistSongs(playlist);
                playlist.setSongs(songList);

                playlists.put(playlistId,playlist);

            }
        } catch (NullPointerException exception) {
            System.out.println(exception);
        } catch (SQLException exception) {
            System.out.println(exception);
        }
        return playlists;
    }

    public Map<Integer,Song> getPlaylistSongs(Playlist playlist) {
        List<Album> albumList=new ArrayList<>();
        Map<Integer,Song> songs = new HashMap<>();
        String query = "select * from playlist_track inner join song on song.song_id=playlist_track.song_id inner join album on song.album_id=album.album_id where playlist_id="+playlist.getPlaylistId()+";";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int songId = resultSet.getInt("song_id");
                String songName = resultSet.getString("song_name");
                String genre = resultSet.getString("genre");
                String singerName = resultSet.getString("singer_name");
                String duration = resultSet.getString("duration");
                String url = resultSet.getString("url");
                int albumId=resultSet.getInt("album_id");
                String albumName=resultSet.getString("album_name");
                String year=resultSet.getString("release_year");

                Album album=null;
                boolean equal=false;
                for(Album a : albumList) {
                    if (a.getAlbumId()==albumId) {
                        album=a;
                        equal=true;
                    }
                }
                if (!equal) {
                    album=new Album(albumId,albumName,year);
                    albumList.add(album);
                }

                Song song = new Song(songId, songName,singerName, genre, duration, url,album);
                songs.put(songId,song);
            }
        } catch (NullPointerException exception) {
            System.out.println(exception);
        } catch (SQLException exception) {
            System.out.println(exception);
        }
        return songs;
    }

}
