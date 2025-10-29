package status200;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

public class Status200 {
    private static long getNumberOfGoodPairs(int n, List<Integer> numbers) {
        long result = 0;
        Map<Integer, Integer> remainders = new HashMap<>();

        for (int num : numbers) {
            int remainder = num % 200;
            if (remainder < 0) {
                remainder += 200; // Обработка отрицательных остатков
            }

            remainders.put(remainder, remainders.getOrDefault(remainder, 0) + 1);
        }

        for (int count : remainders.values()) {
            result += (long) count * (count - 1) / 2;
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = readInt(reader);
            List<Integer> numbers = readList(reader);
            System.out.println(getNumberOfGoodPairs(n, numbers));
        }
    }

    private static List<Integer> readList(BufferedReader reader) throws IOException {
        return Arrays.asList(reader.readLine().strip().split(" "))
                .stream()
                .map(token -> Integer.parseInt(token))
                .collect(Collectors.toList());
    }

    private static int readInt(BufferedReader reader) throws NumberFormatException, IOException {
        return Integer.parseInt(reader.readLine());
    }
}