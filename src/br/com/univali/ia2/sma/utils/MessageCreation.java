/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.univali.ia2.sma.utils;

import jade.content.ContentElement;
import jade.core.AID;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;

/**
 *
 * @author wachsmann
 */
public class MessageCreation {

    // ----------------- Messages Creation Methods -----------------------------
    /*
	ACLMessage newMsg(Agent agent, int perf, ContentElement content)
	{
		return newMsg(agent, perf, content, null) ;
	}
     */
    ACLMessage newMsg(Agent agent, int perf, String content) {
        return newMsg(agent, perf, content, null);
    }

    /*  
	ACLMessage newMsg(Agent agent, int perf, ContentElement ce, AID dest)
	{
		ACLMessage msg = newMsg(agent,perf);
		if (dest != null) msg.addReceiver( dest );
		msg.setLanguage(codec.getName());
		msg.setOntology(ontology.getName());
		try {
			manager.fillContent( msg, ce );
		}
		catch (Exception e) {
			System.out.println("Problem filling with: " + ce);
			e.printStackTrace();
		}
		return msg;
	}
     */
    ACLMessage newMsg(Agent agent, int perf, String content, AID dest) {
        ACLMessage msg = newMsg(agent, perf);
        if (dest != null) {
            msg.addReceiver(dest);
        }
        msg.setContent(content);
        return msg;
    }

    ACLMessage newMsg(Agent agent, int perf) {
        ACLMessage msg = new ACLMessage(perf);
        msg.setConversationId(genCID(agent));
        return msg;
    }

// -------------------------------------------------------------------------
//     Generating unique ConversationIDs
// -------------------------------------------------------------------------
    protected static int cidCnt = 0;
    String cidBase;

    String genCID(Agent agent) {
        if (cidBase == null) {
            cidBase = agent.getLocalName() + hashCode()
                    + System.currentTimeMillis() % 10000 + "_";
        }
        return cidBase + (cidCnt++);
    }

}
