/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.univali.ia2.sma.beehives.queens.behaviours;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.core.behaviours.WakerBehaviour;
import jade.lang.acl.ACLMessage;

/**
 *
 * @author wachsmann
 */
/*
*   Fly out seeking to a drone 
*   Obs: send message to all drones to mate 
*      the first that responds start the mate behaviour
*/
public class SeekDrone extends WakerBehaviour{

    long seekTime;
    Agent queenAgent;
    public SeekDrone(Agent a, long period,long seekTime) {
        super(a, period);
        this.seekTime = (System.currentTimeMillis() + seekTime);
        this.queenAgent = a;
    }

    @Override
    public void onWake(){
        //TODO: Review message invitation
        System.out.println("QueenAgent seeking for drones...");
        ACLMessage msg = new ACLMessage(ACLMessage.QUERY_IF);
        msg.setOntology( "Bee - ontology" );
        msg.setLanguage("Beertugues");
        msg.addReceiver(new AID("Drone", AID.ISLOCALNAME));
        queenAgent.send(msg);
    }

    
    
}
