package io.javabrains.reactiveworkshop;

import java.io.IOException;

import org.reactivestreams.Subscription;

import io.javabrains.reactiveworkshop.SynchronizerGate.events;
import lombok.SneakyThrows;
import reactor.core.publisher.BaseSubscriber;

public class Exercise5 {

    public static void main(String[] args) throws IOException {

		SynchronizerGate gate = new SynchronizerGate();
		
        // Use ReactiveSources.intNumberMono() and ReactiveSources.userMono()
        // Subscribe to a flux using the error and completion hooks
        // ReactiveSources.intNumberMono().doOnError(gate.errConsumer)
				// .doOnSuccess(gate.successConsumer).subscribe();

        // Subscribe to a flux using an implementation of BaseSubscriber
		ReactiveSources.userFlux().subscribe(new ExSubscriber<>(gate));
		gate.waitToComplete();
    }

	public static class ExSubscriber<T> extends BaseSubscriber<T> {

		private SynchronizerGate gate = new SynchronizerGate();
		
		public ExSubscriber(SynchronizerGate gate) {
			this.gate = gate;
		}
		
		@Override
		public void hookOnSubscribe(Subscription subscription) {
			request(2); //back pressure
		}
		
		@Override
		@SneakyThrows
		public void hookOnNext(T value) {
			System.out.println(value);
			Thread.sleep(20_000);
			request(1); //back pressure
		}

		@Override
		public void hookOnComplete() {
			gate.raiseEvent(events.COMPLETE);
		}

		@Override
		public void hookOnError(Throwable t) {
			System.out.println("Error: " + t.getMessage());
			gate.raiseEvent(events.ERROR);
		}
	}

}
