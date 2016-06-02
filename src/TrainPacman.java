import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by terrence on 5/21/16.
 */
public class TrainPacman {
    public File trainingFile;
    public int loopSize;
    public int cacheSize;
    public List<Integer> trainingData;
    public Map<Integer, Integer> memDistance;
    public Map<Integer, String> cacheChoice;

    public TrainPacman() {
    }

    public TrainPacman(File trainingFile, int loopSize, int cacheSize) throws FileNotFoundException {
        this.trainingFile = trainingFile;
        this.loopSize = loopSize;
        this.cacheSize = cacheSize;
        this.trainingData = new ArrayList<>();
        this.memDistance = new HashMap<>();
        this.cacheChoice = new HashMap<>();

        Scanner scan = new Scanner(trainingFile);
        int ctr = 0;
        while (scan.hasNext() && ctr <= (loopSize * 2)) {
            int addr = scan.nextInt();
            trainingData.add(addr);
        }
    }

    /**
     *
     * train the pacman data
     * training data will be two loops
     */
    public void train() {
        for (int i = 0; i < trainingData.size()/2; i++) {
            Integer address1 = trainingData.get(i);


            for (int j = i+loopSize; j < trainingData.size(); j++) {
                Integer address2 = trainingData.get(j);
                if ((address1 <= address2+10) && (address1 >= address2-10)) {
                    int optDistance = j-i;
                    memDistance.put(i, optDistance);

                    // cache choice based off opt distance vs cache size
                    if (optDistance > cacheSize) {
                        cacheChoice.put(i, "MRU");
                        break;
                    }
                    else {
                        cacheChoice.put(i, "LRU");
                        break;
                    }
                }
            }
            // possible third case evict immediately scenario
            if (!cacheChoice.containsKey(i)) {
                cacheChoice.put(i, "MRU");
            }
        }

        System.out.println(cacheChoice.toString());
    }
}
