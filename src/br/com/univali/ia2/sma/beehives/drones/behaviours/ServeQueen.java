/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.univali.ia2.sma.beehives.drones.behaviours;

import br.com.univali.ia2.sma.beehives.drones.DroneAgent;
import br.com.univali.ia2.sma.utils.Constants;
import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

/**
 *
 * @author wachsmann
 */
public class ServeQueen extends CyclicBehaviour{
    DroneAgent drone;
    public ServeQueen(DroneAgent drone){
        this.drone = drone;
    }
    @Override
    public void action() {
        
        ACLMessage msg = drone.receive();
        
        if(msg != null){
            System.out.println(drone.getName()+": Message Received...");
            String content = msg.getContent();
            switch(msg.getPerformative()){
                case ACLMessage.PROPOSE:
                    if(content.equalsIgnoreCase(Constants.MATE_PROPOSAL)){
                        // send message to queen to start mate behaviour on both
                        System.out.println(drone.getName()+": sending proposal...");

                        AID queenAID = msg.getSender();
                        ACLMessage responseMsg = new ACLMessage(ACLMessage.ACCEPT_PROPOSAL);
                        responseMsg.setOntology(msg.getOntology());
                        responseMsg.setLanguage(msg.getLanguage());
                        responseMsg.setContent(Constants.MATE_ACCEPT_PROPOSAL);
                        responseMsg.addReceiver(queenAID);

                        drone.send(responseMsg);
                    }
                    break;
                case ACLMessage.INFORM:
                    if(content.equalsIgnoreCase(Constants.MATING_DONE)){
                        //TODO: SEPPUKU
                        System.out.println(drone.getName()+": SEPPUKU!");
                        drone.doDelete();
                    }
                    break;
                default:
                  block();  
                    
            }
                //System.out.println("Waiting call for queen...");
                block();
            }
        }
    }
    

