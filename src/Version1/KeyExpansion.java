package Version1;

public class KeyExpansion {

    private String key = "Thats my Kung Fu";

    private static int[][] s_box = {
            {0x63, 0x7C, 0x77, 0x7B, 0xF2, 0x6B, 0x6F, 0xC5, 0x30, 0x01, 0x67, 0x2B, 0xFE, 0xD7, 0xAB, 0x76},
            {0xCA, 0x82, 0xC9, 0x7D, 0xFA, 0x59, 0x47, 0xF0, 0xAD, 0xD4, 0xA2, 0xAF, 0x9C, 0xA4, 0x72, 0xC0},
            {0xB7, 0xFD, 0x93, 0x26, 0x36, 0x3F, 0xF7, 0xCC, 0x34, 0xA5, 0xE5, 0xF1, 0x71, 0xD8, 0x31, 0x15},
            {0x04, 0xC7, 0x23, 0xC3, 0x18, 0x96, 0x05, 0x9A, 0x07, 0x12, 0x80, 0xE2, 0xEB, 0x27, 0xB2, 0x75},
            {0x09, 0x83, 0x2C, 0x1A, 0x1B, 0x6E, 0x5A, 0xA0, 0x52, 0x3B, 0xD6, 0xB3, 0x29, 0xE3, 0x2F, 0x84},
            {0x53, 0xD1, 0x00, 0xED, 0x20, 0xFC, 0xB1, 0x5B, 0x6A, 0xCB, 0xBE, 0x39, 0x4A, 0x4C, 0x58, 0xCF},
            {0xD0, 0xEF, 0xAA, 0xFB, 0x43, 0x4D, 0x33, 0x85, 0x45, 0xF9, 0x02, 0x7F, 0x50, 0x3C, 0x9F, 0xA8},
            {0x51, 0xA3, 0x40, 0x8F, 0x92, 0x9D, 0x38, 0xF5, 0xBC, 0xB6, 0xDA, 0x21, 0x10, 0xFF, 0xF3, 0xD2},
            {0xCD, 0x0C, 0x13, 0xEC, 0x5F, 0x97, 0x44, 0x17, 0xC4, 0xA7, 0x7E, 0x3D, 0x64, 0x5D, 0x19, 0x73},
            {0x60, 0x81, 0x4F, 0xDC, 0x22, 0x2A, 0x90, 0x88, 0x46, 0xEE, 0xB8, 0x14, 0xDE, 0x5E, 0x0B, 0xDB},
            {0xE0, 0x32, 0x3A, 0x0A, 0x49, 0x06, 0x24, 0x5C, 0xC2, 0xD3, 0xAC, 0x62, 0x91, 0x95, 0xE4, 0x79},
            {0xE7, 0xC8, 0x37, 0x6D, 0x8D, 0xD5, 0x4E, 0xA9, 0x6C, 0x56, 0xF4, 0xEA, 0x65, 0x7A, 0xAE, 0x08},
            {0xBA, 0x78, 0x25, 0x2E, 0x1C, 0xA6, 0xB4, 0xC6, 0xE8, 0xDD, 0x74, 0x1F, 0x4B, 0xBD, 0x8B, 0x8A},
            {0x70, 0x3E, 0xB5, 0x66, 0x48, 0x03, 0xF6, 0x0E, 0x61, 0x35, 0x57, 0xB9, 0x86, 0xC1, 0x1D, 0x9E},
            {0xE1, 0xF8, 0x98, 0x11, 0x69, 0xD9, 0x8E, 0x94, 0x9B, 0x1E, 0x87, 0xE9, 0xCE, 0x55, 0x28, 0xDF},
            {0x8C, 0xA1, 0x89, 0x0D, 0xBF, 0xE6, 0x42, 0x68, 0x41, 0x99, 0x2D, 0x0F, 0xB0, 0x54, 0xBB, 0x16}
    };

    int[][] roundConstants = {
            {0x01, 0x00, 0x00, 0x00},
            {0x02, 0x00, 0x00, 0x00},
            {0x04, 0x00, 0x00, 0x00},
            {0x08, 0x00, 0x00, 0x00},
            {0x10, 0x00, 0x00, 0x00},
            {0x20, 0x00, 0x00, 0x00},
            {0x40, 0x00, 0x00, 0x00},
            {0x80, 0x00, 0x00, 0x00},
            {0x1B, 0x00, 0x00, 0x00},
            {0x36, 0x00, 0x00, 0x00},
            {0x6C, 0x00, 0x00, 0x00},
            {0xD8, 0x00, 0x00, 0x00},
            {0xAB, 0x00, 0x00, 0x00},
            {0x4D, 0x00, 0x00, 0x00},
            {0x9A, 0x00, 0x00, 0x00}
    };

    public KeyExpansion(){
        //
        // int[][] res = this.generateAlleRoundkeys(this.key, 10);

        // for(int[] r : res){
        //     outToHex(r);
        // }
    }

    /**
     * Generate all 10 roundkeys
     * Always use the key generated in the last round, to generate the next one
     * @return An Array containing all the roundkeys from round 0 to the given number of rounds.
     * Each key consists of 16 numbers in an array of length 16
     */
    public int[][] generateAlleRoundkeys(String key, int rounds){
        int[][] result = new int[rounds+1][16];
        result[0] = this.keyToHex(key);

        for(int i = 1; i<10+1; i++){
            result[i] = this.generateNextRoundKey(result[i-1], i-1);
        }

        return result;
    }

    /**
     * generate the next Roundkey with the alorithm from the AES.pdf
     * 1. Split key into 4 blocks
     * 2. XOR-Functions
     * 3. Combine results to get a new key
     */
    public int[] generateNextRoundKey(int[] key, int roundNo){
        // Split the Hex-Key into 4 blocks of 4 chars
        int[][] w = new int[8][4];
        for(int i = 0; i<4;i++){
            for(int j = 0; j<4;j++){
                w[i][j] = key[i*4+j];
            }
        }

        // xor-functions
        w[4] = XOR_Array(w[0], g(w[3].clone(), roundNo));
        w[5] = XOR_Array(w[4], w[1]);
        w[6] = XOR_Array(w[5], w[2]);
        w[7] = XOR_Array(w[6], w[3]);

        // return roundkey
        // combine the last four elements from w to make the new key
        int[] result = new int[16];

        for(int i = 0; i<4;i++){
            for(int j = 0;j<4; j++){
                result[i*4+j] = w[i+4][j];
            }
        }

        return result;
    }

    /**
     * g-function as described in the Document
     * 1. circular left shift
     * 2. Byte substitution with S-Box
     * 3. Adding round Constant (XOR)
     */
    private int[] g(int[] subkey, int roundNo){
        // circular left shift
        int first = subkey[0];
        for(int i = 1; i<4;i++){
            subkey[i-1] = subkey[i];
        }
        subkey[3] = first;

        // Byte Substitution with s-Box
        for(int i = 0; i<4; i++){
            subkey[i] = this.substituteWithS_Box(subkey[i]);
        }

        // Adding round Constant (XOR)
        // Subtracting 1 from the first element
        subkey = XOR_Array(subkey, this.roundConstants[roundNo]);

        return subkey;
    }

    /**
     * Combine two integer arrays, by XORing the corresponding entries
     */
    private static int[] XOR_Array(int[] a, int[] b){
        if(a.length != b.length) throw new RuntimeException("a and b have to be the same length");
        int[] result = new int[a.length];

        for(int i = 0; i<a.length; i++){
            result[i] = (a[i] ^ b[i]);
        }

        return result;
    }

    /**
     * Find the corresponding value of the input number in the s-box
     */
    private int substituteWithS_Box(int bytes){
        int y = bytes / 16; // to get the first four bits (first letter in the hexadecimal representation)
        int x = bytes % 16; // to get the last four bits (second letter in the hexadecimal representation)

        return s_box[y][x];
    }

    /**
     * Convert the String reresentation into Numbers using ASCII chars
     */
    private int[] keyToHex(String key){
        int[] result = new int[key.length()];

        for(int i = 0; i < key.length(); i++){
            result[i] = key.charAt(i);
        }

        return result;
    }

    /**
     * Print out the integer array representing the key in hexadecimal numbers, to compare it with the example in the document.
     */
    private static void outToHex(int[] i){
        for(int n : i) System.out.print(Integer.toHexString(n) + " ");
        System.out.println();
    }

    public static void main(String[] args){
        new KeyExpansion();
    }
}
