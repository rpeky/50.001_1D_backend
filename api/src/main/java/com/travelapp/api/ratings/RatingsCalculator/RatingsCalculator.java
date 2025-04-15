package com.travelapp.api.ratings.RatingsCalculator;

import com.travelapp.api.shop.entities.ProductReviews;
import com.travelapp.api.ratings.entity.Ratings;

import java.util.List;

public class RatingsCalculator {

    public static double roundRating(double value, int places) {
        double scale = Math.pow(10, places);
        return Math.round(value * scale) / scale;
    }

    public static double computeAverageRating(List<Ratings> ratingsList) {
        if (ratingsList != null && !ratingsList.isEmpty()) {
            return ratingsList.stream()
                    .mapToLong(Ratings::getRating)
                    .average()
                    .orElse(0.0);
        }
        return 0.0;
    }
    public static double computeAverageRatingReviews(List<ProductReviews> reviews) {
        if (reviews != null && !reviews.isEmpty()) {
            return reviews.stream()
                    .mapToLong(ProductReviews::getRating)
                    .average()
                    .orElse(0.0);
        }
        return 0.0;
    }


    public static double computeBayesianAverageActItin(List<Ratings> actItinRatingList, List<Ratings> allRatingsList,
                                                       int minRatings) {

        Double globalAvg = computeAverageRating(allRatingsList);
        Double activityAvg = computeAverageRating(actItinRatingList);

        Integer ratingCount = actItinRatingList.size();


        if (ratingCount == 0) return 0.0;

        Double intermediateRating = ((ratingCount * activityAvg) + (minRatings * globalAvg)) / (ratingCount + minRatings);

        return roundRating(intermediateRating, 1);

    }

    public static double computeBayesianAverageProducts(List<ProductReviews> productReviewsList,
                                                        List<ProductReviews>  allProductReviewsList,
                                                        int minRatings) {

        Double globalAvg = computeAverageRatingReviews(allProductReviewsList);
        Double productAvg = computeAverageRatingReviews(productReviewsList);

        Integer productReviewCount = productReviewsList.size();

        if (productReviewCount == 0) return 0.0;

        Double intermediateRating = ((productReviewCount * productAvg) + (minRatings * globalAvg)) / (productReviewCount + minRatings);

        return roundRating(intermediateRating, 1);
    }

}
