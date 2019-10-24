/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.univali.ia2.sma.beehives.queens.behaviours;

import br.com.univali.ia2.sma.Main;

import br.com.univali.ia2.sma.beehives.drones.DroneAgent;
import br.com.univali.ia2.sma.beehives.queens.QueenAgent;
import br.com.univali.ia2.sma.utils.Constants;
import jade.core.AID;
import jade.core.Agent;
import static jade.core.Agent.AP_MIN;
import static jade.core.Agent.D_ACTIVE;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author wachsmann
 */
public class Mating extends CyclicBehaviour{
    QueenAgent queen;
    public Mating(QueenAgent queen){
        this.queen = queen;
    }
    @Override
    public void action() {
        ACLMessage msg = queen.receive();
        if(msg != null && msg.getPerformative() == ACLMessage.ACCEPT_PROPOSAL){
            System.out.println(queen.getName()+": Message Received");
            String content = msg.getContent();
            
            if(content.equalsIgnoreCase(Constants.MATE_ACCEPT_PROPOSAL)){
                System.out.println("Getting eggs to create new bees");
                int numberOfEggs = Constants.randInt(0, 5);
                
                for (int i = 0; i < numberOfEggs; i++) {
                    
                    Date date = new Date();
                    //This method returns the time in millis
                    long timeMilli = date.getTime();
                    try {
                        if(Constants.randInt(0,1000) % 2 == 0){
                            AgentController newBaby = Main.container.createNewAgent("drone"+timeMilli+i, 
                                    "br.com.univali.ia2.sma.beehives.drones.DroneAgent",
                                    new Object[] {}); //arguments
                            newBaby.start();
                        }else{
                             AgentController newBaby = Main.container.createNewAgent("worker"+timeMilli+i, 
                                    "br.com.univali.ia2.sma.beehives.workers.WorkerAgent",
                                    new Object[] {}); //arguments
                            newBaby.start();
                        }
                        AID droneAID = msg.getSender();
                        ACLMessage responseMsg = new ACLMessage(ACLMessage.INFORM);
                        responseMsg.setOntology(msg.getOntology());
                        responseMsg.setLanguage(msg.getLanguage());
                        responseMsg.setContent(Constants.MATING_DONE);
                        responseMsg.addReceiver(droneAID);

                        queen.send(responseMsg);
                        
                        
                        
                    } catch (StaleProxyException ex) {
                        Logger.getLogger(Mating.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    

                }
                
                
                
                block(30000);
                
            }else{
                block();
            }
        }
    }
    
    
}
