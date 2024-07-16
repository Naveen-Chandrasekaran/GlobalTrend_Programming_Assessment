import java.util.*;

// Definition for a binary tree node.
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serializeHelper(root, sb);
        return sb.toString();
    }

    private void serializeHelper(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append("null,");
            return;
        }
        sb.append(root.val).append(",");
        serializeHelper(root.left, sb);
        serializeHelper(root.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] nodes = data.split(",");
        Queue<String> queue = new LinkedList<>(Arrays.asList(nodes));
        return deserializeHelper(queue);
    }

    private TreeNode deserializeHelper(Queue<String> queue) {
        String val = queue.poll();
        if (val.equals("null")) {
            return null;
        }
        TreeNode node = new TreeNode(Integer.parseInt(val));
        node.left = deserializeHelper(queue);
        node.right = deserializeHelper(queue);
        return node;
    }

    // Print the tree in level order to verify deserialization
    private void printTree(TreeNode root) {
        if (root == null) {
            System.out.println("Tree is empty");
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            if (current == null) {
                System.out.print("null ");
                continue;
            }
            System.out.print(current.val + " ");
            queue.add(current.left);
            queue.add(current.right);
        }
        System.out.println();
    }

    // Test the Codec
    public static void main(String[] args) {
        Codec codec = new Codec();
        // Example tree:
        //     1
        //    / \
        //   2   3
        //      / \
        //     4   5
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);

        // Serialize
        String serializedData = codec.serialize(root);
        System.out.println("Serialized data: " + serializedData);

        // Deserialize
        TreeNode deserializedRoot = codec.deserialize(serializedData);
        System.out.println("Deserialized tree (level order):");
        codec.printTree(deserializedRoot);
    }
}
