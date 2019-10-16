/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.univali.ia2.sma.beehives.queens.behaviours;

import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.WakerBehaviour;
import java.util.Date;

/**
 *
 * @author wachsmann
 */
public class layEggs extends WakerBehaviour{

    public layEggs(Agent a, Date wakeupDate) {
        super(a, wakeupDate);
    }
    
     
    @Override
    protected void onWake() {
        System.out.println("QueenAgent: " + myAgent.getLocalName() + " | Behaviour: " + getBehaviourName() + " finished!");
    }

    
}
