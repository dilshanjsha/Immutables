package demo.synch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

/**
 * synchronized of the object lock explanation
 * @author Dilshan
 *
 */
public class SynchronizedObjectAndMethod {

	public SynchronizedObjectAndMethod() {
		// TODO Auto-generated constructor stub
	}

	Vector<String> vector = new Vector<String>() ;

	/**
	 * 2 threads accessing vector at the same time
	 */
	public void synchBlockVectorShiftsObjectToRight() {


		Thread tTwo=new Thread(new Runnable(){

			@Override
			public synchronized void run() { 
				vector.add("Finst2");
				vector.add(0, vector.get(0).replaceAll("Fi", "Bu")+"replace2"); // takes time so equivalent to Thread.sleep, so executes output second

				synchronized (vector) {
					//Now this "get-if-not-empty" operation is atomic and race condition-free.
					if (vector.size() > 0) {
						System.out.println("Thread 2 "+vector.get(0));
						vector.add("Number2");
					}

				}

			}
		});

		Thread tOne=new Thread(new Runnable(){

			@Override
			public synchronized void run() { 

				try {
					//when commented out the output is 
					//Thread 2 Bunst
					//Thread 1 Bunst
					//At last Bunst
					// or 
					//Thread 2 Bunst
					//Thread 1 Bunst
					//At last Bunst


					Thread.sleep(400);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				vector.add("First1");
				vector.add(0, vector.get(0).replaceAll("Fi", "Bu")+"replace1"); // takes time so equivalent to Thread.sleep, so executes output second

				synchronized (vector) {
					//Now this "get-if-not-empty" operation is atomic and race condition-free.
					if (vector.size() > 0) {
						System.out.println("Thread 1 "+vector.get(0));
						vector.add("Number1");
					}

				}

			}
		});

		tTwo.start();
		tOne.start();

		try {
			tOne.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("At last "+vector.get(0) +" vector contents are "+ vector.toString());
	}

	
	
	/**
	 * block one and block two are locked by same dedicated lock
	 * so when a thread accesses block one, then no thread can access block two
	 */
	public void synchronizedMethod() {	
		final List<String> list=new ArrayList<String>();
		final List<String> synList=Collections.synchronizedList(list);
		final Object dedicatedLock=new Object();
		Thread tOne=new Thread(new Runnable(){

			@Override
			public void run() { // only one thread enters this block at a time
				synchronized(dedicatedLock){ // block one
				for(int i=0;i<5;i++){
					System.out.println(synList.add("add one "+i)+ " one");
				}
				}
			}

		});

		Thread tTwo=new Thread(new Runnable(){

			// even if the run method is synchronized 
			// and if the synchronized ( dedicatedLock ) is commented out
			// then it means the run method takes 'this' as the lock i.e., the object of the method as lock and not the dedicatedLock as lock
			// so two different lock objects doesnt give proper synchronization
			@Override
			public synchronized void run() {// only one thread enters this block at a time
				//synchronized(dedicatedLock){ //block two
				for(int i=0;i<5;i++){
					System.out.println(synList.add("add two "+i)+" two");
				}
				//}
			}

		});

		
		
		tTwo.start(); // starts first
		tOne.start(); // starts next
		try {

			tOne.join(); //InterruptedException
			tTwo.join();//InterruptedException
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// without .join() executes before thread starts
		// with .join() executes after thread starts
		System.out.println(synList.size() +" "+synList); 

	}

	/**
	 * block one and block two are locked by same dedicated lock
	 * so when a thread accesses block one, then no thread can access block two
	 */
	public void synchronizedOfThisMethod() {	
		final List<String> list=new ArrayList<String>();
		final List<String> synList=Collections.synchronizedList(list);
		//final Object dedicatedLock=new Object();
		Thread tOne=new Thread(new Runnable(){

			@Override
			public synchronized void run() { // only one thread enters this block at a time
				//synchronized(dedicatedLock){ // block one
				for(int i=0;i<5;i++){
					System.out.println(synList.add("add one "+i)+ " one");
				}
				//}
			}

		});

		Thread tTwo=new Thread(new Runnable(){

			// even if the run method is synchronized 
			// and if the synchronized ( dedicatedLock ) is commented out
			// then it means the run method takes 'this' as the lock i.e., the object of the method as lock and not the dedicatedLock as lock
			// both the run methods have different object as their lock, i.e., tTwo and tOne
			// so two different lock objects does not give proper synchronization
			@Override
			public synchronized void run() {// only one thread enters this block at a time
				//synchronized(dedicatedLock){ //block two
				for(int i=0;i<5;i++){
					System.out.println(synList.add("add two "+i)+" two");
				}
				//}
			}

		});

		
		
		tTwo.start(); // starts first
		tOne.start(); // starts next
		try {

			tOne.join(); //InterruptedException
			tTwo.join();//InterruptedException
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// without .join() executes before thread starts
		// with .join() executes after thread starts
		System.out.println(synList.size() +" "+synList); 

	}


	public static void main(String[] args) {
		SynchronizedObjectAndMethod v = new SynchronizedObjectAndMethod();
		//v.synchronizedMethod();
		//v.synchronizedOfThisMethod();
		v.synchBlockVectorShiftsObjectToRight();
	}

}
