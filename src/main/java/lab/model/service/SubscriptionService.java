package lab.model.service;

import lab.model.dao.DAOFactory;
import lab.model.dao.SubscriptionDAO;
import lab.model.dao.Tables;
import lab.model.dao.entities.Publication;
import lab.model.dao.entities.Subscription;

import java.util.ArrayList;
import java.util.Objects;

public class SubscriptionService {

    private final SubscriptionDAO subscriptionDAO;
    private final PublicationService publicationService;

    public SubscriptionService(DAOFactory factory) {
        subscriptionDAO = factory.createSubscriptionDAO();
        publicationService = new PublicationService(factory);
    }

    public ArrayList<Publication> getClientSubscription(int id) {
        ArrayList<Subscription> subscriptions = subscriptionDAO.getWhere(" user_id = " + id);
        subscriptionDAO.close();
        if (Objects.requireNonNull(subscriptions).size() != 0) {
            StringBuilder str = new StringBuilder();
            for (Subscription i : subscriptions) {
                str.append(i.getPublication_id());
                str.append(",");
            }
            String req = " Publication_id in (" + str.substring(0, str.length() - 1) + ") ";
            return publicationService.getWhere(req);
        }
        return null;
    }

    public ArrayList<Publication> getClientUnsubscribedPublications(int id) {
        ArrayList<Subscription> subscriptions = subscriptionDAO.getWhere(" user_id = " + id);
        subscriptionDAO.close();
        if (Objects.requireNonNull(subscriptions).size() != 0) {
            StringBuilder str = new StringBuilder();
            for (Subscription i : subscriptions) {
                str.append(i.getPublication_id());
                str.append(",");
            }
            String req = " Publication_id not in (" + str.substring(0, str.length() - 1) + ") ";
            return publicationService.getWhere(req);
        }
        return publicationService.getAll();
    }

    public void add(Subscription subscription) {
        subscriptionDAO.insert(subscription);
        subscriptionDAO.close();
    }

    public void delete(int id) {
        subscriptionDAO.delete(id);
        subscriptionDAO.close();
    }
}
