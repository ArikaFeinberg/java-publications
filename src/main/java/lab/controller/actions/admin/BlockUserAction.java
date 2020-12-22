package lab.controller.actions.admin;

import lab.controller.actions.Action;
import lab.model.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BlockUserAction extends Action {

    private static final Logger log = LogManager.getLogger(BlockUserAction.class);

    @Override
    public String doAction(HttpServletRequest req, HttpServletResponse resp) {
        if (req.getParameter("unblock") != null) {
            UserService userService = new UserService();
            int id = Integer.parseInt(req.getParameter("unblock"));
            userService.unblock(id);
            log.info("User (id): " + id + " has been unblocked by "
                    + req.getSession().getAttribute("username"));
        }
        if (req.getParameter("block") != null) {
            UserService userService = new UserService();
            int id = Integer.parseInt(req.getParameter("block"));
            userService.block(id);
            log.info("User (id): " + id + " has been blocked by "
                    + req.getSession().getAttribute("username"));
        }
        return null;
    }
}
