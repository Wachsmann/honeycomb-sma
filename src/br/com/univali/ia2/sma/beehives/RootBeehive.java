/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.univali.ia2.sma.beehives;

import br.com.univali.ia2.sma.beehives.drones.DroneAgent;
import br.com.univali.ia2.sma.beehives.queens.QueenAgent;
import br.com.univali.ia2.sma.beehives.workers.WorkerAgent;
import java.util.ArrayList;

/**
 *
 * @author wachsmann
 */
public class RootBeehive {
    protected QueenAgent queen;
    protected ArrayList<DroneAgent> drones;
    protected ArrayList<WorkerAgent> workers;
    
}
