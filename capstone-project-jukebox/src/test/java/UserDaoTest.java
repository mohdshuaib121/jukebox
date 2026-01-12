import dao.PlaylistDao;
import dao.UserDao;
import model.Playlist;
import model.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

public class UserDaoTest {
    UserDao userDao;

    @Before
    public void setUp() throws Exception {
        userDao=new UserDao();
    }

    @After
    public void tearDown() throws Exception {
        userDao =null;
    }

    @Test
    public void login() throws SQLException {
        User actual=userDao.login("bhaijaan","faizan123");
        Assert.assertNotNull("mismatch",actual);
    }

    @Test
    public void createPlaylistFailure() throws SQLException {
        User actual=userDao.login("amir","amir123");
        Assert.assertEquals("mismatch",null,actual);
    }

    @Test
    public void isUserPresent() throws SQLException {
        boolean actual=userDao.isUserPresent("bhaijaan",1234567890);
        Assert.assertEquals("mismatch",true,actual);
    }

    @Test
    public void isUserPresentFailure() throws SQLException {
        boolean actual=userDao.isUserPresent("amir",1234567890);
        Assert.assertEquals("mismatch",false,actual);
    }
}
