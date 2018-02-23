package datastructures;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class LatchTable implements ICountDownLatch {


    private Map<Integer, Integer> map;

    public LatchTable() {
        this.map = new HashMap<>();
    }

    @Override
    public void put(int address, int value) {
        this.map.put(address, value);
    }

    @Override
    public int getEmptyAddress() {
        int emptyAddress = 1;

        while(this.map.containsKey(emptyAddress)){
            emptyAddress++;
        }

        return emptyAddress;
    }
    @Override
    public int get(int address) {
        return this.map.get(address);
    }

    @Override
    public void replace(int adress, int value) {
        this.map.replace(adress, value);
    }

    public Set<Map.Entry<Integer, Integer>> entrySet() {
        return this.map.entrySet();
    }
}
