/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.univali.ia2.sma.beehives.workers;

import br.com.univali.ia2.sma.beehives.drones.behaviours.ServeQueen;
import br.com.univali.ia2.sma.beehives.workers.behaviours.Clean;
import br.com.univali.ia2.sma.beehives.workers.behaviours.Clean;
import br.com.univali.ia2.sma.beehives.workers.behaviours.ServeBird;
import br.com.univali.ia2.sma.beehives.workers.behaviours.UpdateBirdList;
import br.com.univali.ia2.sma.utils.Constants;
import jade.core.AID;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import java.util.Vector;

/**
 *
 * @author 6182593
 */
public class WorkerAgent extends Agent {

    private Vector birdAgents = new Vector();
    private String state;

    @Override
    protected void setup() {
        System.out.println("Create WorkerAgent - " + getLocalName());
        this.setState("clean");

        // Register the drone service in the yellow pages
        DFAgentDescription dfd = new DFAgentDescription();
        dfd.setName(getAID());
        ServiceDescription sd = new ServiceDescription();
        sd.setType(Constants.DF_WORKER);
        sd.setName(getLocalName() + "-" + Constants.DF_WORKER);
        dfd.addServices(sd);

        try {
            DFService.register(this, dfd);
            addBehaviour(new ServeBird(this));
            addBehaviour(new UpdateBirdList(this, 9000));
            addBehaviour(new Clean(this));

        } catch (FIPAException fe) {
            fe.printStackTrace();
        }
    }

    protected void takeDown() {
        // Deregister from the yellow pages
        try {
            DFService.deregister(this);
        } catch (FIPAException fe) {
            fe.printStackTrace();
        }
    }

    public void clearBirdList() {
        this.birdAgents.clear();
    }

    public void addBirdInList(Object birdDF) {
        this.birdAgents.add(birdDF);
    }

    public Vector getBirdDFList() {
        return birdAgents;
    }

    public Object getBirdDrawnDFList() {
        Constants constants = new Constants();

        int vector_size = this.birdAgents.size();

        if (vector_size > 0) {
            int drawn_number = constants.drawnNumber(0, vector_size);

            return this.birdAgents.get(drawn_number);
        } else {
            return false;
        }
    }

    public void died() {
        doDelete();
    }

    public void setState(String state) {
        this.state = state;
    }

    public String state() {
        return this.state;
    }
}
