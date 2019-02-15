package edu.smith.cs.csc212.p4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This represents a place in our text adventure.
 * @author jfoley
 *
 */
public class Place {
	/**
	 * This is a list of places we can get to from this place.
	 */
	private List<Exit> exits;
	/**
	 * This is a list of SecretExits that the user has not yet uncovered.
	 */
	private List<Exit> secretExits;
	/**
	 * This is a list of NightExits that can only be viewed at night hours.
	 */
	private List<Exit> nightExits;
	/**
	 * This is a list of DayExits that can only be viewed during day hours.
	 */
	private List<Exit> dayExits;
	/**
	 * This is the identifier of the place.
	 */
	private String id;
	/**
	 * What to tell the user about this place if it is day time.
	 */
	private String description;
	/**
	 * What to tell the user about this place if it is night time.
	 */
	private String nightDescription;
	/**
	 * Whether reaching this place ends the game.
	 */
	private boolean terminal;
	
	/**
	 * Internal only constructor for Place. Use {@link #create(String, String)} or {@link #terminal(String, String)} instead.
	 * @param id - the internal id of this place.
	 * @param description - the user-facing description of the place.
	 * @param nightDescription - the description of the place at night, or just the regular description of the place if there is no special night description.
	 * @param terminal - whether this place ends the game.
	 */
	private Place(String id, String description, String nightDescription, boolean terminal) {
		this.id = id;
		this.description = description;
		this.nightDescription = nightDescription;
		this.exits = new ArrayList<>();
		this.secretExits = new ArrayList<>();
		this.nightExits = new ArrayList<>();
		this.dayExits = new ArrayList<>();
		this.terminal = terminal;
	}
	
	/**
	 * Create an exit for the user to navigate to another Place.
	 * Sort exits into different lists based on what type of exit they are.
	 * @param exit - the description and target of the other Place.
	 */
	public void addExit(Exit exit) {
		if (exit instanceof NightExit) {
			this.nightExits.add(exit);
		}
		else if (exit instanceof DayExit) {
			this.dayExits.add(exit);
		}
		else if (exit.isSecret()) {
			this.secretExits.add(exit);
		}
		this.exits.add(exit);
	}
	
	/**
	 * For gameplay, whether this place ends the game.
	 * @return true if this is the end.
	 */
	public boolean isTerminalState() {
		return this.terminal;
	}
	
	/**
	 * The internal id of this place, for referring to it in {@link Exit} objects.
	 * @return the id.
	 */
	public String getId() {
		return this.id;
	}
	
	/**
	 * The narrative description of this place.
	 * @return what we show to a player about this place.
	 */
	public String getDescription(GameTime gameTime) {
		if (gameTime.isNightTime()) {
			return this.nightDescription;
		}
		return this.description;
	}

	/**
	 * Get a view of the exits from this place that the user can see, for navigation.
	 * @return all the exits that are currently visible to the user.
	 */
	public List<Exit> getVisibleExits(GameTime gameTime) {
		List<Exit> visibleExits = new ArrayList<>(exits);
		/**
		 * If a user has not uncovered secret exits, they will not see them.
		 */
		visibleExits.removeAll(secretExits);
		/**
		 * If it is night time, DayExits are not visible.
		 */
		if (gameTime.isNightTime()) {
			visibleExits.removeAll(dayExits);
		}
		/**
		 * If it is day time, NightExits are not visible.
		 */
		else {
			visibleExits.removeAll(nightExits);
		}
		return Collections.unmodifiableList(visibleExits);
	}
	
	/**
	 * Get a view of the secretExits from this place, in case a user searches for them.
	 * @return all the secret exits from this place
	 */
	public List<Exit> getSecretExits() {
		return secretExits;
	}
	
	/**
	 * This is a terminal location (good or bad).
	 * @param id - this is the id of the place (for creating {@link Exit} objects that go here).
	 * @param description - this is the description of the place.
	 * @return the Place object.
	 */
	public static Place terminal(String id, String description) {
		return new Place(id, description, description, true);
	}
	
	/**
	 * This is a terminal location that has a different description at night.
	 * @param id - this is the id of the place (for creating {@link Exit} objects that go here).
	 * @param description - this is the description of the place.
	 * @return the Place object.
	 */
	public static Place terminal(String id, String description, String nightDescription) {
		return new Place(id, description, nightDescription, true);
	}
	
	/**
	 * Create a place with an id and description.
	 * @param id - this is the id of the place (for creating {@link Exit} objects that go here).
	 * @param description - this is what we show to the user.
	 * @return the new Place object (add exits to it).
	 */
	public static Place create(String id, String description) {
		return new Place(id, description, description, false);
	}
	
	/**
	 * Create a place with an id, a description, and a different description at night.
	 * @param id - this is the id of the place (for creating {@link Exit} objects that go here).
	 * @param description - this is what we show to the user.
	 * @return the new Place object (add exits to it).
	 */
	public static Place create(String id, String description, String nightDescription) {
		return new Place(id, description, nightDescription, false);
	}
	
	/**
	 * Implements what we need to put Place in a HashSet or HashMap.
	 */
	public int hashCode() {
		return this.id.hashCode();
	}
	
	/**
	 * Give a string for debugging what place is what.
	 */
	public String toString() {
		return "Place("+this.id+" with "+this.exits.size()+" exits.)";
	}
	
	/**
	 * Whether this is the same place as another.
	 */
	public boolean equals(Object other) {
		if (other instanceof Place) {
			return this.id.equals(((Place) other).id);
		}
		return false;
	}
	
}
