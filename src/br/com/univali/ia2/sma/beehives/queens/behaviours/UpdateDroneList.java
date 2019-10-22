/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.univali.ia2.sma.beehives.queens.behaviours;

import br.com.univali.ia2.sma.beehives.queens.QueenAgent;
import br.com.univali.ia2.sma.utils.Constants;
import com.sun.corba.se.impl.orbutil.closure.Constant;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import java.util.Vector;

/**
 *
 * @author wachsmann
 */
public class UpdateDroneList extends TickerBehaviour{
    // The list of known seller agents
    
    QueenAgent queenAgent;
    public UpdateDroneList(QueenAgent a, long period) {
        super(a, period);
        this.queenAgent = a;
        
    }
    @Override
    protected void onTick() {
        //System.out.println("Updating drones list...");
        // Update the list of seller agents
        DFAgentDescription template = new DFAgentDescription();
        ServiceDescription sd = new ServiceDescription();
        sd.setType(Constants.DF_DRONE);
        template.addServices(sd);
        try {
            DFAgentDescription[] result = DFService.search(myAgent,
            template);
            queenAgent.clearDroneList();
            for (int i = 0; i < result.length; ++i) {
                //System.out.println("Adding agent => "+ result[i].getName());
                queenAgent.addDroneInList(result[i].getName());
            }
            System.out.println("Drones list updated!");
            queenAgent.addBehaviour(new SeekDrone(queenAgent, 2000));
        }
        catch (FIPAException fe) {
            System.out.println(fe.getACLMessage());
        }
    }
    
    
}
