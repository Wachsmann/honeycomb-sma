/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.univali.ia2.sma.beehives.queens;

import br.com.univali.ia2.sma.beehives.queens.behaviours.Mate;
import br.com.univali.ia2.sma.beehives.queens.behaviours.Reproduce;
import br.com.univali.ia2.sma.beehives.queens.behaviours.SeekDrone;
import br.com.univali.ia2.sma.beehives.queens.behaviours.layEggs;
import jade.core.Agent;
import java.util.Date;

/**
 *
 * @author 6182593
 */
public class QueenAgent extends Agent{
    @Override
    protected void setup() {
        System.out.println("QueenAgent");
        Reproduce reproduce = new Reproduce(this);
        //TODO: create mechanism to randomize time for seeking drone and controlling communication
        reproduce.addSubBehaviour(new SeekDrone(this, AP_MIN, D_ACTIVE));
        
        reproduce.addSubBehaviour(new Mate());
        //TODO: create mechanism to randomize time for laying eggs
        reproduce.addSubBehaviour(new layEggs(this, new Date()));
        //Starts reproduce behaviour for queen
        addBehaviour(reproduce);

    }
}
