/**
 * Created by Timothy Chu on 6/1/2016.
 */
public class Simulator {
    public static void main(String args[]) {
        Lru test = new Lru(3, 1);
        test.put(1, 1);
        test.put(2, 2);
        test.put(3, 3);
        test.get(1);
        test.put(4, 4);

    }

}
