import java.util.HashMap;

class LRU {
    // Node class to represent each entry in the cache
    private class Node {
        int key;
        int value;
        Node prev;
        Node next;
        
        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
    
    private int capacity; // Maximum capacity of the cache
    private HashMap<Integer, Node> map; // HashMap for O(1) access
    private Node head; // Dummy head of the doubly linked list
    private Node tail; // Dummy tail of the doubly linked list
    
    public LRU(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        // Initialize the dummy head and tail nodes
        this.head = new Node(0, 0);
        this.tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
    }
    
    // Get the value of the key if it exists in the cache
    public int get(int key) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            // Move the accessed node to the head of the linked list
            remove(node);
            insertToHead(node);
            return node.value;
        } else {
            return -1; // Return -1 if the key is not found
        }
    }
    
    // Set or insert the value of the key in the cache
    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.value = value;
            // Move the updated node to the head of the linked list
            remove(node);
            insertToHead(node);
        } else {
            if (map.size() == capacity) {
                // Remove the least recently used item
                map.remove(tail.prev.key);
                remove(tail.prev);
            }
            Node newNode = new Node(key, value);
            map.put(key, newNode);
            // Insert the new node at the head of the linked list
            insertToHead(newNode);
        }
    }
    
    // Remove a node from the linked list
    private void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    
    // Insert a node at the head of the linked list
    private void insertToHead(Node node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }
    
    public static void main(String[] args) {
        LRU cache = new LRU(2);
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println("Key: 1 Value: "+cache.get(1));   // returns 1

        cache.put(3, 3);               // evicts key 2
        System.out.println("Key: 2 Value: "+cache.get(2));    // returns -1 (Not Found)
        
        cache.put(4, 4);                     // evicts key 1
        System.out.println("Key: 1 Value: "+cache.get(1));    //returns -1 (Not Found)
        System.out.println("Key: 3 Value: "+cache.get(3));   // returns 3
        System.out.println("Key: 4 Value: "+cache.get(4));    // returns 4

    }
}
