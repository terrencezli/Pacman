import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by Timothy Chu on 6/1/2016.
 */
public class Simulator {
    // Usage: <fname> <loopSize> <cacheSize>
    public static void main(String args[]) throws FileNotFoundException {
        String fname = args[0];
        File testfile = new File(fname);
        int loopSize =  Integer.parseInt(args[1]);
        int cacheSize = Integer.parseInt(args[2]);
        System.out.println("Loop size: " + loopSize);
        System.out.println("Cache size: " + cacheSize);

/*
        TrainPacman tp = new TrainPacman();
        Pacman pac = new Pacman(3, 1, tp);
        pac.put(100, "LRU");
        pac.put(200, "MRU");
        pac.put(300, "LRU");
        pac.get(100);
        pac.put(400, "LRU");
        pac.put(500, "MRU");
*/

        TrainPacman tp = new TrainPacman(testfile, loopSize, cacheSize);
        tp.train();

        Pacman pac = new Pacman(cacheSize, 1, tp);
        Scanner scan = new Scanner(testfile);
        int idx = 0;
        long hitCtr = 0;
        long missCtr = 0;
        while(scan.hasNext()) {
            String addr = scan.next();
            //System.out.println("addr: " + addr);

            //if already in the cache [HIT] - update lru
            if (pac.get(addr) != null) {
                hitCtr++;
            //else [MISS] - update cache and lru
            } else {
                missCtr++;
                pac.put(addr, tp.cacheChoice.get(idx % loopSize));
            }
            idx = (idx + 1) % loopSize;

        }

        System.out.println("Hits: " + hitCtr);
        System.out.println("Misses: " + missCtr);
    }

}
