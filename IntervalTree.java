import java.util.*;

class Interval {
    int start;
    int end;

    public Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }
}

class IntervalTreeNode {
    Interval interval;
    int maxEnd;
    IntervalTreeNode left;
    IntervalTreeNode right;

    public IntervalTreeNode(Interval interval) {
        this.interval = interval;
        this.maxEnd = interval.end;
        this.left = null;
        this.right = null;
    }
}

public class IntervalTree {
    private IntervalTreeNode root;

    public IntervalTree() {
        this.root = null;
    }

    // Insert an interval into the interval tree
    public void insertInterval(int start, int end) {
        Interval newInterval = new Interval(start, end);
        root = insertInterval(root, newInterval);
    }

    private IntervalTreeNode insertInterval(IntervalTreeNode node, Interval interval) {
        if (node == null) {
            return new IntervalTreeNode(interval);
        }

        // Insert into left subtree
        if (interval.start < node.interval.start) {
            node.left = insertInterval(node.left, interval);
        } else { // Insert into right subtree
            node.right = insertInterval(node.right, interval);
        }

        // Update maxEnd in the current node
        if (node.maxEnd < interval.end) {
            node.maxEnd = interval.end;
        }

        return node;
    }

    // Delete an interval from the interval tree
    public void deleteInterval(int start, int end) {
        Interval deleteInterval = new Interval(start, end);
        root = deleteInterval(root, deleteInterval);
    }

    private IntervalTreeNode deleteInterval(IntervalTreeNode node, Interval interval) {
        if (node == null) {
            return null;
        }

        if (interval.start < node.interval.start) {
            node.left = deleteInterval(node.left, interval);
        } else if (interval.start > node.interval.start) {
            node.right = deleteInterval(node.right, interval);
        } else { // Found the node to delete
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }

            // Node with two children: Get the inorder successor (smallest in the right subtree)
            IntervalTreeNode minNode = findMin(node.right);

            // Copy the inorder successor's content to this node
            node.interval = minNode.interval;

            // Delete the inorder successor
            node.right = deleteInterval(node.right, minNode.interval);
        }

        // Update maxEnd in the current node
        if (node != null) {
            node.maxEnd = Math.max(node.interval.end, maxEnd(node.right));
        }

        return node;
    }

    private IntervalTreeNode findMin(IntervalTreeNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    private int maxEnd(IntervalTreeNode node) {
        return node == null ? Integer.MIN_VALUE : node.maxEnd;
    }

    // Find all intervals that overlap with [start, end]
    public List<Interval> findOverlappingIntervals(int start, int end) {
        List<Interval> result = new ArrayList<>();
        findOverlappingIntervals(root, start, end, result);
        return result;
    }

    private void findOverlappingIntervals(IntervalTreeNode node, int start, int end, List<Interval> result) {
        if (node == null) {
            return;
        }

        // Check if node's interval overlaps with [start, end]
        if (node.interval.start <= end && node.interval.end >= start) {
            result.add(node.interval);
        }

        // Recursively search left and right subtrees if necessary
        if (node.left != null && node.left.maxEnd >= start) {
            findOverlappingIntervals(node.left, start, end, result);
        }
        if (node.right != null && node.right.interval.start <= end) {
            findOverlappingIntervals(node.right, start, end, result);
        }
    }

    public static void main(String[] args) {
        IntervalTree intervalTree = new IntervalTree();

        // Insert intervals
        intervalTree.insertInterval(15, 20);
        intervalTree.insertInterval(10, 30);
        intervalTree.insertInterval(17, 19);
        intervalTree.insertInterval(5, 20);
        intervalTree.insertInterval(12, 15);
        intervalTree.insertInterval(30, 40);

        // Find intervals overlapping with [14, 16]
        List<Interval> overlappingIntervals = intervalTree.findOverlappingIntervals(14, 16);
        System.out.println("Intervals overlapping with [14, 16]:");
        for (Interval interval : overlappingIntervals) {
            System.out.println("[" + interval.start + ", " + interval.end + "]");
        }

        // Delete an interval [15, 20]
        intervalTree.deleteInterval(15, 20);

        // Find intervals overlapping with [14, 16] after deletion
        overlappingIntervals = intervalTree.findOverlappingIntervals(14, 16);
        System.out.println("\nIntervals overlapping with [14, 16] after deletion:");
        for (Interval interval : overlappingIntervals) {
            System.out.println("[" + interval.start + ", " + interval.end + "]");
        }
    }
}
