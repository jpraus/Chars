package com.praus.chars.character;

/**
 *
 * @author Jiří Praus <jpraus@kerio.com>
 */
public class FatigueException extends Exception {	
	
	public enum Cause {
		MOVEMENT
	}
	
	private final Cause cause;

	public FatigueException(Cause cause) {
		this.cause = cause;
	}

	public Cause getFatigueCause() {
		return cause;
	}
}
