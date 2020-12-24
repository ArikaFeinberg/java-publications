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
public class UserServiceTest {

    private UsersDAO usersDAO;
    private DAOFactory DAOfactory;

    private static final User TEST_USER_ONE = new User.UserBuilder()
            .setUser_id(0)
            .setUserName("test_username")
            .setRole(Role.USER)
            .setPassword("test_password")
            .setEmail("test@email.com")
            .setBlocked(false)
            .build();

    private static final User TEST_USER_TWO = new User.UserBuilder()
            .setUser_id(1)
            .setUserName("test_username1")
            .setRole(Role.USER)
            .setPassword("test_password1")
            .setEmail("test1@email.com")
            .setBlocked(false)
            .build();

    @Before
    public void before() {
        DAOfactory = mock(DAOFactory.class);
        usersDAO = mock(UsersDAO.class);
    }

    @Test
    public void getWhere_ok() {
        ArrayList<User> mockResult = new ArrayList<>();
        mockResult.add(TEST_USER_ONE);
        when(DAOfactory.createUsersDAO()).thenReturn(usersDAO);
        when(usersDAO.getWhere(anyString())).thenReturn(mockResult);
        doNothing().when(usersDAO).close();
        User actualUser = new UserService(DAOfactory).getUser(
                TEST_USER_ONE.getUserName(), TEST_USER_ONE.getPassword());
        assertNotNull(actualUser);
        assertEquals(TEST_USER_ONE, actualUser);
        verify(usersDAO).getWhere(anyString());
    }

    @Test
    public void registration_ok() {
        ArrayList<User> mockResult = new ArrayList<>();
        mockResult.add(TEST_USER_ONE);
        when(usersDAO.getWhere(anyString())).thenReturn(mockResult);
        when(DAOfactory.createUsersDAO()).thenReturn(usersDAO);
        doNothing().when(usersDAO).close();
        new UserService(DAOfactory).registration(TEST_USER_ONE);
        User actualUser = new UserService(DAOfactory).getUser(
                TEST_USER_ONE.getUserName(), TEST_USER_TWO.getPassword());
        assertNotNull(actualUser);
        assertEquals(TEST_USER_ONE, actualUser);
        verify(usersDAO).insert(TEST_USER_ONE);
    }

    @Test
    public void getAll_ok() {
        ArrayList<User> mockResult = new ArrayList<>();
        mockResult.add(TEST_USER_ONE);
        mockResult.add(TEST_USER_TWO);
        when(usersDAO.getAll()).thenReturn(mockResult);
        when(DAOfactory.createUsersDAO()).thenReturn(usersDAO);
        doNothing().when(usersDAO).close();
        ArrayList<User> actualResult = new UserService(DAOfactory).getAll();
        assertNotNull(actualResult);
        assertEquals(mockResult, actualResult);
        verify(usersDAO).getAll();
    }

    @Test
    public void block_ok() {
        ArrayList<User> mockResult = new ArrayList<>();
        mockResult.add(TEST_USER_ONE);
        when(usersDAO.getWhere(anyString())).thenReturn(mockResult);
        when(usersDAO.getByID(TEST_USER_ONE.getUser_id())).thenReturn(TEST_USER_ONE);
        when(DAOfactory.createUsersDAO()).thenReturn(usersDAO);
        doNothing().when(usersDAO).close();
        new UserService(DAOfactory).registration(TEST_USER_ONE);
        new UserService(DAOfactory).block(TEST_USER_ONE.getUser_id());
        User actualResult = new UserService(DAOfactory).getUser(
                TEST_USER_ONE.getUserName(), TEST_USER_ONE.getPassword());
        assertNotNull(actualResult);
        assertTrue(actualResult.getBlocked());
        verify(usersDAO).update(TEST_USER_ONE);
    }

    @Test
    public void unblock_ok() {
        ArrayList<User> mockResult = new ArrayList<>();
        mockResult.add(TEST_USER_ONE);
        when(usersDAO.getWhere(anyString())).thenReturn(mockResult);
        when(usersDAO.getByID(TEST_USER_ONE.getUser_id())).thenReturn(TEST_USER_ONE);
        when(DAOfactory.createUsersDAO()).thenReturn(usersDAO);
        doNothing().when(usersDAO).close();
        new UserService(DAOfactory).unblock(TEST_USER_ONE.getUser_id());
        User actualResult = new UserService(DAOfactory).getUser(
                TEST_USER_ONE.getUserName(), TEST_USER_ONE.getPassword());
        assertNotNull(actualResult);
        assertFalse(actualResult.getBlocked());
        verify(usersDAO).update(TEST_USER_ONE);
    }
}
