package ru.job4j.banktransfer;

import java.util.*;

/**
 * Class for storing information about users and their account.
 *
 * @author Dmitrii Eskov (eskovdmi@gmail.com)
 * @since 30.01.2019
 * @version 1.0
 */
public class Bank {

    /**
     * Storage for users and their information.
     */
    private Map<User, List<Account>> map = new TreeMap<>();

    /**
     * Add a new user to this.map
     * @param user a new user
     */
    public void addUser(User user) {
        this.map.put(user, new ArrayList<>());
    }

    /**
     * Deletes a user from this.map
     * @param user a user to be deleted
     */
    public List<Account> deleteUser(User user) {
        List<Account> result = null;
        if (this.map.containsKey(user)) {
            result = this.map.remove(user);
        }
        return result;
    }

    /**
     * Adds a new account for a user.
     * @param passport - a user's passport
     * @param account - a user's account
     */
    public void addAccountToUser(String passport, Account account) {
        Set<User> userSet = this.map.keySet();
        for (User user : userSet) {
            if (user.getPassport().equals(passport)) {
                this.map.get(user).add(account);
            }
        }
    }

    /**
     * Deletes a user's account.
     * @param passport - a user's passport
     * @param account - a user's account
     */
    public boolean deleteAccountFromUser(String passport, Account account) {
        boolean result = false;
        Set<User> userSet = this.map.keySet();
        for (User user : userSet) {
            if (user.getPassport().equals(passport)) {
                result = this.map.get(user).remove(account);
            }
        }
        return result;
    }

    /**
     * Returns all user's accounts.
     * @param passport - a user's passport
     * @return a list of user's accounts
     */
    public List<Account> getUserAccounts(String passport) {
       List<Account> result = null;
       Set<User> users = this.map.keySet();
       for (User user : users) {
           if (user.getPassport().equals(passport)) {
               result = this.map.get(user);
           }
       }
       return result;
    }

    /**
     * Returns a user's account.
     * @param passport - a user's passport
     * @return a user's account
     */
    public Account getActualAccount(String passport, Account account) {
        Account result = this.findAccountByPassportAndRequisites(passport, account.getRequisites());
        return result;
    }

    /**
     * Finds a specific account by user's passport and requisites of an account.
     * @param passport - a user's passport
     * @param requisites - requisites of an account
     * @return true - if the operation successful, false - otherwise
     */
    public Account findAccountByPassportAndRequisites(String passport, String requisites) {
        Set<User> userSet = this.map.keySet();
        Account result = null;
        for (User user : userSet) {
            if (user.getPassport().equals(passport)) {
                for (Account account : this.map.get(user)) {
                    if (account.getRequisites().equals(requisites)) {
                        result = account;
                    }
                }

            }
        }
        return result;
    }

    /**
     * Transfers money between accounts.
     * @param srcPassport - a source passport
     * @param srcRequisite - a source requisite
     * @param destPassport - a destination passport
     * @param dstRequisite - a destination requisite
     * @param amount - amount of money to be transferred
     * @return true - if the operation successful, false - otherwise
     */
    public boolean transferMoney(String srcPassport, String srcRequisite, String destPassport, String dstRequisite, double amount) {
        boolean result = false;
        Account srcAcc = this.findAccountByPassportAndRequisites(srcPassport, srcRequisite);
        Account destAcc = this.findAccountByPassportAndRequisites(destPassport, dstRequisite);
        if (srcAcc != null && srcAcc.getValue() > amount && destAcc != null) {
            srcAcc.setDecreasedValue(amount);
            destAcc.setIncreasedValue(amount);
            result = true;
        }
        return result;
    }
}