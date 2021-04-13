import Map.RBTreeMap;
import org.junit.jupiter.api.Test;

public class encodingTest {

    @Test
    void test() {
        RBTreeMap<Character, Integer> frequenceMap = new RBTreeMap<Character, Integer>();
        RBTreeMap<Character, String> mapOfCodes = new RBTreeMap<Character, String>();

        ShannonFanoAlgo.fillFrequenceMap("Don't cross the bridge until you come to it.", frequenceMap);
        ShannonFanoAlgo.getPrefixCodes(mapOfCodes, frequenceMap,"", 'n');
        System.out.println(ShannonFanoAlgo.encoding("1010011011110110110000001" +
                "010011011001100100001000001" +
                "1000011111110000110011101100101" +
                "11101011111000011110011110100000101" +
                "1011100100001101110000101001011010010" +
                "110000110000011000101011000010001", mapOfCodes));

        ShannonFanoAlgo.fillFrequenceMap("Still waters run deep.", frequenceMap);
        ShannonFanoAlgo.getPrefixCodes(mapOfCodes, frequenceMap,"", 'n');
        System.out.println(ShannonFanoAlgo.encoding("00010000101111111" +
                "111100111000110000110010001111" +
                "1000100011101010100011011011000" +
                "100101", mapOfCodes));

        ShannonFanoAlgo.fillFrequenceMap("There's no such thing as a free lunch.", frequenceMap);
        ShannonFanoAlgo.getPrefixCodes(mapOfCodes, frequenceMap,"", 'n');
        System.out.println(ShannonFanoAlgo.encoding("1011101011110001011110" +
                "0000110010001011011010011011101100101001" +
                "01101010100001100100111010100101100101" +
                "00101000110001011111111010100001110001011" +
                "00101010001", mapOfCodes));
    }
}
