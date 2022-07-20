import java.util.LinkedList;
import java.util.Queue;

public class ConcurrencyQueueImpl<T> implements ConcurrencyQueue<T> {

    private Queue<T> queue;
    private int capacity;


    public ConcurrencyQueueImpl(int capacity) {
        this.capacity = capacity;
        this.queue = new LinkedList<>();
    }


    @Override
    public synchronized void push(T data)  {
        System.out.println("Inserting Data " + data +" into queue by Thread = "+ Thread.currentThread().getId());


        try {
//            Thread.sleep(5*1000);
            while (capacity == queue.size()) {
                // wait for consumer to consume the message
                System.out.println("Queue is Full ="+ queue +" , Waiting by Thread = "+ Thread.currentThread().getId());
                wait();

            }
            if (queue.size() == 0) {
                System.out.println("Queue was Empty , Notify all the threads");
                notifyAll();
            }
            queue.add(data);
            System.out.println("Inserted Data " + data +" into queue by Thread = "+ Thread.currentThread().getId());

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized T poll() {
        System.out.println("Polling Data from queue by Thread = "+ Thread.currentThread().getId());
        try {
            while (queue.isEmpty()) {
                /// wait for producer to push data
                System.out.println("Queue is Empty , Waiting by Thread = "+ Thread.currentThread().getId());
                wait();
            }
            if (queue.size() == capacity) {
                System.out.println("Queue was full , Notify all the threads");
                notifyAll();
            }
            T item = queue.poll();
            System.out.println("Polled Data "+item+" from queue by Thread = "+ Thread.currentThread().getId());
            return item;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
