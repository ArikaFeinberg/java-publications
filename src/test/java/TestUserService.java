import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;

import lab.model.dao.DAOAbstractFactory;
import lab.model.dao.UsersDAO;
import lab.model.dao.connection.ConnectionPool;
import lab.model.dao.entities.User;
import lab.model.dao.entities.enums.Role;
import lab.model.service.UserService;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TestUserService {

    private UsersDAO usersDAO;

    @Before
    public void before() {
        usersDAO = mock(UsersDAO.class);
    }

    @Test
    public void get_user_ok() {
        //TODO reflection in order to not address production DB
        User testUser = new User.UserBuilder().
                setUser_id(0).
                setUserName("test_username")
                .setRole(Role.USER)
                .setPassword("test_password")
                .setEmail("test@email.com")
                .setBlocked(false)
                .build();
        String sqlArgs = " username  = '"
                + testUser.getUserName() + "' AND password = '"
                + testUser.getPassword() + "' ";
        when(usersDAO.getByID(0)).thenReturn(testUser);
        User user = new UserService().getUser("test_username", "test_password");
        assertNotNull(user);
        assertEquals(testUser, user);
        verify(usersDAO).getWhere(sqlArgs);
    }

    @Test
    public void registration_ok() {

    }

}

