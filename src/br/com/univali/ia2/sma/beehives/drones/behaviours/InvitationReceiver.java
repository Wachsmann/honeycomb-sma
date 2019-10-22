/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.univali.ia2.sma.beehives.drones.behaviours;

import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

/**
 *
 * @author wachsmann
 */
public class InvitationReceiver extends SimpleBehaviour{
    
    private MessageTemplate template;
    private long    timeOut, 
                    wakeupTime;
    private boolean finished;

    private ACLMessage msg;

    public ACLMessage getMessage() { return msg; }
    private Agent droneAgent;

    public InvitationReceiver(Agent a, int millis, MessageTemplate mt) {
        this.droneAgent = a;
        timeOut = millis;
        template = mt;
    }
  
    public void onStart() {
        wakeupTime = (timeOut<0 ? Long.MAX_VALUE:System.currentTimeMillis() + timeOut);
    }

    public boolean done () {
        return finished;
    }

    public void action() 
    {
        
        if(template == null)
            msg = myAgent.receive();
        else
            msg = myAgent.receive(template);

        if( msg != null) {
            System.out.println(droneAgent.getName() + "receving a message...");
            finished = true;
            handle( msg );
            return;
        }
        long dt = wakeupTime - System.currentTimeMillis();
        if ( dt > 0 ) 
            block(dt);
        else {
            System.out.println(droneAgent.getName() + "receving a message...");
            finished = true;
            handle(msg);
        }
    }

    public void handle( ACLMessage m) { 
    
        /* can be redefined in sub_class */
        if(m == null)
            System.out.println("Message received is empty ");

        else{
            System.out.println("Message received => "+ m.getLanguage());
            System.out.println("Message received => "+ m.getOntology());
            System.out.println("Message received => "+ m.getContent());
        }
    }

    public void reset() {
        msg = null;
        finished = false;
        super.reset();
    }

    public void reset(int dt) {
        timeOut= dt;
        reset();
    }

    
}
