package goodString;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GoodStrings {

    private static String convertToGoodString(String s) {

        int len = s.length();
        if (len <= 1) {
            return s;
        }

        int dif = Math.abs('a' - 'A');
        char[] result = new char[len];
        int writeIndex = 0;

        for (int i = 0; i < len; i++) {
            if (writeIndex > 0 && Math.abs(result[writeIndex - 1] - s.charAt(i)) == dif) {
                writeIndex--; // Удаляем предыдущий символ
            } else {
                result[writeIndex] = s.charAt(i);
                writeIndex++;
            }
        }

        return new String(result, 0, writeIndex);
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String probablyBadString = reader.readLine().strip();
            System.out.println(convertToGoodString(probablyBadString));
        }
    }
}

