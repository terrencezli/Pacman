import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by terrence on 5/21/16.
 */
public class TrainPacman {
    /**
     *
     * @param args file cachesize loopsize
     */
    public static void main(String args[]) {
        //int cacheSize = Integer.parseInt(args[1]);
        int cacheSize = 3;
        int loopSize = 4;
        List<Integer> trainingData = new ArrayList<>();
        Map<Integer, Integer> memDistance = new HashMap<>();
        Map<Integer, String> cacheChoice = new HashMap<>();

        trainingData.add(0);
        trainingData.add(100);
        trainingData.add(200);
        trainingData.add(300);
        trainingData.add(14);
        trainingData.add(112);
        trainingData.add(215);
        trainingData.add(313);

        for (int i = 0; i < trainingData.size()/2; i++) {
            Integer address1 = trainingData.get(i);

            for (int j = i+loopSize; j < trainingData.size(); j++) {
                Integer address2 = trainingData.get(j);
                if (address1 <= address2+100 && address1 >= address2-100) {
                    int optDistance = j-i;
                    memDistance.put(i, optDistance);

                    // cache choice based off opt distance vs cache size
                    if (optDistance > cacheSize) {
                        cacheChoice.put(i, "MRU");
                    }
                    else {
                        cacheChoice.put(i, "LRU");
                    }
                }
            }
        }

        System.out.print(memDistance.toString());
        System.out.print(cacheChoice.toString());

    }
}
