import java.util.ArrayList;
import java.util.List;

import lab.model.dao.DAOFactory;
import lab.model.dao.PublicationsDAO;
import lab.model.dao.entities.Publication;
import lab.model.dao.entities.enums.Theme;
import lab.model.service.PublicationService;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import org.junit.runner.RunWith;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PublicationServiceTest {

    private PublicationsDAO publicationsDAO;
    private DAOFactory DAOfactory;

    public static final Publication TEST_PUBLICATION = new Publication.PublicationBuilder()
            .setPublication_id(1)
            .setTheme(Theme.NEWS)
            .setName("test_news")
            .setPrice(45)
            .build();

    public static final Publication ANOTHER_TEST_PUBLICATION = new Publication.PublicationBuilder()
            .setPublication_id(2)
            .setTheme(Theme.SPORT)
            .setName("test_sport")
            .setPrice(65)
            .build();

    @Before
    public void before() {
        DAOfactory = mock(DAOFactory.class);
        publicationsDAO = mock(PublicationsDAO.class);
    }

    @Test
    public void getById_ok() {
        when(DAOfactory.createPublicationsDAO()).thenReturn(publicationsDAO);
        when(publicationsDAO.getByID(TEST_PUBLICATION.getPublication_id()))
                .thenReturn(TEST_PUBLICATION);
        doNothing().when(publicationsDAO).close();

        Publication actualPublication = new PublicationService(DAOfactory)
                .getById(1);
        assertEquals(TEST_PUBLICATION, actualPublication);
        verify(publicationsDAO).getByID(TEST_PUBLICATION.getPublication_id());
    }

    @Test
    public void getAll_ok() {
        ArrayList<Publication> mockResultPublication = new ArrayList<>();
        mockResultPublication.add(TEST_PUBLICATION);

        when(DAOfactory.createPublicationsDAO()).thenReturn(publicationsDAO);
        when(publicationsDAO.getAll()).thenReturn(mockResultPublication);
        doNothing().when(publicationsDAO).close();

        List<Publication> actualPublications = new PublicationService(DAOfactory)
                .getAll();
        assertEquals(1, actualPublications.size());
        assertEquals(TEST_PUBLICATION, actualPublications.get(0));
        verify(publicationsDAO).getAll();
    }

    @Test
    public void sort_ok() {
        ArrayList<Publication> mockResultPublication = new ArrayList<>();
        mockResultPublication.add(ANOTHER_TEST_PUBLICATION);
        mockResultPublication.add(TEST_PUBLICATION);

        when(DAOfactory.createPublicationsDAO()).thenReturn(publicationsDAO);
        when(publicationsDAO.getAll()).thenReturn(mockResultPublication);
        doNothing().when(publicationsDAO).close();

        ArrayList<Publication> allPublications = new PublicationService(DAOfactory).getAll();
        new PublicationService(DAOfactory).sort(allPublications, 2);

        assertEquals(2, allPublications.size());
        assertEquals(TEST_PUBLICATION, allPublications.get(0));
        assertEquals(ANOTHER_TEST_PUBLICATION, allPublications.get(1));
        verify(publicationsDAO).getAll();
    }

    @Test
    public void getByTheme_ok() {
        ArrayList<Publication> mockResultPublication = new ArrayList<>();
        mockResultPublication.add(ANOTHER_TEST_PUBLICATION);
        mockResultPublication.add(TEST_PUBLICATION);

        when(DAOfactory.createPublicationsDAO()).thenReturn(publicationsDAO);
        when(publicationsDAO.getAll()).thenReturn(mockResultPublication);
        doNothing().when(publicationsDAO).close();

        ArrayList<Publication> allPublications = new PublicationService(DAOfactory).getAll();
        ArrayList<Publication> filteredPublications = new PublicationService(DAOfactory)
                .getByTheme(allPublications, Theme.SPORT);

        assertEquals(2, allPublications.size());
        assertEquals(1, filteredPublications.size());
        assertEquals(ANOTHER_TEST_PUBLICATION, filteredPublications.get(0));
        verify(publicationsDAO).getAll();
    }

    @Test
    public void getByName_ok() {
        ArrayList<Publication> mockResultPublication = new ArrayList<>();
        mockResultPublication.add(ANOTHER_TEST_PUBLICATION);
        mockResultPublication.add(TEST_PUBLICATION);

        when(DAOfactory.createPublicationsDAO()).thenReturn(publicationsDAO);
        when(publicationsDAO.getAll()).thenReturn(mockResultPublication);
        doNothing().when(publicationsDAO).close();

        ArrayList<Publication> allPublications = new PublicationService(DAOfactory).getAll();
        ArrayList<Publication> filteredPublications = new PublicationService(DAOfactory)
                .getByName(allPublications, TEST_PUBLICATION.getName());

        assertEquals(2, allPublications.size());
        assertEquals(1, filteredPublications.size());
        assertEquals(TEST_PUBLICATION, filteredPublications.get(0));
        verify(publicationsDAO).getAll();
    }

    @Test
    public void deletePublication_ok() {
        when(DAOfactory.createPublicationsDAO()).thenReturn(publicationsDAO);
        doNothing().when(publicationsDAO).close();

        new PublicationService(DAOfactory).insertPublication(TEST_PUBLICATION);
        new PublicationService(DAOfactory).deletePublication(TEST_PUBLICATION.getPublication_id());

        List<Publication> actualPublications = new PublicationService(DAOfactory).getAll();
        assertEquals(0, actualPublications.size());
        verify(publicationsDAO).delete(TEST_PUBLICATION.getPublication_id());
    }

    @Test
    public void updatePublication_ok() {
        ArrayList<Publication> mockResult = new ArrayList<>();
        Publication editedPublication = new Publication.PublicationBuilder()
                .setPublication_id(TEST_PUBLICATION.getPublication_id())
                .setTheme(Theme.POLITICS)
                .setName("test_politics")
                .setPrice(70)
                .build();
        mockResult.add(editedPublication);

        when(publicationsDAO.getAll()).thenReturn(mockResult);
        when(DAOfactory.createPublicationsDAO()).thenReturn(publicationsDAO);
        doNothing().when(publicationsDAO).close();

        new PublicationService(DAOfactory).insertPublication(TEST_PUBLICATION);
        new PublicationService(DAOfactory).updatePublication(editedPublication);
        List<Publication> actualPublications = new PublicationService(DAOfactory).getAll();
        assertEquals(1, actualPublications.size());
        assertEquals(editedPublication.getName(), actualPublications.get(0).getName());
        verify(publicationsDAO).insert(any(Publication.class));
        verify(publicationsDAO).update(any(Publication.class));
    }

    @Test
    public void insertPublication_ok() {
        ArrayList<Publication> mockResultPublication = new ArrayList<>();
        mockResultPublication.add(TEST_PUBLICATION);

        when(DAOfactory.createPublicationsDAO()).thenReturn(publicationsDAO);
        when(publicationsDAO.getAll()).thenReturn(mockResultPublication);
        doNothing().when(publicationsDAO).close();

        new PublicationService(DAOfactory).insertPublication(TEST_PUBLICATION);
        List<Publication> actualPublications = new PublicationService(DAOfactory).getAll();
        assertEquals(1, actualPublications.size());
        assertEquals(TEST_PUBLICATION, actualPublications.get(0));
        verify(publicationsDAO).insert(TEST_PUBLICATION);
        verify(publicationsDAO).getAll();
    }

    @Test
    public void getWhere_ok() {
        ArrayList<Publication> mockResult = new ArrayList<>();
        mockResult.add(ANOTHER_TEST_PUBLICATION);
        when(DAOfactory.createPublicationsDAO()).thenReturn(publicationsDAO);
        when(publicationsDAO.getWhere(anyString())).thenReturn(mockResult);
        doNothing().when(publicationsDAO).close();

        List<Publication> actualPublications = new PublicationService(DAOfactory)
                .getWhere(" publication_id  = '"
                        + ANOTHER_TEST_PUBLICATION.getPublication_id()
                        + "' AND price = '"
                        + ANOTHER_TEST_PUBLICATION.getPrice() + "' ");
        assertEquals(1, actualPublications.size());
        assertEquals(ANOTHER_TEST_PUBLICATION, actualPublications.get(0));
        verify(publicationsDAO).getWhere(anyString());
    }
}
