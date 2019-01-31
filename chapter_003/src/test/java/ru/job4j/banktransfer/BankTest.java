package ru.job4j.banktransfer;

import org.junit.Test;

import java.util.ArrayList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

/**
 * Class for testing the Bank class.
 *
 * @author Dmitrii Eskov (eskovdmi@gmail.com)
 * @since 30.01.2019
 * @version 1.0
 */
public class BankTest {

    /**
     * Tests when it needs to transfer from person A to person B.
     */
    @Test
    public void whenTransferFromOneToAnotherUserThenSuccess() {
        Bank bank = new Bank();
        User first = new User("Peter", "4123");
        User second = new User("Jack", "9720");
        bank.addUser(first);
        bank.addUser(second);
        Account firstAccount = new Account(10000.00, "73298479324");
        Account secondAccount = new Account(20000.00, "8453045");
        bank.addAccountToUser(first.getPassport(), new Account(10000.00, "73298479324"));
        bank.addAccountToUser(second.getPassport(), secondAccount);
        assertThat(bank.transferMoney(first.getPassport(), firstAccount.getRequisites(), second.getPassport(),
                secondAccount.getRequisites(), 5000), is(true));
    }

    /**
     * Tests when it needs to transfer from person A to person B and the result is unsuccessful.
     */
    @Test
    public void whenTransferFromOneToAnotherUserThenUnsuccessful() {
        Bank bank = new Bank();
        User first = new User("Peter", "4123");
        User second = new User("Jack", "9720");
        bank.addUser(first);
        bank.addUser(second);
        Account firstAccount = new Account(10000.00, "73298479324");
        Account secondAccount = new Account(20000.00, "8453045");
        bank.addAccountToUser(first.getPassport(), new Account(10000.00, "73298479324"));
        bank.addAccountToUser(second.getPassport(), secondAccount);
        assertThat(bank.transferMoney(first.getPassport(), firstAccount.getRequisites(), second.getPassport(),
                secondAccount.getRequisites(), 12000), is(false));
    }

    /**
     * Tests when it needs to delete a user's account.
     */
    @Test
    public void whenToDeleteUserAccountThenSuccess() {
        Bank bank = new Bank();
        User first = new User("Peter", "4123");
        bank.addUser(first);
        Account firstAcc = new Account(10000.00, "73298479324");
        bank.addAccountToUser(first.getPassport(), firstAcc);
        assertThat(bank.deleteAccountFromUser(first.getPassport(), firstAcc), is(true));
    }

    /**
     * Tests when it needs to find a user's account and it is not found.
     */
    @Test
    public void whenAccountNotFound() {
        Bank bank = new Bank();
        User first = new User("Peter", "4123");
        Account firstAcc = new Account(10000.00, "73298479324");
        bank.addUser(first);
        assertNull(bank.getActualAccount(first.getPassport(), firstAcc));
    }

    /**
     * Tests when it needs to delete a user.
     */
    @Test
    public void whenToDeleteUserThenSuccess() {
        Bank bank = new Bank();
        User first = new User("Peter", "4123");
        bank.addUser(first);
        Account firstAcc = new Account(10000.00, "73298479324");
        bank.addAccountToUser(first.getPassport(), firstAcc);
        bank.deleteUser(first);
        assertNull(bank.getUserAccounts(first.getPassport()));
    }

    /**
     * Tests when it needs to delete a user.
     */
    @Test
    public void whenToDeleteUserThenNoSuccess() {
        Bank bank = new Bank();
        User first = new User("Peter", "4123");
        bank.deleteUser(first);
        assertNull(bank.deleteUser(first));
    }

    /**
     * Tests when it needs to get all users' accounts.
     */
    @Test
    public void whenToGetAllUserAccountsThenSuccess() {
        Bank bank = new Bank();
        User first = new User("Peter", "4123");
        bank.addUser(first);
        Account firstAccount = new Account(10000.00, "73298479324");
        Account secondAccount = new Account(20000.00, "8453045");
        bank.addAccountToUser(first.getPassport(), firstAccount);
        bank.addAccountToUser(first.getPassport(), secondAccount);
        ArrayList<Account> expect = new ArrayList<>();
        expect.add(firstAccount);
        expect.add(secondAccount);
        assertThat(bank.getUserAccounts(first.getPassport()).containsAll(expect), is(true));
    }

    /**
     * Tests when it needs to get an actual user account and the result is successful.
     */
    @Test
    public void whenToGetActualUserAccountThenSuccess() {
        Bank bank = new Bank();
        User first = new User("Peter", "4123");
        bank.addUser(first);
        Account firstAccount = new Account(10000.00, "73298479324");
        bank.addAccountToUser(first.getPassport(), firstAccount);
        assertThat(bank.getActualAccount(first.getPassport(), firstAccount), is(firstAccount));
    }

    /**
     * Tests when it needs to get an actual user account and the result is unsuccessful.
     */
    @Test
    public void whenToGetActualUserAccountThenNoSuccess() {
        Bank bank = new Bank();
        User first = new User("Peter", "4123");
        bank.addUser(first);
        Account firstAccount = new Account(10000.00, "73298479324");
        assertNull(bank.getActualAccount(first.getPassport(), firstAccount));
    }
}