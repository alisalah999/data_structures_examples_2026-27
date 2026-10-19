package CA1_Ali;
/**
 * DynamicArray - CA1 implementation for Strings
 * Implements an array-based list with expansion factor of 2.
 */
public class DynamicArray {
//    standard initial capacity of 10 elements case sinsiteve
//    an expansion factor of 2
//
    private static final int INITIAL_CAPACITY = 10;
    private String[] data;
    private int size;
 /*
   the list now has a real array with a length 10
   the list starts empty (size = 0)
   this makes adding elemets, removing and shifting ...
 */
    public DynamicArray() {
        data = String[INITIAL_CAPACITY];
        size = 0;
    }

    /*
     * Return the number of element currently stored on the list.
     * @return the current size of the list
     */
    public int size() {
        return size;
    }

    /**
     * Returns the element stored at the specified index.
     *
     * @param index the position to retrieve
     * @return the String stored at the given index
     * @throws IndexOutOfBoundsException if the index is less than 0 or greater than or equal to size
     */
    public String get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        return data[index];
    }


    /**
     * Returns the index of the given element
     * if the element is not found, returns -1
     *
     * @param element the String to search for
     * @return the index of the element, or -1 if not found
     */
    public int indexOf(String element) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }
    /**
     * Inserts the given element at the specified index.
     * Null values are not allowed. Elements after the index are shifted right.
     *
     * @param element the String to insert
     * @param index the position at which the element should be inserted
     * @throws IllegalArgumentException if the element is null
     * @throws IndexOutOfBoundsException if index is less than 0 or greater than size
     */
    public void add(String element, int index) {

        if (element == null) {
            throw new IllegalArgumentException("Null values are not allowed.");
        }

        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }

        // expand if full
        if (size == data.length) {
            String[] newData = new String[data.length * 2];
            System.arraycopy(data, 0, newData, 0, data.length);
            data = newData;
        }

        // shift elements right
        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }

        data[index] = element;
        size++;
    }
    /*
     * Replace the element at the given index with the supplied element.
     * Null values are not allowed.
     * @return the element that was previously stored at the index
     */
    public int set(element, index) {
        if (element == null) {
            throw new IllegalArgumentException("Null values are not allowed.");
        }

        if (index <0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }

        String oldValue = data[index]; //temp store the element being replace
        data[index] = element; //relacing the element with the new one
        return oldValue; // return the previous element
    }

    public String remove(index) {
        if (index < 0 || index >= size){
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }

        String removedValue = data[index];
        // shift elements left
        for (int i = index;
             i < size -1;
             i++) {
            data[i] = data[i+1];
        }
        // was thinking first to set the last element to null
        size--;

        return removedValue;
    }
    /*
     * Reset the list to its original empty state.
     * All data is removed and size becomes 0.
     */
    public void clear() {
        data = new String[10];  // reset array to original capacity
        size = 0;               // reset size
    }

    /*
     * Create a new list containing only one copy of each element.
     * The original list is not modified.
     * @return a new DynamicArray with duplicates removed
     */
    public DynaminArray deduplicate() {
        DynamicArray result = new DynamicArray();

        for (int i = 0; i<size; i++) {
            String current = data[i];

            if (result.indexOf(current) == -1) {
                result.add(current);
            }
        }
        return result;
    }

    /*
     * Join another DynamicArray onto the end of this one.
     * Null lists are not allowed.
     */
    public void join(DynamicArray other) {

        if (other == null) {
            throw new IllegalArgumentException("Cannot join a null list");
        }

        // check if we need to expand
        if ((data.length - size) < other.size) {
            String[] temp = new String[size + other.size];

            System.arraycopy(data, 0, temp, 0, size);

            data = temp;
        }

        // copy the other list's data into this one
        System.arraycopy(other.data, 0, data, size, other.size);

        size += other.size;
    }





}
