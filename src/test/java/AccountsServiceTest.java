import java.util.ArrayList;

import lab.controller.validator.exeptions.ValidationException;
import lab.model.dao.AccountsDAO;
import lab.model.dao.DAOFactory;
import lab.model.dao.PublicationsDAO;
import lab.model.dao.entities.Account;
import lab.model.dao.entities.Publication;
import lab.model.dao.entities.enums.Theme;
import lab.model.service.AccountsService;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import org.junit.runner.RunWith;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AccountsServiceTest {

    private AccountsDAO accountsDAO;
    private PublicationsDAO publicationsDAO;
    private DAOFactory DAOfactory;

    private static final Account TEST_ACCOUNT = new Account.AccountBuilder()
            .setUser_id(1)
            .setScore(0)
            .build();

    public static final Publication TEST_PUBLICATION = new Publication.PublicationBuilder()
            .setPublication_id(1)
            .setTheme(Theme.NEWS)
            .setName("test")
            .setPrice(45)
            .build();

    @Before
    public void before() {
        DAOfactory = mock(DAOFactory.class);
        accountsDAO = mock(AccountsDAO.class);
        publicationsDAO = mock(PublicationsDAO.class);
    }

    @Test
    public void getUsersAccount_ok() {
        ArrayList<Account> mockResult = new ArrayList<>();
        mockResult.add(TEST_ACCOUNT);
        when(DAOfactory.createAccountsDAO()).thenReturn(accountsDAO);
        when(accountsDAO.getWhere(anyString())).thenReturn(mockResult);
        doNothing().when(accountsDAO).close();
        Account actualAccount = new AccountsService(DAOfactory)
                .getUsersAccount(TEST_ACCOUNT.getUser_id());
        assertNotNull(actualAccount);
        assertEquals(TEST_ACCOUNT, actualAccount);
        verify(accountsDAO).getWhere(anyString());
    }

    @Test
    public void replenish_ok() {
        ArrayList<Account> mockResult = new ArrayList<>();
        mockResult.add(TEST_ACCOUNT);
        when(DAOfactory.createAccountsDAO()).thenReturn(accountsDAO);
        when(accountsDAO.getWhere(anyString())).thenReturn(mockResult);
        doNothing().when(accountsDAO).close();
        new AccountsService(DAOfactory).replenish(TEST_ACCOUNT, 25.00);
        Account actualAccount = new AccountsService(DAOfactory)
                .getUsersAccount(TEST_ACCOUNT.getUser_id());
        assertNotNull(actualAccount);
        assertEquals(25, TEST_ACCOUNT.getScore(), 0.00);
        verify(accountsDAO).update(TEST_ACCOUNT);
    }

    @Test
    public void makePayment_ok() {
        ArrayList<Account> mockResult = new ArrayList<>();
        mockResult.add(TEST_ACCOUNT);
        when(DAOfactory.createAccountsDAO()).thenReturn(accountsDAO);
        when(DAOfactory.createPublicationsDAO()).thenReturn(publicationsDAO);
        when(publicationsDAO.getByID(TEST_PUBLICATION.getPublication_id()))
                .thenReturn(TEST_PUBLICATION);
        when(accountsDAO.getWhere(anyString())).thenReturn(mockResult);
        doNothing().when(accountsDAO).close();
        doNothing().when(publicationsDAO).close();
        new AccountsService(DAOfactory).replenish(TEST_ACCOUNT, 40.00);
        new AccountsService(DAOfactory).makePayment(
                TEST_ACCOUNT, TEST_PUBLICATION.getPublication_id());
        Account actualAccount = new AccountsService(DAOfactory)
                .getUsersAccount(TEST_ACCOUNT.getUser_id());
        assertNotNull(actualAccount);
        assertEquals(20, TEST_ACCOUNT.getScore(), 0.00);
        verify(accountsDAO, times(2)).update(TEST_ACCOUNT);
    }

    @Test(expected = ValidationException.class)
    public void makePayment_not_ok() {
        when(DAOfactory.createAccountsDAO()).thenReturn(accountsDAO);
        when(DAOfactory.createPublicationsDAO()).thenReturn(publicationsDAO);
        when(publicationsDAO.getByID(TEST_PUBLICATION.getPublication_id()))
                .thenReturn(TEST_PUBLICATION);
        doNothing().when(publicationsDAO).close();
        new AccountsService(DAOfactory).makePayment(
                TEST_ACCOUNT, TEST_PUBLICATION.getPublication_id());
    }

    @Test
    public void createAccount_ok() {
        ArrayList<Account> mockResult = new ArrayList<>();
        mockResult.add(TEST_ACCOUNT);
        when(DAOfactory.createAccountsDAO()).thenReturn(accountsDAO);
        when(accountsDAO.getWhere(anyString())).thenReturn(mockResult);
        doNothing().when(accountsDAO).close();
        new AccountsService(DAOfactory).createAccount(1);
        Account actualAccount = new AccountsService(DAOfactory)
                .getUsersAccount(TEST_ACCOUNT.getUser_id());
        assertNotNull(actualAccount);
        assertEquals(TEST_ACCOUNT, actualAccount);
        verify(accountsDAO).insert(any(Account.class));
    }
}
