package main.ratelimiter.tokenbucket;

import main.ratelimiter.RateLimiter;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class TokenBucket implements RateLimiter {

    private final int bucketCapacity;
    private final int refreshRate; // Tokens added per second
    private final AtomicInteger currentCapacity;
    private final AtomicLong lastUpdatedTime;

    public TokenBucket(int bucketCapacity, int refreshRate) {
        this.bucketCapacity = bucketCapacity;
        this.refreshRate = refreshRate;
        this.currentCapacity = new AtomicInteger(bucketCapacity); // Proper initialization
        this.lastUpdatedTime = new AtomicLong(System.currentTimeMillis());
    }

    @Override
    public boolean grantAccess(UUID requestId) {
        refreshBucket();
        if (currentCapacity.get() > 0) {
            currentCapacity.decrementAndGet();
            return true;
        }
        return false;
    }

    private void refreshBucket() {
        long currentTime = System.currentTimeMillis();
        long elapsedTimeInSeconds = (currentTime - lastUpdatedTime.get()) / 1000;

        if (elapsedTimeInSeconds > 0) { // Only update if at least a second has passed
            int additionalTokens = (int) (elapsedTimeInSeconds * refreshRate);
            int updatedCapacity = Math.min(currentCapacity.get() + additionalTokens, bucketCapacity);
            currentCapacity.set(updatedCapacity);
            lastUpdatedTime.set(currentTime);
        }
    }
}
