import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by terrence on 5/21/16.
 */
public class TrainPacman {
    public static void main(String args[]) {
        List<Integer> trainingData = new ArrayList<>();
        Map<Integer, Integer> memDistance = new HashMap<>();

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

            for (int j = i+1; j < trainingData.size(); j++) {
                Integer address2 = trainingData.get(j);
                if (address1 <= address2+100 && address1 >= address2-100) {
                        memDistance.put(address1, address2-address1);
                }
            }
        }

    }
}
