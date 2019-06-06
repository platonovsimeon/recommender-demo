/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simeonplatonov.recommender;


import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import weka.classifiers.functions.LinearRegression;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.CSVLoader;


/**
 *
 * @author Simeon
 */
public class Recommender {
  LinearRegression model;
  
 public Recommender(String trainingCSV) throws IOException, Exception{
     CSVLoader loader = new CSVLoader();
     loader.setSource(new File(trainingCSV));
     Instances dataset = loader.getDataSet();
  
     dataset.setClassIndex(21);

     this.model = new LinearRegression();
     this.model.buildClassifier(dataset);
 }
  
 public void recommend(String itemsCSV) throws IOException{
     CSVLoader loader = new CSVLoader();
     loader.setSource(new File(itemsCSV));
     Instances dataset = loader.getDataSet();
   
     dataset.forEach(item->{
         Instance instance = (Instance) item;
         try {
             double prediction =  this.model.classifyInstance(instance);
             System.out.println(prediction);
         } catch (Exception ex) {
             Logger.getLogger(Recommender.class.getName()).log(Level.SEVERE, null, ex);
         }
     });
     
  }
}
