/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.univali.ia2.sma.beehives.workers.behaviours;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.TickerBehaviour;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 *
 * @author leoda
 */
public class Clean extends TickerBehaviour {

    long cleaning_time;

    public Clean(Agent a, long period, long cleaning_time) {
        super(a, period);
        this.cleaning_time = (System.currentTimeMillis() + cleaning_time);
    }

    @Override
    protected void onTick() {
        if (System.currentTimeMillis() > this.cleaning_time) {
            System.out.println("Agent: " + myAgent.getLocalName() + " | Behaviour: " + getBehaviourName() + " Finish!");
            stop();
        } else {
            System.out.println("Agent: " + myAgent.getLocalName() + " | Behaviour: " + getBehaviourName() + " cleaning up!");
        }
    }
}
