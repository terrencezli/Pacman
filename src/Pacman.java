import java.util.*;

/**
 * Created by Timothy Chu on 5/26/2016.
 */
public class Pacman extends AbstractCache {

    protected final List<Object> m_access = new LinkedList<Object>();
    public TrainPacman tp;
    public int ctr = 0;

    /**
     * Constructor calls super constructor with same parameters.
     */
    public Pacman(int size, int evictCount, TrainPacman t) {
        super(size, evictCount);
        this.tp = t;
    }

    /**
     * Evicts the 1st object or m_evictCount objects form m_access list.
     */
    @Override
    protected void evict() {
        for (int i = 0; i < m_evictCount; i++) {
            //find the thing to evict
            Set keys = m_cacheMap.keySet();
            Iterator<Object> iter = keys.iterator();
            while(iter.hasNext()) {
                Object tmp = iter.next();
                if (m_cacheMap.get(tmp).equals("MRU")) {
                    m_cacheMap.remove(tmp);
                    m_access.remove(tmp);
                    return;
                }
            }

            //Case for all LRU
            Iterator<Object> it = m_access.iterator();
            for (int j = 0; j < m_evictCount; j++)
                if (it.hasNext()) {
                    Object o = it.next();
                    m_cacheMap.remove(o);
                    it.remove();
                }
        }
    }

    @Override
    protected void hitAccess(Object key) {
        int index = m_access.indexOf(key);
        if (index >= 0)
            m_access.remove(index);
        else
            System.out.println("was it really a hit?");
        m_access.add(key);
    }

    @Override
    protected void putAccess(Object key) {
        int index = m_access.indexOf(key);
        if (index >= 0) {
            System.out.println("key in cache!");
            m_access.remove(index);
        }
        m_access.add(key);
    }

    @Override
    public List<Object> removeValues(Object value) {
        List<Object> removed = super.removeValues(value);
        m_access.removeAll(removed);
        return removed;
    }

    @Override
    public int size() {
        return m_cacheMap.size();
    }

    /**
     * return false, if we have an error.
     */
    @Override
    public boolean test() {
        if (size() > m_cacheMaxSize || m_access.size() > size() || m_access.size() > m_cacheMaxSize)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return m_cacheMap.toString() + "\n" + m_access.toString();
    }

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
            //if (cache[loopIndex] == addr) {
            //    cacheHit++;
            //} else {
            //    cacheMiss++;
            //    //has MRU...evict MRU
            //    cache[loopIndex] = addr;
            //}
            //loopIndex++;
        }
    }
}
