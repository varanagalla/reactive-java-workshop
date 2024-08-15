package io.javabrains.reactiveworkshop;

import java.time.Duration;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Consumer;

import lombok.SneakyThrows;

/**
 * SynchronizerGate
 */
public class SynchronizerGate {

	public ReentrantLock lock = new ReentrantLock(true);
	private Condition compelete = lock.newCondition();

	public static enum events {
		COMPLETE,
		ERROR
	}

	private volatile boolean _complete;

	private volatile boolean _error;

	public void raiseEvent(events event) {
		lock.lock();
		try {
			switch (event) {
				case COMPLETE:
					this.compelete.signal();
					break;
				case ERROR:
					this.compelete.signal();
					break;
			}
		} finally {
			lock.unlock();
		}
	}

	@SneakyThrows
	public void waitToComplete() {
		lock.lock();
		try {
			this.compelete.await();
			System.out.println("Completed!!");
		} finally {
			lock.unlock();
		}
	}

	public void waitForCompletion(Duration waitPeriod) {

	}

	public Consumer<? super Throwable> errConsumer = t -> {
		System.out.println(t.getMessage());
		this.raiseEvent(events.ERROR);
	};

	public Consumer<? super Object> successConsumer = o -> {
		System.out.println(o.toString());
		this.raiseEvent(events.COMPLETE);
	};

	public Runnable completeConsumer = () -> this.raiseEvent(events.COMPLETE);

}
