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
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


/**
 *
 * @author Simeon
 */
public class BeerDataConvert {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        FileReader reader = new FileReader("data/download.json");
        Object obj = jsonParser.parse(reader);
        JSONArray beerList = (JSONArray) obj;
        ArrayList<JSONObject> jsonBeers = new ArrayList<JSONObject>();
        beerList.forEach(beer->{
            jsonBeers.add((JSONObject) beer);
        });
 
        BufferedWriter bw = new BufferedWriter(new FileWriter("data/beerpreference.csv"));
        for(JSONObject beer:jsonBeers){
            String id = beer.get("id").toString();
         
            String[] flavors =  beer.get("flavors").toString().split(",");
            for(JSONObject yBeer:jsonBeers){
                String yId =  yBeer.get("id").toString();
                String[] yFlavors =  yBeer.get("flavors").toString().split(",");
                
                if(!yId.equals(id)){
                    int similarity = 0;
                    for(String flavor:flavors){
                        for(String yFlavor:yFlavors){
                            if(flavor.toLowerCase().equals(yFlavor.toLowerCase())){
                                similarity++;
                            }
                        }
                    }
                    
                    bw.write(id+","+yId+","+similarity+"\n");
                    
                }
            }
        }
        bw.close();
    }
    
   
    
}
