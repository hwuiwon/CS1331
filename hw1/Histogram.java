import java.util.Scanner;
import java.io.File;

public class Histogram {

	public static void main(String[] args) throws Exception {
		String filename = args[0];
        String content = new Scanner(new File(filename)).useDelimiter("\\Z").next().replaceAll("[^\\d.]", " ");
        String[] numbers = content.split("\\s+");
        int bins = 0;

        if (args.length != 2) {
            Scanner input = new Scanner(System.in);
            System.out.println("How many bins would you like?");
            bins = input.nextInt();
        } else {
            bins = Integer.parseInt(args[1]);
        }

        if (bins <= 100) {
            int gap = 100/bins;
            for (int i=100; i>0; i-=gap) {
                String temp = String.format("%3s", i);
                if (i == 100-(100/gap)*gap || i == 100/bins) {
                    System.out.print(temp + " -  " + 0 + " | ");
                    for (int j=1; j<numbers.length; j++) {
                        if (Integer.parseInt(numbers[j]) <= i) {
                            System.out.print("[]");
                        }
                    }
                } else {
                    String temp2 = String.format("%2s", i-(gap-1));
                    System.out.print(temp + " - " + temp2 + " | ");
                    for (int j=1; j<numbers.length; j++) {
                        if (Integer.parseInt(numbers[j]) >= i-(gap-1) && Integer.parseInt(numbers[j]) <= i) {
                            System.out.print("[]");
                        }
                    }
                }
                System.out.println();
            }
        }
	}
}