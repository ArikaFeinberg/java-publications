package lab.controller.actions.user;

import lab.controller.actions.Action;
import lab.model.dao.entities.Publication;
import lab.model.dao.entities.User;
import lab.model.service.AccountsService;
import lab.model.service.SubscriptionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class HomeAction extends Action {
    @Override
    public String doAction(HttpServletRequest req, HttpServletResponse resp) {
        AccountsService accountsService = new AccountsService();
        User user = (User)req.getSession().getAttribute("user");
        req.getSession().setAttribute("account",accountsService.getUsersAccount(user.getUser_id()));

        SubscriptionService subscriptionService = new SubscriptionService();
        ArrayList<Publication> subscriptions = subscriptionService.getClientSubscription(user.getUser_id());

        req.setAttribute("subscriptions",subscriptions);

        return "/jsp/user/home.jsp";
    }
}
