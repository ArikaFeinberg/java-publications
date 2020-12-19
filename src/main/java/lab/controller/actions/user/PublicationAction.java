package lab.controller.actions.user;

import lab.controller.actions.Action;
import lab.controller.Pagination;
import lab.model.dao.entities.Publication;
import lab.model.dao.entities.User;
import lab.model.dao.entities.enums.Theme;
import lab.model.service.PublicationService;
import lab.model.service.SubscriptionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class PublicationAction extends Action {
    @Override
    public String doAction(HttpServletRequest req, HttpServletResponse resp) {
        PublicationService publicationService = new PublicationService();
        SubscriptionService subscriptionService = new SubscriptionService();

        User user = (User)req.getSession().getAttribute("user");

        ArrayList<Publication> publications = subscriptionService.getClientNotSubscription(user.getUser_id());

        if(req.getParameter("search")!=null){
            publications = publicationService.getByName(publications,req.getParameter("search"));
        }
        if(req.getParameter("theme")!=null && !req.getParameter("theme").equals("")){
            Theme theme = Theme.valueOf(req.getParameter("theme").toUpperCase());
            publications = publicationService.getByTheme(publications,theme);
        }

        if(req.getParameter("sort")!=null){
            publicationService.sort(publications,Integer.parseInt(req.getParameter("sort")));
        }

        Pagination.pagination(req,publications,"publications",5);


        return "/jsp/user/publication.jsp";
    }
}
