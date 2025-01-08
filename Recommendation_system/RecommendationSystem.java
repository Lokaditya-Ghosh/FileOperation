import org.apache.mahout.cf.taste.eval.*;
import org.apache.mahout.cf.taste.impl.common.*;
import org.apache.mahout.cf.taste.impl.model.file.*;
import org.apache.mahout.cf.taste.impl.neighborhood.*;
import org.apache.mahout.cf.taste.impl.recommender.*;
import org.apache.mahout.cf.taste.impl.similarity.*;
import org.apache.mahout.cf.taste.model.*;
import org.apache.mahout.cf.taste.recommender.*;
import org.apache.mahout.cf.taste.similarity.*;

import java.io.*;
import java.util.*;

public class RecommendationSystem {

    public static void main(String[] args) throws Exception {
        DataModel model = new FileDataModel(new File("data.csv"));
        UserSimilarity similarity = new PearsonCorrelationSimilarity(model);
        UserNeighborhood neighborhood = new NearestNUserNeighborhood(2, similarity, model);

        Recommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);

        List<RecommendedItem> recommendations = recommender.recommend(1, 3);
        for (RecommendedItem recommendation : recommendations) {
            System.out.println(recommendation);
        }
    }
}
