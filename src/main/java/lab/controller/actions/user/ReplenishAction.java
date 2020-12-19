package lab.controller.actions.user;

import lab.controller.actions.Action;
import lab.controller.validator.Validator;
import lab.model.dao.entities.Account;
import lab.model.service.AccountsService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReplenishAction extends Action {
    @Override
    public String doAction(HttpServletRequest req, HttpServletResponse resp) {
        try{
            String score = req.getParameter("score");
            Validator.isDouble(score);
            AccountsService accountsService = new AccountsService();
            Account account =(Account) req.getSession().getAttribute("account");
            accountsService.replenish(account,Double.parseDouble(score));
            req.setAttribute("error","Replenish is done!");
            req.getSession().setAttribute("account",account);
        }catch (Exception e){
            req.setAttribute("error",e.getMessage());
        }
        return null;
    }
}
