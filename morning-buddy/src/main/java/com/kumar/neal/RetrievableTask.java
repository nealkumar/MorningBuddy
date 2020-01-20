package com.kumar.neal;

public abstract class RetrievableTask<T> implements Task, Runnable{
	
	protected volatile T obj;
	private java.util.concurrent.Semaphore objSem;
	
	protected RetrievableTask(){
		this.objSem = new java.util.concurrent.Semaphore(0, false);
	}

	@Override
	public final void run() {
		// TODO Auto-generated method stub
		System.out.println("Thread " 
							+ Thread.currentThread().getId()
							+ " is running...");
		this.execute();
		this.objSem.release();
	}
	
	protected abstract void execute();

	/**
	 * Blocks until {@link obj} of Type &lt?&gt is not null.
	 */
	@Override 
	@ThreadSafe
	public final T getVal() throws InterruptedException {
		// TODO Auto-generated method stub
		this.objSem.acquire();
		return obj;
	}

}
