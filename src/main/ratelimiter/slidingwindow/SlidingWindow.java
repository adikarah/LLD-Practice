package main.ratelimiter.slidingwindow;

import main.ratelimiter.RateLimiter;

import java.util.Queue;
import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedQueue;

public class SlidingWindow implements RateLimiter {

    int windowCapacity;
    long timeWindowInSeconds;

    Queue<Request> windowQueue;

    public SlidingWindow(int windowCapacity, long timeWindowInSeconds) {
        this.windowCapacity = windowCapacity;
        this.timeWindowInSeconds = timeWindowInSeconds;
        this.windowQueue = new ConcurrentLinkedQueue<>();
    }


    @Override
    public boolean grantAccess(UUID requestId) {
        long currentTime = System.currentTimeMillis();

        checkAndUpdateWindowQueue(currentTime);

        if (windowQueue.size() < windowCapacity) {
            windowQueue.offer(new Request(requestId, currentTime));
            return true;
        }
        return false;
    }

    private void checkAndUpdateWindowQueue(long currentTime) {
        if (this.windowQueue.isEmpty()) {
            return;
        }

        var calculatedTime = (currentTime - this.windowQueue.peek().timeStamp()) / 1000;
        while (!this.windowQueue.isEmpty() && calculatedTime >= timeWindowInSeconds) {

            this.windowQueue.poll();
        }
    }
}
