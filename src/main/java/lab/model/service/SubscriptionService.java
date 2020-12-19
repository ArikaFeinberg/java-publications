package lab.model.service;

import lab.model.dao.DAOAbstractFactory;
import lab.model.dao.SubscriptionDAO;
import lab.model.dao.entities.Publication;
import lab.model.dao.entities.Subscription;

import java.util.ArrayList;
import java.util.Objects;

public class SubscriptionService {

    public ArrayList<Publication> getClientSubscription(int id){
        SubscriptionDAO subscriptionDAO = (SubscriptionDAO)DAOAbstractFactory.getDAO("SUBSCRIPTION");
        ArrayList<Subscription> subscriptions = subscriptionDAO.getWhere(" user_id = " + id);
        subscriptionDAO.close();
        if(Objects.requireNonNull(subscriptions).size()!=0) {
            StringBuilder str = new StringBuilder();
            for (Subscription i: subscriptions) {
                str.append( i.getPublication_id());
                str.append(",");
            }

            String req = " Publication_id in (" + str.substring(0,str.length()-1) +") ";
            PublicationService publicationService = new PublicationService();

            return publicationService.getWhere(req);
        }
        return null;
    }
    public ArrayList<Publication> getClientNotSubscription(int id){
        SubscriptionDAO subscriptionDAO = (SubscriptionDAO)DAOAbstractFactory.getDAO("SUBSCRIPTION");
        ArrayList<Subscription> subscriptions = subscriptionDAO.getWhere(" user_id = " + id);
        subscriptionDAO.close();
        PublicationService publicationService = new PublicationService();
        if(Objects.requireNonNull(subscriptions).size()!=0) {
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

    public void add(Subscription subscription){
        SubscriptionDAO subscriptionDAO = (SubscriptionDAO)DAOAbstractFactory.getDAO("SUBSCRIPTION");
        subscriptionDAO.insert(subscription);
        subscriptionDAO.close();
    }

    public void delete(int id){
        SubscriptionDAO subscriptionDAO = (SubscriptionDAO)DAOAbstractFactory.getDAO("SUBSCRIPTION");
        subscriptionDAO.delete(id);
        subscriptionDAO.close();
    }
}
