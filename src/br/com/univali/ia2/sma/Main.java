/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.univali.ia2.sma;

import br.com.univali.ia2.sma.utils.Constants;
import jade.Boot;

/**
 *
 * @author 6182593
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String[] param = new String[2];
        param[0] = "-gui";
        /*
        param[1] = "Léo:br.com.univali.ia2.sma.beehives.WorkerAgent()";        
        */
        param[1] = "drone1:br.com.univali.ia2.sma.beehives.drones.DroneAgent();"+
                   "drone2:br.com.univali.ia2.sma.beehives.drones.DroneAgent();"+
                   "queen:br.com.univali.ia2.sma.beehives.queens.QueenAgent();"+
                   "worker:br.com.univali.ia2.sma.beehives.workers.WorkerAgent();"+

                "";        

        Boot.main(param);
    }
    
}