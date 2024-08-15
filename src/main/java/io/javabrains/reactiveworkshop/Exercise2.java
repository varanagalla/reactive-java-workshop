package io.javabrains.reactiveworkshop;

public class Exercise2 {

    public static void main(String[] args) {

		SynchronizerGate gate = new SynchronizerGate();

        // Use ReactiveSources.intNumbersFlux() and ReactiveSources.userFlux()

        // Print all numbers in the ReactiveSources.intNumbersFlux stream
		// ReactiveSources.intNumbersFlux()
		// 	.doOnComplete(() -> gate.raiseEvent(COMPLETE))
		// 	.doOnError(t -> {
		// 			System.out.println("Error: " + t.getMessage());
		// 			gate.raiseEvent(ERROR);
		// 		})
		// 	.subscribe(System.out::println);
		
        // Print all users in the ReactiveSources.userFlux stream
		ReactiveSources.userFlux().subscribe(System.out::println, gate.errConsumer, gate.completeConsumer);
		
		gate.waitToComplete();

    }

}
