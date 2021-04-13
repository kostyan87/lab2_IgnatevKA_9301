import Map.RBTreeMap;
import data_structures.LinkedList;
import data_structures.Stack;

public class ShannonFanoAlgo {

    public static void main(String[] args) {
        RBTreeMap<Character, Integer> frequenceMap1 = new RBTreeMap<Character, Integer>();
        RBTreeMap<Character, String> mapOfCodes1 = new RBTreeMap<Character, String>();
        RBTreeMap<Character, Integer> frequenceMap2 = new RBTreeMap<Character, Integer>();
        RBTreeMap<Character, String> mapOfCodes2 = new RBTreeMap<Character, String>();
        RBTreeMap<Character, Integer> frequenceMap3 = new RBTreeMap<Character, Integer>();
        RBTreeMap<Character, String> mapOfCodes3 = new RBTreeMap<Character, String>();

        String s1 = "Still waters run deep.";
        String s2 = "Don't cross the bridge until you come to it.";
        String s3 = "There's no such thing as a free lunch.";

        String codingS1 = coding(s1);
        String codingS2 = coding(s2);
        String codingS3 = coding(s3);

        System.out.println("Coding:");

        System.out.println("First string:");
        System.out.println(coding(s1));
        System.out.println("Second string:");
        System.out.println(coding(s2));
        System.out.println("Third string:");
        System.out.println(coding(s3));


        fillFrequenceMap(s1, frequenceMap1);
        fillFrequenceMap(s2, frequenceMap2);
        fillFrequenceMap(s3, frequenceMap3);

        getPrefixCodes(mapOfCodes1, frequenceMap1, "", 'n');
        getPrefixCodes(mapOfCodes2, frequenceMap2, "", 'n');
        getPrefixCodes(mapOfCodes3, frequenceMap3, "", 'n');

        System.out.println("Encoding:");

        System.out.println("First string:");
        System.out.println(encoding(codingS1, mapOfCodes1));
        System.out.println("Second string:");
        System.out.println(encoding(codingS2, mapOfCodes2));
        System.out.println("Third string:");
        System.out.println(encoding(codingS3, mapOfCodes3));
    }

    public static String coding(String s) {
        RBTreeMap<Character, Integer> frequenceMap = new RBTreeMap<Character, Integer>();
        RBTreeMap<Character, String> mapOfCodes = new RBTreeMap<Character, String>();
        String codingString;
        double compressionСoefficient;

        fillFrequenceMap(s, frequenceMap);
        //frequenceMap.printMap();
        getPrefixCodes(mapOfCodes, frequenceMap,"", 'n');
        mapOfCodes.printMap();
        codingString = getCodingString(mapOfCodes, s);
        compressionСoefficient = (s.length() * 8.0) / codingString.length();
        System.out.println(compressionСoefficient);

        return codingString;
    }

    public static String encoding(String s, RBTreeMap<Character, String> mapOfCodes) {

        String encodingString = "";
        int i = 0;
        while (i < s.length()) {

            Stack<RBTreeMap.Node> stack = new Stack();
            RBTreeMap.Node<Character, String> current = mapOfCodes.root;
            stack.push(current);

            while (stack.getSize() > 0)
            {
                current = stack.pop();

                // Проверяем совпадает ли последовательность с кодом символа
                int count = 0;
                for (int k = 0; k < current.value.length(); k++) {
                    if (i == s.length()) break;
                    if (s.charAt(i) == current.value.charAt(k)) {
                        i++;
                        count++;
                    }
                    else {
                        i -= count;
                        break;
                    }
                }

                // Если сопало
                if (count == current.value.length()) {
                    encodingString += current.key;
                }

                if (current.rightChild != RBTreeMap.Node.nil) stack.push(current.rightChild);
                if (current.leftChild != RBTreeMap.Node.nil) stack.push(current.leftChild);
            }
        }

        return encodingString;
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

    public static void getPrefixCodes(RBTreeMap<Character, String> mapOfCodes,
                                     RBTreeMap<Character, Integer> frequenceMap,
                                     String prefixCode, char code) {

        //RBTreeMap<Character, Integer> leftBranch = new RBTreeMap<Character, Integer>();
        //RBTreeMap<Character, Integer> rightBranch = new RBTreeMap<Character, Integer>();

        if (frequenceMap.getSize() == 1) {
            mapOfCodes.insert(frequenceMap.root.key, prefixCode + code);
            return;
        }

        if (code == 'n') {
            getPrefixCodes(mapOfCodes, division(frequenceMap).get(0), prefixCode,'0');
            getPrefixCodes(mapOfCodes, division(frequenceMap).get(1), prefixCode,'1');
        }
        else {
            prefixCode += code;
            getPrefixCodes(mapOfCodes, division(frequenceMap).get(0), prefixCode,'0');
            getPrefixCodes(mapOfCodes, division(frequenceMap).get(1), prefixCode,'1');
        }
    }

    public static String getCodingString(RBTreeMap<Character, String> mapOfCodes, String s) {

        String codingString = "";

        for (int i = 0; i < s.length(); i++) {
            codingString += mapOfCodes.find(s.charAt(i)).value;
        }

        return codingString;
    }
}
