package service;

import model.Song;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import Exception.JukeBoxException;
import java.util.*;

public class Player {
    String filePath;
    File songFile;
    AudioInputStream audioInputStream;
    Clip clip;
    long currentFrame=0L;
    String status;
    int i=0;


    //method to play songs available  in `jukebox`.`songs` database
    public void play(String filePath) {

        this.filePath=filePath;
        songFile = new File(filePath);

        try {
            audioInputStream = AudioSystem.getAudioInputStream(songFile);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
//            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.setMicrosecondPosition(currentFrame);
            clip.start();
            status="playing";

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException exception) {
            exception.printStackTrace();
        }
    }

    public void pause() {
        if (status.equalsIgnoreCase("paused")) {
            System.out.println("audio is already paused");
            return;
        }
        this.currentFrame=clip.getMicrosecondPosition();
        clip.stop();
        status="paused";
    }

    public void resume() {
        if (status.equalsIgnoreCase("playing")) {
            System.out.println("audio is already being played");
            return;
        }
        clip.close();
        this.play(filePath);
    }

    public void skip(int second) {
        clip.stop();
        clip.close();
        currentFrame+=second*1000000;
        this.play(filePath);
    }

    public void restart() {
        clip.stop();
        clip.close();
        currentFrame=0L;
        this.play(filePath);
    }

    public void stop() {
        currentFrame=0L;
        clip.stop();
        clip.close();
    }

    /*public void songPlayer(Song song) {
        if (song==null)
            return;
        Scanner scanner=new Scanner(System.in);
        play(song.getUrl());
        int choice=-1;
        do {
            String durationMin=""+(int)(clip.getMicrosecondLength())/60000000;
            String durationSec=""+((int)(clip.getMicrosecondLength())%1000000)%60;
            if (durationSec.length()>2)
                durationSec.substring(0,2);
            String durationTime=durationMin+":"+durationSec;
            String remainingMin=""+(int)(clip.getMicrosecondLength()-clip.getMicrosecondPosition())/60000000;
            String remainingSec=""+((int)(clip.getMicrosecondLength()-clip.getMicrosecondPosition())/1000000)%60;
            if (remainingSec.length()>2)
                remainingSec.substring(0,2);
            String remainigTime=remainingMin+":"+remainingSec;
            System.out.format("__________________________________________________________________________________________________________________\n");
            System.out.format("%-4s%-100s%10s\n","|",song.getSongName(),"|");
            System.out.format("%-4s%-100s%10s\n","|","Singer : "+song.getSingerName(),"|");
            System.out.format("%-4s%-100s%10s\n","|","Genre  : "+song.getGenre(),"|");
            System.out.format("%-4s%-100s%10s\n","|","Album  : "+song.getAlbum().getName(),"|");
            System.out.format("%-4s%-100s%10s\n","|","Genre  : "+song.getGenre(),"|");
            System.out.format("%-4s%-100s%10s\n","|","Duration  : "+durationTime,"|");
            System.out.format("%-4s%-100s%10s\n","|","Remaining : "+remainigTime,"|");
            System.out.format("|%113s\n","|");
            System.out.format("%-4s%-100s%10s\n","|","Status  : "+status,"|");
            System.out.format("|%113s\n","|");
            System.out.format("|________________________________________________________________________________________________________________|\n");
            System.out.println("\n1. pause/play\n2. Restart\n3. Skip 10 second\n4. Stop\n");
            choice=scanner.nextInt();
            switch (choice) {
                case 1 :
                    if (status.equalsIgnoreCase("playing")) {
                        pause();
                    } else {
                        resume();
                    }
                    break;

                case 2 :
                    restart();
                    break;

                case 3 :
                    skip(10);
                    break;

                case 4 :
                    stop();
                    break;

                default:
            }
        }while (choice!=3);
    }*/

   /* public void songPlayer(List<Song> songList) {
        if (songList==null){
            return;
        }
        Scanner scanner=new Scanner(System.in);
        int choice=-1;
        for (int i=0;i<songList.size();i++) {

           Song song=songList.get(i);
            if (song==null)
                return;

            play(song.getUrl());
            choice=-1;
            do {
                String durationMin=""+(int)(clip.getMicrosecondLength())/60000000;
                String durationSec=""+((int)(clip.getMicrosecondLength())%1000000)%60;
                if (durationSec.length()>2)
                    durationSec.substring(0,2);
                String durationTime=durationMin+":"+durationSec;
                String remainingMin=""+(int)(clip.getMicrosecondLength()-clip.getMicrosecondPosition())/60000000;
                String remainingSec=""+((int)(clip.getMicrosecondLength()-clip.getMicrosecondPosition())/1000000)%60;
                if (remainingSec.length()>2)
                    remainingSec.substring(0,2);
                String remainigTime=remainingMin+":"+remainingSec;

                System.out.format("__________________________________________________________________________________________________________________\n");
                System.out.format("%-4s%-100s%10s\n","|",song.getSongName(),"|");
                System.out.format("%-4s%-100s%10s\n","|","Singer : "+song.getSingerName(),"|");
                System.out.format("%-4s%-100s%10s\n","|","Genre  : "+song.getGenre(),"|");
                System.out.format("%-4s%-100s%10s\n","|","Album  : "+song.getAlbum().getName(),"|");
                System.out.format("%-4s%-100s%10s\n","|","Genre  : "+song.getGenre(),"|");
                System.out.format("%-4s%-100s%10s\n","|","Duration  : "+durationTime,"|");
                System.out.format("%-4s%-100s%10s\n","|","Remaining : "+remainigTime,"|");
                System.out.format("|%113s\n","|");
                System.out.format("%-4s%-100s%10s\n","|","Status  : "+status,"|");
                System.out.format("|%113s\n","|");
                System.out.format("|________________________________________________________________________________________________________________|\n");
                if (i+1 == songList.size())
                    System.out.format("%-4s%-100s%10s\n","|","Complete!!!","|");
                else
                    System.out.format("%-4s%-100s%10s\n","|","Next Song : "+songList.get(i+1).getSongName()+",  Duration : "+songList.get(i+1).getDuration(),"|");
                System.out.format("|________________________________________________________________________________________________________________|\n");

                System.out.println("\n1. pause/play\n2. Restart\n3. Skip 10 second\n4. Next\n0. Exit");


                choice=scanner.nextInt();
                switch (choice) {
                    case 1 :
                        if (status.equalsIgnoreCase("playing")) {
                            pause();
                        } else {
                            resume();
                        }
                        break;

                    case 2 :
                        restart();
                        break;

                    case 3 :
                        skip(10);
                        break;

                    case 4 :
                        stop();
                        break;

                    case 0 :
                        stop();
                        break;

                    default:
                }
            }while (choice!=4 && choice!=0);
            if (choice==0)
                break;
        }
    }*/

    public void songPlayer(Map<Integer,Song> songList) {
        List<Song> songs=new ArrayList<>();
        for (Map.Entry<Integer,Song> entry : songList.entrySet()) {
            songs.add(entry.getValue());
        }
        songPlayer(songs);
    }

    public void songPlayer(Song song) {
        if (song==null)
            return;
        Scanner scanner=new Scanner(System.in);
        play(song.getUrl());
        int choice=-1;
        do {
            TimerTask task=new TimerTask() {
                @Override
                public void run() {
                    String durationMin=""+(int)(clip.getMicrosecondLength())/60000000;
                    String durationSec=""+((int)(clip.getMicrosecondLength())%1000000)%60;
                    if (durationSec.length()>2)
                        durationSec.substring(0,2);
                    String durationTime=durationMin+":"+durationSec;
                    String remainingMin=""+(int)(clip.getMicrosecondLength()-clip.getMicrosecondPosition())/60000000;
                    String remainingSec=""+((int)(clip.getMicrosecondLength()-clip.getMicrosecondPosition())/1000000)%60;
                    if (remainingSec.length()>2)
                        remainingSec.substring(0,2);
                    String remainigTime=remainingMin+":"+remainingSec;

                    System.out.format("__________________________________________________________________________________________________________________\n");
                    System.out.format("%-4s%-100s%10s\n","|",song.getSongName(),"|");
                    System.out.format("%-4s%-100s%10s\n","|","Singer : "+song.getSingerName(),"|");
                    System.out.format("%-4s%-100s%10s\n","|","Genre  : "+song.getGenre(),"|");
                    System.out.format("%-4s%-100s%10s\n","|","Album  : "+song.getAlbum().getName(),"|");
                    System.out.format("%-4s%-100s%10s\n","|","Genre  : "+song.getGenre(),"|");
                    System.out.format("%-4s%-100s%10s\n","|","Duration  : "+durationTime,"|");
                    System.out.format("%-4s%-100s%10s\n","|","Remaining : "+remainigTime,"|");
                    System.out.format("|%113s\n","|");
//                    System.out.format("%-4s%-100s%10s\n","|","Status  : "+status,"|");
                    System.out.format("|%113s\n","|");
                    System.out.format("|________________________________________________________________________________________________________________|\n");
                    System.out.println("\n1. pause/play\n2. Restart\n3. Skip 10 second\n4. Stop\n");

                    if (!clip.isRunning()){
                        status="end";
                        this.cancel();
                    }
                }

            };

            Timer timer=null;
            if (status.equalsIgnoreCase("playing")) {
                timer=new Timer();
                timer.scheduleAtFixedRate(task,0,5000);
            } else {
                try{
                    timer.cancel();
                }catch (Exception e) {
                }
            }


            choice=scanner.nextInt();
            switch (choice) {
                case 1 :
                    if (status.equalsIgnoreCase("playing")) {

                        try{
                            timer.cancel();
                        }catch (Exception e) {}
                        pause();
                    } else {
                        resume();
                    }
                    break;

                case 2 :
                    restart();
                    break;

                case 3 :
                    try{
                        timer.cancel();
                    }catch (Exception e) {}
                    skip(10);
                    break;
                case 4 :
                    try{
                        timer.cancel();
                    }catch (Exception e) {}
                    stop();
                    break;

                default:
            }
        }while (choice!=4);
    }

    public void songPlayer(List<Song> songList) {
        if (songList==null){
            return;
        }
        Scanner scanner=new Scanner(System.in);
        int choice=-1;
        for (i=0;i<songList.size();i++) {

            Song song=songList.get(i);
            if (song==null)
                return;

            play(song.getUrl());
            choice=-1;
            do {
                TimerTask task=new TimerTask() {
                    @Override
                    public void run() {
                        String durationMin=""+(int)(clip.getMicrosecondLength())/60000000;
                        String durationSec=""+((int)(clip.getMicrosecondLength())%1000000)%60;
                        if (durationSec.length()>2)
                            durationSec.substring(0,2);
                        String durationTime=durationMin+":"+durationSec;
                        String remainingMin=""+(int)(clip.getMicrosecondLength()-clip.getMicrosecondPosition())/60000000;
                        String remainingSec=""+((int)(clip.getMicrosecondLength()-clip.getMicrosecondPosition())/1000000)%60;
                        if (remainingSec.length()>2)
                            remainingSec.substring(0,2);
                        String remainigTime=remainingMin+":"+remainingSec;

                        System.out.format("__________________________________________________________________________________________________________________\n");
                        System.out.format("%-4s%-100s%10s\n","|",song.getSongName(),"|");
                        System.out.format("%-4s%-100s%10s\n","|","Singer : "+song.getSingerName(),"|");
                        System.out.format("%-4s%-100s%10s\n","|","Genre  : "+song.getGenre(),"|");
                        System.out.format("%-4s%-100s%10s\n","|","Album  : "+song.getAlbum().getName(),"|");
                        System.out.format("%-4s%-100s%10s\n","|","Genre  : "+song.getGenre(),"|");
                        System.out.format("%-4s%-100s%10s\n","|","Duration  : "+durationTime,"|");
                        System.out.format("%-4s%-100s%10s\n","|","Remaining : "+remainigTime,"|");
                        System.out.format("|%113s\n","|");
//                    System.out.format("%-4s%-100s%10s\n","|","Status  : "+status,"|");
                        System.out.format("|%113s\n","|");
                        System.out.format("|________________________________________________________________________________________________________________|\n");
                        if (i+1 == songList.size())
                            System.out.format("%-4s%-100s%10s\n","|","Complete!!!","|");
                        else
                            System.out.format("%-4s%-100s%10s\n","|","Next Song : "+songList.get(i+1).getSongName()+",  Duration : "+songList.get(i+1).getDuration(),"|");
                        System.out.format("|________________________________________________________________________________________________________________|\n");
                        System.out.println("\n1. pause/play\n2. Restart\n3. Skip 10 second\n4. Next\n5. Shuffle\n0. Exit");

                        if (!clip.isRunning()){
                            status="end";
                            this.cancel();
                        }
                    }

                };

                Timer timer=null;
                if (status.equalsIgnoreCase("playing")) {
                    timer=new Timer();
                    timer.scheduleAtFixedRate(task,0,5000);
                } else {
                    try{
                        timer.cancel();
                    }catch (Exception e) {
                    }
                }


                choice=scanner.nextInt();
                switch (choice) {
                    case 1 :
                        if (status.equalsIgnoreCase("playing")) {

                            try{
                                timer.cancel();
                            }catch (Exception e) {}
                            pause();
                        } else {
                            resume();
                        }
                        break;

                    case 2 :
                        restart();
                        break;

                    case 3 :
                        try{
                            timer.cancel();
                        }catch (Exception e) {}
                        skip(10);
                        break;

                    case 4 :
                        try{
                            timer.cancel();
                        }catch (Exception e) {}
                        stop();
                        break;

                    case 5 :
                        Collections.shuffle(songList);
                        break;

                    case 0 :
                        try{
                            timer.cancel();
                        }catch (Exception e) {}
                        stop();
                        break;

                    default:
                }
            }while (choice!=4 && choice!=0);
            if (choice==0)
                break;
        }
    }

}
