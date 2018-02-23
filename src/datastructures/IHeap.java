package datastructures;

import datastructures.exceptions.HeapException;
import datastructures.exceptions.NegativeAddressException;
import datastructures.exceptions.NotAllocatedAddressException;
import datastructures.exceptions.TakenAddressException;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public interface IHeap {

    /**
     * Adds in the heap the value at corresponding address.
     * @param address - location in memory where the value will be stored
     *                Address must be positive. 0 corresponds to NULL.
     *                Negative addresses will throw an exception.
     * @param value - value stored at address
     *
     * @throws datastructures.exceptions.NegativeAddressException
     */
    public void put(int address, int value) throws NegativeAddressException, TakenAddressException;

    /** Returns an empty address in the heap.
     * @return Next current location in heap
     */
    public int getEmptyAddress();

    /** Gets the value located at address.
     * @param address
     * @return
     * @throws NotAllocatedAddressException
     * @throws NegativeAddressException
     */
    public int get(int address) throws NotAllocatedAddressException, NegativeAddressException;

    /** Updates the value at address.
     * @param adress
     * @param value
     * @throws NegativeAddressException
     */
    public void replace(int adress, int value) throws NegativeAddressException, NotAllocatedAddressException;

    /** Frees up the memory at address location.
     * @param address
     * @throws NegativeAddressException
     */
    public void release(int address) throws HeapException;

    public Set<Map.Entry<Integer, Integer>> entrySet();

    public void garbageCollect(Collection<Integer> symTableValues) throws HeapException;
}
