import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

/**
 * Created by Timothy Chu on 5/26/2016.
 */
public class Pacman {
    public static void main(String args[]) {
        String testFile = args[0];
        int loopSize = Integer.parseInt(args[1]);
        int cacheSize = Integer.parseInt(args[2]);
        //Stack<Integer> cache = Stack<>();
        int loopIndex = 0;
        int cacheHit = 0;
        int cacheMiss = 0;

        //Training Phase
        //TrainPacman train = new TrainPacman(testFile, loopSize, cacheSize);

        /* Note: We can possibly have a counter system too, which keeps track of how
        many cache set accesses happened, and use a second chance system.
         */

        //Testing Phase
        Scanner scan = new Scanner(testFile);
        while (scan.hasNext()) {
            int addr = scan.nextInt();
            if (cache[loopIndex] == addr) {
                cacheHit++;
            } else {
                cacheMiss++;
                //has MRU...evict MRU
                cache[loopIndex] = addr;
            }
            loopIndex++;
        }
    }
}
