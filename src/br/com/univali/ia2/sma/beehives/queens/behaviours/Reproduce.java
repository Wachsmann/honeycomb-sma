/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.univali.ia2.sma.beehives.queens.behaviours;
import br.com.univali.ia2.sma.beehives.queens.QueenAgent;

import jade.core.behaviours.SequentialBehaviour;

/**
 *
 * @author wachsmann
 */
public class Reproduce extends SequentialBehaviour{
    QueenAgent queenAgent;
    public Reproduce(QueenAgent a){
        this.queenAgent = a;
    }
    public int onEnd() {
        //TODO: check end action for queen
        return 0 ;
    }
}
