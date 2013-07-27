package com.shunix.portalsnav.utils;

public class AsyncHelper {
	private boolean isFinished;
	private AsyncInterface async;
	private Object object;

	public AsyncHelper(AsyncInterface async) {
		isFinished = false;
		this.async = async;
		object = new Object();
	}

	public void AsyncWorkBegin() {
		new Thread() {
			@Override
			public void run() {
				synchronized (object) {
					async.beginWork();
					object.notifyAll();
				}
			}
		}.start();
		isFinished = true;
	}

	public void AsyncWorkEnd() {
		try {
			new Thread() {
				@Override
				public void run() {
					synchronized (object) {
						while (!isFinished) {
							try {
								object.wait();
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						async.endWork();
					}
				}
			}.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
