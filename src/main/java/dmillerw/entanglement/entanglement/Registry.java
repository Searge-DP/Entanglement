package dmillerw.entanglement.entanglement;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author dmillerw
 */
public class Registry {

    public static final Registry INSTANCE = new Registry();

    private Map<Integer, BlockData> dataCache = Maps.newHashMap();
    private Map<Integer, List<FrequencyListener>> listeners = Maps.newHashMap();

    public void updateData(int freq, BlockData data) {
        dataCache.put(freq, data);

        System.out.println("Updating frequency " + freq);

        List<FrequencyListener> list = listeners.get(freq);
        if (list != null) {
            for (FrequencyListener l : list) {
                l.onDataUpdate(data);
            }
        }
    }

    public void registerListener(int freq, FrequencyListener listener) {
        listener.onDataUpdate(dataCache.get(freq));

        List<FrequencyListener> list = listeners.get(freq);
        if (list == null) {
            list = Lists.newArrayList();
        }

        list.add(listener);

        listeners.put(freq, list);
    }

    public void removeListener(int freq, FrequencyListener listener) {
        List<FrequencyListener> list = listeners.get(freq);
        if (list != null) {
            Iterator<FrequencyListener> iterator = list.iterator();
            while (iterator.hasNext()) {
                if (listener == iterator.next())
                    iterator.remove();
            }
        }
    }
}
