package datastructures;

import java.util.HashMap;
import java.util.Map;

public class CountDownLatch implements ICountDownLatch {


    private Map<Integer, Integer> map;

    public CountDownLatch() {
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
}
