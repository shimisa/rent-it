package com.example.rentit.core.feedback.domain;

/**
 * @author Shimi Sadaka
 * @version 1.0
 * @since 4/6/2022
 */
public interface Rating {
    void setNumOfPositives(long numOfPositives);
    long getNumOfPositives();
    void setNumOfNegatives(long numOfPositives);
    long getNumOfNegatives();
    void setNumOfNeutrals(long numOfPositives);
    long getNumOfNeutrals();
}
