package HW5;

public class HashSet {
    private int[] arr;
    private int capacity;

    public HashSet() {
        capacity = 16;
        arr = new int[capacity];
    }

    private int hash(int key) {
        return key % capacity;
    }

    public void add(int key) {
        int index = hash(key);
        while(arr[index] != 0) {
            index++;
            index %= capacity;
        }
        arr[index] = key;
    }

    public void remove(int key) {
        int index = hash(key);
        while(arr[index] != key) {
            index++;
            index %= capacity;
        }
        arr[index] = 0;
    }
}

