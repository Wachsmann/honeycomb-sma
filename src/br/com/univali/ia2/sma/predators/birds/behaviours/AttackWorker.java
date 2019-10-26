/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.univali.ia2.sma.predators.birds.behaviours;

import br.com.univali.ia2.sma.beehives.queens.behaviours.*;
import br.com.univali.ia2.sma.beehives.queens.QueenAgent;
import br.com.univali.ia2.sma.beehives.workers.WorkerAgent;
import br.com.univali.ia2.sma.predators.birds.BirdAgent;
import br.com.univali.ia2.sma.utils.Constants;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.OneShotBehaviour;
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
public class AttackWorker extends TickerBehaviour {

    long seekTime;
    BirdAgent birdAgent;

    public AttackWorker(BirdAgent a, long period) {
        super(a, period);
        this.birdAgent = a;
    }

    @Override
    public void onTick() {
        System.out.println("Agent: " + birdAgent.getLocalName() + "   | Behaviour: Bird attacking the bees!");

        block(seekTime);
        AID myID = birdAgent.getAID();

        // LUCK A BEE TO ATTACK
        Object agent = birdAgent.getWorkerDrawnDFList();

        if (!agent.equals(false)) {
            // SEND MESSAGE OF ATTACK
            AID agentID = (AID) agent;
            ACLMessage msg = new ACLMessage(ACLMessage.PROPOSE);
            msg.setOntology(Constants.BEES_ONTOLOGY);
            msg.setLanguage(Constants.BEES_LANGUAGE);
            msg.setContent(Constants.BIRD_ATTACK);

            msg.addReceiver(agentID);
            birdAgent.send(msg);
        } else {
            System.out.println("Don't have workers..");
        }
    }
}
