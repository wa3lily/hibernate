package ru.sfedu.hibernate.enums;

public enum BookStatus {
    UNTOUCHED,
    EDITING,
    WAIT_AUTHOR_CORRECTIONS,
    WAIT_AUTHOR_AGR,
    WAIT_EDITOR_AGR,
    WAIT_MAKING,
    WAIT_EDITOR_CORRECTIONS,
    MAKING,
    DONE
}
