package es.rtbclient.simpleserver.engine; 

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.rtbclient.console.simpleconsole.response.bean.ResponseBean;
import es.rtbclient.simpleserver.bean.ResponseDataBean;
import es.rtbclient.simpleserver.template.ConsoleTemplate;
import es.rtbclient.simpleserver.template.console.SimpleConsole;
import es.rtbclient.simpleserver.thread.MonitorThread;
import es.rtbclient.simpleserver.thread.RejectedExecutionHandlerImpl;
import es.rtbclient.simpleserver.thread.TaskThread;

//https://www.journaldev.com/1069/threadpoolexecutor-java-thread-pool-example-executorservice


public class StatisticsMain {
	
	private final Logger logger = LoggerFactory.getLogger(StatisticsMain.class);
	
	private long corePoolSize = 0;
	private long maxPoolSize = 0;
	private long numCall = 0;
	private long numIt = 0;
	private long timeInSeconds = 0;
	private String clientClass = "";
	
	public StatisticsMain(String[] args) throws Exception{
		
		corePoolSize = Long.parseLong(args[0]);
		maxPoolSize = Long.parseLong(args[1]);
		numCall = Long.parseLong(args[2]);
		numIt = Long.parseLong(args[3]);
		timeInSeconds = Long.parseLong(args[4]);
		clientClass = args[5];
	}

	
	
	public void run() throws Exception{
		
		SimpleDateFormat sDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		logger.info("[STATISTICSMAIN] StatisticsMain " + corePoolSize + " " + maxPoolSize + " " + numCall + " " + numIt + " " + timeInSeconds + " " + clientClass +  "");		
		ConsoleTemplate template = (ConsoleTemplate)Class.forName(clientClass).newInstance();
		
		
		if (corePoolSize <= 0) {throw new Exception("Invalid Num core Pool " + corePoolSize);}
		if (maxPoolSize <= 0) {throw new Exception("Invalid Max Num core Pool " + maxPoolSize);} 
		if (numCall <= 0) {throw new Exception("Invalid num Call " + numCall);}
		if (numIt <= 0) {throw new Exception("Invalid Num iteration " + numIt);}
		if (timeInSeconds <= 0) {throw new Exception("Invalid seconds " + timeInSeconds);}
		
		int begin = clientClass.lastIndexOf(".")+1;
		int end = clientClass.length();
		
		
		if (begin <= end) {
			clientClass = clientClass.substring(begin, end);
		}
		
		for (int i=0; i<numIt; i++) {
			//Long startTime = System.currentTimeMillis();
			Long timeInMillis = processIteration(clientClass, template,i);
			//Long stopTime = System.currentTimeMillis();
			
			String result = sDF.format(new Date()) + ";" + clientClass + ";" + (i+1) + ";" + timeInMillis; 
			generateStatistics_3(result);
			
		}
		
	}
	
	
	private void generateStatistics_3(String result) {
		
		try {
		
			FileOutputStream fos = new FileOutputStream(new File("./output/totalThreadIt.csv"),true);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
			bw.write(result);
			bw.newLine();
			bw.close();
			fos.close();
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	
	private Long processIteration(String clientClass, ConsoleTemplate template, long numIt) throws Exception{
		
		RejectedExecutionHandlerImpl rejectionHandler = new RejectedExecutionHandlerImpl();
		
		ConcurrentLinkedQueue<ResponseDataBean> queue = new ConcurrentLinkedQueue<ResponseDataBean>();
		
		MonitorThread monitor = new MonitorThread(queue);
		
		Thread outputThread = new Thread(monitor);
		
		outputThread.start();
		
		Long startTime = System.currentTimeMillis();
		
		ThreadFactory threadFactory = Executors.defaultThreadFactory();
		ThreadPoolExecutor executorPool =
				new ThreadPoolExecutor(
						(int)corePoolSize,
						(int)maxPoolSize,
						(int)timeInSeconds,
						TimeUnit.SECONDS, 
						new ArrayBlockingQueue<Runnable>((int)maxPoolSize),
						threadFactory, rejectionHandler);	
		
		for(int i=0; i<numCall; i++) {
			executorPool.execute(new TaskThread(template,clientClass,numIt,i,queue));
		}
		
		/*
		while (executorPool.getTaskCount()!=executorPool.getCompletedTaskCount()){
		    logger.warn("[EXECUTORPOOL] count="+executorPool.getTaskCount()+","+executorPool.getCompletedTaskCount());
		    Thread.sleep(1500);
		}
		*/
				
		
		executorPool.shutdown();
		executorPool.awaitTermination(timeInSeconds, TimeUnit.SECONDS);
		
		
		Long stopTime = System.currentTimeMillis();
		
		
		//monitor.shutdown();
		Thread.sleep(1000);
		monitor.processResults();
		
		
		return stopTime-startTime;
	}
	
	
	

	public static void main(String[] args) throws Exception {
		try {	
			if (args.length != 6) {
				
				System.out.println("StatisticsMain <corePoolSize> <maxPoolSize> <numCall> <NumIt> <keepAliveTime> <Class>");
				System.out.println("<corePoolSize> - num threads cada vez");
				System.out.println("<maxPoolSize> - num threads max");
				System.out.println("<numCall> - num call");
				System.out.println("<numIt> - num Iteraciones");
				System.out.println("<keepAliveTime> - max time of processing (in seconds)");
				System.out.println("<Class> - client class");
				
			}else {	
				System.out.println("StatisticsMain -- INI");
				StatisticsMain main = new StatisticsMain(args); 
				main.run();
				System.out.println("StatisticsMain -- END");
			}
		}catch(Exception e) {
			System.err.println("StatisticsMain " + e.getMessage());
		}
		System.exit(0);
	}

}
