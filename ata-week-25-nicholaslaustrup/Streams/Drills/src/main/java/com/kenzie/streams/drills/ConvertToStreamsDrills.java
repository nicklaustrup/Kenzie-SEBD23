package com.kenzie.streams.drills;

import com.kenzie.streams.drills.resources.Card;
import com.kenzie.streams.drills.resources.Dish;
import com.kenzie.streams.drills.resources.Rank;
import com.kenzie.streams.drills.resources.Suit;
import com.kenzie.streams.drills.resources.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Convert the following methods from using for loops to
 * using Streams.
 */
public class ConvertToStreamsDrills {

    /**
     * Calculates the square roots of a list of numbers.
     * @param numbers a list of numbers
     * @return a list of square roots of the numbers.
     */
    public static List<Double> returnSquareRoot(List<Integer> numbers) {
        if (numbers == null) {return Collections.emptyList();}
        return numbers.stream()
                .map(Math::sqrt)
                .collect(Collectors.toList());
    }

    /**
     * Returns a list of ages of the provided Users.
     * @param users a list of Users.
     * @return a list of Users' ages.
     */
    public static List<Integer> getAgeFromUsers(List<User> users) {
        if (users == null) {return Collections.emptyList();}
        return users.stream()
                .map(User::getAge)
                .collect(Collectors.toList());
    }

    /**
     * Returns a list of distinct ages of the provided Users.
     * @param users a list of Users.
     * @return a distinct list of Users ages.
     */
    public static List<Integer> getDistinctAges(List<User> users) {
        if (users == null) {return Collections.emptyList();}

        return users.stream()
                .map(User::getAge)
                .distinct()
                .collect(Collectors.toList());
    }

    /**
     * Returns a sublist of the input list of Users, based on a provided limit.
     * @param users a list of Users.
     * @param limit the maximum size of the sublist to return.
     * @return a sublist of the input list of Users.
     */
    public static List<User> getLimitedUserList(List<User> users, int limit) {
        if (users == null) {return Collections.emptyList();}

        return users.stream()
                .limit(limit)
                .collect(Collectors.toList());
    }

    /**
     * Counts the users in the input list whose age is > 25.
     * @param users a list of Users.
     * @return the total count of Users whose age is > 25.
     */
    public static long countUsersOlderThan25(List<User> users) {
        if (users == null) {return 0;}

        return users.stream().filter(user -> user.getAge() > 25).count();
    }

    /**
     * Searches the given list of Users for one with a particular name.
     *
     * Returns on the first instance of a User with the matching name.
     *
     * @param users a list of Users.
     * @param name the name to search for.
     * @return an Optional User whose name matches the input
     */
    public static Optional<User> findAny(List<User> users, String name) {
        if (users == null) {return Optional.empty();}

        return users.stream().filter(user -> user.getName().equals(name)).findFirst();
    }

    /**
     * Sorts the input list of Dishes by their calorie value, first removing
     * any that are above 400 calories.
     * @param menu a list of Dishes.
     * @return Dishes that are low in calories, sorted by number of calories.
     */
    public static List<String> sortDishesByCalories(List<Dish> menu) {
        if (menu == null) {return Collections.emptyList();}

        return Optional.ofNullable(menu).orElse(Collections.emptyList())
                .stream()
                .filter(calories -> calories.getCalories() < 400)
                .sorted(Comparator.comparingInt(Dish::getCalories))
                .map(Dish::getName)
                .collect(Collectors.toList());
    }

    /**
     * Generate a new deck of cards.  Make sure you have a card of every suit and rank.
     * @return every card that should be in a deck
     */
    public static List<Card> newDeck() {
        return Stream.of(Suit.values())
                .flatMap(suit -> Stream.of(Rank.values())
                        .map(rank -> new Card(suit, rank)))
                .collect(Collectors.toList());
    }
}
