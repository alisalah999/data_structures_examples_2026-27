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
     * Returns the element stored at the given index.
     * @param index the position to retrieve
     * @return the String at the given index
     */
    public int get(int index) {
        // am adding some validation here later
        return data[index]
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
}
