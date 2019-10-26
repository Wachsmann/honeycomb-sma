/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.univali.ia2.sma.beehives.workers.behaviours;

import br.com.univali.ia2.sma.beehives.workers.WorkerAgent;
import br.com.univali.ia2.sma.utils.Constants;
import jade.core.AID;
import jade.core.behaviours.SimpleBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;

/**
 *
 * @author wachsmann
 */
/*
*   Fly out seeking a drone 
*   Obs: send message to all drones to mate 
*      the first that responds start the mate behaviour
 */
public class AttackWarningWorker extends SimpleBehaviour {

    WorkerAgent workerAgent;
    long seekTime;

    public AttackWarningWorker(WorkerAgent a) {
        this.workerAgent = a;
    }

    @Override
    public void action() {
        //System.out.println("Updating drones list...");
        // Update the list of seller agents
        DFAgentDescription template = new DFAgentDescription();
        ServiceDescription sd = new ServiceDescription();
        sd.setType(Constants.DF_WORKER);
        template.addServices(sd);
        try {

            DFAgentDescription[] result = DFService.search(myAgent, template);

            for (int i = 0; i < result.length; ++i) {
                //System.out.println("Send message attack warning => " + result[i].getName());

                block(seekTime);

                // LUCK A BEE TO ATTACK
                Object agent = result[i].getName();

                if (!agent.equals(false)) {
                    // SEND MESSAGE OF ATTACK
                    AID agentID = (AID) agent;
                    ACLMessage msg = new ACLMessage(ACLMessage.PROPOSE);
                    msg.setOntology(Constants.BEES_ONTOLOGY);
                    msg.setLanguage(Constants.BEES_LANGUAGE);
                    msg.setContent(Constants.BEE_ATTACK_WARNING);

                    msg.addReceiver(agentID);
                    workerAgent.send(msg);
                    workerAgent.died();

                } else {
                    System.out.println("Don't have workers..");
                }
            }
            block(100000);
            //birdAgent.addBehaviour(new SeekDrone(birdAgent, 2000));
        } catch (FIPAException fe) {
            System.out.println(fe.getACLMessage());
        }
    }

    @Override
    public boolean done() {
        return true;
    }
}
