/*
 * Created on 07-Sep-2004
 * @author bandara
 */
package rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RMISecurityManager; //****
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;  //****
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;

import common.*;

/**
 * @author bandara
 *
 */
public class RMIServer extends UnicastRemoteObject implements RMIServerI {

	private int totalMessages = -1;
	private int[] receivedMessages;

	public RMIServer() throws RemoteException {
	}

	public void receiveMessage(MessageInfo msg) throws RemoteException {

		// TO-DO: On receipt of first message, initialise the receive buffer

		// TO-DO: Log receipt of the message

		// TO-DO: If this is the last expected message, then identify
		//        any missing messages
		
		
		
		/* Avalin barname ke nashod :P :D
		//----------------------------------------------------------------------
		// TO-DO: On receipt of first message, initialise the receive buffer
		if(receivedMessages == null){
			totalMessages = 0;
			receivedMessages = new int [msg.totalMessages];
			//receivedMessages = new int(msg.totalMessages);
		}
		// TO-DO: Log receipt of the message
		totalMessages++;
		receivedMessages[msg.messageNum] = 1;
		// TO-DO: If this is the last expected message, then identify
		//        any missing messages
		if(totalMessages == msg.totalMessages){

			if(receivedMessages == null || totalMessages <= 0)
				return;
			
			String missingMessages = "";
			for(int i = 0; i <receivedMessages.length; i++)
				if(receivedMessages[i] == 0)
					missingMessages += i + ", ";
			System.out.println("******* SUMMARY *******");
			System.out.println("Number of messages received: " + totalMessages);
			if(totalMessages == receivedMessages.length)
				System.out.println("No missing messages!");
			else
				System.out.println("Lost Messages: " + missingMessages);
			receivedMessages = null;
			totalMessages = -1;
		}
		//----------------------------------------------------------------------	
		*/

		//==========================
		
		
		// On receipt of first message, initialize the receive buffer
		if (receivedMessages == null) {
			totalMessages = msg.totalMessages;
			receivedMessages = new int[msg.totalMessages];
		}
		
		// Log receipt of the message
		receivedMessages[msg.messageNum] = 1;
		
		// If this is the last expected message, then identify
		//    any missing messages
		if (msg.messageNum + 1 == totalMessages) {
			System.out.println("Messages being totaled....");
			
			String s = "Lost packet numbers: ";
			int count = 0;
			for (int i = 0; i < totalMessages; i++) {
				if (receivedMessages[i] != 1) {
					count++;
					s = s + " " + (i+1) + ", ";
				}
			}
			
			if (count == 0) s = s + "None";
			
			System.out.println("Total messages sent      : " + totalMessages);
			System.out.println("Total messages received  : " + (totalMessages - count));
			System.out.println("Total messages lost      : " + count);
			System.out.println(s);
			System.out.println("Test finished.");
			System.exit(0);
		}
		
		//==========================
		
		
	}


	public static void main(String[] args) {

		RMIServer rmis = null;

		// TO-DO: Initialise Security Manager

		// TO-DO: Instantiate the server class

		// TO-DO: Bind to RMI registry

		// TO-DO: Initialise Security Manager
		
		
		
		/* avali ke tokhmi daroumad!! :D
		//---------------------
		if (System.getSecurityManager() == null){
			System.setSecurityManager(new RMISecurityManager());
		}
		// TO-DO: Instantiate the server class
		try{
			rmis = new RMIServer();
		} catch(RemoteException e){
			System.out.println("Remote Exception: " + e);
		}
		// TO-DO: Bind to RMI registry
		rebindServer("localhost",rmis); //XXX NOT SURE
		//--------------------
		*/
		
		//===========================================
		// Initialize Security Manager
		if(System.getSecurityManager()==null){
            System.setSecurityManager(new RMISecurityManager());
        }
		
		//{
		try{
			rmis = new RMIServer();
		} catch(RemoteException e){
			System.out.println("Remote Exception: " + e);
		}
		// bind to rmi registry
		rebindServer("localhost",rmis); //XXX NOT SURE
		//}
		
		
		/* *****
		// Bind to RMI registry 
		try {
			LocateRegistry.createRegistry( 1099 );
			//Naming.rebind("RMIServer", new RMIServer());
			Naming.rebind("RMIServer", rmis);
		} catch (RemoteException e) {
			System.out.println("Error initializing registry or binding server.");
			System.exit(-1);
		} catch (MalformedURLException e) {
			System.out.println("Could not bind server to defined registry as the URL was malformed.");
			System.exit(-1);
		}
		//==========================================
		 */
		
		System.out.println("RMI SERVER RUNNING....");
	}

	protected static void rebindServer(String serverURL, RMIServer server) {

		// TO-DO:
		// Start / find the registry (hint use LocateRegistry.createRegistry(...)
		// If we *know* the registry is running we could skip this (eg run rmiregistry in the start script)

		// TO-DO:
		// Now rebind the server to the registry (rebind replaces any existing servers bound to the serverURL)
		// Note - Registry.rebind (as returned by createRegistry / getRegistry) does something similar but
		// expects different things from the URL field.
		
		//------------------
		Registry reg;
		// TO-DO:
		// Start / find the registry (hint use LocateRegistry.createRegistry(...)
		// If we *know* the registry is running we could skip this (eg run rmiregistry in the start script)
		try{
		reg = LocateRegistry.createRegistry(1099);
		// TO-DO:
		// Now rebind the server to the registry (rebind replaces any existing servers bound to the serverURL)
		// Note - Registry.rebind (as returned by createRegistry / getRegistry) does something similar but
		// expects different things from the URL field.
		reg.rebind("RMIServer",server);
		} catch (RemoteException e){
			e.printStackTrace();
		}
		//------------------
		
	}
}
