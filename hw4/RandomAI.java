import java.util.Random;

/**
 * @author hkim944
 * @version 1.0
 */

public class RandomAI extends AI {

    private static final Random randomizer = new Random();

    /**
     * Creates a RandomAI with two coordinates
     * @param cannonTarget Location at which cannon is aimed
     * @param secretHQ Location of secret headquarters
     */
    public RandomAI(Coordinates cannonTarget, Coordinates secretHQ) {
        super(cannonTarget, secretHQ);
    }

    /**
     * @return Whether cannon should swap target
     */
    @Override
    public boolean shouldSwapCannonTarget() {
        return randomizer.nextBoolean();
    }

    /**
     * @return Whether cannon should self destruct
     */
    @Override
    public boolean shouldSelfDestruct() {
        return randomizer.nextBoolean();
    }
}