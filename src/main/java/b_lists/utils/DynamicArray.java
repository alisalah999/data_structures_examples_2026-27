package b_lists.utils;

public class DynamicArray {
    private static final int EXPANSION_MULTIPLIER = 2;
    private int size = 0;
    private int [] data = new int[10];

    private void ensureCapacity(){
        if(size == data.length){
            int [] temp = new int[data.length*EXPANSION_MULTIPLIER];

            for (int i = 0; i < data.length; i++) {
                temp[i] = data[i];
            }

            data = temp;
        }
    }

    public void add(int value){
        ensureCapacity();

        data[size] = value;
        size++;
    }

    public int get(int index){
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException("Index " + index + " is outside bounds of array");
        }
        return data[index];
    }

    public int size(){
        return size;
    }

    public int indexOf(int target){
        for (int i = 0; i < size; i++) {
            if(data[i] == target){
                return i;
            }
        }

        return -1;
    }

    public int remove(int index){
        validateIndex(index);

        int removed = data[index];

        for (int i = index; i < size-1; i++) {
            data[i] = data[i+1];
        }

        data[size-1] = 0;
        size--;

        return removed;
    }

    private void validateIndex(int index){
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException("Index " + index + " is outside bounds of list");
        }
    }

    private void validateInclusiveIndex(int index){
        if(index < 0 || index > size){
            throw new IndexOutOfBoundsException("Index " + index + " is outside bounds of list");
        }
    }

    public void add(int index, int value){
        validateInclusiveIndex(index);

        ensureCapacity();

//        for (int i = size; i > index; i--) {
//            data[i] = data[i-1];
//        }

        System.arraycopy(data, index, data, index+1, (size-index));

        data[index] = value;
        size++;
    }

    public int lastIndexOf(int target){
        for (int i = size-1; i >= 0; i--) {
            if(data[i] == target){
                return i;
            }
        }
        return -1;
    }

    public DynamicArray subset(int startIndex, int endIndex){
        validateIndex(startIndex);

        if(endIndex < 0 | endIndex > size){
            throw new IndexOutOfBoundsException("Index " + endIndex + " is outside bounds of list");
        }

        if(startIndex >= endIndex){
            throw new IllegalArgumentException("Start index of subset (" + startIndex + ") must be less than or equal" +
                    " to end index (" + endIndex + ")");
        }

        DynamicArray subset = new DynamicArray();
        for (int i = startIndex; i < endIndex; i++) {
            subset.add(data[i]);
        }

        return subset;
    }

    public DynamicArray altSubset(int startIndex, int endIndex){
        validateIndex(startIndex);

        if(endIndex < 0 || endIndex > size){
            throw new IndexOutOfBoundsException("Index " + endIndex + " is outside bounds of list");
        }

        if(startIndex > endIndex){
            throw new IllegalArgumentException("Start index of subset (" + startIndex + ") must be less than or equal" +
                    " to end index (" + endIndex + ")");
        }

        DynamicArray subset = new DynamicArray();

        int subsetSize = endIndex - startIndex;
        if(subset.data.length < subsetSize){
            subset.data = new int[subsetSize+10];
        }

        System.arraycopy(data, startIndex, subset.data, 0, subsetSize);
        subset.size = subsetSize;

        return subset;
    }
}
