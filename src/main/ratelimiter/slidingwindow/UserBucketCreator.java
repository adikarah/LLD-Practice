package main.ratelimiter.slidingwindow;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UserBucketCreator {

    private final UUID userId;
    private Map<UUID, SlidingWindow> userMap;

    public UserBucketCreator(int capacity, long refreshRate) {
        this.userId = UUID.randomUUID();
        this.userMap = new HashMap<>();
        userMap.put(userId, new SlidingWindow(capacity, refreshRate));
    }

    public void accessApplication(UUID requestId) {
        if (userMap.get(userId).grantAccess(requestId)) {
            System.out.println(Thread.currentThread().getName() + "-> can access application");
        } else {
            System.out.println(Thread.currentThread().getName() + "-> cannot access application");
        }
    }
}
