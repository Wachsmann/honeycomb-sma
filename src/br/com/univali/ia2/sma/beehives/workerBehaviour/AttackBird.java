/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.univali.ia2.sma.beehives.workers.behaviours;

import br.com.univali.ia2.sma.beehives.queens.behaviours.*;
import br.com.univali.ia2.sma.beehives.queens.QueenAgent;
import br.com.univali.ia2.sma.beehives.workers.WorkerAgent;
import br.com.univali.ia2.sma.predators.birds.BirdAgent;
import br.com.univali.ia2.sma.utils.Constants;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.SimpleBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.core.behaviours.WakerBehaviour;
import jade.domain.AMSService;
import jade.domain.FIPAAgentManagement.AMSAgentDescription;
import jade.domain.FIPAAgentManagement.SearchConstraints;
import jade.lang.acl.ACLMessage;
import java.io.IOException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author wachsmann
 */
/*
*   Fly out seeking a drone 
*   Obs: send message to all drones to mate 
*      the first that responds start the mate behaviour
 */
public class AttackBird extends Behaviour {

    WorkerAgent workerAgent;
    int count = 0;

    public AttackBird(WorkerAgent a) {
        this.workerAgent = a;
    }

    @Override
    public void action() {

        System.out.println("Attacking birds..");

        AID myID = workerAgent.getAID();

        // LUCK A BEE TO ATTACK
        Object agent = workerAgent.getBirdDrawnDFList();

        if (!agent.equals(false)) {
            // SEND MESSAGE OF ATTACK
            AID agentID = (AID) agent;
            ACLMessage msg = new ACLMessage(ACLMessage.PROPOSE);
            msg.setOntology(Constants.BEES_ONTOLOGY);
            msg.setLanguage(Constants.BEES_LANGUAGE);
            msg.setContent(Constants.BEE_ATTACK);

            msg.addReceiver(agentID);
            workerAgent.send(msg);
            count++;
        } else {
            System.out.println("Don't have birds..");
        }
    }

    @Override
    public boolean done() {
        return count >= 4;
    }
}
