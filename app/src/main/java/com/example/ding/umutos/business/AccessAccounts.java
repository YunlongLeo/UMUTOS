package com.example.ding.umutos.business;

import java.util.List;

import com.example.ding.umutos.application.Service;
import com.example.ding.umutos.objects.Account;
import com.example.ding.umutos.persistence.AccountPersistence;

public class AccessAccounts {

    private AccountPersistence accountPersistence;
    private List<Account> accounts;

    public AccessAccounts() {
        accountPersistence = Service.getAccountPersistence();
        accounts = null;
    }

    public AccessAccounts(final AccountPersistence accountPersistence) {
        this();
        this.accountPersistence = accountPersistence;
    }

    public List<Account> getAccounts() {
        accounts = accountPersistence.getAccountSequential();
        return accounts;
    }

    public Account getAccountByID(int userID) {
        return accountPersistence.getAccountByID(userID);
    }

    public boolean insertAccount(Account currentAccount) {
        if(currentAccount != null) {
            accountPersistence.insertAccount(currentAccount);
            return true;
        }
        return false;
    }

    public boolean updateAccount(Account currentAccount) {
        return accountPersistence.updateAccount(currentAccount) != null;
    }

    public void deleteAccount(Account currentAccount) {
        accountPersistence.deleteAccount(currentAccount);
    }

    public Account Login(String userName, String password) {
        Account targetAccount = null;
        getAccounts();
        for(int i = 0; i < accounts.size(); i++)
        {
            boolean sameUserName = accounts.get(i).getUserName().equals(userName);
            boolean samePassword = Integer.parseInt(accounts.get(i).getPassword()) == password.hashCode();
            if(samePassword && sameUserName)
                targetAccount = accounts.get(i);
        }
        return targetAccount;
    }

    public Account register(String userName, String passWord) {
        Account targetAccount = null;
        AccountValidator validator = new AccountValidator();
        getAccounts();
        if(validator.validateUserName(userName, accounts) && validator.validatePassword(passWord))
        {
            targetAccount = new Account(userName, Integer.toString(passWord.hashCode()));
            this.insertAccount(targetAccount);
        }
        return targetAccount;
    }

    public List<String> getAccountComment(int userID)
    {
        return getAccountByID(userID).getComment();
    }

    public double getAccountRate(int userID)
    {
        return getAccountByID(userID).getRate();
    }

    public boolean CommentUser(String comment, int sellerID)
    {
        return getAccountByID(sellerID).getComment().add(comment);
    }

    public void RateUser(int sellerID, double rate)
    {
        Account seller = getAccountByID(sellerID);
        RateCalculator newCalculator = new RateCalculator();
        seller.setRate(newCalculator.calculateRate(seller,rate));
    }
}
