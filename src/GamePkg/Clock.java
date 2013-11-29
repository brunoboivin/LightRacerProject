package GamePkg;

/** Clock class used to define the timing of the game.
 * 
 * @author Shahrzad Tighnavardmollasaraei <shahrzad.tighnavardmollasaraei@mail.mcgill.ca>
 * @version 1.0
 * @since 2013-11-08
 */

public class Clock {
	
	/**
	 * The number of milliseconds that constitute one cycle.
	 */
	private float millisecondsPerCycle;
	
	/**
	 * Last Clock update time which is used to calculate change in time
	 */
	private long lastClockUpdate;
	
	/**
	 * The number of elapsed cycles not polled yet.
	 */
	private int numberOfElapsedCycles;
	
	/**
	 * The amount of excess time remaining for the next elapsed cycle.
	 */
	private float excessTimeForNextElapsedCycles;
	
	/**
	 * Returns true if clock is paused.
	 */
	private boolean isClockPaused;
	
	/**
	 * Creates a new clock with number of cycles elapsed per second.
	 * @param clockCyclesPerSecond The number of cycles elapsed per second.
	 */
	public Clock(float clockCyclesPerSecond) {
		setClockCyclesPerSecond(clockCyclesPerSecond);
		resetClock();
	}
	
	/**
	 * Sets the number of cycles elapsed per second
	 * @param cyclesPerSecond The number of cycles per second.
	 */
	public void setClockCyclesPerSecond(float cyclesPerSecond) {
		this.millisecondsPerCycle = (1.0f / cyclesPerSecond) * 1000;
	}
	
	/**
	 * Resets clock parameters
	 */
	public void resetClock() { 
		this.numberOfElapsedCycles = 0;
		this.excessTimeForNextElapsedCycles = 0.0f;                 
		this.lastClockUpdate = getCurrentTime();
		this.isClockPaused = false;
	}
	
	/**
	 * Updates the clock parameters.
	 * Only when the clock is unpaused the number of elapsed cycles and 
	 * excess time towards elapsed cycles will be calculated.
	 * Clock should be updated i.e. updateClock called every frame 
	 * even if it is paused
	 */
	public void updateClock() { 
		//Get the current time and calculate the delta time.
		long currentUpdate = getCurrentTime();
		float delta = (float)(currentUpdate - lastClockUpdate) + excessTimeForNextElapsedCycles;
		
		//Update the number of elapsed and excess ticks if not paused.
		if(!isClockPaused) {
			this.numberOfElapsedCycles += (int)Math.floor(delta / millisecondsPerCycle);
			this.excessTimeForNextElapsedCycles = delta % millisecondsPerCycle;
		}
		
		//Set the last update time for the next update cycle.
		this.lastClockUpdate = currentUpdate;
	}
	
	/**
	 * Pauses or unpauses the clock. While paused, a clock will not update
	 * elapsed cycles or cycle excess, though the {@code updateClock} method should
	 * still be called every frame to prevent issues.
	 * @param paused Whether or not to pause clock.
	 */
	public void setPaused(boolean paused) { 
		this.isClockPaused = paused;
	}
	
	/**
	 * Checks to see if the clock is currently paused.
	 * @return Whether or not this clock is paused.
	 */
	public boolean isClockPaused() { 
		return isClockPaused;
	}
	
	/**
	 * Checks to see if a cycle has elapsed for this clock yet. If so,
	 * the number of elapsed cycles will be decremented by one.
	 * @return Whether or not a cycle has elapsed.
	 * @see peekElapsedCycle
	 */
	public boolean hasElapsedCycle() { 
		if(numberOfElapsedCycles > 0) {
			this.numberOfElapsedCycles--;
			return true;
		}
		return false;
	}
	
	/**
	 * Checks to see if a cycle has elapsed for this clock yet. Unlike
	 * {@code hasElapsedCycle}, the number of cycles will not be decremented
	 * if the number of elapsed cycles is greater than 0.
	 * @return Whether or not a cycle has elapsed.
	 * @see hasElapsedCycle
	 */
	public boolean peekElapsedCycle() {
		return (numberOfElapsedCycles > 0);
	}
	
	/**
	 * Calculates the current time in milliseconds using the computer's high
	 * resolution clock. This is much more reliable than
	 * {@code System.getCurrentTimeMillis()}, and quicker than
	 * {@code System.nanoTime()}.
	 * @return The current time in milliseconds.
	 */
	private static final long getCurrentTime() {
		return (System.nanoTime() / 1000000L);
	}

}
