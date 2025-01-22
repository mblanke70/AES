public class AES {

    // 10 Runden, 16 Byte Plaintext, 16 Byte = 128 Bit Schlüssel
    public int[][] states = new int[10][16];
    public int[][] keys   = new int[10][16];

    public AES(String plain, String key) {

        for(int i=0; i<plain.length(); i++) {
            states[0][i] = (int) plain.charAt(i);
            keys  [0][i] = (int) key.charAt(i);
        }

        System.out.println( "Plaintext: " + toHex(states[0]) );
        System.out.println( "Key: " + toHex(keys[0]) );
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
