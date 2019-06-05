/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simeonplatonov.recommender;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.json.simple.parser.ParseException;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.impl.similarity.EuclideanDistanceSimilarity;
/**
 *
 * @author Simeon
 */
public class ItemRecommend {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException, ParseException, TasteException {
        DataModel dm = new FileDataModel(new File("data/beerpreference.csv"));
        ItemSimilarity sim = new EuclideanDistanceSimilarity(dm);
        
        GenericItemBasedRecommender recommender = new GenericItemBasedRecommender(dm,sim);
        
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a beer id:");
        long inputId = Long.parseLong(sc.nextLine());
        

        for(LongPrimitiveIterator items = dm.getItemIDs();items.hasNext();){
            long itemId = items.nextLong();
            if(itemId==inputId){
                List<RecommendedItem> recommendations = recommender.mostSimilarItems(itemId,3);
                for(RecommendedItem recommendation:recommendations){
                    System.out.println(recommendation);
                }
            }       
        }
        sc.close();  
    }

}
