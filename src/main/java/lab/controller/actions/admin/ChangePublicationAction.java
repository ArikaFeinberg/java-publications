package lab.controller.actions.admin;

import lab.controller.actions.Action;
import lab.controller.validator.Validator;
import lab.controller.validator.exeptions.ValidationException;
import lab.model.dao.entities.Publication;
import lab.model.dao.entities.enums.Theme;
import lab.model.service.PublicationService;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class ChangePublicationAction extends Action {

    private static final Logger log = LogManager.getLogger(ChangePublicationAction.class);

    @Override
    public String doAction(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        if (req.getParameter("publication_id") != null) {
            try {
                String theme = req.getParameter("theme");
                String name = req.getParameter("name");
                String score = req.getParameter("score");
                int publicationId = Integer.parseInt(req.getParameter("publication_id"));
                Validator.isDouble(score);

                PublicationService publicationService = new PublicationService();

                publicationService.updatePublication(
                        new Publication.PublicationBuilder()
                                .setPublication_id(publicationId)
                                .setName(name)
                                .setPrice(Double.parseDouble(score))
                                .setTheme(Theme.valueOf(theme))
                                .build()
                );
                log.info("Publication " + publicationId
                        + " has been edited by " + session.getAttribute("username"));
                return "Publication";
            } catch (ValidationException e) {
                req.setAttribute("error", e.getMessage());
                log.error("Validation exception at publication change action. Username: " + session.getAttribute("username"));
            } catch (Exception e) {
                req.setAttribute("error", "Database error");
                log.error("Database error at publication change action. Username: " + session.getAttribute("username"));
            }
        }
        return null;
    }
}
