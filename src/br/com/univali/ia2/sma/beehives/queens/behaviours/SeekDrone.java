/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.univali.ia2.sma.beehives.queens.behaviours;

import br.com.univali.ia2.sma.beehives.queens.QueenAgent;
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
import java.util.Vector;

/**
 *
 * @author wachsmann
 */
/*
*   Fly out seeking a drone 
*   Obs: send message to all drones to mate 
*      the first that responds start the mate behaviour
*/
public class SeekDrone extends OneShotBehaviour{

    QueenAgent queenAgent;
    public SeekDrone(QueenAgent a) {
        this.queenAgent = a;
    }

 

    @Override
    public void action() {
        Vector agents = queenAgent.getDroneDFList();
        if(agents.size() > 0){
            System.out.println("Seeking drones..");
            //SENDING TO ONLY DRONES
            AID myID = queenAgent.getAID();
           
            int i = Constants.randInt(0, agents.size() - 1);
            System.out.println("Sorted drone to get lucky => "+i);


            AID agentID = (AID) agents.get(i);

            ACLMessage msg = new ACLMessage(ACLMessage.PROPOSE);
            msg.setOntology( Constants.BEES_ONTOLOGY);
            msg.setLanguage(Constants.BEES_LANGUAGE);
            msg.setContent(Constants.MATE_PROPOSAL);
            
            msg.addReceiver(agentID);
            queenAgent.send(msg);            
        }
        
    }

    
    
}
