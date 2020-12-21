package lab.controller.actions.admin;

import lab.controller.actions.Action;
import lab.controller.validator.Validator;
import lab.model.dao.entities.Publication;
import lab.model.dao.entities.enums.Theme;
import lab.model.service.PublicationService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ChangePublicationAction extends Action {
    @Override
    public String doAction(HttpServletRequest req, HttpServletResponse resp) {
        if (req.getParameter("publication_id") != null) {
            try {
                String theme = req.getParameter("theme");
                String name = req.getParameter("name");
                String score = req.getParameter("score");
                Validator.isDouble(score);

                PublicationService publicationService = new PublicationService();

                publicationService.updatePublication(
                        new Publication.PublicationBuilder()
                                .setPublication_id(Integer.parseInt(req.getParameter("publication_id")))
                                .setName(name)
                                .setPrice(Double.parseDouble(score))
                                .setTheme(Theme.valueOf(theme))
                                .build()
                );

                return "Publication";
            } catch (Exception e) {
                req.setAttribute("error", e.getMessage());
            }
        }
        return null;
    }
}
