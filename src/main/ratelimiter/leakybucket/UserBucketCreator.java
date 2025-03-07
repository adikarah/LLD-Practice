package main.ratelimiter.leakybucket;

import java.util.HashMap;
import java.util.UUID;

public class UserBucketCreator {

    private final UUID userId;
    private final HashMap<UUID, LeakyBucket> userMap;

    public UserBucketCreator(int capacity, int leakRate) {
        userId = UUID.randomUUID();
        userMap = new HashMap<>();
        userMap.put(userId, new LeakyBucket(capacity, leakRate));
    }

    public void accessApplication(UUID requestId) {
        if (userMap.get(userId).grantAccess(requestId)) {
            System.out.println(Thread.currentThread().getName() + " -> can access application");
        } else {
            System.out.println(Thread.currentThread().getName() + " -> cannot access application (Rate limited)");
        }
    }
}
