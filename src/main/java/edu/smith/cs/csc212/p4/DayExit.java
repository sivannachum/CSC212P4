package edu.smith.cs.csc212.p4;

/**
 * This class represents an exit from one place to another that can only be seen during the Day.
 * @author sivan
 *
 */
public class DayExit extends Exit {
	/**
	 * Create a new DayExit.
	 * @param target - where it goes.
	 * @param description - how it looks.
	 */
	public DayExit(String target, String description) {
		super(target, description);
	}
}
