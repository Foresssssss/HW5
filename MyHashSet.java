package HW5;

public class MyHashSet<T> {
    private static final int DEFAULT_CAPACITY = 16;
    private static final double DEFAULT_LOAD_FACTOR = 0.75;

    private Entry<T>[] buckets;
    private int size;
    private double loadFactor;

    public MyHashSet() {
        this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    public MyHashSet(int capacity) {
        this(capacity, DEFAULT_LOAD_FACTOR);
    }

    public MyHashSet(int capacity, double loadFactor) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive");
        }
        if (loadFactor <= 0.0 || loadFactor >= 1.0) {
            throw new IllegalArgumentException("Load factor must be between 0.0 and 1.0");
        }
        this.buckets = new Entry[capacity];
        this.size = 0;
        this.loadFactor = loadFactor;
    }

    public void add(T element) {
        if (contains(element)) {
            return;
        }
        if ((double) size / buckets.length >= loadFactor) {
            resize();
        }

        int bucketIndex = getBucketIndex(element);

        Entry<T> newEntry = new Entry<>(element);
        newEntry.next = buckets[bucketIndex];
        buckets[bucketIndex] = newEntry;
        size++;
    }

    public void remove(T element) {
        int bucketIndex = getBucketIndex(element);
        Entry<T> prev = null;
        Entry<T> current = buckets[bucketIndex];

        while (current != null) {
            if (current.element.equals(element)) {
                if (prev == null) { // Removing the first entry in the bucket
                    buckets[bucketIndex] = current.next;
                } else {
                    prev.next = current.next;
                }
                size--;
                return;
            }
            prev = current;
            current = current.next;
        }
    }

    public boolean contains(T element) {
        int bucketIndex = getBucketIndex(element);
        Entry<T> current = buckets[bucketIndex];

        while (current != null) {
            if (current.element.equals(element)) {
                return true;
            }
            current = current.next;
        }

        return false;
    }

    public int size() {
        return size;
    }

    private int getBucketIndex(T element) {
        int hashCode = element.hashCode();
        return Math.abs(hashCode % buckets.length);
    }

    private void resize() {
        int newCapacity = buckets.length * 2;
        Entry<T>[] newBuckets = new Entry[newCapacity];

        for (Entry<T> entry : buckets) {
            Entry<T> current = entry;
            while (current != null) {
                Entry<T> next = current.next;
                int bucketIndex = getBucketIndex(current.element);
                current.next = newBuckets[bucketIndex];
                newBuckets[bucketIndex] = current;
                current = next;
            }
        }

        buckets = newBuckets;
    }

    private static class Entry<T> {
        private final T element;
        private Entry<T> next;

        public Entry(T element) {
            this.element = element;
            this.next = null;
        }
    }
}