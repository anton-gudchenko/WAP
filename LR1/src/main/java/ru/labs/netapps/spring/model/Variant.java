package ru.labs.netapps.spring.model;

public record Variant(String code, String digits, int last2, String last4, int sum, int seed) {
}
