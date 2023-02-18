package com.c.refactoring.movie;

import com.c.refactoring.StringUtils;

import java.util.Arrays;
import java.util.List;

public class Movie {

    public static final List<String> VALID_B_RATINGS_LIST = Arrays.asList("B1", "B2", "B3", "B4");
    String rating;

    public Movie(String rating) {
        super();
        this.rating = rating;
    }

    public String getRating() {
        return rating;
    }

    /*Axx or By
    Where x represents any digit between 0 and 9, and y represents 
    any digit between 1 and 4*/
    public boolean isValidRating() {
        String rating = this.getRating();

        if (rating == null)
            return false;

        if (isValidARating(rating))
            return true;

        if (isValidBRating(rating))
            return true;

        return false;
    }

    private boolean isValidARating(String rating) {
        String firstCharacter = rating.substring(0, 1);
        return firstCharacter.equalsIgnoreCase("A")
                && rating.length() == 3
                && StringUtils.isNumeric(rating.substring(1, 3));
    }

    private boolean isValidBRating(String rating) {

        return VALID_B_RATINGS_LIST.contains(rating);
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
