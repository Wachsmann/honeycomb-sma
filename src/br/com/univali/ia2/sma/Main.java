/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.univali.ia2.sma;

import br.com.univali.ia2.sma.utils.Constants;
import jade.Boot;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;

/**
 *
 * @author 6182593
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static ContainerController container;

    public static void main(String[] args) {
        // TODO code application logic here
        /*String[] param = new String[2];
        param[0] = "-gui";
        
        param[1] = "Léo:br.com.univali.ia2.sma.beehives.WorkerAgent()";        
        param[1] = "drone1:br.com.univali.ia2.sma.beehives.drones.DroneAgent();"
                + "drone2:br.com.univali.ia2.sma.beehives.drones.DroneAgent();"
                + "queen:br.com.univali.ia2.sma.beehives.queens.QueenAgent();"
                + "worker:br.com.univali.ia2.sma.beehives.workers.WorkerAgent();"
                + "";
        param[1] = "worker1:br.com.univali.ia2.sma.beehives.workers.WorkerAgent();"
                + "worker2:br.com.univali.ia2.sma.beehives.workers.WorkerAgent();"
                + "bird1:br.com.univali.ia2.sma.predators.birds.BirdAgent();"
                + "";
        Boot.main(param);
         */
        String[] param = new String[2];
        param[0] = "-gui";
        param[1] = "";
        /*
        param[1] = "Léo:br.com.univali.ia2.sma.beehives.WorkerAgent()";        
        
        param[1] = "drone1:br.com.univali.ia2.sma.beehives.drones.DroneAgent();"+
                   "drone2:br.com.univali.ia2.sma.beehives.drones.DroneAgent();"+
                   "queen:br.com.univali.ia2.sma.beehives.queens.QueenAgent();"+
                   "worker:br.com.univali.ia2.sma.beehives.workers.WorkerAgent();"+
            "";        
         */
        Boot.main(param);

        //Get the JADE runtime interface (singleton)
        jade.core.Runtime runtime = jade.core.Runtime.instance();
        //Create a Profile, where the launch arguments are stored
        Profile profile = new ProfileImpl();
        profile.setParameter(Profile.CONTAINER_NAME, "Hive1");
        profile.setParameter(Profile.MAIN_HOST, "localhost");
        //create a non-main agent container
        container = runtime.createAgentContainer(profile);
        try {
            AgentController queen = container.createNewAgent("queen",
                    "br.com.univali.ia2.sma.beehives.queens.QueenAgent",
                    new Object[]{});//arguments
            queen.start();

            AgentController drone = container.createNewAgent("drone1",
                    "br.com.univali.ia2.sma.beehives.drones.DroneAgent",
                    new Object[]{});//arguments
            drone.start();

            AgentController worker1 = container.createNewAgent("worker1",
                    "br.com.univali.ia2.sma.beehives.workers.WorkerAgent",
                    new Object[]{});//arguments
            worker1.start();

            AgentController worker2 = container.createNewAgent("worker2",
                    "br.com.univali.ia2.sma.beehives.workers.WorkerAgent",
                    new Object[]{});//arguments
            worker2.start();

            AgentController bird = container.createNewAgent("bird",
                    "br.com.univali.ia2.sma.predators.birds.BirdAgent",
                    new Object[]{});//arguments
            bird.start();
            
            AgentController controller = container.createNewAgent("controller",
                    "br.com.univali.ia2.sma.utils.ControllerAgent",
                    new Object[]{});//arguments
            controller.start();

        } catch (StaleProxyException e) {
            e.printStackTrace();
        }
    }
}
