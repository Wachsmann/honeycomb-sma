/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.univali.ia2.sma.utils;

import static br.com.univali.ia2.sma.Main.container;
import br.com.univali.ia2.sma.predators.birds.*;
import br.com.univali.ia2.sma.beehives.drones.DroneAgent;
import br.com.univali.ia2.sma.predators.birds.behaviours.AttackWorker;
import br.com.univali.ia2.sma.predators.birds.behaviours.ServeBee;
import br.com.univali.ia2.sma.predators.birds.behaviours.UpdateWorkerList;
import br.com.univali.ia2.sma.utils.Constants;
import jade.core.Agent;
import static jade.core.Agent.AP_MIN;
import static jade.core.Agent.D_ACTIVE;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.core.behaviours.WakerBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;
import java.util.Vector;

/**
 *
 * @author 6182593
 */
public class ControllerAgent extends Agent {

    public int count = 0;

    @Override
    protected void setup() {
        addBehaviour(new TickerBehaviour(this, 15000) {
            protected void onTick() {
                createNewBird();
            }
        });
    }

    public void createNewBird() {

        try {
            AgentController bird = container.createNewAgent("bird" + this.count,
                    "br.com.univali.ia2.sma.predators.birds.BirdAgent",
                    new Object[]{});//arguments
            bird.start();
            
            this.count++;
        } catch (StaleProxyException e) {
            e.printStackTrace();
        }
    }
}
