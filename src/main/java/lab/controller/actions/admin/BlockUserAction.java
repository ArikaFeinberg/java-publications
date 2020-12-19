package lab.controller.actions.admin;

import lab.controller.actions.Action;
import lab.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BlockUserAction extends Action {
    @Override
    public String doAction(HttpServletRequest req, HttpServletResponse resp) {
        if(req.getParameter("unblock")!=null){
            UserService userService = new UserService();
            int id = Integer.parseInt(req.getParameter("unblock"));
            userService.unblock(id);
        }
        if(req.getParameter("block")!=null){
            UserService userService = new UserService();
            int id = Integer.parseInt(req.getParameter("block"));
            userService.block(id);
        }
        return null;
    }
}
