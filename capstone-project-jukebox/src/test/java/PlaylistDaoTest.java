import dao.PlaylistDao;
import model.Playlist;
import model.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

public class PlaylistDaoTest {
    PlaylistDao playlistDao;

    @Before
    public void setUp() throws Exception {
        playlistDao =new PlaylistDao();
    }

    @After
    public void tearDown() throws Exception {
        playlistDao =null;
    }

    @Test
    public void createPlaylist() throws SQLException {
        Playlist actual= playlistDao.createPlaylist("sad",new User(1,"bhaijaan","faizan123","faizan",1234567890L,"faizan@gmail.com","2023-01-04"));
        Assert.assertNotNull("mismatch",actual);
    }

    @Test
    public void createPlaylistFailure() throws SQLException {
        Playlist actual= playlistDao.createPlaylist("sad",new User(1,"bhaijaan","faizan123","faizan",1234567890L,"faizan@gmail.com","2023-01-04"));
        Assert.assertNotEquals("mismatch",null,actual);
    }

    @Test
    public void addSongToPlaylist(){
        try {
            boolean actual= playlistDao.addSongToPlaylist(2,1);
            Assert.assertNotNull(actual);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void addSongToPlaylistFailure(){
        try {
            boolean actual= playlistDao.addSongToPlaylist(2,1);
            Assert.assertNotEquals(null,actual);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void deleteSongFromPlaylist() throws SQLException {
        try {
            boolean actual= playlistDao.deleteSongFromPlaylist(2,1);
            Assert.assertNotNull(actual);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void deleteSongFromPlaylistFailure() {
        try {
            boolean actual= playlistDao.deleteSongFromPlaylist(2,1);
            Assert.assertNotEquals(null,actual);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void deletePlaylist() throws SQLException {
        try {
            boolean actual= playlistDao.deletePlaylist(2);
            Assert.assertNotNull(actual);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void deletePlaylistFailure() {
        try {
            boolean actual= playlistDao.deletePlaylist(1);
            Assert.assertNotEquals(null,actual);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
