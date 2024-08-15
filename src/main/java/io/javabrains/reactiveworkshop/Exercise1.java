package io.javabrains.reactiveworkshop;

public class Exercise1 {

	public static void main(String[] args) {

		// Use StreamSources.intNumbersStream() and StreamSources.userStream()

		// Print all numbers in the intNumbersStream stream
		StreamSources.intNumbersStream().forEach(System.out::println);
		System.out.println("===1====");
		
		// Print numbers from intNumbersStream that are less than 5
		StreamSources.intNumbersStream().filter(num -> num < 5).forEach(System.out::println);
		System.out.println("===2====");
		
		// Print the second and third numbers in intNumbersStream that's greater than 5
		StreamSources.intNumbersStream().filter(num -> num > 5).skip(1).limit(2).forEach(System.out::println);
		System.out.println("===3====");

		// Print the first number in intNumbersStream that's greater than 5.
		// If nothing is found, print -1
		StreamSources.intNumbersStream().filter(num -> num > 5).findFirst().ifPresentOrElse(System.out::println,
				() -> System.out.println(-1));
		System.out.println("===4====");

		// Print first names of all users in userStream
		StreamSources.userStream().map(u -> u.getFirstName()).forEach(System.out::println);
		System.out.println("===5====");
		
		// Print first names in userStream for users that have IDs from number stream
		StreamSources.userStream().filter(u -> StreamSources.intNumbersStream().anyMatch(n -> n == u.getId()))
				.map(u -> u.getFirstName()).forEach(System.out::println);
		System.out.println("===6====");

	}

}
