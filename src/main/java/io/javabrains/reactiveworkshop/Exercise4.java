package io.javabrains.reactiveworkshop;

import java.io.IOException;

// import io.javabrains.reactiveworkshop.SynchronizerGate.events;

public class Exercise4 {

    public static void main(String[] args) throws IOException {

		// SynchronizerGate gate = new SynchronizerGate();

        // Use ReactiveSources.intNumberMono()

        // Print the value from intNumberMono when it emits
        // ReactiveSources.intNumberMono().doOnError(gate.errConsumer)
		// 	.subscribe(num -> {
		// 			System.out.println(num);
		// 			gate.raiseEvent(events.COMPLETE);
		// 		});

        // Get the value from the Mono into an integer variable
		Integer number = ReactiveSources.intNumberMono().blockOptional().orElse(null);
		System.out.println(number);

       // gate.waitToComplete();
    }

}
