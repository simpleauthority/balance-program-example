import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Account implements Comparable<Account> {
    private final int id;
    private final List<Float> balances;

    public Account(int id, float balance) {
        this.id = id;

        List<Float> balances = new ArrayList<>();
        balances.add(balance);
        this.balances = balances;
    }

    public int getId() {
        return id;
    }

    public float getHighBalance() {
        return balances.stream().max(Comparator.naturalOrder()).orElseThrow(IllegalStateException::new);
    }

    public float getLowBalance() {
        return balances.stream().min(Comparator.naturalOrder()).orElseThrow(IllegalStateException::new);
    }

    public float getLastBalance() {
        return balances.get(balances.size() - 1);
    }

    public void addBalance(float balance) {
        balances.add(balance);
    }

    @Override
    public int compareTo(Account account) {
        if (account.getLastBalance() > this.getLastBalance()) {
            return 1;
        } else if (this.getLastBalance() > account.getLastBalance()) {
            return -1;
        }

        return 0;
    }

    @Override
    public String toString() {
        return String.format("Account:\t%d\t\tCurrent Balance:\t$%.2f\t\tLow Balance:\t$%.2f\t\tHigh Balance:\t$%.2f", getId(), getLastBalance(), getLowBalance(), getHighBalance());
    }
}
