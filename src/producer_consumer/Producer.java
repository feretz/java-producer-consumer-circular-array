package producer_consumer;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Producer extends Thread {

    private String name;
    private String type;
    private ServeryArrayQueue servery;

    public Producer(String name, String type, ServeryArrayQueue servery) {
        this.name = name;
        this.type = type;
        this.servery = servery;
    }

    public String getPName() {
        return name;
    }

    public void setPName(String name) {
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
        int x = 0,y = 0;
        Random ran;
        String choice = null;
        for (int i = 0; i < 20; i++) {
            if (this.getType() == "food") {
                ran = new Random();
                x = ran.nextInt(4) + 4;
                choice = Main.menu[x];
            } else if (this.getType() == "drink") {
                ran = new Random();
                x = ran.nextInt(4) + 0;
                choice = Main.menu[x];
            }

            try {
                ran = new Random();
                y = ran.nextInt(4001) + 1000;
                Thread.sleep(y);
                while (!servery.insert(new Product(x + 1, this.type, choice.split("_")[1], choice.substring(choice.lastIndexOf("_") + 1)))) {
                    synchronized (servery) {
                        try {
                            servery.wait();
                        } catch (Exception e) {

                        }
                    }
                }
                synchronized (servery) {
                    servery.notifyAll();
                    System.out.println("Producer " + this.getPName() + " produced an item: Type: " + this.getType() + ", Kind: " + choice.split("_")[1] + ", Name: " + choice.substring(choice.lastIndexOf("_") + 1)+", Code: "+(x+1)+", Time: "+y/1000+" seconds");
                }
            } catch (Exception ex) {
                Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
