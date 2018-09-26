import java.util.Random;

/**
 * @author hkim944
 * @version 1.0
 */

public class VendingMachine {

    private static double totalSales = 0;
    private VendingItem[][][] shelf = new VendingItem[6][3][5];
    private int luckyChance = 0;
    private Random rand = new Random();

    /**
     * Default constructor
     */
    public VendingMachine() {
        restock();
    }

    /**
     * @param code Location selected
     * @return VendingItem placed in selected location
     */
    public VendingItem vend(String code) {
        VendingItem[] tmp = shelf[code.charAt(0) - 65][code.charAt(1) - 49];
        if ((int) code.charAt(0) > 70
                || (int) code.charAt(0) < 65 || tmp[0] == null) {
            System.out.println("There is no item");
            return null;
        } else {
            VendingItem vItem = tmp[0];
            if (tmp[1] == null) {
                tmp[0] = null;
            } else {
                for (int i = 0; i < shelf[0][0].length - 1; i++) {
                    if (tmp[i + 1] != null) {
                        tmp[i] = tmp[i + 1];
                        tmp[i + 1] = null;
                    }
                }
            }
            if (free()) {
                System.out.println("You can get this for free!");
            }
            totalSales += vItem.getPrice();
            return vItem;
        }
    }

    /**
     * @return Whether user can get item for free
     */
    private boolean free() {
        if (rand.nextInt(100) < luckyChance) {
            luckyChance = 0;
            return true;
        } else {
            luckyChance++;
            return false;
        }
    }

    /**
     * Fill each spot in the vending machine with a randomly chosen item
     */
    public void restock() {
        for (int i = 0; i < shelf.length; i++) {
            for (int j = 0; j < shelf[0].length; j++) {
                for (int k = 0; k < shelf[0][0].length; k++) {
                    shelf[i][j][k] = VendingItem.values()[
                        rand.nextInt(VendingItem.values().length)];
                }
            }
        }
    }

    /**
     * @return Total amount sold
     */
    public static double getTotalSales() {
        return totalSales;
    }

    /**
     * @return Total number of items left
     */
    public int getNumberOfItems() {
        int number = 0;
        for (VendingItem[][] vItem1 : shelf) {
            for (VendingItem[] vItem2 : vItem1) {
                for (VendingItem vItem3 : vItem2) {
                    if (vItem3 != null) {
                        number++;
                    }
                }
            }
        }
        return number;
    }

    /**
     * @return Total value of items left
     */
    public double getTotalValue() {
        double value = 0;
        for (VendingItem[][] vItem1 : shelf) {
            for (VendingItem[] vItem2 : vItem1) {
                for (VendingItem vItem3 : vItem2) {
                    if (vItem3 != null) {
                        value += vItem3.getPrice();
                    }
                }
            }
        }
        return value;
    }

    /**
     * @return Percentage of getting item for free (%)
     */
    public int getLuckyChance() {
        return luckyChance;
    }

    /**
     * @return How vending machine looks in console
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("----------------------------------------------------------"
            + "------------\n");
        s.append("                            VendaTron 9000                "
            + "            \n");
        for (int i = 0; i < shelf.length; i++) {
            s.append("------------------------------------------------------"
                + "----------------\n");
            for (int j = 0; j < shelf[0].length; j++) {
                VendingItem item = shelf[i][j][0];
                String str = String.format("| %-20s ",
                    (item == null ? "(empty)" : item.name()));
                s.append(str);
            }
            s.append("|\n");
        }
        s.append("----------------------------------------------------------"
            + "------------\n");
        s.append(String.format("There are %d items with a total "
            + "value of $%.2f.%n", getNumberOfItems(), getTotalValue()));
        s.append(String.format("Total sales across vending machines "
            + "is now: $%.2f.%n", getTotalSales()));
        return s.toString();
    }
}