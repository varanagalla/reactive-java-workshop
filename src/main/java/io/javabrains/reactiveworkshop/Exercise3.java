package io.javabrains.reactiveworkshop;

import java.io.IOException;

public class Exercise3 {

    public static void main(String[] args) throws IOException {

		SynchronizerGate gate = new SynchronizerGate();

        // Use ReactiveSources.intNumbersFlux()

        // Get all numbers in the ReactiveSources.intNumbersFlux stream
        // into a List and print the list and its size
		ReactiveSources.intNumbersFlux().collectList()
			.doOnError(gate.errConsumer)
			.subscribe(gate.successConsumer);

        gate.waitToComplete();
		
    }

}
