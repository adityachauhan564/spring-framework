package topic38_streams_capstone;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/*
 * Topic    : Stream API on a real-world problem (a common interview question)
 * Key idea : You get a messy list of phone numbers - typed by users in every possible format,
 *            like a CRM export from a sales team. Clean it with a chain of small steps:
 *              filter()   - keep or drop
 *              map()      - change the value
 *              distinct() - remove duplicates
 *              collect()  - build the final list
 *            Nothing runs until the last step (collect).
 * Run      : java -ea -cp out topic38_streams_capstone.PhoneNumberCleaner
 * Try this : Also accept numbers that start with "0" followed by 10 digits.
 */
public class PhoneNumberCleaner {

    static List<String> clean(List<String> rawNumbers) {
        return rawNumbers.stream()
                .filter(Objects::nonNull)                    // 1. drop nulls first (trim() on a null would crash)
                .map(String::trim)                           // 2. remove spaces at the start and end
                .filter(text -> !text.isEmpty())             // 3. drop empty entries
                .map(text -> text.replaceAll("[^0-9]", ""))  // 4. keep only the digits - removes "Call:", "+", "-", spaces
                .map(PhoneNumberCleaner::removeCountryCode)  // 5. 91XXXXXXXXXX -> XXXXXXXXXX
                .filter(digits -> digits.length() == 10)     // 6. keep only proper 10-digit Indian mobile numbers
                .distinct()                                  // 7. the same number twice? keep it once
                .collect(Collectors.toList());
    }

    // +91 is India's country code. 12 digits starting with 91 -> drop the "91"
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
