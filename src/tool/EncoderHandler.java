package tool;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class EncoderHandler {

    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
//        boolean result = verifyChecksum("/home/eclipse-jee-indigo-SR2-linux-gtk-x86_64.tar.gz", "177750b65a21a9043105fd0820b85b58cf148ae4");
//        System.out.println("Does the file's checksum matches the expected one? " + result);
    }

    /**
     * Verifies file's SHA1 checksum
     *
     * @return true if the expeceted SHA1 checksum matches the file's SHA1 checksum; false otherwise.
     * @throws NoSuchAlgorithmException
     * @throws IOException
     */
    public static String sha1(byte data[]) throws NoSuchAlgorithmException {
        MessageDigest sha1 = MessageDigest.getInstance("SHA1");

        sha1.update(data, 0, data.length);
        byte[] hashBytes = sha1.digest();

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < hashBytes.length; i++) {
            sb.append(Integer.toString((hashBytes[i] & 0xff) + 0x100, 16).substring(1));
        }

        String fileHash = sb.toString();

        return fileHash;
    }


}