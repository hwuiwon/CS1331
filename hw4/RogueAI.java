/**
 * @author hkim944
 * @version 1.0
 */

public class RogueAI extends AI {

    private int firewallProtection;
    private int alertLevel;
    private final int maxAlert;

    /**
     * Creates a RogueAI with two coordinates, firewallProtection,
     * alertLevel, and maxAlert
     * @param firewallProtection Security level of the firewall
     * @param alertLevel Current alert level of the computer
     * @param maxAlert Alert level at which the computer self destructs
     * @param cannonTarget Location at which cannon is aimed
     * @param secretHQ Location of secret headquarters
     */
    public RogueAI(int firewallProtection, int alertLevel,
            int maxAlert, Coordinates cannonTarget, Coordinates secretHQ) {
        super(cannonTarget, secretHQ);
        this.firewallProtection = firewallProtection;
        this.alertLevel = alertLevel;
        this.maxAlert = maxAlert;
    }

    /**
     * Creates a RogueAI with two coordinates, firewallProtection,
     * maxAlert, and 0 alertLevel
     * @param firewallProtection Security level of the firewall
     * @param maxAlert Alert level at which the computer self destructs
     * @param cannonTarget Location at which cannon is aimed
     * @param secretHQ Location of secret headquarters
     */
    public RogueAI(int firewallProtection, int maxAlert,
            Coordinates cannonTarget, Coordinates secretHQ) {
        this(firewallProtection, 0, maxAlert, cannonTarget, secretHQ);
    }

    /**
     * Creates a RogueAI with two coordinates, firewallProtection,
     * 0 alertLevel, and 10 maxAlert
     * @param firewallProtection Security level of the firewall
     * @param cannonTarget Location at which cannon is aimed
     * @param secretHQ Location of secret headquarters
     */
    public RogueAI(int firewallProtection,
            Coordinates cannonTarget, Coordinates secretHQ) {
        this(firewallProtection, 0, 10, cannonTarget, secretHQ);
    }

    /**
     * Lower firewallProtection by 2 and alertLevel by 1
     */
    public void lowerFirewall() {
        firewallProtection -= 2;
        alertLevel++;
    }

    /**
     * @return Whether cannon should swap target
     */
    @Override
    public boolean shouldSwapCannonTarget() {
        return firewallProtection <= 0;
    }

    /**
     * @return Whether cannon should self destruct
     */
    @Override
    public boolean shouldSelfDestruct() {
        return alertLevel >= maxAlert;
    }

    /**
     * @return Sentence in a form of "Dr. Chipotle's guacamole cannon
     *         is currently pointed at latitude: 13.31, longitude: 13.01,
     *         and is at alert level 10 with firewall protection 5"
     */
    public String toString() {
        return super.toString() + ", and is at alert level " + alertLevel
                + " with firewall protection " + firewallProtection + ".";
    }

    /**
     * @return Security level of the firewall
     */
    public int getFirewallProtection() {
        return firewallProtection;
    }

    /**
     * @return Current alert level of the computer
     */
    public int getAlertLevel() {
        return alertLevel;
    }

    /**
     * @return Alert level at which the computer self destructs
     */
    public int getMaxAlert() {
        return maxAlert;
    }
}