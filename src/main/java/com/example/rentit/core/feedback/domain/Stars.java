package com.example.rentit.core.feedback.domain;

/**
 * @author Shimi Sadaka
 * @version 1.0
 * @since 4/4/2022
 */
public enum Stars {
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5);

    int val;
    Stars(int val) {
        this.val = val;
    }

    public int val() {
        return val;
    }
}
