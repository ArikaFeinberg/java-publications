package lab.controller.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ExitAction extends Action {

    private static final Logger log = LogManager.getLogger(ExitAction.class);

    @Override
    public String doAction(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        session.invalidate();
        log.info("User/Admin " + session.getAttribute("username") + " has logged out");
        return "Login";
    }
}
