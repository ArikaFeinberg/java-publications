package lab.controller.actions.admin;

import lab.controller.actions.Action;
import lab.model.dao.DAOFactory;
import lab.model.service.PublicationService;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeletePublicationAction extends Action {

    private static final Logger log = LogManager.getLogger(DeletePublicationAction.class);

    @Override
    public String doAction(HttpServletRequest req, HttpServletResponse resp) {
        if (req.getParameter("delete") != null) {
            PublicationService publicationService = new PublicationService(DAOFactory.FACTORY);
            int publicationId = Integer.parseInt(req.getParameter("delete"));
            publicationService.deletePublication(publicationId);
            log.info("Publication " + publicationId
                    + " has been deleted by " + req.getSession().getAttribute("username"));
        }
        return null;
    }
}
