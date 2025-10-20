package calculator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringAddCalculator {
    private static final String DEFAULT_DELIMS = ",|:";
    private static final Pattern CUSTOM = Pattern.compile("//(.)\\\\n(.*)"); // ← 수정됨

    public static int add(String input) {
        if (input == null || input.isEmpty()) return 0;

        Matcher m = CUSTOM.matcher(input);
        String numbers = input;
        String delim = DEFAULT_DELIMS;

        if (m.matches()) {
            delim = Pattern.quote(m.group(1));
            numbers = m.group(2);
        }

        String[] tokens = numbers.split(delim);
        int sum = 0;
        for (String token : tokens) {
            sum += parsePositive(token);
        }
        return sum;
    }

    private static int parsePositive(String token) {
        int num;
        try {
            num = Integer.parseInt(token);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자가 아닙니다: " + token);
        }
        if (num < 0) {
            throw new IllegalArgumentException("음수는 허용되지 않습니다: " + num);
        }
        return num;
    }
}
