package main.ratelimiter.tokenbucket;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RateLimiterApplication {

    public static void main(String[] args) {
        UserBucketCreator userBucketCreator = new UserBucketCreator(10, 1);

        ExecutorService executorService = Executors.newFixedThreadPool(12);

        for (int i = 0; i < 12; i++) {
            UUID requestId = UUID.randomUUID();
            executorService.execute(() -> userBucketCreator.accessApplication(requestId));
        }

        executorService.shutdown();


    }
}
