package roman;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;


public class Roman {
    private static final Map<Character, Integer> ROMAN_VALUES = Map.of(
            'I', 1,
            'V', 5,
            'X', 10,
            'L', 50,
            'C', 100,
            'D', 500,
            'M', 1000
    );

    private static int convertToArabic(String romanNumber) {
        if (romanNumber == null || romanNumber.isBlank()) { //Проверка строки на Null и что не пустая
            return -1;
        }
        int resultNumber = 0;
        int len = romanNumber.length();
        Map<Character, Integer> count = new HashMap<>(); //Map для хранения количества повторений каждого символа
        int max = Integer.MAX_VALUE; //Переменная для проверки убывания значений слева направо

        for (int i = 0; i < len; i++) {
            char current = romanNumber.charAt(i);

            if (!ROMAN_VALUES.containsKey(current)) {  //Проверка на корректность символа
                return -1;
            }

            count.put(current, count.getOrDefault(current, 0) + 1); //Увеличение счетчика появлений символа

            //Проверка на превышение лимита появлений символа
            if ((current == 'V' || current == 'L' || current == 'D') && count.get(current) > 1) {
                return -1;
            }

            //Проверка на превышение лимита появлений символа
            if ((current == 'I' || current == 'X' || current == 'C' || current == 'M') && count.get(current) > 3) {
                return -1;
            }

            int currentValue = ROMAN_VALUES.get(current);

            if (currentValue > max) { //Проверка убывания значений в числе
                return -1;
            }

            // Проверяем следующий символ для случаев вычитания
            if (i < len - 1) {
                char next = romanNumber.charAt(i + 1);
                if (!ROMAN_VALUES.containsKey(next)) { //Проверка на корректность символа
                    return -1;
                }

                int nextValue = ROMAN_VALUES.get(next);

                //Число появлений текущего символа перед следующим символом с большим значением не может быть больше 1
                if (nextValue > currentValue && count.get(current) > 1) {
                    return -1;
                }

                if (nextValue > currentValue) {
                    // Допустимые случаи вычитания
                    if (nextValue <= max && ((current == 'I' && (next == 'V' && !count.containsKey('V') || next == 'X')) ||
                            (current == 'X' && (next == 'L' && !count.containsKey('L') || next == 'C')) ||
                            (current == 'C' && (next == 'D' && !count.containsKey('D') || next == 'M')))) {
                        resultNumber += (nextValue - currentValue);
                        max = currentValue;
                        count.put(next, 3);
                        count.put(current, 3);
                        i++;  //Пропускаем следующий символ, т.к. уже учли
                    } else {
                        return -1; // Некорректное вычитание
                    }
                } else {
                    resultNumber += currentValue;
                    max = currentValue;
                }
            } else {
                resultNumber += currentValue;
                max = currentValue;
            }
        }
        return resultNumber;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String romanNumber = reader.readLine().strip();
            System.out.println(convertToArabic(romanNumber));
        }
    }
}
