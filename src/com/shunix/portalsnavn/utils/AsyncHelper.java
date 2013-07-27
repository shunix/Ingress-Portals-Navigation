package com.shunix.portalsnavn.utils;

public class AsyncHelper {
	public boolean isFinished = false;
	/**
	 * Call this method to start the time-consuming work.
	 * @param begin : An interface that contains BeginWork() method.
	 * @return
	 */
	public boolean AsyncRequest(AsyncBegin begin) {
		Thread thread = new Request(begin);
		thread.start();
		return isFinished;
	}
	/**
	 * Do time-consuming work here
	 * @author Shunix
	 *
	 */
	class Request extends Thread {
		private AsyncBegin begin;
		public Request(AsyncBegin begin) {
			this.begin = begin;
		}
		@Override
		public void run() {
			super.run();
			begin.BeginWork();
			isFinished = true;
			notifyAll();
		}
	}
	
	/**
	 * Call this method to do something to the result
	 * @param end : An interface that contains the EndWork() method.
	 */
	public void AsyncResponse(AsyncEnd end) {
		if(!isFinished) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		end.EndWork();
	}
}
