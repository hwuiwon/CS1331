/**
 * @author hkim944
 * @version 1.0
 */

public class DoctorCS {

    private AI ai;
    private final String secretIdentity;
    private int jlaid;
    private boolean safe;

    /**
     * Creates a DoctorCS with an ai, secretIdentity, and jlaid
     * @param ai AI object
     * @param secretIdentity Doctor CS’s hidden identity
     * @param jlaid Doctor CS’s Justice League of America ID
     */
    public DoctorCS(AI ai, String secretIdentity, int jlaid) {
        this.ai = ai;
        this.secretIdentity = secretIdentity;
        this.jlaid = jlaid;
        safe = false;
    }

    /**
     * If ai is RogueAI, lower firewall until it is less or equal to 0.
     * Then swap the target of a cannon to secret headquarters.
     * If succeeds, change boolean safe to true.
     */
    public void saveTheDay() {
        if (ai instanceof RogueAI) {
            while (((RogueAI) ai).getFirewallProtection() > 0) {
                ((RogueAI) ai).lowerFirewall();
            }
            ai.swapCannonTarget(ai.getSecretHQ());
            safe = true;
        } else if (ai instanceof RandomAI) {
            ai.swapCannonTarget(ai.getSecretHQ());
            safe = true;
        }
    }

    /**
     * @return Whether DoctorCS or Dr. Chipotle has won
     */
    public String getStatus() {
        if (safe) {
            return "Doctor CS has saved the day!";
        } else if (ai.getDestructed()) {
            return "Dr. Chipotle has succeeded in his plan…";
        }
        return "Georgia Tech is still in danger!";
    }

    /**
     * @return Sentence in a format "(secretIdentity) aka Doctor CS
     *         with JLAID: (jlaid)"
     */
    @Override
    public String toString() {
        return secretIdentity + " aka Doctor CS with JLAID: " + jlaid;
    }

    /**
     * @return AI object
     */
    public AI getAI() {
        return ai;
    }

    /**
     * @return Doctor CS’s Justice League of America ID
     */
    public int getJlaid() {
        return jlaid;
    }
}