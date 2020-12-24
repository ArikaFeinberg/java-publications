package lab.controller.actions.user;

import lab.controller.actions.Action;
import lab.controller.validator.Validator;
import lab.controller.validator.exeptions.ValidationException;
import lab.model.dao.entities.Account;
import lab.model.service.AccountsService;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ReplenishAction extends Action {

    private static final Logger log = LogManager.getLogger(ReplenishAction.class);

    @Override
    public String doAction(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        try {
            String score = req.getParameter("score");
            Validator.isDouble(score);
            AccountsService accountsService = new AccountsService();
            Account account = (Account) session.getAttribute("account");
            accountsService.replenish(account, Double.parseDouble(score));
            req.setAttribute("error", "Replenish is done!");
            session.setAttribute("account", account);
            log.info("Account of user " + account.getUser_id() + " replenished by " + score);
        } catch (ValidationException e) {
            req.setAttribute("error", e.getMessage());
            log.error("Validation exception at replenish action. Username: " + session.getAttribute("username"));
        } catch (Exception e) {
            req.setAttribute("error", "Database error");
            log.error("Database error at replenish action. Username: " + session.getAttribute("username"));
        }
        return null;
    }
}
