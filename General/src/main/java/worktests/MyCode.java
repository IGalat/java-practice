package worktests;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

enum UserSort {
    BY_AGE(Comparator.comparing(User::getAge)),
    BY_GIVEN_NAME(Comparator.comparing(User::getGivenName)),
    BY_FAMILY_NAME(Comparator.comparing(User::getFamilyName));

    private final Comparator<User> userComparator;

    UserSort(Comparator<User> userComparator) {
        this.userComparator = userComparator;
    }

    public Comparator<User> getUserComparator() {
        return userComparator;
    }
}

final class User {
    private final int age;
    private final String givenName;
    private final String familyName;

    public User(int age, String givenName, String familyName) {
        if (age < 0 || givenName == null || familyName == null) {
            throw new IllegalArgumentException();
        }
        this.age = age;
        this.givenName = givenName;
        this.familyName = familyName;
    }

    public int getAge() {
        return age;
    }

    public String getGivenName() {
        return givenName;
    }

    public String getFamilyName() {
        return familyName;
    }

    @Override
    public String toString() {
        return "User{" +
                "age=" + age +
                ", givenName='" + givenName + '\'' +
                ", familyName='" + familyName + '\'' +
                '}';
    }
}

public class MyCode {
    public List<User> sortBy(List<User> users, UserSort userSort) {
        if (users == null || userSort == null) {
            throw new IllegalArgumentException();
        }
        return users
                .stream()
                .filter(Objects::nonNull)
                .sorted(Comparator.nullsFirst(userSort.getUserComparator()))
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        User user1 = new User(12, "Mike", "Jordan");
        User user2 = new User(34, "Queen", "Elisabeth");

        List<User> users10 = new ArrayList<>();
        users10.add(user1);
        users10.add(user2);
        users10.add(null);

        System.out.println(new MyCode().sortBy(users10, UserSort.BY_AGE));
        System.out.println(new MyCode().sortBy(List.of(user1, user2), UserSort.BY_GIVEN_NAME));
        System.out.println(new MyCode().sortBy(List.of(user1, user2), UserSort.BY_FAMILY_NAME));
    }
}
