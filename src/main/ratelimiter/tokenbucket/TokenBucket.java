package main.ratelimiter.tokenbucket;

import main.ratelimiter.RateLimiter;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class TokenBucket implements RateLimiter {

    private int bucketCapacity;

    private int refreshRate;

    private AtomicInteger currentCapacity;

    private AtomicLong lastUpdatedTime;

    public TokenBucket(int bucketCapacity, int refreshRate) {
        this.bucketCapacity = bucketCapacity;
        this.refreshRate = refreshRate;
        this.currentCapacity.set(bucketCapacity);
        this.lastUpdatedTime.set(System.currentTimeMillis());

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

    public void refreshBucket() {
        long currentTime = System.currentTimeMillis();
        int additionalToken = (int) ((currentTime - lastUpdatedTime.get()) / 1000 * refreshRate);
        int currentCapacity = Math.min(this.currentCapacity.get() + additionalToken, bucketCapacity);
        this.currentCapacity.set(currentCapacity);
        this.lastUpdatedTime.set(currentTime);
    }
}
