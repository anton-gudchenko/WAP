package ru.labs.netapps.spring.util;

import ru.labs.netapps.spring.model.Variant;

public final class VariantUtil {
    private VariantUtil() {}

    public static Variant fromCode(String code) {
        String safe = code == null ? "" : code.trim();
        String digits = safe.replaceAll("\\D", "");

        if (digits.isEmpty()) {
            int seed = unicodeSum(safe) % 10000;
            String last4 = String.format("%04d", seed);
            int last2 = seed % 100;
            return new Variant(safe, "", last2, last4, seed, seed);
        }

        int sum = 0;
        for (char ch : digits.toCharArray()) sum += ch - '0';

        String last4 = digits.length() >= 4
                ? digits.substring(digits.length() - 4)
                : String.format("%04d", Integer.parseInt(digits));

        int last2 = Integer.parseInt(last4.substring(2));
        int seed = Integer.parseInt(last4);

        return new Variant(safe, digits, last2, last4, sum, seed);
    }

    private static int unicodeSum(String s) {
        int acc = 0;
        for (int i = 0; i < s.length(); i++) acc += s.charAt(i);
        return acc;
    }
}
