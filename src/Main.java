import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Main extends JFrame implements KeyListener {

    static ArrayList<Thread> producersList = new ArrayList<>();
    static ArrayList<Thread> consumersList = new ArrayList<>();
    static Store store = new Store();

    public Main() {
        setSize(0, 0);
        setVisible(true);
        addKeyListener(this);
    }

    public static void main(String[] args) {

        Main f = new Main();
        for (int i = 0; i < 3; ++i) {
            Producer producer = new Producer(store,++i);
            producersList.add(new Thread(producer));
            producersList.get(--i).start();

        }
        for (int i = 0; i < 2; ++i) {
            Consumer consumer = new Consumer(store, ++i);
            consumersList.add(new Thread(consumer));
            consumersList.get(--i).start();
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode()==KeyEvent.VK_Q) {
           for (Thread a : producersList) {
               a.interrupt();
           }
           for (Thread a : consumersList) {
                a.interrupt();
           }
           Thread final_consumer1 = new Thread(new Runnable() {
               @Override
               public synchronized void run() {
                   while (Store.getTitleProducts() != 0) {
                       int value = Store.rnd(100);
                       if (value > Store.product) {
                           value = Store.product;
                       }
                       Store.product -= value;
                       System.out.println("Покупатель" + 1 + " купил " + value + " товар(ов)");
                       System.out.println("Товаров на складе: " + Store.product);
                       try {
                           Thread.sleep(1000);
                       } catch (InterruptedException ex) {
                           throw new RuntimeException(ex);
                       }
                   }
               }
           });
            Thread final_consumer2 = new Thread(new Runnable() {
                @Override
                public synchronized void run() {
                    while (Store.getTitleProducts() != 0) {
                        int value = Store.rnd(100);
                        if (value > Store.product) {
                            value = Store.product;
                        }
                        Store.product -= value;
                        System.out.println("Покупатель" + 2 + " купил " + value + " товар(ов)");
                        System.out.println("Товаров на складе: " + Store.product);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
            });
            final_consumer1.start();
            final_consumer2.start();
            try {
                final_consumer1.join();
                final_consumer2.join();
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
            System.exit(0);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}