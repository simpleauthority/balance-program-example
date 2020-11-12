import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Account implements Comparable<Account> {
    private final int id;
    private final List<Float> balances;

    public Account(int id, float balance) {
        this.id = id;

        // create a list with the initial balance
        List<Float> balances = new ArrayList<>();
        balances.add(balance);
        this.balances = balances;
    }

    public int getId() {
        return id;
    }

    public float getHighBalance() {
        // convert list to stream, get the maximum value after sorting from min->max, if for some reason it can't find one throw an IllegalStateException
        return balances.stream().max(Comparator.naturalOrder()).orElseThrow(IllegalStateException::new);
    }

    public float getLowBalance() {
        // convert list to stream, get the minimum value after sorting from min->max, if for some reason it can't find one throw an IllegalStateException
        return balances.stream().min(Comparator.naturalOrder()).orElseThrow(IllegalStateException::new);
    }

    // get the most recent value in the list, aka current balance
    public float getLastBalance() {
        return balances.get(balances.size() - 1);
    }

    // add a balance into the ledger list
    public void addBalance(float balance) {
        balances.add(balance);
    }

    @Override
    public int compareTo(Account account) {
        // if the given account's last balance > this account's last balance, then given is greater; return 1
        if (account.getLastBalance() > this.getLastBalance()) {
            return 1;
        }
        // if this account's last balance > the given account's last balance, then this is greater; return -1
        else if (this.getLastBalance() > account.getLastBalance()) {
            return -1;
        }
        // last balance of each account is equal; return 0
        return 0;
    }

    @Override
    public String toString() {
        // poop everything out into a string
        return String.format("Account:\t%d\t\tCurrent Balance:\t$%.2f\t\tLow Balance:\t$%.2f\t\tHigh Balance:\t$%.2f", getId(), getLastBalance(), getLowBalance(), getHighBalance());
    }
}
