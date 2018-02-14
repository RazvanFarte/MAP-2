package datastructures;

import java.util.HashMap;

public class Dictionary<K, V> extends HashMap<K,V> implements IDictionary<K, V> {

    @Override
    public String toString() {
        String resultString = new String();

        resultString += "{";
        for (Entry<K, V> elem: super.entrySet()) {
            resultString += elem.getKey() + ":" + elem.getValue() + " | ";
        }
        resultString += "}\n";

        return resultString;
    }
}
