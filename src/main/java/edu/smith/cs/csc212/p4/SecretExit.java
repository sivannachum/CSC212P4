package edu.smith.cs.csc212.p4;

/**
 * This class represents an exit from one place to another that can only be seen if a user searches for it.
 * @author sivan
 *
 */
public class SecretExit extends Exit {
	/**
	 * SecretExits begin hidden, but if a user finds them, they must then become not hidden.
	 */
	private boolean hidden;
	
	/**
	 * Create a new SecretExit.
	 * @param target - where it goes.
	 * @param description - how it looks.
	 */
	public SecretExit(String target, String description) {
		super(target, description);
		// SecretExits always begin hidden.
		hidden = true;
	}
	
	@Override
	/**
	 * This method returns an Exit's secret value.
	 * SecretExits are secret until they are searched for and found.
	 * @return whether an exit is secret or not
	 */
	public boolean isSecret() {
		return hidden;
	}
	
	@Override
	/**
	 * This method makes a SecretExit visible to a user.
	 */
	public void search() {
		hidden = false;
	}

}
