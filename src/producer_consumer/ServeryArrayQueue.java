package producer_consumer;

public class ServeryArrayQueue {

    Product a[];
    int head, tail;

    public ServeryArrayQueue(int size) {
        this.a = new Product[size];
        this.head = this.tail = -1;

    }

    public synchronized boolean insert(Product item) {
        int p;
        p = (tail + 1) % a.length;

        if (p == head) {
            System.out.println("Buffer full");
            return false;
        } else {
            tail = p;
            a[tail] = item;
            if (head == -1) {
                head = 0;
            }
            return true;
        }
    }

    public synchronized Product retrieve(Consumer c) {
        Product result = a[head];
        if (head == tail) {
            head = tail = -1;
        } else {
            head = (head + 1) % a.length;
        }
        return result;
    }    
    
    public boolean empty() {
        return head == -1;
    }

    public boolean compatibility(Consumer c) {
        if (!c.getType().equals(a[head].getType())) {
            return false;
        } else {
            return true;
        }

    }
}
