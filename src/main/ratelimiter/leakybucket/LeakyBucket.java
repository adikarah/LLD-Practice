package main.ratelimiter.leakybucket;

import main.ratelimiter.RateLimiter;

import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class LeakyBucket implements RateLimiter {

    private final BlockingQueue<UUID> queue;

    public LeakyBucket(int capacity, int leakRate) {
        queue = new LinkedBlockingQueue<>(capacity);

        // Start a scheduled task that removes requests at a fixed rate (leaking)
        Executors.newSingleThreadScheduledExecutor()
                .scheduleAtFixedRate(() -> {
                    if (!queue.isEmpty()) {
                        queue.poll(); // Remove one request at a time
                    }
                }, 0, leakRate, TimeUnit.MILLISECONDS);
    }

    @Override
    public boolean grantAccess(UUID requestId) {
        if (queue.remainingCapacity() > 0) {
            queue.add(requestId);
            return true;
        }
        return false;
    }
}
