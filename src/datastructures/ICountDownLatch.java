package datastructures;

import datastructures.exceptions.NegativeAddressException;
import datastructures.exceptions.NotAllocatedAddressException;
import datastructures.exceptions.TakenAddressException;

public interface ICountDownLatch {

    public void put(int address, int value);

    public int getEmptyAddress();

    public int get(int address);

    public void replace(int adress, int value);
}
