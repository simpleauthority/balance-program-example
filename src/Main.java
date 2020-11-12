import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Stream;

public class Main {
    static Set<Account> accounts = new TreeSet<>(Account::compareTo);

    public static void main(String[] args) throws FileNotFoundException {
        // read file, skipping header
        Scanner scanner = new Scanner(new File("balances-0.txt"));
        scanner.nextLine();

        while (scanner.hasNextLine()) {
            // split and parse id and balance
            String[] split = scanner.nextLine().split("\t");
            int id = Integer.parseInt(split[0]);
            float bal = Float.parseFloat(split[1].substring(1));

            // is the account already in the tree set?
            if (stream(id).count() == 1) {
                // yes, find it
                Account account = stream(id).findFirst().orElseThrow(IllegalStateException::new);
                // add new balance
                account.addBalance(bal);
                // remove original from tree set
                accounts.removeIf(acc -> acc.getId() == id);
                // add back into tree set
                accounts.add(account);
            } else {
                // nope, add it to the tree set
                accounts.add(new Account(id, bal));
            }
        }

        // for each account, call System.out.println(account) (which calls Account.toString)
        accounts.forEach(System.out::println);
    }

    // just to make it a bit cleaner, not necessary
    private static Stream<Account> stream(int id) {
        return accounts.stream().filter(acc -> acc.getId() == id);
    }
}
