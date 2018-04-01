package producer_consumer;


public class Main {

    public static final String[] menu = {
        "drink_coffee_frappe", "drink_coffee_espresso", "drink_juice_orange", "drink_juice_mix", "food_sandwich_turkey", "food_sandwich_tuna", "food_pasta_carbonara", "food_pasta_tomato"
    };

    public static void main(String[] args) {
        
        ServeryArrayQueue productServery = new ServeryArrayQueue(10);

        Thread producerThread1 = new Thread(new Producer("Chef", "food", productServery));
        Thread producerThread2 = new Thread(new Producer("Barista", "drink", productServery));
        Thread consumerThread1 = new Thread(new Consumer("Waiter_A", "food", productServery));
        Thread consumerThread2 = new Thread(new Consumer("Waiter_B", "drink", productServery));
        
        producerThread1.start();
        producerThread2.start();
        consumerThread1.start();
        consumerThread2.start();

    }

}
