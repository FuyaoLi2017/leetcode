/**
 * Design a food rating system that can do the following:

Modify the rating of a food item listed in the system.
Return the highest-rated food item for a type of cuisine in the system.
Implement the FoodRatings class:

FoodRatings(String[] foods, String[] cuisines, int[] ratings) Initializes the system. The food items are described by foods, cuisines and ratings, all of which have a length of n.
foods[i] is the name of the ith food,
cuisines[i] is the type of cuisine of the ith food, and
ratings[i] is the initial rating of the ith food.
void changeRating(String food, int newRating) Changes the rating of the food item with the name food.
String highestRated(String cuisine) Returns the name of the food item that has the highest rating for the given type of cuisine. If there is a tie, return the item with the lexicographically smaller name.
Note that a string x is lexicographically smaller than string y if x comes before y in dictionary order, that is, either x is a prefix of y, or if i is the first position such that x[i] != y[i], then x[i] comes before y[i] in alphabetic order.
 */

 // Solution 1: Priority Queue
 // don't need to remove element from PQ
//  If we fetch any element (foodRating, foodName) from the priority queue then there are only two cases: either the element has the correct foodRating or an old rating.
// One food can only have one rating, we can verify the fetched element's foodRating with the rating stored in foodRatingMap against the key foodName. If the values don't match, it means the rating for foodName was changed and we can safely discard this fetched element of the priority queue and move on to the next highest rating in the priority queue.


class Food implements Comparable<Food> {
    // Store the food's rating.
    public int foodRating;
    // Store the food's name.
    public String foodName;

    public Food(int foodRating, String foodName) {
        this.foodRating = foodRating;
        this.foodName = foodName;
    }

    // Implement the compareTo method for comparison
    @Override
    public int compareTo(Food other) {
        // If food ratings are the same, sort based on their names (lexicographically smaller name food will be on top)
        if (foodRating == other.foodRating) {
            return foodName.compareTo(other.foodName);
        }
        // Sort based on food rating (bigger rating food will be on top)
        return -1 * Integer.compare(foodRating, other.foodRating);
    }
}

class FoodRatings {
    // Map food with its rating.
    private Map<String, Integer> foodRatingMap;
    // Map food with the cuisine it belongs to.
    private Map<String, String> foodCuisineMap;
    
    // Store all food of a cuisine in a priority queue (to sort them on ratings/name)
    // Priority queue element -> Food: (foodRating, foodName)
    private Map<String, PriorityQueue<Food>> cuisineFoodMap;

    public FoodRatings(String[] foods, String[] cuisines, int[] ratings) {
        foodRatingMap = new HashMap<>();
        foodCuisineMap = new HashMap<>();
        cuisineFoodMap = new HashMap<>();

        for (int i = 0; i < foods.length; ++i) {
            // Store 'rating' and 'cuisine' of the current 'food' in 'foodRatingMap' and 'foodCuisineMap' maps.
            foodRatingMap.put(foods[i], ratings[i]);
            foodCuisineMap.put(foods[i], cuisines[i]);
            // Insert the '(rating, name)' element into the current cuisine's priority queue.
            cuisineFoodMap.computeIfAbsent(cuisines[i], k -> new PriorityQueue<>()).add(new Food(ratings[i], foods[i]));
        }
    } 
    
    public void changeRating(String food, int newRating) {
        // Update food's rating in the 'foodRating' map.
        foodRatingMap.put(food, newRating);
        // Insert the '(new food rating, food name)' element into the respective cuisine's priority queue.
        String cuisineName = foodCuisineMap.get(food);
        cuisineFoodMap.get(cuisineName).add(new Food(newRating, food));
    }
    
    public String highestRated(String cuisine) {
        // Get the highest rated 'food' of 'cuisine'.
        Food highestRated = cuisineFoodMap.get(cuisine).peek();
        
        // If the latest rating of 'food' doesn't match with the 'rating' on which it was sorted in the priority queue,
        // then we discard this element from the priority queue.
        while (foodRatingMap.get(highestRated.foodName) != highestRated.foodRating) {
            cuisineFoodMap.get(cuisine).poll();
            highestRated = cuisineFoodMap.get(cuisine).peek();
        }
        
        // Return the name of the highest-rated 'food' of 'cuisine'.
        return highestRated.foodName;
    }
}


// Solution 2: hashmap and sortedset - treeset
// I was originally planning to use this approach, not sure if I can use TreeSet/TreeMap
// auto sort like PQ
class FoodRatings {
    // Map food with its rating.
    private Map<String, Integer> foodRatingMap = new HashMap<>();
    // Map food with cuisine it belongs to.
    private Map<String, String> foodCuisineMap = new HashMap<>();

    // Store all food of a cuisine in set (to sort them on ratings/name)
    // Set element -> Pair: (-1 * foodRating, foodName)
    private Map<String, TreeSet<Pair<Integer, String>>> cuisineFoodMap = new HashMap<>();

    public FoodRatings(String[] foods, String[] cuisines, int[] ratings) {
        for (int i = 0; i < foods.length; ++i) {
            // Store 'rating' and 'cuisine' of current 'food' in 'foodRatingMap' and 'foodCuisineMap' maps.
            foodRatingMap.put(foods[i], ratings[i]);
            foodCuisineMap.put(foods[i], cuisines[i]);

            // Insert the '(-1 * rating, name)' element in the current cuisine's set.
            cuisineFoodMap
                .computeIfAbsent(cuisines[i], k -> new TreeSet<>((a, b) -> {
                    int compareByRating = Integer.compare(a.getKey(), b.getKey());
                    if (compareByRating == 0) {
                        // If ratings are equal, compare by food name (in ascending order).
                        return a.getValue().compareTo(b.getValue());
                    }
                    return compareByRating;
                }))
                .add(new Pair(-ratings[i], foods[i]));
        }
    }

    public void changeRating(String food, int newRating) {
        // Fetch cuisine name for food.
        String cuisineName = foodCuisineMap.get(food);

        // Find and delete the element from the respective cuisine's set.
        TreeSet<Pair<Integer, String>> cuisineSet = cuisineFoodMap.get(cuisineName);
        Pair<Integer, String> oldElement = new Pair<>(-foodRatingMap.get(food), food);
        cuisineSet.remove(oldElement);

        // Update food's rating in 'foodRating' map.
        foodRatingMap.put(food, newRating);
        // Insert the '(-1 * new rating, name)' element in the respective cuisine's set.
        cuisineSet.add(new Pair<>(-newRating, food));
    }

    public String highestRated(String cuisine) {
        Pair<Integer, String> highestRated = cuisineFoodMap.get(cuisine).first();
        // Return name of the highest rated 'food' of 'cuisine'.
        return highestRated.getValue();
    }
}