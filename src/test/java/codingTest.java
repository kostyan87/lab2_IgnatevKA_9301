import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class codingTest {

    @Test
    void testStringWithManyCharacters() {
        String s1 = "Still waters run deep.";
        String s2 = "Don't cross the bridge until you come to it.";
        String s3 = "There's no such thing as a free lunch.";

        assertEquals(ShannonFanoAlgo.coding(s1),
                "00010000101111111111100" +
                        "111000110000110010001111" +
                        "10001000111010101000110110" +
                        "11000100101");
        assertEquals(ShannonFanoAlgo.coding(s2),
                "1010011011110110110000001" +
                        "010011011001100100001000001" +
                        "1000011111110000110011101100101" +
                        "11101011111000011110011110100000101" +
                        "1011100100001101110000101001011010010" +
                        "110000110000011000101011000010001");
        assertEquals(ShannonFanoAlgo.coding(s3),
                "10111010111100010111100000110" +
                        "0100010110110100110111011001010" +
                        "010110101010000110010011101010010110" +
                        "010100101000110001011111111010100001110" +
                        "00101100101010001");
    }

    @Test
    void testStringWithTwoCharacters() {
        String s1 = "St";
        assertEquals(ShannonFanoAlgo.coding(s1),"1 0 ");
    }
}
