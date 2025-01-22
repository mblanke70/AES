public class AES {

    // 10 Runden, 16 Byte Plaintext, 16 Byte = 128 Bit Schlüssel
    public int[][] states = new int[11][16];

    public int[] key = new int[16];

    public AES(String p, String k) {

        for(int i=0; i<p.length(); i++) {
            states[0][i] = (int) p.charAt(i);   // state 0
            key[i]       = (int) k.charAt(i);   // round key 0
        }

        System.out.println( "Plaintext: " + toHex(states[0]) );
        System.out.println( "Key: " + toHex(key) );

        // Rundenschlüssekl generieren (key expansion)
        /*
         * Ablauf:
         *
         * R = 0
         * addRoundKey(keys[R])
         *
         * wiederhole für R=1 bis 9:
         *   subBytes
         *   shiftRows
         *   mixColumns
         *   addRoundKey(keys[R])
         *
         * subBytes
         * shiftRows
         * mixColumns
         */
    }

    public int[] addRoundKey(int[] input) {
        return new int[]{0};
    }

    public int[] subBytes(int[] input) {
        return new int[]{0};
    }

    public int[] shiftRows(int[] input) {
        return new int[]{0};
    }

    public int[] mixColumns(int[] input) {
        return new int[]{0};
    }


    public static void main(String[] argv) {
        AES aes = new AES("Two One Nine Two","Thats my Kung Fu");
    }

    public static String toHex(int[] vals) {
        String s = "";
        for(int i=0; i<16; i++) {
            s += Integer.toHexString( vals[i] ) + " ";
        }
        return s;
    }
}
