import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Random;

/**
 * Created by Timothy Chu on 5/26/2016.
 */
public class TestGenerator {
    //Usage: <Test-File-Name> <Num-Loops> <Num-Linear-Access-Patterns-Per-Loop> <Num-Access-Per-Linear-Pattern>
    public static void main(String args[]) throws FileNotFoundException, UnsupportedEncodingException {
        Random rand = new Random();
        int numLoops = Integer.parseInt(args[0]);
        int numLinearAccessPatternsPerLoop = Integer.parseInt(args[1]);
        int numAccessesPerLinearPattern = Integer.parseInt(args[2]);
        String fname = numLoops + "Loop" + numLinearAccessPatternsPerLoop + "Pattern" + numAccessesPerLinearPattern + "Access.txt";
        PrintWriter writer = new PrintWriter(fname, "UTF-8");

        System.out.println("Loop size: " + numLoops);
        System.out.println("Number of Linear Access Patterns per loop " + numLinearAccessPatternsPerLoop);

        for (int i = 0; i < numLoops; i++) {
            for (int j = 0; j < numLinearAccessPatternsPerLoop; j++) {
                for (int k = 1; k <= numAccessesPerLinearPattern; k++) {
                    int offset = rand.nextInt(7) - 3;
                    int address = 100 * k + offset;
                    writer.println(address);
                }
            }
        }

        writer.close();
    }
}
