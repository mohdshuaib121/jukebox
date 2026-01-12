import dao.SongDao;
import model.Song;
import org.junit.*;

import java.sql.SQLException;
import java.util.Map;

public class SongDaoTest {
    static SongDao songDao;

    @Before
    public void setUp(){
        songDao=new SongDao();
    }

    @AfterClass
    public static void tearDown(){
        songDao=null;
    }

    @Test
    public void getSongs() {

        Map<Integer, Song> actual=songDao.getAllSongs();
        Assert.assertNotNull("obtained result is null",actual);
    }
    @Test
    public void getSongsFailure() {

        Map<Integer, Song> actual=songDao.getAllSongs();

        Assert.assertNotEquals("value mismatch", null,actual);
    }


    @Test
    public void getSongByArtistName() {
        Map<Integer, Song> actual=songDao.getSongsBySinger("Arijit Singh");
        Assert.assertNotNull("value mismatch",actual);
    }

    @Test
    public void getSongByArtistNameFailure(){
        Map<Integer, Song> actual=songDao.getSongsBySinger("Arijit Singh");
        Assert.assertNotEquals("value mismatch",null,actual);
    }

    @Test
    public void getSongByGenreName(){
        Map<Integer, Song> actual=songDao.getSongsByGenre("sad");
        Assert.assertNotNull("value mismatch",actual);
    }

    @Test
    public void getSongByGenreNameFailure(){
        Map<Integer, Song> actual=songDao.getSongsByGenre("pop");
        Assert.assertNotEquals("value mismatch",null,actual);
    }

    @Test
    public void getSongByName(){
        Map<Integer, Song> actual=songDao.getSongsByName("nagin");
        Assert.assertNotNull("value mismatch",actual);
    }

    @Test
    public void getSongByNameFailure(){
        Map<Integer, Song> actual=songDao.getSongsByName("Ek Villian");
        Assert.assertNotEquals("value mismatch",null,actual);
    }
}
