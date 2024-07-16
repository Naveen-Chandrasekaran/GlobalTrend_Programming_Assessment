class TrieNode {
    // Each node has an array of children and a boolean to mark the end of a word
    TrieNode[] children;
    boolean isEndOfWord;

    // Constructor to initialize the node
    public TrieNode() {
        children = new TrieNode[26]; // Each index corresponds to a letter 'a' to 'z'
        isEndOfWord = false;
    }
}

public class Trie {
    private TrieNode root;

    // Constructor to initialize the Trie with a root node
    public Trie() {
        root = new TrieNode();
    }

    // Method to insert a word into the trie
    public void insert(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            if (node.children[index] == null) {
                node.children[index] = new TrieNode();
            }
            node = node.children[index];
        }
        node.isEndOfWord = true; // Mark the end of the word
    }

    // Method to search for a word in the trie
    public boolean search(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            if (node.children[index] == null) {
                return false;
            }
            node = node.children[index];
        }
        return node.isEndOfWord; // Return true only if it's the end of the word
    }

    // Method to check if any word in the trie starts with a given prefix
    public boolean startsWith(String prefix) {
        TrieNode node = root;
        for (char c : prefix.toCharArray()) {
            int index = c - 'a';
            if (node.children[index] == null) {
                return false;
            }
            node = node.children[index];
        }
        return true; // If we can traverse the trie up to the end of the prefix, return true
    }

    // Main method for testing
    public static void main(String[] args) {
        Trie trie = new Trie();

        // Inserting words into the trie
        trie.insert("apple");
        trie.insert("app");

        // Testing search method
        System.out.println(trie.search("apple")); // true
        System.out.println(trie.search("app"));   // true
        System.out.println(trie.search("appl"));  // false

        // Testing startsWith method
        System.out.println(trie.startsWith("app")); // true
        System.out.println(trie.startsWith("apl")); // false
    }
}
