package com.travelapp.api.ratings.RatingsCalculator;

import com.travelapp.api.AAshop.entities.ProductReviews;
import com.travelapp.api.activities.entity.Activities;
import com.travelapp.api.ratings.entity.Ratings;

import java.util.List;

public class RatingsCalculator {
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


    public static double computeBayesianAverage(double activityAvg, long activityCount,
                                         double globalAvg, int minRatings) {
        if (activityCount == 0) return globalAvg;

        return ((activityCount * activityAvg) + (minRatings * globalAvg)) / (activityCount + minRatings);
    }

}
