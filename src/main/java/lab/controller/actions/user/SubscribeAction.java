package lab.controller.actions.user;

import lab.controller.actions.Action;
import lab.model.dao.entities.Account;
import lab.model.dao.entities.Subscription;
import lab.model.dao.entities.User;
import lab.model.service.AccountsService;
import lab.model.service.SubscriptionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SubscribeAction extends Action {
    @Override
    public String doAction(HttpServletRequest req, HttpServletResponse resp) {
        try {
            int publication_id = Integer.parseInt(req.getParameter("publication_id"));
            SubscriptionService subscriptionService = new SubscriptionService();
            AccountsService accountsService = new AccountsService();
            User user = (User) req.getSession().getAttribute("user");
            accountsService.makePayment((Account) req.getSession().getAttribute("account"), publication_id);
            subscriptionService.add(
                    new Subscription
                            .SubscriptionBuilder()
                            .setUser_id(user.getUser_id())
                            .setPublication_id(publication_id)
                            .build()
            );

        } catch (Exception e) {
            req.setAttribute("error", e.getMessage());
        }
        return null;
    }
}
