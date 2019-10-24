/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.univali.ia2.sma.utils;

import jade.core.AID;
import java.util.Random;

/**
 *
 * @author 6182593
 */
public class Constants {
    public static final String BEES_ONTOLOGY = "BEES";
    
    public static final String BEES_LANGUAGE = "BEERTUGUES";
    public static final String DF_DRONE = "DF_DRONE";
    public static final String MATE_PROPOSAL = "Let's make some eggs!";
    public static final String MATE_ACCEPT_PROPOSAL = "Mating!";
    public static final String MATING_DONE = "Mating done!";

    
/**
 * Returns a pseudo-random number between min and max, inclusive.
 * The difference between min and max can be at most
 * <code>Integer.MAX_VALUE - 1</code>.
 *
 * @param min Minimum value
 * @param max Maximum value.  Must be greater than min.
 * @return Integer between min and max, inclusive.
 * @see java.util.Random#nextInt(int)
 */
public static int randInt(int min, int max) {

    // NOTE: This will (intentionally) not run as written so that folks
    // copy-pasting have to think about how to initialize their
    // Random instance.  Initialization of the Random instance is outside
    // the main scope of the question, but some decent options are to have
    // a field that is initialized once and then re-used as needed or to
    // use ThreadLocalRandom (if using at least Java 1.7).
    // 
    // In particular, do NOT do 'Random rand = new Random()' here or you
    // will get not very good / not very random results.
    Random rand = new Random();

    // nextInt is normally exclusive of the top value,
    // so add 1 to make it inclusive
    int randomNum = rand.nextInt((max - min) + 1) + min;
    
    return randomNum;
}
}
