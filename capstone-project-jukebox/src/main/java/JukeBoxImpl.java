import dao.PlaylistDao;
import dao.SongDao;
import dao.UserDao;
import model.Playlist;
import model.Song;
import model.User;
import service.Player;

import java.io.IOException;
import Exception.JukeBoxException;
import service.SongSorter;

import java.time.LocalDate;
import java.util.*;

public class JukeBoxImpl {

    public static void signupLoginMenu() {
        System.out.println();
        System.out.format("____________________________________________________________________________________________________\n");
        System.out.format("%-40s%-20s%40s\n","|","    JUKEBOX","|");
        System.out.format("|%99s\n","|");
        System.out.format("|%99s\n","|");
        System.out.format("|%99s\n","|");
        System.out.format("|%99s\n","|");
        System.out.format("%-40s%-20s%40s\n","|","1. Login","|");
        System.out.format("%-40s%-20s%40s\n","|","2. Signup","|");
        System.out.format("%-40s%-20s%40s\n","|","0. Exit","|");
        System.out.format("|%99s\n","|");
        System.out.format("|%99s\n","|");
        System.out.format("|%99s\n","|");
        System.out.format("|%99s\n","|");
        System.out.format("|%99s\n","|");
        System.out.format("|__________________________________________________________________________________________________|\n");
        System.out.print("enter your choice : ");

    }

    public static void homeMenu() {
        System.out.println();
        System.out.format("____________________________________________________________________________________________________\n");
        System.out.format("%-40s%-20s%40s\n","|","WELCOME TO JUKEBOX","|");
        System.out.format("|%99s\n","|");
        System.out.format("|%99s\n","|");
        System.out.format("|%99s\n","|");
        System.out.format("|%99s\n","|");
        System.out.format("%-40s%-20s%40s\n","|","1. Song Player","|");
        System.out.format("%-40s%-20s%40s\n","|","2. Playlist","|");
        System.out.format("%-40s%-20s%40s\n","|","0. Exit","|");
        System.out.format("|%99s\n","|");
        System.out.format("|%99s\n","|");
        System.out.format("|%99s\n","|");
        System.out.format("|%99s\n","|");
        System.out.format("|%99s\n","|");
        System.out.format("|__________________________________________________________________________________________________|\n");
        System.out.print("enter your choice : ");
    }

    public static void playerMenu() {
        System.out.println();
        System.out.format("____________________________________________________________________________________________________\n");
        System.out.format("%-40s%-20s%40s\n","|","JUKEBOX PLAYER","|");
        System.out.format("|%99s\n","|");
        System.out.format("|%99s\n","|");
        System.out.format("%-30s%-40s%30s\n","|","1. Play a song from the list","|");
        System.out.format("%-30s%-40s%30s\n","|","2. Search a song by name","|");
        System.out.format("%-30s%-40s%30s\n","|","3. Search a song by singer name","|");
        System.out.format("%-30s%-40s%30s\n","|","4. Search a song by genre","|");
        System.out.format("%-30s%-40s%30s\n","|","0. Exit from song player","|");
        System.out.format("|%99s\n","|");
        System.out.format("|%99s\n","|");
        System.out.format("|%99s\n","|");
        System.out.format("|%99s\n","|");
        System.out.format("|__________________________________________________________________________________________________|\n");
        System.out.print("enter your choice : ");
    }

    public static void displayPlaylist(Map<Integer,Playlist> playlists) {

        System.out.format("__________________________________________________________________________________________________________________\n");
        System.out.format("%-44s%-30s%40s\n","|","ALL SONGS","|");
        System.out.format("|%113s\n","|");
        System.out.println("|----------------------------------------------------------------------------------------------------------------|");
        System.out.printf("| %-25s%-85s |\n", "Playlist Id", "Playlist Name");
        System.out.println("|----------------------------------------------------------------------------------------------------------------|");

        for (Map.Entry<Integer,Playlist> entry : playlists.entrySet()) {
            Playlist playlist=entry.getValue();
            String str = String.format("| %-25s%-85s |", playlist.getPlaylistId(), playlist.getPlaylistName());
            System.out.println(str);
        }
        System.out.format("|%113s\n","|");
        System.out.format("|%113s\n","|");
        System.out.format("|________________________________________________________________________________________________________________|\n");
        System.out.println("1. Create new playlist\n2. Delete playlist\n3. Show playlist\n0. Exit\n\n enter your choice : ");
    }

    public static void displaySongs(Map<Integer,Song> songs) {
        System.out.println("|------------------------------------------------------------------------------------------------------------------------------------|");
        System.out.printf("| %-15s%-40s%-20s%-20s%-20s%-15s |\n", "Song Id", "Song Name", "Genre", "Artist Name", "Album Name", "Duration");
        System.out.println("|------------------------------------------------------------------------------------------------------------------------------------|");

        if (songs==null)
            return;
        for (Map.Entry<Integer,Song> entry : songs.entrySet()) {
            Song song=entry.getValue();
            String str = String.format("| %-15s%-40s%-20s%-20s%-20s%-15s |", song.getSongId(), song.getSongName(), song.getGenre(), song.getSingerName(),song.getAlbum().getName(), song.getDuration());
            System.out.println(str);

        }
        System.out.format("|%133s\n","|");
        System.out.format("|____________________________________________________________________________________________________________________________________|\n");
    }

    public static void displayAllSongs(Map<Integer,Song> songLists) {
        System.out.format("______________________________________________________________________________________________________________________________________\n");
        System.out.format("%-44s%-50s%40s\n","|","  SONGS","|");
        System.out.format("|%133s\n","|");
        displaySongs(songLists);
        System.out.println("Enter the song Id of Song that you want to play\nenter -1 to play all song\n0 for exit ");
    }




    public static void main(String[] args) throws IOException {
        try {
            User user=null;
            Player player=new Player();
            UserDao userDao=new UserDao();
            SongDao songDao=new SongDao();
            PlaylistDao playlistDao=new PlaylistDao();
            Map<Integer,Song> songList=new HashMap<>();
            Map<Integer, Playlist> playlists=new HashMap<>();
            Scanner scanner=new Scanner(System.in);
            Scanner scanner1=new Scanner(System.in);


            // user signup/login
            int choice=-1;
            do {
                signupLoginMenu();
                choice=scanner.nextInt();
                System.out.println();

                switch (choice) {
                    case 1 : // sign in
                        System.out.println("-------SIGN IN--------");
                        System.out.println("enter your user name : ");
                        String userName=scanner.next();
                        System.out.println("enter your password : ");
                        String password=scanner.next();
                        user=userDao.login(userName,password);
                        if(user!=null) {
                            choice=0;
                            break;
                        }
                        System.out.println("you don't have any account \ndo you want sign up enter 1 or 0 for exit");
                        int s=scanner.nextInt();
                        if (s==0) {
                            System.out.println("Thank you !");
                            System.exit(0);
                        }
                        if (s!=1) {
                            System.out.println("Try again!!!");
                            break;
                        }

                    case 2 : // sign up
                        System.out.println("--------SIGN UP-------");
                        System.out.print("\nenter your name : ");
                        String name= scanner1.nextLine();
                        scanner1.nextLine();
                        System.out.print("\nenter your user name : ");
                        userName=scanner.next();
                        System.out.print("\nenter your phone number : ");
                        long phoneNumber=scanner.nextLong();
                        System.out.print("\nenter your email : ");
                        String email=scanner.next();
                        System.out.print("\nenter your password : ");
                        password=scanner.next();
                        String date=String.valueOf(LocalDate.now());
                        if(userDao.isUserPresent(userName,phoneNumber)) {
                            System.out.println("\nyou have already an account Please sigin");
                        } else if (userDao.isUserNamePresent(userName)) {
                            System.out.println("try with another user name!!");
                        } else {
                            user=new User();
                            user.setName(name);
                            user.setUserName(userName);
                            user.setPhoneNumber(phoneNumber);
                            user.setEmail(email);
                            user.setPassword(password);
                            user.setDate(date);
                            user=userDao.createUser(user);
                            if (user!=null) {
                                System.out.println("Account created successfully!");
                                choice=0;
                            }
                            else {
                                System.out.println("Something wrong please try again!!!");
                            }
                        }

                        break;

                    case 0 :
                        System.out.println("Thankyou");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice!  Try again");
                }
            }while (choice!=0);


            // inside jukebox
            choice=-1;
            do {
                homeMenu();
                choice=scanner.nextInt();
                System.out.println();
                switch (choice) {
                    case 1 : // song player
                        int choice2=-1;
                        do {
                            playerMenu();
                            choice2=scanner.nextInt();
                            System.out.println();

                            switch (choice2) {
                                case 1 : // all songs
                                    songList=songDao.getAllSongs();
                                    do{
                                        displayAllSongs(songList);
                                        System.out.println("-2 for alphabetical order by song name :\n" +
                                                "-3 for alphabetical order by singer name :\n" +
                                                "-4 for alphabetical order ny album name : ");
                                        int ch3=scanner.nextInt();
                                        if (ch3==0)
                                            break;
                                        if(ch3==-1) {
                                            player.songPlayer(songList);
                                        }
                                        if (ch3==-2){
                                            songList= SongSorter.sortSongsByName(songList);
                                        }
                                        if (ch3==-3){
                                            songList= SongSorter.sortSongsBySinger(songList);
                                        }
                                        if (ch3==-4){
                                            songList= SongSorter.sortSongsByAlbum(songList);
                                        }
                                        try {
                                            if (songList.get(ch3)==null)
                                                throw new JukeBoxException("You enter wrong song id");
                                            player.songPlayer(songList.get(ch3));
                                        }catch (JukeBoxException e) {
                                            System.out.println(e);
                                        }

                                    }while (true);


                                    break;

                                case 2 : // song by name
                                    System.out.println("enter song name");
                                    String songName=scanner1.nextLine();
                                    scanner1.nextLine();
                                    songList=songDao.getSongsByName(songName);
                                    do{
                                        displayAllSongs(songList);
                                        int ch3=scanner.nextInt();
                                        if (ch3==0)
                                            break;
                                        if(ch3==-1) {
                                            player.songPlayer(songList);
                                        }
                                        try {
                                            if (songList.get(ch3)==null)
                                                throw new JukeBoxException("You enter wrong song id");
                                            player.songPlayer(songList.get(ch3));
                                        }catch (JukeBoxException e) {
                                            System.out.println(e);
                                        }
                                    }while (true);

                                    break;

                                case 3 : // song by singer
                                    System.out.println("enter singer name");
                                    String singerName=scanner1.nextLine();
                                    scanner1.nextLine();
                                    songList=songDao.getSongsBySinger(singerName);
                                    do{
                                        displayAllSongs(songList);
                                        int ch3=scanner.nextInt();
                                        if (ch3==0)
                                            break;
                                        if(ch3==-1) {
                                            player.songPlayer(songList);
                                        }
                                        try {
                                            if (songList.get(ch3)==null)
                                                throw new JukeBoxException("You enter wrong song id");
                                            player.songPlayer(songList.get(ch3));
                                        }catch (JukeBoxException e) {
                                            System.out.println(e);
                                        }
                                    }while (true);

                                    break;

                                case 4 : // song by genre
                                    System.out.println("enter genre");
                                    String genre=scanner1.nextLine();
                                    scanner1.nextLine();
                                    songList=songDao.getSongsByGenre(genre);
                                    do{
                                        displayAllSongs(songList);
                                        int ch3=scanner.nextInt();
                                        if (ch3==0)
                                            break;
                                        if(ch3==-1) {
                                            player.songPlayer(songList);
                                        }
                                        try {
                                            if (songList.get(ch3)==null)
                                                throw new JukeBoxException("You enter wrong song id");
                                            player.songPlayer(songList.get(ch3));
                                        }catch (JukeBoxException e) {
                                            System.out.println(e);
                                        }
                                    }while (true);

                                    break;

                                case 0 : // for back
                                    choice2=0;
                                    break;

                                default:
                                    System.out.println("Invalid choice try again ");
                            }
                        }while (choice2!=0);
                        break;

                    case 2 : // playlists
                        choice2=-1;
                        playlists=playlistDao.getAllPlaylist(user);
                        do {
                            displayPlaylist(playlists);
                            choice2=scanner.nextInt();

                            switch (choice2) {
                                case 1 : // create new playlist
                                    System.out.println("enter playlist name : ");
                                    String playlistName=scanner1.nextLine();
                                    scanner1.nextLine();
                                    Playlist playlist=playlistDao.createPlaylist(playlistName,user);
                                    playlists.put(playlist.getPlaylistId(),playlist);
                                    System.out.println("Playlist created !");
                                    break;

                                case 2 : // delete playlist
                                    System.out.println("enter playlist id : ");
                                    int playlistId=scanner.nextInt();
                                    if (playlistDao.deletePlaylist(playlistId)) {
                                        playlists.remove(playlistId);
                                        System.out.println("Playlist deleted !");
                                    }
                                    break;

                                case 3 : // show play list
                                    System.out.println("enter playlist id : ");
                                    playlistId=scanner.nextInt();
                                    int ch4=-1;
                                    do{
                                        displaySongs(playlists.get(playlistId).getSongs());
                                        System.out.println("1. add song\n2. delete song\n3. play song\n0. exit");
                                        ch4=scanner.nextInt();
                                        switch (ch4) {
                                            case 1 : // add song
                                                songList=songDao.getAllSongs();
                                                displaySongs(songList);
                                                System.out.println("enter song id to add : ");
                                                int songId=scanner.nextInt();
                                                playlistDao.addSongToPlaylist(playlistId,songId);
                                                playlists.get(playlistId).getSongs().put(songId,songList.get(songId));
                                                break;

                                            case 2 : // delete song
                                                System.out.println("enter song id to delete : ");
                                                songId=scanner.nextInt();
                                                playlistDao.deleteSongFromPlaylist(playlistId,songId);
                                                playlists.get(playlistId).getSongs().remove(songId);
                                                break;

                                            case 3 : // play song
                                                do{
                                                    displayAllSongs(playlists.get(playlistId).getSongs());
                                                    int ch3=scanner.nextInt();
                                                    if (ch3==0)
                                                        break;
                                                    if(ch3==-1) {
                                                        player.songPlayer(playlists.get(playlistId).getSongs());
                                                    }
                                                    try {
                                                        if (playlists.get(playlistId).getSongs().get(ch3)==null)
                                                            throw new JukeBoxException("You enter wrong song id");
                                                        player.songPlayer(playlists.get(playlistId).getSongs().get(ch3));
                                                    }catch (JukeBoxException e) {
                                                        System.out.println(e);
                                                    }
                                                }while (true);
                                                break;

                                            case 0 :
                                                break;

                                            default:
                                                System.out.println("invalid choice!");
                                        }
                                    }while (ch4!=0);
                                    break;

                                case 0 : // exit
                                    break;

                                default:
                                    System.out.println("Invalid choice!");
                            }
                        }while (choice2!=0);

                        break;

                    case 0 :
                        break;

                    default:
                        System.out.println("Invalid Choice!");
                }
            }while (choice!=0);

        }catch (Exception e) {
            e.printStackTrace();
        }

    }


}
