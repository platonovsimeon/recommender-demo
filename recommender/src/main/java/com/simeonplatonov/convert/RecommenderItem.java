/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simeonplatonov.convert;

/**
 *
 * @author Simeon
 */
public class RecommenderItem {
    String id;
    int[] category;
    float rating;
    
    public RecommenderItem(String id, int[] category){
        this.id = id;
        this.category = category;
    }
    
    public void rate(float rating){
        this.rating = rating;
    }
}
