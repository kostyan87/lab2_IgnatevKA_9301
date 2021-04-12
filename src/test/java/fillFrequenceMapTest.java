import Map.RBTreeMap;
import Map.RBTreeUtils;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class fillFrequenceMapTest {

    @Test
    void testFillString() {

        String expectedMap = "Node{key=i, value=3, color=true}\n" +
                             "Node{key=e, value=1, color=true}\n" +
                             "Node{key= , value=3, color=false}\n" +
                             "Node{key=g, value=1, color=false}\n" +
                             "Node{key=s, value=3, color=false}\n" +
                             "Node{key=r, value=1, color=true}\n" +
                             "Node{key=n, value=1, color=false}\n" +
                             "Node{key=t, value=4, color=true}\n";
        RBTreeMap<Character, Integer> testMap = new RBTreeMap<Character, Integer>();

        ShannonFanoAlgo.fillFrequenceMap("it is test string", testMap);

        assertEquals(RBTreeUtils.mapToString(testMap), expectedMap);
    }

    @Test
    void testEmptyString() {

        String expectedMap = "";
        RBTreeMap<Character, Integer> testMap = new RBTreeMap<Character, Integer>();

        ShannonFanoAlgo.fillFrequenceMap("", testMap);

        assertEquals(RBTreeUtils.mapToString(testMap), "Map is empty");
    }
}
