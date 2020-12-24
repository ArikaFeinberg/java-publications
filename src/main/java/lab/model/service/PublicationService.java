package lab.model.service;

import lab.model.dao.DAOFactory;
import lab.model.dao.PublicationsDAO;
import lab.model.dao.Tables;
import lab.model.dao.entities.Publication;
import lab.model.dao.entities.enums.Theme;

import java.util.ArrayList;
import java.util.Comparator;

public class PublicationService {

    private final PublicationsDAO publicationsDAO;

    public PublicationService(DAOFactory factory) {
        publicationsDAO = factory.createPublicationsDAO();
    }

    public Publication getById(int id) {
        Publication publication = publicationsDAO.getByID(id);
        publicationsDAO.close();
        return publication;
    }

    public ArrayList<Publication> getAll() {
        ArrayList<Publication> publications = publicationsDAO.getAll();
        publicationsDAO.close();
        return publications;
    }

    public void sort(ArrayList<Publication> publications, int sort) {
        if (sort == 1) {
            publications.sort(Comparator.comparing(Publication::getName));
        } else if (sort == 2) {
            publications.sort(Comparator.comparing(Publication::getPrice));
        }
    }

    public ArrayList<Publication> getByTheme(ArrayList<Publication> publications, Theme theme) {
        ArrayList<Publication> res = new ArrayList<>();
        for (Publication publication : publications) {
            if (publication.getTheme() == theme) {
                res.add(publication);
            }
        }
        return res;
    }

    public ArrayList<Publication> getByName(ArrayList<Publication> publications, String name) {
        ArrayList<Publication> res = new ArrayList<>();
        for (Publication publication : publications) {
            if (publication.getName().equals(name)) {
                res.add(publication);
            }
        }
        return res;
    }

    public void deletePublication(int id) {
        SubscriptionService subscriptionService = new SubscriptionService(DAOFactory.FACTORY);
        subscriptionService.delete(id);
        publicationsDAO.delete(id);
        publicationsDAO.close();
    }

    public void updatePublication(Publication publication) {
        publicationsDAO.update(publication);
        publicationsDAO.close();
    }

    public void insertPublication(Publication publication) {
        publicationsDAO.insert(publication);
        publicationsDAO.close();
    }

    public ArrayList<Publication> getWhere(String str) {
        ArrayList<Publication> arr = publicationsDAO.getWhere(str);
        publicationsDAO.close();
        return arr;
    }

}
