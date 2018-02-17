package datastructures.exceptions;

import datastructures.Dictionary;
import datastructures.IHeap;

import java.util.Map;
import java.util.Objects;

public class Heap implements IHeap {

    private Dictionary<Integer, Integer> heap;
    public static int NULL = 0;

    public Heap() {
        this.heap = new Dictionary<>();
    }

    public Map<Integer, Integer> getHeap() {
        return heap;
}


    /** If address is out of heap memory( address < 0), return false.
     * @param address
     * @return
     */
    private boolean isAddressValid(int address) {
        return address >= 0? true : false;
    }

    private boolean isAddressTaken(int address) {
        return this.heap.containsKey(address);
    }

    /** Wraper for isAddressValid and isAddressTaken. Throws exceptions
     * @param address
     * @throws NegativeAddressException
     * @throws TakenAddressException
     */
    private void checkAddressNegative(int address) throws NegativeAddressException {
        if(!isAddressValid(address))
            throw new NegativeAddressException("Invalid address");
    }

    private void checkAddressTaken(int address) throws TakenAddressException {
        if(isAddressTaken(address))
            throw new TakenAddressException("Address is already taken.");
    }

    private void checkAddressNotAllocated(int address) throws NotAllocatedAddressException {
        if(!this.heap.containsKey(address))
            throw new NotAllocatedAddressException(String.format("Nothing at location area %d", address));
    }
    /**
     * Adds in the heap the value at corresponding address.
     *
     * @param address - location in memory where the value will be stored
     *                Address must be positive. 0 corresponds to NULL.
     *                Negative addresses will throw an exception.
     * @param value   - value stored at address
     * @throws NegativeAddressException
     */
    @Override
    public void put(int address, int value) throws NegativeAddressException, TakenAddressException {
        checkAddressNegative(address);
        checkAddressTaken(address);

        this.heap.put(address, value);
    }

    /**
     * Returns an empty address in the heap.
     *
     * @return Next current location in heap
     */
    @Override
    public int getEmptyAddress() {
        int emptyAddress = 1;

        while(this.heap.containsKey(emptyAddress)){
            emptyAddress++;
        }

        return emptyAddress;
    }

    /**
     * Gets the value located at address.
     *
     * @param address
     * @return
     * @throws NotAllocatedAddressException
     */
    @Override
    public int get(int address) throws NotAllocatedAddressException, NegativeAddressException {
        checkAddressNegative(address);
        checkAddressNotAllocated(address);

        return this.heap.get(address);
    }

    /**
     * Updates the value at address.
     *
     * @param adress
     * @param value
     * @throws NegativeAddressException
     */
    @Override
    public void replace(int adress, int value) throws NegativeAddressException, NotAllocatedAddressException {
        checkAddressNegative(adress);
        checkAddressNotAllocated(adress);

        this.heap.replace(adress, value);
    }

    /**
     * Frees up the memory at address location.
     *
     * @param address
     * @throws NegativeAddressException
     */
    @Override
    public void release(int address) throws NegativeAddressException, NotAllocatedAddressException {
        checkAddressNegative(address);
        checkAddressNotAllocated(address);

        this.heap.remove(address);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Heap heap1 = (Heap) o;
        return Objects.equals(getHeap(), heap1.getHeap());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getHeap());
    }

    @Override
    public String toString() {
        return "Heap{" +
                heap.toString() +
                '}';
    }
}
