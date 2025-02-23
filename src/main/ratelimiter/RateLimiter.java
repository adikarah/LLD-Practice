package main.ratelimiter;

import java.util.UUID;

public interface RateLimiter {

    boolean grantAccess(UUID requestId);
}
