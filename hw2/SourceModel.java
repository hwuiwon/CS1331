import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class SourceModel {

    private String sourceName = "", content = "";
    private double[][] matrix = new double[26][26];
    private char[] alp = new char[26];
    private HashMap<String, Integer> alphabet = new HashMap<>();

    public static void main(String[] args) {
        int tmp = args.length;
        double probTtl = 0.0;
        ArrayList<Double> probs = new ArrayList<Double>(tmp - 1);
        String testStr = args[args.length - 1];
        ArrayList<SourceModel> sModels = new ArrayList<SourceModel>();
        for (int i = 0; i < tmp - 1; i++) {
            if (args[i].split("\\.")[0].equals("*")) {
                File dir = new File(System.getProperty("user.dir"));
                for (File file : dir.listFiles()) {
                    if (file.getName().endsWith((".corpus"))) {
                        sModels.add(new SourceModel(file.getName()
                            .split("\\.")[0], file.getName()));
                    }
                }
                break;
            } else {
                sModels.add(new SourceModel(args[i].split("\\.")[0],
                    args[i].toString()));
            }
        }
        System.out.println("Analyzing: " + testStr);
        for (SourceModel sm : sModels) {
            probTtl += sm.probability(testStr);
        }
        probTtl = 1 / probTtl;
        for (SourceModel sm : sModels) {
            System.out.println("Probability that test string is"
                + String.format("%9s", sm.getName()) + ": "
                + String.format("%.2f ", sm.probability(testStr) * probTtl));
            probs.add(sm.probability(testStr) * probTtl);
        }
        for (int i = 0; i < probs.size(); i++) {
            if (probs.get(i) == Collections.max(probs)) {
                System.out.print("Test String is most likely "
                    + sModels.get(i).getName());
            }
        }
        System.out.println();
    }

    @SuppressWarnings("resource")
    public SourceModel(String name, String fileName) {
        sourceName = name;
        System.out.print("Training " + sourceName + " model ... ");
        try {
            content = new Scanner(new File(fileName)).useDelimiter("\\Z").next()
                    .replaceAll("[^A-Za-z]", "").toLowerCase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        makeMatrix();
        System.out.println("done.");
    }

    public void makeMatrix() {
        int tmp = 0;
        for (char i = 'a'; i <= 'z'; ++i) {
            alp[tmp] = i;
            alphabet.put(String.valueOf(i), tmp++);
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = 0;
            }
        }
        for (int i = 0; i < content.length() - 1; i++) {
            matrix[alphabet.get(String.valueOf(content.charAt(i)))]
                [alphabet.get(String.valueOf(content.charAt(i + 1)))]++;
        }
        for (int i = 0; i < matrix.length; i++) {
            tmp = 0;
            for (double a : matrix[i]) {
                tmp += a;
            }
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][j] = 0.01;
                } else {
                    matrix[i][j] /= tmp;
                }
            }
        }
    }

    public String getName() {
        return sourceName;
    }

    @Override
    public String toString() {
        String tmp = "Model: " + sourceName + "\n ";
        for (char i = 'a'; i <= 'z'; ++i) {
            tmp += String.format("%5s", String.valueOf(i));
        }
        tmp += "\n";

        for (int i = 0; i < matrix.length; i++) {
            tmp += alp[i] + " ";
            for (double a : matrix[i]) {
                tmp += (String.format("%.2f ", a));
            }
            tmp += "\n";
        }
        return tmp;
    }

    public double probability(String testStr) {
        double prob = 1.0;
        String tmpStr = testStr.replaceAll("[^A-Za-z]", "").toLowerCase();
        for (int i = 0; i < tmpStr.length() - 1; i++) {
            prob *= matrix[alphabet.get(String.valueOf(tmpStr.charAt(i)))]
                    [alphabet.get(String.valueOf(tmpStr.charAt(i + 1)))];
        }
        return prob;
    }
}