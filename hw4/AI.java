/**
 * @author hkim944
 * @version 1.0
 */

public abstract class AI {

    private boolean destructed;
    private Coordinates cannonTarget;
    private Coordinates secretHQ;

    /**
     * Creates an AI with two coordinates
     * @param cannonTarget Location at which cannon is aimed
     * @param secretHQ Location of secret headquarters
     */
    public AI(Coordinates cannonTarget, Coordinates secretHQ) {
        this.cannonTarget = cannonTarget;
        this.secretHQ = secretHQ;
    }

    /**
     * Creates an AI with two coordinates
     * @param newCoords Coordinate that replace cannon's original aim
     * @return Whether cannonTarget is changed
     */
    public boolean swapCannonTarget(Coordinates newCoords) {
        if (!(destructed && newCoords.equals(cannonTarget))) {
            if (shouldSwapCannonTarget()) {
                cannonTarget = newCoords;
                return true;
            } else if (shouldSelfDestruct()) {
                selfDestruct();
                return false;
            }
        }
        return false;
    }

    /**
     * @return Whether cannon should swap target
     */
    public abstract boolean shouldSwapCannonTarget();

    /**
     * Set destructed to true
     */
    public void selfDestruct() {
        destructed = true;
    }

    /**
     * @return Whether cannon should self destruct
     */
    public abstract boolean shouldSelfDestruct();

    /**
     * @return Sentence in a format "Dr. Chipotle’s guacamole
     *         cannon is currently pointed at (cannonTarget)"
     */
    @Override
    public String toString() {
        return "Dr. Chipotle’s guacamole cannon is currently"
                + " pointed at " + cannonTarget.toString();
    }

    /**
     * @return Whether cannon is destructed
     */
    public boolean getDestructed() {
        return destructed;
    }

    /**
     * @return Location at which cannon is aimed
     */
    public Coordinates getCannonTarget() {
        return cannonTarget;
    }

    /**
     * @return Location of secret headquarters
     */
    public Coordinates getSecretHQ() {
        return secretHQ;
    }
}