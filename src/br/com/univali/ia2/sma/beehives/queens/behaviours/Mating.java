/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.univali.ia2.sma.beehives.queens.behaviours;

import br.com.univali.ia2.sma.beehives.queens.QueenAgent;
import br.com.univali.ia2.sma.utils.Constants;
import jade.core.AID;
import jade.core.Agent;
import static jade.core.Agent.AP_MIN;
import static jade.core.Agent.D_ACTIVE;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;

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
               block(30000);
                
            }else{
                block();
            }
        }
    }
    
    
}
