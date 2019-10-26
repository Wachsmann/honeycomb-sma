/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.univali.ia2.sma.predators.birds;

import br.com.univali.ia2.sma.beehives.drones.DroneAgent;
import br.com.univali.ia2.sma.predators.birds.behaviours.AttackWorker;
import br.com.univali.ia2.sma.predators.birds.behaviours.ServeBee;
import br.com.univali.ia2.sma.predators.birds.behaviours.UpdateWorkerList;
import br.com.univali.ia2.sma.utils.Constants;
import jade.core.Agent;
import static jade.core.Agent.AP_MIN;
import static jade.core.Agent.D_ACTIVE;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.core.behaviours.WakerBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.wrapper.StaleProxyException;
import java.util.Vector;

/**
 *
 * @author 6182593
 */
public class BirdAgent extends Agent {

    private Vector workerAgents = new Vector();
    private int life = 100;

    //TODO: create mechanism to randomize time for seeking drone and controlling communication
    @Override
    protected void setup() {
        System.out.println("Create QueenAgent - " + getLocalName());

        DFAgentDescription dfd = new DFAgentDescription();
        dfd.setName(getAID());
        ServiceDescription sd = new ServiceDescription();
        sd.setType(Constants.DF_BIRD);
        sd.setName(getLocalName() + "-" + Constants.DF_BIRD);
        dfd.addServices(sd);
        try {
            addBehaviour(new ServeBee(this));
            DFService.register(this, dfd);
        } catch (FIPAException fe) {
            fe.printStackTrace();
        }
        //addBehaviour(new UpdateWorkerList(this, 2000));
        //addBehaviour(new SeekDrone(this, 10000));
        //addBehaviour(new Mating(this));
        addBehaviour(new UpdateWorkerList(this, 10000));
        addBehaviour(new AttackWorker(this, 10000));

    }

    public void clearWorkerList() {
        this.workerAgents.clear();
    }

    public void addWorkerInList(Object droneDF) {
        this.workerAgents.add(droneDF);
    }

    public Vector getWorkerDFList() {
        return workerAgents;
    }

    public Object getWorkerDrawnDFList() {
        Constants constants = new Constants();

        int vector_size = this.workerAgents.size();

        if (vector_size > 0) {
            int drawn_number = constants.drawnNumber(0, vector_size);

            return this.workerAgents.get(drawn_number);
        } else {
            return false;
        }
    }

    public void removeLife() {
        this.life -= 25;

        if (this.life <= 0) {
            System.out.println("Bee " + getAID().getName() + " died.");
            doDelete();

        } else {
            System.out.println("Bee life: " + this.life);

        }
    }
}
