public class Store {
    public static int product = 200;

    public static int rnd(int max)
    {
        return (int) (Math.random() * ++max);
    }

    public static int getTitleProducts() {
        return product;
    }

    public synchronized void get(int number) {
        while (product == 0) {
            try {
                wait();
            }
            catch (InterruptedException ignored) {
            }
        }
        int value = rnd(100);
        if (value > product) {
            value = product;
        }
        product -= value;
        System.out.println("Покупатель" + number + " купил " + value + " товар(ов)");
        System.out.println("Товаров на складе: " + product);
        notifyAll();
    }
    public synchronized void put(int number) {
        while (product > 80) {
            try {
                wait();
            }
            catch (InterruptedException ignored) {
            }
        }
        int value = rnd(100);
        product += value;
        System.out.println("Производитель" + number + " добавил " + value + " товар(ов)");
        System.out.println("Товаров на складе: " + product);
        notifyAll();
    }
}
