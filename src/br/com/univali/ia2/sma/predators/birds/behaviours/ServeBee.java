/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.univali.ia2.sma.predators.birds.behaviours;

import br.com.univali.ia2.sma.beehives.workers.behaviours.*;
import br.com.univali.ia2.sma.beehives.drones.behaviours.*;
import br.com.univali.ia2.sma.beehives.drones.DroneAgent;
import br.com.univali.ia2.sma.beehives.workers.WorkerAgent;
import br.com.univali.ia2.sma.predators.birds.BirdAgent;
import br.com.univali.ia2.sma.predators.birds.behaviours.AttackWorker;
import br.com.univali.ia2.sma.utils.Constants;
import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author wachsmann
 */
public class ServeBee extends CyclicBehaviour {

    BirdAgent birdAgent;

    public ServeBee(BirdAgent bird) {
        this.birdAgent = bird;
    }

    @Override
    public void action() {

        ACLMessage msg = birdAgent.receive();
        if (msg != null && msg.getPerformative() == ACLMessage.PROPOSE) {
            String content = msg.getContent();

            if (content.equalsIgnoreCase(Constants.BEE_ATTACK)) {
                //System.out.println(birdAgent.getName() + ": Message Received... BEE_ATTACK");
                birdAgent.removeLife();
                
                block(2000);
            } else {
                //System.out.println("Waiting call for queen...");
                block();
            }
        }
    }

}
