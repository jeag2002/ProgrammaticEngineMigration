package es.rtbclient.simpleserver.thread;

import java.util.concurrent.ConcurrentLinkedQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.rtbclient.simpleserver.bean.ResponseDataBean;
import es.rtbclient.simpleserver.template.ConsoleTemplate;

public class TaskThread implements Runnable {
	
	private final Logger logger = LoggerFactory.getLogger(TaskThread.class);
	
	private ConsoleTemplate template;
	private String nameConsole;
	private long numCall;
	private long numIt;

	private ConcurrentLinkedQueue<ResponseDataBean> queue;
	
	
	public TaskThread (ConsoleTemplate _template, String _nameConsole, long _numIt, long _numCall, ConcurrentLinkedQueue<ResponseDataBean> _queue) {
		template = _template;
		nameConsole = _nameConsole;
		numIt = _numIt;
		numCall = _numCall;
		queue = _queue;
	}
	

	@Override
	public void run() {
		try {
			logger.info("[" + Thread.currentThread().getName() + "] Console ("+nameConsole+") NumIt("+numIt+") numCall("+numCall+")  Start");
			
			long startTime = System.currentTimeMillis();
			
			String data = template.processCall();
			
			ResponseDataBean bean = new ResponseDataBean();
			bean.setNumCall((int)numCall);
			bean.setNumIt((int)numIt);
			bean.setResponse(data);
			bean.setConsole(nameConsole);
			
			long stopTime = System.currentTimeMillis();
			bean.setTime(stopTime-startTime);
			
			queue.add(bean);
			
			logger.info("[" + Thread.currentThread().getName() + "] Console ("+nameConsole+") NumIt("+numIt+") numCall("+numCall+") End");
		}catch(Exception e) {}
	}

}
