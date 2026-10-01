package topic26_maps_and_hashing;

/*
 * Topic    : How a HashMap works inside (a common interview question: "build one without using HashMap")
 * Key idea : Inside, a HashMap is just an array of "buckets".
 *            Think of a post office with 16 pigeon-holes:
 *            - hashCode() of the key decides WHICH pigeon-hole the letter goes into.
 *            - Two keys can land in the same pigeon-hole. This is called a collision.
 *              They are simply kept one after another in a small linked list (this is called chaining).
 *            - To find a key: go to its pigeon-hole, then check the few letters there with equals().
 * Run      : java -ea -cp out topic26_maps_and_hashing.MyHashMap   (-ea turns on the assert checks)
 * Try this : Add resizing - when size > 0.75 * buckets.length, double the array.
 */
public class MyHashMap<K, V> {

    // one entry (one letter) in a bucket's linked list
    private static class Node<K, V> {
        final K key;
        V value;
        Node<K, V> next;            // the next entry in the same bucket, or null if this is the last

        Node(K key, V value, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    // ponytail: fixed 16 buckets, no resizing, no null keys - fine for learning, but gets slow with many keys
    private final Node<K, V>[] buckets;
    private int size;

    @SuppressWarnings("unchecked")
    public MyHashMap() {
        buckets = (Node<K, V>[]) new Node[16];
    }

    private int indexFor(K key) {
        // hashCode() can be negative. floorMod still gives a position from 0 to 15
        return Math.floorMod(key.hashCode(), buckets.length);
    }

    public void put(K key, V value) {
        int index = indexFor(key);
        for (Node<K, V> node = buckets[index]; node != null; node = node.next) {
            if (node.key.equals(key)) {   // key is already there: just replace the value
                node.value = value;
                return;
            }
        }
        buckets[index] = new Node<>(key, value, buckets[index]);   // new key: add it at the front of the bucket
        size++;
    }

    public V get(K key) {
        // go to the right bucket, then walk its list looking for the key
        for (Node<K, V> node = buckets[indexFor(key)]; node != null; node = node.next) {
            if (node.key.equals(key)) {
                return node.value;
            }
        }
        return null;                      // not found
    }

    public boolean remove(K key) {
        int index = indexFor(key);
        Node<K, V> previous = null;
        for (Node<K, V> node = buckets[index]; node != null; node = node.next) {
            if (node.key.equals(key)) {
                if (previous == null) {
                    buckets[index] = node.next;   // it was the first one: the bucket now starts at the next node
                } else {
                    previous.next = node.next;    // it was in the middle: the previous node skips over it
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
        map.put("one", 100);                       // same key again: replaces 1 with 100

        System.out.println("get(one) = " + map.get("one"));
        System.out.println("size     = " + map.size());

        assert map.get("one") == 100;
        assert map.get("two") == 2;
        assert map.get("three") == null;
        assert map.size() == 2;

        // 100 keys in only 16 buckets - collisions are sure to happen, so chaining gets tested too
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
