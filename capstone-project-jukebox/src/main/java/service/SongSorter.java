package service;

import model.Song;

import java.util.*;

public class SongSorter {
    public static Map<Integer, Song> sortSongsByName(Map<Integer,Song> songs) {
        List<Song> list=new ArrayList<>();
        for (Map.Entry<Integer,Song> entry : songs.entrySet()) {
            list.add(entry.getValue());
        }
        Comparator<Song> comparatorByName= (o1, o2) -> o1.getSongName().compareTo(o2.getSongName());
        Collections.sort(list,comparatorByName);
        Map<Integer,Song> sortedSongs=new LinkedHashMap<>();
        for (Song song : list) {
            sortedSongs.put(song.getSongId(),song);
        }
        return sortedSongs;
    }

    public static Map<Integer, Song> sortSongsByAlbum(Map<Integer,Song> songs) {
        List<Song> list=new ArrayList<>();
        for (Map.Entry<Integer,Song> entry : songs.entrySet()) {
            list.add(entry.getValue());
        }
        Comparator<Song> comparatorByName= (o1, o2) -> o1.getAlbum().getName().compareTo(o2.getAlbum().getName());
        Collections.sort(list,comparatorByName);
        Map<Integer,Song> sortedSongs=new LinkedHashMap<>();
        for (Song song : list) {
            sortedSongs.put(song.getSongId(),song);
        }
        return sortedSongs;
    }

    public static Map<Integer, Song> sortSongsBySinger(Map<Integer,Song> songs) {
        List<Song> list=new ArrayList<>();
        for (Map.Entry<Integer,Song> entry : songs.entrySet()) {
            list.add(entry.getValue());
        }
        Comparator<Song> comparatorByName= (o1, o2) -> o1.getSingerName().compareTo(o2.getSingerName());
        Collections.sort(list,comparatorByName);
        Map<Integer,Song> sortedSongs=new LinkedHashMap<>();
        for (Song song : list) {
            sortedSongs.put(song.getSongId(),song);
        }
        return sortedSongs;
    }

    /*public static Map<Integer, Song> suffleSongs(Map<Integer,Song> songs) {
        List<Song> list=new ArrayList<>();
        for (Map.Entry<Integer,Song> entry : songs.entrySet()) {
            list.add(entry.getValue());
        }
        Collections.shuffle(list);
        Map<Integer,Song> sortedSongs=new LinkedHashMap<>();
        for (Song song : list) {
            sortedSongs.put(song.getSongId(),song);
        }
        return sortedSongs;
    }*/

}
