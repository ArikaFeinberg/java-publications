package lab.model.service;

import lab.model.dao.DAOAbstractFactory;
import lab.model.dao.PublicationsDAO;
import lab.model.dao.entities.Publication;
import lab.model.dao.entities.enums.Theme;

import java.util.ArrayList;
import java.util.Comparator;

public class PublicationService {

    public Publication getById(int id) {
        PublicationsDAO publicationsDAO = (PublicationsDAO) DAOAbstractFactory.getDAO("PUBLICATIONS");
        Publication publication = publicationsDAO.getByID(id);
        publicationsDAO.close();
        return publication;
    }

    public ArrayList<Publication> getAll() {
        PublicationsDAO publicationsDAO = (PublicationsDAO) DAOAbstractFactory.getDAO("PUBLICATIONS");
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
        for (Publication publication :
                publications) {
            if (publication.getTheme() == theme) {
                res.add(publication);
            }
        }
        return res;
    }

    public ArrayList<Publication> getByName(ArrayList<Publication> publications, String name) {
        ArrayList<Publication> res = new ArrayList<Publication>();
        for (Publication publication :
                publications) {
            if (publication.getName().equals(name)) {
                res.add(publication);
            }
        }
        return res;
    }

    public void deletePublication(int id) {
        PublicationsDAO publicationsDAO = (PublicationsDAO) DAOAbstractFactory.getDAO("PUBLICATIONS");
        SubscriptionService subscriptionService = new SubscriptionService();
        subscriptionService.delete(id);
        publicationsDAO.delete(id);
        publicationsDAO.close();
    }

    public void updatePublication(Publication publication) {
        PublicationsDAO publicationsDAO = (PublicationsDAO) DAOAbstractFactory.getDAO("PUBLICATIONS");
        publicationsDAO.update(publication);
        publicationsDAO.close();
    }

    public void insertPublication(Publication publication) {
        PublicationsDAO publicationsDAO = (PublicationsDAO) DAOAbstractFactory.getDAO("PUBLICATIONS");
        publicationsDAO.insert(publication);
        publicationsDAO.close();
    }

    public ArrayList<Publication> getWhere(String str) {
        PublicationsDAO publicationsDAO = (PublicationsDAO) DAOAbstractFactory.getDAO("PUBLICATIONS");
        ArrayList<Publication> arr = publicationsDAO.getWhere(str);
        publicationsDAO.close();
        return arr;
    }

}
