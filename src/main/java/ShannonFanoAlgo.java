import Map.RBTreeMap;
import data_structures.LinkedList;
import data_structures.Stack;

public class ShannonFanoAlgo {

    public static void main(String[] args) {
        RBTreeMap<Character, Integer> frequenceMap = new RBTreeMap<Character, Integer>();
        LinkedList<RBTreeMap<Character, Integer>> testList = new LinkedList<>();
        fillFrequenceMap("it is test string", frequenceMap);
        testList = division(frequenceMap);

        System.out.println(sumOfMap(testList.get(0)));
        System.out.println(sumOfMap(testList.get(1)));
    }

    public static String coding(String s) {
        RBTreeMap<Character, Integer> frequenceMap = new RBTreeMap<Character, Integer>();

        fillFrequenceMap(s, frequenceMap);
        frequenceMap.printMap();

        return null;
    }

    public static void fillFrequenceMap(String s, RBTreeMap<Character, Integer> frequenceMap) {

        for (int i = 0; i < s.length(); i++) {
            if (frequenceMap.containsKey(s.charAt(i))) {
                frequenceMap.find(s.charAt(i)).value++;
            }
            else frequenceMap.insert(s.charAt(i), 1);
        }
    }

    public static int sumOfMap(RBTreeMap<Character, Integer> frequenceMap) {
        if (frequenceMap.root == RBTreeMap.Node.nil) return 0;

        int sum = 0;
        RBTreeMap.Node<Character, Integer> current = frequenceMap.root;
        Stack<RBTreeMap.Node> stack = new Stack();

        stack.push(current);

        while (stack.getSize() > 0)
        {
            current = stack.pop();
            sum += current.value;
            if (current.rightChild != RBTreeMap.Node.nil) stack.push(current.rightChild);
            if (current.leftChild != RBTreeMap.Node.nil) stack.push(current.leftChild);
        }

        return sum;
    }

    public static LinkedList<RBTreeMap<Character, Integer>> division(RBTreeMap<Character, Integer> frequenceMap) {
        RBTreeMap<Character, Integer> A = new RBTreeMap<Character, Integer>();
        RBTreeMap<Character, Integer> B = new RBTreeMap<Character, Integer>();
        LinkedList<RBTreeMap<Character, Integer>> resultList = new LinkedList<>();
        RBTreeMap.Node<Character, Integer> current = frequenceMap.root;
        Stack<RBTreeMap.Node> stack = new Stack();

        if (frequenceMap.isEmpty()) throw new RuntimeException("Map is empty");

        if (frequenceMap.getSize() == 1) {
            resultList.pushBack(frequenceMap);
            return resultList;
        }

        stack.push(current);

        while (stack.getSize() > 0)
        {
            current = stack.pop();

            if (sumOfMap(A) < sumOfMap(B))
                A.insert(current.key, current.value);
            else
                B.insert(current.key, current.value);

            if (current.rightChild != RBTreeMap.Node.nil) stack.push(current.rightChild);
            if (current.leftChild != RBTreeMap.Node.nil) stack.push(current.leftChild);
        }

        resultList.pushBack(A);
        resultList.pushBack(B);

        return resultList;
    }
}
