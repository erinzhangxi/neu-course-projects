import java.util.*;

import tester.Tester;

/**
 * A class that defines a new permutation code, as well as methods for encoding
 * and decoding of the messages that use this code.
 */
public class PermutationCode {
    /** The original list of characters to be encoded */
    ArrayList<Character> alphabet = new ArrayList<Character>(Arrays.asList('a',
            'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
            'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'));

    ArrayList<Character> code = new ArrayList<Character>(26);
    
    /** A random number generator */
    Random rand = new Random();

    /**
     * Create a new instance of the encoder/decoder with a new permutation code
     */
    PermutationCode() {
        this.code = this.initEncoder();
    }

    /**
     * Create a new instance of the encoder/decoder with the given code
     */
    PermutationCode(ArrayList<Character> code) {
        this.code = code;
    }

    /** Initialize the encoding permutation of the characters */
    ArrayList<Character> initEncoder() {
        ArrayList<Character> a0 = new ArrayList<Character>();
        ArrayList<Character> randomCode = new ArrayList<Character>();

        a0.addAll(this.alphabet);

        while (a0.size() > 0) {
            int i = rand.nextInt(a0.size());

            randomCode.add(a0.get(i));
            a0.remove(i);
        }
        return randomCode;
    }

    /**
     * produce an encoded <code>String</code> from the given <code>String</code>
     * 
     * @param source
     *            the <code>String</code> to encode
     * @return the secretly encoded <String>
     */
    String encode(String source, String acc) {
        if (source.length() > 0) {
            return encode(source.substring(1), 
                    acc + this.code.get(alphabet.indexOf(source.charAt(0))));
        }
        else {
            return acc;
        }
    }

    /**
     * produce an decoded <code>String</code> from the given <code>String</code>
     * 
     * @param source
     *            the <code>String</code> to decode
     * @return the revealed <String>
     */
    String decode(String code, String acc) {
       if (code.length() > 0) {
           return decode(code.substring(1), 
                   acc + this.alphabet.get(alphabet.indexOf(code.charAt(0))));
       }
       else {
           return acc;
       }
    }
}

class ExamplesPermutation {

    PermutationCode p0;
    PermutationCode p1;
    PermutationCode pRandom;
    PermutationCode p2;

    ArrayList<Character> a0;
    ArrayList<Character> a1;

    String s0;
    String s1;
    String s2;
    String s2e;
    String s3;
    String s3e;

    void initialize() {

        ArrayList<Character> arr0 = new ArrayList<Character>(
                Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
                        'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u',
                        'v', 'w', 'x', 'y', 'z'));

        ArrayList<Character> encryption = new ArrayList<Character>(
                Arrays.asList('b', 'e', 'a', 'c', 'd', 'f', 'g', 'h', 'i', 'j',
                        'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u',
                        'v', 'w', 'x', 'y', 'z', 'a'));

        p0 = new PermutationCode(arr0);
        p1 = new PermutationCode(encryption);
        pRandom = new PermutationCode();

        this.a0 = new ArrayList<Character>();

        s0 = "";
        s1 = "abc";
        s2 = "cabbed";
        s2e = "abeedc";
        s3 = "badace";
        s3e = "ebcbad";

    }

    // tests for the method decode(String)
    void testDecode(Tester t) {
        this.initialize();

        t.checkExpect(this.p1.decode(s2e, ""), s2);
        t.checkExpect(this.p1.decode(s3e, ""), s3);

    }

    // tests for the method encode(String)
    void testEncode(Tester t) {
        this.initialize();

        this.p2 = new PermutationCode(this.pRandom.code);

        t.checkExpect(this.p1.encode(s2, ""), s2e);
        t.checkExpect(this.p1.encode(s3, ""), s3e);
        t.checkExpect(this.pRandom.encode(s3, ""), this.p2.encode(s3, ""));

    }

}
