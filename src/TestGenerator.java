import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Random;

/**
 * Created by Timothy Chu on 5/26/2016.
 */
public class TestGenerator {
    //Usage: <Test-File-Name> <Loop-Size> <Num-Linear-Access-Patterns-Per-Loop>
    public static void main(String args[]) throws FileNotFoundException, UnsupportedEncodingException {
        Random rand = new Random();
        int loopSize = Integer.parseInt(args[1]);
        int numLinearAccessPatternsPerLoop = Integer.parseInt(args[2]);
        PrintWriter writer = new PrintWriter(args[0], "UTF-8");

        System.out.println("Loop size: " + loopSize);
        System.out.println("Number of Linear Access Patterns per loop " + numLinearAccessPatternsPerLoop);

        for (int i = 0; i < loopSize; i++) {
            for (int j = 0; j < numLinearAccessPatternsPerLoop; j++) {
                for (int k = 1; k <= 100; k++) {
                    int offset = rand.nextInt(21) - 10;
                    int address = 100 * k + offset;
                    writer.println(address);
                }
            }
        }

        writer.close();
    }
}
