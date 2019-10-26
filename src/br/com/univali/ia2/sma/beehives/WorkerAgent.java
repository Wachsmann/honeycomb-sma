/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.univali.ia2.sma.beehives;

<<<<<<< Updated upstream:src/br/com/univali/ia2/sma/beehives/WorkerAgent.java
import br.com.univali.ia2.sma.beehives.workerBehaviour.Clean;
=======
import br.com.univali.ia2.sma.beehives.drones.behaviours.ServeQueen;
import br.com.univali.ia2.sma.beehives.workers.behaviours.Clean;
import br.com.univali.ia2.sma.beehives.workers.behaviours.ServeBird;
import br.com.univali.ia2.sma.beehives.workers.behaviours.UpdateBirdList;
import br.com.univali.ia2.sma.utils.Constants;
import jade.core.AID;
>>>>>>> Stashed changes:src/br/com/univali/ia2/sma/beehives/workers/WorkerAgent.java
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

    @Override
    protected void setup() {
<<<<<<< Updated upstream:src/br/com/univali/ia2/sma/beehives/WorkerAgent.java
        System.out.println("WorkerAgent");
        addBehaviour(new Clean(this, 1000, 5000));
=======
        System.out.println("Create WorkerAgent - " + getLocalName());

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
            addBehaviour(new Clean(this, 1000, 20000));;
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
    
    public String state() {
        return "clean";
>>>>>>> Stashed changes:src/br/com/univali/ia2/sma/beehives/workers/WorkerAgent.java
    }
}
