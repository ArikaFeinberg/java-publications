package lab.controller.actions.user;

import lab.controller.actions.Action;
import lab.controller.Pagination;
import lab.model.dao.entities.Publication;
import lab.model.dao.entities.enums.Theme;
import lab.model.service.PublicationService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class GuestAction extends Action {
    @Override
    public String doAction(HttpServletRequest req, HttpServletResponse resp) {
        PublicationService publicationService = new PublicationService();

        ArrayList<Publication> publications = publicationService.getAll();

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

        Pagination.pagination( req, publications,"publications",5);
        req.setAttribute("m",1);
        return "/jsp/guest.jsp";
    }
}
