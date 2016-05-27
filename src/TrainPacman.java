import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by terrence on 5/21/16.
 */
public class TrainPacman {
    File trainingFile;
    int loopSize;
    int cacheSize;
    List<Integer> trainingData;
    Map<Integer, Integer> memDistance;
    Map<Integer, String> cacheChoice;

    public TrainPacman() {
    }

    public TrainPacman(File trainingFile, int loopSize, int cacheSize) {
        this.trainingFile = trainingFile;
        this.loopSize = loopSize;
        this.cacheSize = cacheSize;

        this.trainingData = new ArrayList<>();
        this.memDistance = new HashMap<>();
        this.cacheChoice = new HashMap<>();
    }

    public File getTrainingFile() {
        return trainingFile;
    }

    public int getLoopSize() {
        return loopSize;
    }

    public int getCacheSize() {
        return cacheSize;
    }

    public List<Integer> getTrainingData() {
        return trainingData;
    }

    public Map<Integer, Integer> getMemDistance() {
        return memDistance;
    }

    public Map<Integer, String> getCacheChoice() {
        return cacheChoice;
    }

    public void setTrainingFile(File trainingFile) {
        this.trainingFile = trainingFile;
    }

    public void setLoopSize(int loopSize) {
        this.loopSize = loopSize;
    }

    public void setCacheSize(int cacheSize) {
        this.cacheSize = cacheSize;
    }

    public void setTrainingData(List<Integer> trainingData) {
        this.trainingData = trainingData;
    }

    public void setMemDistance(Map<Integer, Integer> memDistance) {
        this.memDistance = memDistance;
    }

    public void setCacheChoice(Map<Integer, String> cacheChoice) {
        this.cacheChoice = cacheChoice;
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
