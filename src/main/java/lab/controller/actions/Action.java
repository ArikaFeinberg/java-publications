package lab.controller.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class Action {
    public abstract String doAction(HttpServletRequest req, HttpServletResponse resp);
}
