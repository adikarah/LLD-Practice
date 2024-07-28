package main.vendingmachine.utility;

/**
 * A parameterized utility class to hold two different object.
 *
 * @author Praveen Tripathi
 */


public record Bucket<E1, E2>(E1 first, E2 second) {

}
