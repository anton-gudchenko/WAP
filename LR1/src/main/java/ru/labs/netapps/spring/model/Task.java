package ru.labs.netapps.spring.model;

import java.time.OffsetDateTime;

public record Task(long id, String title, String description, OffsetDateTime createdAt, String variantCode) {
}
