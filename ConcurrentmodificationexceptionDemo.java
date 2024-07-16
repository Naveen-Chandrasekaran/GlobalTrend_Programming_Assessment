import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

public class ConcurrentmodificationexceptionDemo {

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();

        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        // This will throw ConcurrentModificationException
        try {
            Iterator<Integer> it = list.iterator();
            while (it.hasNext()) {
                Integer value = it.next();
                System.out.println("List Value:" + value);
                if (value.equals(3)) {
                    list.remove(value);
                }
            }
        } catch (ConcurrentModificationException e) {
            System.out.println("Caught ConcurrentModificationException");
        }

        // Proper way to remove elements using an iterator
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            Integer value = iterator.next();
            if (value.equals(3)) {
                iterator.remove();
            }
        }

        // Printing the list after modification
        System.out.println("List after removal: " + list);
    }
}
