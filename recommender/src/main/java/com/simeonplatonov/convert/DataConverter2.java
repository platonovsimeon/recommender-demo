/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simeonplatonov.convert;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Simeon
 */
public class DataConverter2 {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        BufferedReader reader = new BufferedReader(new FileReader("data/beers.csv"));
        String line = reader.readLine();
        
        ArrayList<RecommenderItem> itemsToRecommend = new ArrayList<RecommenderItem>();
        while(line!=null){
            String[] values = line.split(",");          
            String id = values[0];
            String[] newValues  = Arrays.copyOfRange(values,1,values.length);
            int[] category = new int[values.length-1];
            int i=0;
            for(String value:newValues){
                category[i] = parseInt(value);
                i++;
            }
            RecommenderItem item = new RecommenderItem(id,category);
            itemsToRecommend.add(item);
             
            line = reader.readLine();
        }        
        reader.close();
               
        ArrayList<RecommenderItem> ratedItems = new ArrayList<RecommenderItem>();
        RecommenderItem testItem1 = itemsToRecommend.get(0);
        RecommenderItem testItem2 = itemsToRecommend.get(1);
        RecommenderItem testItem3 = itemsToRecommend.get(2);
        testItem1.rate(0);
        testItem2.rate(0);
        testItem3.rate(1);
        ratedItems.add(testItem1);
        ratedItems.add(testItem2);
        ratedItems.add(testItem3);
        itemsToRecommend.remove(0);
        itemsToRecommend.remove(0);
        itemsToRecommend.remove(0);
  
        
        BufferedWriter writer = new BufferedWriter(new FileWriter("data/ratedItems.csv"));
        BufferedWriter writer2 = new BufferedWriter(new FileWriter("data/itemsToRecommend.csv"));
        
        String attributes = "";
        for(int i=0;i<ratedItems.get(0).category.length;i++){
            attributes+=i+",";
        }
        writer.write(attributes+"rating\n");
        writer2.write(attributes+"rating\n");
        
        for(RecommenderItem ratedItem:ratedItems){
            String dataToWrite="";
            for(int index:ratedItem.category){
                dataToWrite+=index+",";
            }
            dataToWrite+=ratedItem.rating;
            dataToWrite+="\n";
            writer.write(dataToWrite);
        }
        for(RecommenderItem itemToRecommend:itemsToRecommend){
            String dataToWrite="";
            for(int index:itemToRecommend.category){
                
                dataToWrite+=index+",";
            }
          
            dataToWrite+="\n";
            writer2.write(dataToWrite);
        }
        writer.close();
        writer2.close();
    }
    
}
