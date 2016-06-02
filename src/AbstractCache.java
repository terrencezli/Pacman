import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public abstract class AbstractCache implements CacheInterface {

    /** cache */
    protected Map<Object, Object> m_cacheMap = new HashMap<Object, Object>();

    /** cache size */
    protected final int           m_cacheMaxSize;

    /** evict count */
    protected final int           m_evictCount;

    /**
     *
     * @param size
     * @param evictCount
     */
    public AbstractCache(int size, int evictCount) {
        m_cacheMaxSize = size;
        m_evictCount = evictCount;
    }

    public Collection<Object> values() {
        return m_cacheMap.values();
    }

    public Object get(Object key) {
        Object value = m_cacheMap.get(key);
        if (value != null) {
            hitAccess(key);
            //System.out.println("hit: " + key.toString());
        } else {
            //System.out.println("miss: " + key.toString());
        }

        return value;
    }

    protected abstract void hitAccess(Object key);

    public int maxSize() {
        return m_cacheMaxSize;
    }

    public Object put(Object key, Object val) {
        if (m_cacheMaxSize > 0) {
            if (size() >= m_cacheMaxSize)
                evict();
            putAccess(key);

            //System.out.println("put: " + key.toString());
            return m_cacheMap.put(key, val);
        }
        return null;
    }

    protected abstract void evict();

    protected abstract void putAccess(Object key);

    public List<Object> removeValues(Object value) {
        List<Object> removed = new ArrayList<Object>();
        Iterator<Object> iter = m_cacheMap.keySet().iterator();
        while (iter.hasNext()) {
            Object o = iter.next();
            if (m_cacheMap.get(o) == value) {
                removed.add(o);
                iter.remove();
            }
        }
        return removed;
    }

    public int size() {
        return m_cacheMap.size();
    }

    public boolean test() {
        return false;
    }

    public String toString() {
        return m_cacheMap.toString();
    }
}