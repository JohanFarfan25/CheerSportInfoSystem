/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.club.utils;

import java.util.Random;

/**
 *
 * @author johan.farfan
 */
public class GeneratePassword {
    
    private static Random rand = new Random();
    
    private GeneratePassword(){}
    
    public static String generate(){
        return generate(8);
    }
    
    public static String generate(int quantity){
        String newPassword = "";
        for (int i = 0; i < quantity; i++) {
            newPassword += rand.nextBoolean()?
                    getCharacter():
                    getNumber();
        }
     return newPassword;
    }
    
    private static String getCharacter(){
         char c = (char) (rand.nextInt(27) + 64);
        return String.valueOf(c);
    }
    
    private static String getNumber(){
        return String.valueOf(rand.nextInt(9));
    }
        
}
