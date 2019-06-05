/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simeonplatonov.recommender;

import java.io.File;
import java.io.IOException;
import java.util.List;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.LogLikelihoodSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
/**
 *
 * @author Simeon
 */
public class ItemRecommend {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, TasteException {
        DataModel dm = new FileDataModel(new File("data/movies.csv"));
        ItemSimilarity sim = new LogLikelihoodSimilarity(dm);
        GenericItemBasedRecommender recommender = new GenericItemBasedRecommender(dm,sim);
        
        int i = 0;
        for(LongPrimitiveIterator items = dm.getItemIDs();items.hasNext();){
            long itemId = items.nextLong();
            List<RecommendedItem> recommendations = recommender.mostSimilarItems(itemId,10);
            for(RecommendedItem recommendation:recommendations){
                System.out.println(recommendation);
            }
        }
    }
    
}
