package producer_consumer;

import java.util.Random;

public class Consumer extends Thread {

    private String name;
    private String type;
    private ServeryArrayQueue servery;

    Product item;

    public Consumer(String name, String type, ServeryArrayQueue servery) {
        this.name = name;
        this.type = type;
        this.servery = servery;
    }

    public String getCName() {
        return name;
    }

    public void setCName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public void run() {
        int count = 0;
        for (int i=0;i<20;i++) {
            while (servery.empty() || !servery.compatibility(this)) {
                synchronized (servery) {
                    try {
                        servery.wait();
                        servery.notifyAll();
                    } catch (Exception e) {

                    }
                }
            }
            synchronized (servery) {
                item = servery.retrieve(this);
                count++;
                                Random ran = new Random();
                int x = ran.nextInt(4001) + 1000;
                System.out.println("Consumer " + this.getCName() + " consumed an item: Type: " + item.getType() + ", Kind: " + item.getKind() + ", Name: " + item.getName()+", Code: "+item.getCode()+", Time: "+x/1000+" seconds");
                try {
                    Thread.sleep(x);
                } catch (InterruptedException ex) {

                }
            }

        }

    }
}
