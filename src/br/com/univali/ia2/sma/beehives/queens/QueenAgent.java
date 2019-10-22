/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.univali.ia2.sma.beehives.queens;

import br.com.univali.ia2.sma.beehives.drones.DroneAgent;
import br.com.univali.ia2.sma.beehives.queens.behaviours.Mating;
import br.com.univali.ia2.sma.beehives.queens.behaviours.Reproduce;
import br.com.univali.ia2.sma.beehives.queens.behaviours.SeekDrone;
import br.com.univali.ia2.sma.beehives.queens.behaviours.UpdateDroneList;
import jade.core.Agent;
import static jade.core.Agent.AP_MIN;
import static jade.core.Agent.D_ACTIVE;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.TickerBehaviour;
import java.util.Vector;

/**
 *
 * @author 6182593
 */
public class QueenAgent extends Agent{


    private Vector droneAgents = new Vector();
    
    //TODO: create mechanism to randomize time for seeking drone and controlling communication
    @Override
    protected void setup() {
        System.out.println("QueenAgent");
        addBehaviour(new UpdateDroneList(this, 2000));
        addBehaviour(new SeekDrone(this, 10000));
        addBehaviour(new Mating(this));
        
    }
    public void clearDroneList() {
        this.droneAgents.clear();
    }
    public void addDroneInList(Object droneDF){
        this.droneAgents.add(droneDF);
    }
    public Vector getDroneDFList(){
        return droneAgents;
    }
}
