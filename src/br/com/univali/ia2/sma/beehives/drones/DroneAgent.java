/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.univali.ia2.sma.beehives.drones;

import br.com.univali.ia2.sma.beehives.drones.behaviours.InvitationReceiver;
import br.com.univali.ia2.sma.beehives.drones.behaviours.ServeQueen;
import br.com.univali.ia2.sma.utils.Constants;
import com.sun.corba.se.impl.orbutil.closure.Constant;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

/**
 *
 * @author 6182593
 */
public class DroneAgent extends Agent{
     @Override
    protected void setup() {
        // Register the drone service in the yellow pages
        DFAgentDescription dfd = new DFAgentDescription();
        dfd.setName(getAID());
        ServiceDescription sd = new ServiceDescription();
        sd.setType(Constants.DF_DRONE);
        sd.setName(getLocalName()+"-"+Constants.DF_DRONE);
        dfd.addServices(sd);
        try {
            DFService.register(this, dfd);
            addBehaviour(new ServeQueen(this));
        }
        catch (FIPAException fe) {
            fe.printStackTrace();
        }
        
        
    }
    protected void takeDown(){
        // Deregister from the yellow pages
        try {
            DFService.deregister(this);
        }
        catch (FIPAException fe) {
            fe.printStackTrace();
        }
    }
}
