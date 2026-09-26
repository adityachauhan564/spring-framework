package topic07_hashmap_and_optional;

/*
 * Topic    : How a HashMap works inside (interview question: "build one without HashMap")
 * Key idea : an array of "buckets". hashCode() picks the bucket; keys that land in
 *            the same bucket (a collision) are kept in a small linked list (chaining).
 * Run      : java -ea -cp out topic07_hashmap_and_optional.MyHashMap   (-ea turns on the asserts)
 * Try this : add resizing - when size > 0.75 * buckets.length, double the array.
 */
public class MyHashMap<K, V> {

    // one entry in a bucket's linked list
    private static class Node<K, V> {
        final K key;
        V value;
        Node<K, V> next;

        Node(K key, V value, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    // ponytail: fixed 16 buckets, no resizing, no null keys - fine for learning, slows down with many keys
    private final Node<K, V>[] buckets;
    private int size;

    @SuppressWarnings("unchecked")
    public MyHashMap() {
        buckets = (Node<K, V>[]) new Node[16];
    }

    private int indexFor(K key) {
        // floorMod keeps the index positive even when hashCode() is negative
        return Math.floorMod(key.hashCode(), buckets.length);
    }

    public void put(K key, V value) {
        int index = indexFor(key);
        for (Node<K, V> node = buckets[index]; node != null; node = node.next) {
            if (node.key.equals(key)) {   // key already there: replace the value
                node.value = value;
                return;
            }
        }
        buckets[index] = new Node<>(key, value, buckets[index]);   // add at the front
        size++;
    }

    public V get(K key) {
        for (Node<K, V> node = buckets[indexFor(key)]; node != null; node = node.next) {
            if (node.key.equals(key)) {
                return node.value;
            }
        }
        return null;
    }

    public boolean remove(K key) {
        int index = indexFor(key);
        Node<K, V> previous = null;
        for (Node<K, V> node = buckets[index]; node != null; node = node.next) {
            if (node.key.equals(key)) {
                if (previous == null) {
                    buckets[index] = node.next;   // removing the first node
                } else {
                    previous.next = node.next;    // unlink from the middle
                }
                size--;
                return true;
            }
            previous = node;
        }
        return false;
    }

    public int size() {
        return size;
    }

    public static void main(String[] args) {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        map.put("one", 1);
        map.put("two", 2);
        map.put("one", 100);                       // replace

        System.out.println("get(one) = " + map.get("one"));
        System.out.println("size     = " + map.size());

        assert map.get("one") == 100;
        assert map.get("two") == 2;
        assert map.get("three") == null;
        assert map.size() == 2;

        // 100 keys in 16 buckets forces collisions, so chaining is tested too
        MyHashMap<Integer, Integer> squares = new MyHashMap<>();
        for (int i = -50; i < 50; i++) {
            squares.put(i, i * i);
        }
        assert squares.size() == 100;
        assert squares.get(-7) == 49;
        assert squares.remove(-7);
        assert !squares.remove(-7);
        assert squares.get(-7) == null;
        assert squares.size() == 99;

        System.out.println("All MyHashMap checks passed");
    }
}
