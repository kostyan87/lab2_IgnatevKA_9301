import Map.RBTreeMap;

public class ShannonFanoAlgo {

    public static void main(String[] args) {
        coding("it is test string");
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
}
