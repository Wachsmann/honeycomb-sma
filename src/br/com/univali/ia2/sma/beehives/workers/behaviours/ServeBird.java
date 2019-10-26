/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.univali.ia2.sma.beehives.workers.behaviours;

import br.com.univali.ia2.sma.beehives.drones.behaviours.*;
import br.com.univali.ia2.sma.beehives.drones.DroneAgent;
import br.com.univali.ia2.sma.beehives.workers.WorkerAgent;
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
public class ServeBird extends CyclicBehaviour {

    WorkerAgent worker;

    public ServeBird(WorkerAgent worker) {
        this.worker = worker;
    }

    @Override
    public void action() {

        ACLMessage msg = worker.receive();
        if (msg != null && msg.getPerformative() == ACLMessage.PROPOSE) {
            String content = msg.getContent();

            if (content.equalsIgnoreCase(Constants.BIRD_ATTACK)) {
                worker.setState("attack");

                //System.out.println(worker.getName() + ": Message Received... ATTACK");
                worker.addBehaviour(new AttackWarningWorker(worker));

            } else if (content.equalsIgnoreCase(Constants.BEE_ATTACK_WARNING)) {
                System.out.println("Agent: " + worker.getLocalName() + " | Behaviour: Spot all the bees on the attack!");
                // System.out.println(worker.getName() + ": Message Received... ATTACK_WARNING");

                worker.addBehaviour(new AttackBird(worker));

            } else {
                //System.out.println("Waiting call for queen...");
                block();
            }
        }
    }

}
