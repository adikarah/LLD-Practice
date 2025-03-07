package main.ratelimiter.leakybucket;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RateLimiterApplication {

    public static void main(String[] args) {
        int bucketCapacity = 5;   // Max 5 requests can be held at a time
        int leakRate = 1000;      // Leak 1 request per second

        UserBucketCreator userBucketCreator = new UserBucketCreator(bucketCapacity, leakRate);

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 10; i++) {
            UUID requestId = UUID.randomUUID();
            executorService.execute(() -> userBucketCreator.accessApplication(requestId));

            try {
                Thread.sleep(200);  // Simulating request intervals
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        executorService.shutdown();
    }
}
