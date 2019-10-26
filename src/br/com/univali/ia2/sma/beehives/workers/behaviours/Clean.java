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
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author wachsmann
 */
public class Clean extends SimpleBehaviour {

    WorkerAgent worker;

    public Clean(WorkerAgent worker) {
        this.worker = worker;

    }

    @Override
    public void action() {
        block(2000);
        System.out.println("Agent: " + myAgent.getLocalName() + " | Behaviour: " + getBehaviourName() + " cleaning up!");
    }

    @Override
    public boolean done() {
        return (!this.worker.state().equalsIgnoreCase("clean"));
    }
}
