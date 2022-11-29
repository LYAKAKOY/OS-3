public class Consumer implements Runnable {

    static boolean flag = false;
    private int number;
    Store store;
    Consumer(Store store, int number){
        this.store=store;
        this.number = number;
    }
    public void run(){
        while (true) {
            if (!Thread.currentThread().isInterrupted())
                store.get(number);
            if (flag && Store.getTitleProducts() == 0)
                break;
        }
    }
}
