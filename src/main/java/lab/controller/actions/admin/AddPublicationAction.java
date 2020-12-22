package lab.controller.actions.admin;

import lab.controller.actions.Action;
import lab.controller.validator.Validator;
import lab.controller.validator.exeptions.ValidationException;
import lab.model.dao.entities.Publication;
import lab.model.dao.entities.enums.Theme;
import lab.model.service.PublicationService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddPublicationAction extends Action {

    private static final Logger log = LogManager.getLogger(AddPublicationAction.class);

    @Override
    public String doAction(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String theme = req.getParameter("theme");
            String name = req.getParameter("name");
            String score = req.getParameter("score");
            Validator.isDouble(score);

            PublicationService publicationService = new PublicationService();

            publicationService.insertPublication(
                    new Publication.PublicationBuilder()
                            .setName(name)
                            .setPrice(Double.parseDouble(score))
                            .setTheme(Theme.valueOf(theme))
                            .build()
            );

            return "Publication";
        } catch (ValidationException e) {
            req.setAttribute("error", e.getMessage());
            log.error("Validation exception at publication adding action. Username: "
                    + req.getSession().getAttribute("username"));
        } catch (Exception e) {
            req.setAttribute("error", "Database error");
            log.error("Database error at publication adding action. Username: "
                    + req.getSession().getAttribute("username"));
        }
        return null;
    }
}
