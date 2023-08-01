package HW5;

public class Main {
    public static void main(String[] args) {
        MyHashSet<Integer> set = new MyHashSet<>();
        set.add(1);
        set.add(2);
        set.add(3);

        System.out.println("Size: " + set.size()); // Размер: 3
        System.out.println("Contains 2: " + set.contains(2)); // Содержит ли 2: true
        System.out.println("Contains 4: " + set.contains(4)); // Содержит ли 4: false

        set.remove(2);
        System.out.println("Size: " + set.size()); // Размер: 2
        System.out.println("Contains 2: " + set.contains(2)); // Содержит ли 2: false
    }
}
