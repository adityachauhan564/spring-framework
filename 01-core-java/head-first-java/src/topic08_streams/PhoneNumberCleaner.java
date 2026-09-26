package topic08_streams;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/*
 * Topic    : Stream API - a real-world pipeline (interview question)
 * Key idea : a stream is a chain of small steps. filter() keeps or drops,
 *            map() transforms, distinct() removes duplicates, collect() builds the result.
 *            Nothing runs until the terminal step (collect).
 * Run      : java -ea -cp out topic08_streams.PhoneNumberCleaner
 * Try this : also accept numbers that start with "0" + 10 digits.
 */
public class PhoneNumberCleaner {

    static List<String> clean(List<String> rawNumbers) {
        return rawNumbers.stream()
                .filter(Objects::nonNull)                    // 1. drop nulls (before trim, or trim throws)
                .map(String::trim)                           // 2. trim spaces
                .filter(text -> !text.isEmpty())             // 3. drop blank strings
                .map(text -> text.replaceAll("[^0-9]", ""))  // 4. keep digits only
                .map(PhoneNumberCleaner::removeCountryCode)  // 5. 91XXXXXXXXXX -> XXXXXXXXXX
                .filter(digits -> digits.length() == 10)     // 6. keep valid 10-digit numbers
                .distinct()                                  // 7. remove duplicates
                .collect(Collectors.toList());
    }

    private static String removeCountryCode(String digits) {
        if (digits.length() == 12 && digits.startsWith("91")) {
            return digits.substring(2);
        }
        return digits;
    }

    public static void main(String[] args) {
        List<String> data = Arrays.asList(
                "Call: 98765-43210",
                "Office: +91 9988776655",
                "Fake: 123-45",
                "Alt: 9123456780",
                null,
                "Duplicate: 9876543210",
                " ",
                "Space format: 98765 43210");

        List<String> cleaned = clean(data);
        System.out.println(cleaned);

        assert cleaned.equals(List.of("9876543210", "9988776655", "9123456780"));
    }
}
