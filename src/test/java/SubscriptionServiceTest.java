import java.util.ArrayList;
import java.util.List;

import lab.model.dao.DAOFactory;
import lab.model.dao.PublicationsDAO;
import lab.model.dao.SubscriptionDAO;
import lab.model.dao.entities.Publication;
import lab.model.dao.entities.Subscription;
import lab.model.dao.entities.enums.Theme;
import lab.model.service.SubscriptionService;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import org.junit.runner.RunWith;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SubscriptionServiceTest {

    private SubscriptionDAO subscriptionDAO;
    private PublicationsDAO publicationsDAO;
    private DAOFactory DAOfactory;

    private static final Subscription TEST_SUBSCRIPTION = new Subscription.SubscriptionBuilder()
            .setPublication_id(1)
            .setUser_id(1)
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
        subscriptionDAO = mock(SubscriptionDAO.class);
        publicationsDAO = mock(PublicationsDAO.class);
    }

    @Test
    public void getClientSubscription_ok() {
        ArrayList<Publication> mockResultPublication = new ArrayList<>();
        mockResultPublication.add(TEST_PUBLICATION);
        ArrayList<Subscription> mockResultSubscription = new ArrayList<>();
        mockResultSubscription.add(TEST_SUBSCRIPTION);

        when(DAOfactory.createSubscriptionDAO()).thenReturn(subscriptionDAO);
        when(DAOfactory.createPublicationsDAO()).thenReturn(publicationsDAO);
        when(publicationsDAO.getWhere(anyString())).thenReturn(mockResultPublication);
        when(subscriptionDAO.getWhere(anyString())).thenReturn(mockResultSubscription);
        doNothing().when(publicationsDAO).close();
        doNothing().when(subscriptionDAO).close();

        List<Publication> actualPublications = new SubscriptionService(DAOfactory)
                .getClientSubscription(1);
        assertEquals(1, actualPublications.size());
        assertEquals(TEST_PUBLICATION, actualPublications.get(0));
        verify(subscriptionDAO).getWhere(anyString());
        verify(publicationsDAO).getWhere(anyString());
    }

    @Test
    public void getClientSubscription_not_ok() {
        when(DAOfactory.createSubscriptionDAO()).thenReturn(subscriptionDAO);
        when(DAOfactory.createPublicationsDAO()).thenReturn(publicationsDAO);

        when(subscriptionDAO.getWhere(anyString())).thenReturn(new ArrayList<>());
        doNothing().when(subscriptionDAO).close();

        List<Publication> actualPublications = new SubscriptionService(DAOfactory)
                .getClientSubscription(1);
        assertNull(actualPublications);
        verify(subscriptionDAO).getWhere(anyString());
        verify(publicationsDAO, never()).getWhere(anyString());
    }

    @Test
    public void getClientUnsubscribedPublications_ok() {
        ArrayList<Subscription> mockResultSubscription = new ArrayList<>();
        mockResultSubscription.add(TEST_SUBSCRIPTION);

        when(DAOfactory.createSubscriptionDAO()).thenReturn(subscriptionDAO);
        when(DAOfactory.createPublicationsDAO()).thenReturn(publicationsDAO);
        when(publicationsDAO.getWhere(anyString())).thenReturn(new ArrayList<>());
        when(subscriptionDAO.getWhere(anyString())).thenReturn(mockResultSubscription);
        doNothing().when(publicationsDAO).close();
        doNothing().when(subscriptionDAO).close();

        List<Publication> actualPublications = new SubscriptionService(DAOfactory)
                .getClientUnsubscribedPublications(1);
        assertEquals(0, actualPublications.size());
        verify(subscriptionDAO).getWhere(anyString());
        verify(publicationsDAO).getWhere(anyString());
    }

    @Test
    public void add_subscription_ok() {
        ArrayList<Publication> mockResultPublication = new ArrayList<>();
        mockResultPublication.add(TEST_PUBLICATION);
        ArrayList<Subscription> mockResultSubscription = new ArrayList<>();
        mockResultSubscription.add(TEST_SUBSCRIPTION);

        when(DAOfactory.createSubscriptionDAO()).thenReturn(subscriptionDAO);
        when(DAOfactory.createPublicationsDAO()).thenReturn(publicationsDAO);
        when(publicationsDAO.getWhere(anyString())).thenReturn(mockResultPublication);
        when(subscriptionDAO.getWhere(anyString())).thenReturn(mockResultSubscription);
        doNothing().when(publicationsDAO).close();
        doNothing().when(subscriptionDAO).close();

        new SubscriptionService(DAOfactory).add(TEST_SUBSCRIPTION);
        List<Publication> actualPublications = new SubscriptionService(DAOfactory)
                .getClientSubscription(1);
        assertEquals(1, actualPublications.size());
        assertEquals(TEST_PUBLICATION, actualPublications.get(0));
        verify(subscriptionDAO).insert(TEST_SUBSCRIPTION);
    }

    @Test
    public void delete_subscription_ok() {
        when(DAOfactory.createSubscriptionDAO()).thenReturn(subscriptionDAO);
        when(DAOfactory.createPublicationsDAO()).thenReturn(publicationsDAO);
        doNothing().when(subscriptionDAO).close();

        new SubscriptionService(DAOfactory).add(TEST_SUBSCRIPTION);
        new SubscriptionService(DAOfactory).delete(TEST_SUBSCRIPTION.getPublication_id());
        List<Publication> actualPublications = new SubscriptionService(DAOfactory)
                .getClientSubscription(1);
        assertNull(actualPublications);
        verify(subscriptionDAO).delete(TEST_SUBSCRIPTION.getPublication_id());
    }
}
