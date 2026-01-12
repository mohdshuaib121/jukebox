package dao;

import model.Album;
import model.Song;
import service.SongSorter;

import java.sql.*;
import java.util.*;

public class SongDao {
    Connection connection;
    public SongDao(){
        connection=DbOperations.createConnection();
    }

    public Map<Integer,Song> getAllSongs() {
        List<Album> albumList=new ArrayList<>();
        Map<Integer,Song> songs = new HashMap<>();
        String query = "select * from song inner join album on song.album_id=album.album_id;";
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

    public Map<Integer,Song> getSongsBySinger(String name) {
        List<Album> albumList=new ArrayList<>();
        Map<Integer,Song> songs = new HashMap<>();
        String query = "select * from song inner join album on song.album_id=album.album_id where song.singer_name like '%"+name+"%' order by song_name;";
        try (Statement statement = connection.prepareStatement(query)) {
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
        return SongSorter.sortSongsByName(songs);
    }

    public Map<Integer,Song> getSongsByName(String name) {
        List<Album> albumList=new ArrayList<>();
        Map<Integer,Song> songs = new HashMap<>();
        String query = "select * from song inner join album on song.album_id=album.album_id where song.song_name like '%"+name+"%';";
        try (Statement statement = connection.prepareStatement(query)) {
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
        return SongSorter.sortSongsByName(songs);
    }

    public Map<Integer,Song> getSongsByGenre(String name) {
        List<Album> albumList=new ArrayList<>();
        Map<Integer,Song> songs = new HashMap<>();
        String query = "select * from song inner join album on song.album_id=album.album_id where song.genre like '%"+name+"%' order by song_name;";
        try (Statement statement = connection.prepareStatement(query)) {
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
        return SongSorter.sortSongsByName(songs);
    }

}
