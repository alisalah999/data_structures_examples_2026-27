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
    /**
     * Creates a new empty DynamicArray with an initial capacity of 10.
     * The list starts empty (size = 0).
     */
    public DynamicArray() {
        data = new String[INITIAL_CAPACITY];
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
     * @throws IndexOutOfBoundsException if index is less than 0 or greater than or equal to size
     */
    public String get(int index) {
        validateIndex(index);
        return data[index];
    }


    /**
     * Returns the index of the given element (case-insensitive).
     * If the element is not found, returns -1.
     *
     * @param element the String to search for
     * @return the index of the element, or -1 if not found
     * @throws IllegalArgumentException if element is null
     */
    public int indexOf(String element) {
        if (element == null) {
            throw new IllegalArgumentException("Null values are not allowed.");
        }

        for (int i = 0; i < size; i++) {
            if (data[i].equalsIgnoreCase(element)) {
                return i;
            }
        }
        return -1;
    }
    /**
     * Adds the given element to the end of the list.
     *
     * @param element the String to add
     * @throws IllegalArgumentException if element is null
     */
    public void add(String element) {
        add(size, element);
    }
    /**
     * Inserts the given element at the specified index.
     * Elements after the index are shifted right.
     *
     * @param index the position at which the element should be inserted
     * @param element the String to insert
     * @throws IllegalArgumentException if element is null
     * @throws IndexOutOfBoundsException if index is less than 0 or greater than size
     */
    public void add(int index, String element) {

        if (element == null) {
            throw new IllegalArgumentException("Null values are not allowed.");
        }

        validateInclusiveIndex(index);

        ensureCapacity(size + 1);

        // shift elements right
        System.arraycopy(data, index, data, index + 1, size - index);

        data[index] = element;
        size++;
    }
    /**
     * Replaces the element at the given index with the supplied element.
     *
     * @param index the position to replace
     * @param element the new element to store
     * @return the element previously stored at the index
     * @throws IllegalArgumentException if element is null
     * @throws IndexOutOfBoundsException if index is less than 0 or greater than or equal to size
     */
    public String set(int index, String element) {

        if (element == null) {
            throw new IllegalArgumentException("Null values are not allowed.");
        }

        validateIndex(index);

        String oldValue = data[index];
        data[index] = element;

        return oldValue;
    }
    /**
     * Removes the element at the specified index.
     * Elements after the index are shifted left.
     *
     * @param index the position of the element to remove
     * @return the removed element
     * @throws IndexOutOfBoundsException if index is less than 0 or greater than or equal to size
     */
    public String remove(int index) {

        validateIndex(index);

        String removedValue = data[index];

        // shift elements left
        System.arraycopy(data, index + 1, data, index, size - index - 1);

        data[size - 1] = null;
        size--;

        return removedValue;
    }
    /**
     * Removes all occurrences of the given element.
     * Returns the number of removed elements.
     *
     * @param element the String to remove
     * @return the number of removed occurrences
     * @throws IllegalArgumentException if element is null
     */
    public int removeAll(String element) {

        if (element == null) {
            throw new IllegalArgumentException("Null values are not allowed.");
        }

        int count = 0;
        int i = 0;

        while (i < size) {
            if (data[i].equalsIgnoreCase(element)) {
                remove(i);
                count++;
            } else {
                i++;
            }
        }

        return count;
    }
    /*
     * Removes all elements from the list.
     * Resets the list to its original empty state.
     */
    public void clear() {
        data = new String[INITIAL_CAPACITY];
        size = 0;
    }

    /*
     * Creates a new DynamicArray containing only one copy of each element
     * from this list. The original list is not modified.
     *
     * @return a new DynamicArray with duplicates removed
     */
    public DynamicArray deduplicate() {

        DynamicArray result = new DynamicArray();

        for (int i = 0; i < size; i++) {
            String current = data[i];

            if (result.indexOf(current) == -1) {
                result.add(current);
            }
        }

        return result;
    }

    /*
     * Join another DynamicArray onto the end of this one.
     * The current list expands if necessary.
     *
     * @param other the DynamicArray to join to this list
     * @throws IllegalArgumentException if the other list is null
     */
    public void join(DynamicArray other) {

        if (other == null) {
            throw new IllegalArgumentException("Cannot join a null list");
        }

        ensureCapacity(size + other.size);

        System.arraycopy(other.data, 0, data, size, other.size);

        size += other.size;
    }

    /**
     * Returns a string representation of the list in the format:
     * [A, B, C]
     *
     * @return a String showing the contents of the list
     */
    public String toString() {

        if (size == 0) {
            return "[]";
        }

        String result = "[";

        for (int i = 0; i < size - 1; i++) {
            result += data[i] + ", ";
        }

        result += data[size - 1] + "]";

        return result;
    }
    private void validateIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
    }

    private void validateInclusiveIndex(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
    }

    private void ensureCapacity(int required) {
        while (data.length < required) {
            String[] newData = new String[data.length * 2];
            System.arraycopy(data, 0, newData, 0, size);
            data = newData;
        }
    }
}