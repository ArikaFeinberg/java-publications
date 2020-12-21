package lab.controller.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ExitAction extends Action {
    @Override
    public String doAction(HttpServletRequest req, HttpServletResponse resp) {
        req.getSession().invalidate();
        return "Login";
    }
}
