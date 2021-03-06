package edu.smith.cs.csc212.p4;

/**
 * This class represents an exit from one place to another that can only be seen at night.
 * @author sivan
 *
 */
public class NightExit extends Exit {
	/**
	 * Create a new NightExit.
	 * @param target - where it goes.
	 * @param description - how it looks.
	 */
	public NightExit(String target, String description) {
		super(target, description);
	}
}
