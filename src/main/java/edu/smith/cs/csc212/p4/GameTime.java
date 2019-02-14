package edu.smith.cs.csc212.p4;

public class GameTime {
	
	/**
	 * What hour it is in the game.
	 */
	private int hour;
	/**
	 * The amount of hours that have passed since the GameTime has been created.
	 * Note that this is based on how many times increaseHour has been called and is not based on real time.
	 */
	private int hoursPassed;
	
	/**
	 * Constructs a GameTime at the default of 0:00.
	 */
	public GameTime() {
		hour = 0;
		hoursPassed = 0;
	}
	
	/**
	 * Constructs a GameTime with a given initialTime.
	 * @param initialTime the time at which the GameTime starts.
	 * @precondition initialTime is between 0 and 23, inclusive. Otherwise the time is set to 0.
	 */
	public GameTime(int initialTime) {
		if (initialTime <= 23 && initialTime >= 0) {
			hour = initialTime;
		}
		else {
			hour = 0;
		}
		hoursPassed = 0;
	}
	
	/**
	 * Tells the user what hour it is.
	 * @return the time
	 */
	public int getHour() {
		return hour;
	}
	
	/**
	 * Tells the user how many hours have passed since they created the GameTime.
	 * Note that this is based on how many times the user has called increaseHour and is not based in real time.
	 * @return the amount of hours that have passed
	 */
	public int getHoursPassed() {
		return hoursPassed;
	}
	
	/**
	 * Makes it so that 1 hour has passed.
	 * Also increments hoursPassed by 1.
	 */
	public void increaseHour() {
		if (hour == 23) {
			hour = 0;
		}
		else {
			hour++;
		}
		hoursPassed++;
	}

}
