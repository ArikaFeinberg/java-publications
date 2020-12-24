package lab.model.service;

import lab.controller.validator.exeptions.ValidationException;
import lab.model.dao.AccountsDAO;
import lab.model.dao.DAOFactory;

import lab.model.dao.PublicationsDAO;
import lab.model.dao.Tables;
import lab.model.dao.entities.Account;
import lab.model.dao.entities.Publication;

public class AccountsService {

    private final AccountsDAO accountsDAO;
    private final PublicationService publicationService;

    public AccountsService(DAOFactory factory) {
        accountsDAO = factory.createAccountsDAO();
        publicationService = new PublicationService(factory);
    }

    public Account getUsersAccount(int id) {
        Account account =
                accountsDAO.getWhere("  userNumber  = "
                        + id).get(0);
        accountsDAO.close();
        return account;
    }

    public void replenish(Account account, double score) {
        account.setScore(account.getScore() + score);
        accountsDAO.update(account);
        accountsDAO.close();
    }

    public void makePayment(Account account, int pub_id) {
        Publication publication = publicationService.getById(pub_id);
        if (account.getScore() - publication.getPrice() > 0) {
            account.setScore(account.getScore() - publication.getPrice());
            accountsDAO.update(account);
        } else {
            throw new ValidationException("Not enough money!");
        }
        accountsDAO.close();
    }

    public void createAccount(int id) {
        accountsDAO.insert(new Account.AccountBuilder()
                .setUser_id(id)
                .setScore(0)
                .build());
        accountsDAO.close();
    }
}
