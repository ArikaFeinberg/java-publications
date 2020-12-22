package lab.controller.actions.user;

import lab.controller.actions.Action;
import lab.controller.actions.ExitAction;
import lab.controller.validator.exeptions.ValidationException;
import lab.model.dao.entities.Account;
import lab.model.dao.entities.Subscription;
import lab.model.dao.entities.User;
import lab.model.service.AccountsService;
import lab.model.service.SubscriptionService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SubscribeAction extends Action {

    private static final Logger log = LogManager.getLogger(SubscribeAction.class);

    @Override
    public String doAction(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        try {
            int publication_id = Integer.parseInt(req.getParameter("publication_id"));
            SubscriptionService subscriptionService = new SubscriptionService();
            AccountsService accountsService = new AccountsService();
            User user = (User) session.getAttribute("user");
            accountsService.makePayment((Account) session.getAttribute("account"), publication_id);
            subscriptionService.add(
                    new Subscription
                            .SubscriptionBuilder()
                            .setUser_id(user.getUser_id())
                            .setPublication_id(publication_id)
                            .build()
            );
            log.info("User " + user.getUserName() + " has subscribed to " + publication_id);
        } catch (ValidationException e) {
            req.setAttribute("error", e.getMessage());
            log.error("Validation exception at subscribe action. Username: " + session.getAttribute("username"));
        } catch (Exception e) {
            req.setAttribute("error", "Database error");
            log.error("Database error at subscribe action. Username: " + session.getAttribute("username"));
        }
        return null;
    }
}
