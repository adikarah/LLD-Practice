package main.ratelimiter.slidingwindow;

import java.util.UUID;

public record Request(UUID requestId, long timeStamp) {
}
