/*
 * Created on 07-Sep-2004
 * @author bandara
 */
package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

import common.*;

/**
 * @author bandara
 *
 */
public interface RMIServerI extends Remote {
	public void receiveMessage(MessageInfo msg) throws RemoteException;
}
