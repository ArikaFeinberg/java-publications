package lab.model.service;

import lab.controller.validator.exeptions.ValidationException;
import lab.model.dao.AccountsDAO;
import lab.model.dao.DAOAbstractFactory;

import lab.model.dao.entities.Account;
import lab.model.dao.entities.Publication;

public class AccountsService {
    public Account getUsersAccount(int id) {
        AccountsDAO accountsDAO = (AccountsDAO) DAOAbstractFactory.getDAO("ACCOUNTS");
        Account account =
                accountsDAO.getWhere("  userNumber  = "
                        + id).get(0);
        accountsDAO.close();
        return account;
    }

    public void replenish(Account account, double score) {
        AccountsDAO accountsDAO = (AccountsDAO) DAOAbstractFactory.getDAO("ACCOUNTS");
        account.setScore(account.getScore() + score);
        accountsDAO.update(account);
        accountsDAO.close();
    }

    public void makePayment(Account account, int pub_id) {
        AccountsDAO accountsDAO = (AccountsDAO) DAOAbstractFactory.getDAO("ACCOUNTS");
        Publication publication = new PublicationService().getById(pub_id);
        if (account.getScore() - publication.getPrice() > 0) {
            account.setScore(account.getScore() - publication.getPrice());
            accountsDAO.update(account);
        } else {
            throw new ValidationException("Not enough money!");
        }
        accountsDAO.close();
    }

    public void createAccount(int id) {
        AccountsDAO accountsDAO = (AccountsDAO) DAOAbstractFactory.getDAO("ACCOUNTS");
        accountsDAO.insert(
                new Account.AccountBuilder()
                        .setUser_id(id)
                        .setScore(0)
                        .build()
        );
        accountsDAO.close();
    }
}
