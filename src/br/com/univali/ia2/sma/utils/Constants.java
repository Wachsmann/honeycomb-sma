/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.univali.ia2.sma.utils;

import jade.core.AID;

/**
 *
 * @author 6182593
 */
public class Constants {

    public static String BEES_ONTOLOGY = "BEES";

    public static String BEES_LANGUAGE = "BEERTUGUES";
    public static String DF_DRONE = "DF_DRONE";
    public static String DF_WORKER = "DF_WORKER";
    public static String DF_BIRD = "DF_BIRD";
    public static String MATE_PROPOSAL = "Let's make some eggs!";
    public static String BEE_ATTACK_WARNING = "There are birds attacking me!";
    public static String BIRD_ATTACK = "I will kill you bee!";
    public static String BEE_ATTACK = "I will kill you bird!";
    public static String MATE_ACCEPT_PROPOSAL = "Mating!";

    public int drawnNumber(int minimum_limit, int maximum_limit) {
        return (int) (java.lang.Math.random() * maximum_limit + minimum_limit);
    }
}
