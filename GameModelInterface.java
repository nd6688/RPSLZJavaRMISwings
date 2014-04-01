/*
 * GameModelInterface.java
 *
 * Version:1, 12/09/2013
 */
/**
 * Description: Interface.
 * 
 * @author Bhargav Solanki
 * @author Neel Desai
 */
public interface GameModelInterface extends java.rmi.Remote {
	public int[] myChoice(int choice, String player)
			throws java.rmi.RemoteException;
}
