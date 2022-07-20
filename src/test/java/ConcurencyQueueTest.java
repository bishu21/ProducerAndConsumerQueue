import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ConcurencyQueueTest {
    @Test
    void testMulti() throws InterruptedException {
        ConcurrencyQueue<Integer> queue = new ConcurrencyQueueImpl<>(3);
        ExecutorService executorService = Executors.newFixedThreadPool(10);

//        executorService.submit(() -> queue.poll());
//        executorService.submit(() -> queue.poll());
//        executorService.submit(() -> queue.poll());
        executorService.submit(() -> queue.push(1));
        executorService.submit(() -> queue.push(2));
        executorService.submit(() -> queue.push(3));
        executorService.submit(() -> queue.push(4));
        executorService.submit(() -> queue.push(5));
        executorService.submit(() -> queue.push(6));

        executorService.submit(() -> queue.poll());
        executorService.submit(() -> queue.poll());

        executorService.awaitTermination(20, TimeUnit.SECONDS);

    }
}
