import Map.RBTreeMap;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class sumOfMapTest {

    @Test
    void testNotEmptyMap() {
        RBTreeMap<Character, Integer> testMap = new RBTreeMap<Character, Integer>();
        ShannonFanoAlgo.fillFrequenceMap("it is test string", testMap);
        assertEquals(ShannonFanoAlgo.sumOfMap(testMap),17);
    }

    @Test
    void testEmptyMap() {
        RBTreeMap<Character, Integer> testMap = new RBTreeMap<Character, Integer>();
        ShannonFanoAlgo.fillFrequenceMap("", testMap);
        assertEquals(ShannonFanoAlgo.sumOfMap(testMap),0);
    }
}
