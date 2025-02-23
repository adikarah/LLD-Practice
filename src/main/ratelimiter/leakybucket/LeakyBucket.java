package main.ratelimiter.leakybucket;

import main.ratelimiter.RateLimiter;

import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class LeakyBucket implements RateLimiter {

    BlockingQueue<UUID> queue;

    public LeakyBucket(int capacity) {
        queue = new LinkedBlockingQueue<>(capacity);
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
