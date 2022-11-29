public class Producer implements Runnable {
    private final int number;
    Store store;
    Producer(Store store, int number){
        this.store=store;
        this.number = number;
    }
    public void run(){
        while (true) {
            if (!Thread.currentThread().isInterrupted())
                store.put(number);
        }
    }
}
