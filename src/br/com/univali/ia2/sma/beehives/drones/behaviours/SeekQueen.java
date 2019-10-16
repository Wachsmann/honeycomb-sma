/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.univali.ia2.sma.beehives.drones.behaviours;

import br.com.univali.ia2.sma.beehives.drones.DroneAgent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

/**
 *
 * @author wachsmann
 */
public class SeekQueen extends CyclicBehaviour{
    DroneAgent drone;
    public SeekQueen(DroneAgent drone){
        this.drone = drone;
    }
    @Override
    public void action() {
        ACLMessage msg = drone.receive();
        if(msg != null){
            String content = msg.getContent();
            //TODO: Review language for mate 
            if(content.equalsIgnoreCase("Mate")){
                //TODO: send message to queen to start mate behaviour on both
            }else{
                block();
            }
        }
    }
    
}
