package sisyphus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class Sisyphus {

    private static long getEnergyForUnion(List<Long> stones) {
        PriorityQueue<Long> q = new PriorityQueue<>(stones);
        long sum = 0;
        while (q.size() >= 2) {
            long a1 = q.remove();
            long a2 = q.remove();
            sum += a1 + a2;
            q.add(a1 + a2);
        }
        return sum;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = readInt(reader);
            List<Long> stones = readList(reader);
            System.out.println(getEnergyForUnion(stones));
        }
    }

    private static List<Long> readList(BufferedReader reader) throws IOException {
        return Arrays.asList(reader.readLine().strip().split(" "))
                .stream()
                .map(Long::parseLong)
                .collect(Collectors.toList());
    }

    private static int readInt(BufferedReader reader) throws NumberFormatException, IOException {
        return Integer.parseInt(reader.readLine());
    }
}

