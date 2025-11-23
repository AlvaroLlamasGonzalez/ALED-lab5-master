package es.upm.dit.aled.lab5;

import java.util.LinkedList;
import java.util.Queue;

import es.upm.dit.aled.lab5.gui.Position2D;

/**
 * Extension of Area that maintains a strict queue of the Patients waiting to
 * enter in it. After a Patient exits, the first one in the queue will be
 * allowed to enter.
 * 
 * @author rgarciacarmona
 */
public class AreaQueue extends Area {
	private Queue<Patient> waitQueue;
	public AreaQueue(String name, int time, int capacity, Position2D position) {
        super(name, time, capacity, position);
        this.waitQueue = new LinkedList<>();
    }
	
    @Override
	public synchronized void enter(Patient p) {
    	/*waitQueue.add(p);
        waiting++;
		while(numPatients>=capacity|| waitQueue.peek() != p) {
			try {
                wait();
            } catch (InterruptedException e) {
            	Thread.currentThread().interrupt();
                // remove patient from queue and decrement waiting if interrupted
            	 waitQueue.remove(p);
                 waiting--;
                 return;
            }
		}
		
			waitQueue.remove(); // should be 'patient'
	        waiting--;    // no longer waiting
	        numPatients++; // being attended now
     
	*/
    	System.out.println("Patient " + p.getNumber() + " trying to enter " + this.getName());
		this.waiting++;
		this.waitQueue.add(p); // Add to the end of the queue
		try {
			while (numPatients >= capacity || this.waitQueue.peek() != p) {
				System.out.println("Patient " + p.getNumber() + " waiting for " + this.getName()
						+ ". Front of the queue?: " + this.waitQueue.peek().equals(p));
				wait();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
			Thread.currentThread().interrupt(); // Restore interrupted status
		}
		this.waitQueue.remove(); // Dequeue from the front
		this.numPatients++;
		this.waiting--;
		System.out.println("Patient " + p.getNumber() + " has entered " + this.name);
	
	}
	
	// TODO
}
