import java.util.ArrayList;

import lab.model.dao.DAOFactory;
import lab.model.dao.UsersDAO;
import lab.model.dao.entities.User;
import lab.model.dao.entities.enums.Role;
import lab.model.service.UserService;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TestUserService {

    private UsersDAO usersDAO;
    private DAOFactory DAOfactory;

    @Before
    public void before() {
        DAOfactory = mock(DAOFactory.class);
        usersDAO = mock(UsersDAO.class);
    }

    @Test
    public void get_user_ok() {
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
        ArrayList<User> userList = new ArrayList<>();
        userList.add(testUser);
        when(usersDAO.getWhere(anyString())).thenReturn(userList);
        doNothing().when(usersDAO).close();
        when(DAOfactory.createUsersDAO()).thenReturn(usersDAO);
        User user = new UserService(DAOfactory).getUser("test_username", "test_password");
        assertNotNull(user);
        assertEquals(testUser, user);
        verify(usersDAO).getWhere(sqlArgs);
    }

    @Test
    public void registration_ok() {

    }

}

