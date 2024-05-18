import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class BankAccount {
    private double balance = 0;
    private final Lock lock = new ReentrantLock();

    // Deposit method with lock
    public void deposit(double amount) {
        lock.lock();
        try {
            // Critical section
            double newBalance = balance + amount;
            balance = newBalance;
        } finally {
            lock.unlock();
        }
    }
    public double getBalance() {
        lock.lock();
        try {
            return balance;
        } finally {
            lock.unlock();
        }
    }
}

public class Main {
    public static void main(String[] args) throws InterruptedException {
        BankAccount account = new BankAccount();
        Thread[] threads = new Thread[1000];
        for (int i = 0; i < 1000; i++) {
            threads[i] = new Thread(() -> account.deposit(100));
            threads[i].start();
        }
        for (int i = 0; i < 1000; i++) {
            threads[i].join();
        }
        System.out.println("Final balance: " + account.getBalance());
    }
}
