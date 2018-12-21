package es.rtbclient.simpleserver.thread;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;

import es.rtbclient.simpleserver.bean.ResponseDataBean;

public class MonitorThread implements Runnable{
	
	ConcurrentLinkedQueue<ResponseDataBean> queue;
	
	ArrayList<ResponseDataBean> data;
	
	
	 private boolean run=true;
	
	public MonitorThread(ConcurrentLinkedQueue<ResponseDataBean> _queue) {
		queue = _queue;
		data = new ArrayList<ResponseDataBean>();
	}
	
	public void shutdown(){
	    this.run=false;
	}
	
	
	public void processResults() {
		generateStatistics_1();
		generateStatistics_2();
		data.clear();
	}
	
	
	private void generateStatistics_1() {
		
		try {	
			FileOutputStream fos = new FileOutputStream(new File("./output/outputResults.csv"),true);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
			
			for(ResponseDataBean rDB: data) {
				bw.write(rDB.toString());
				bw.newLine();
			}
			
			bw.close();
			fos.close();
				
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private void generateStatistics_2() {
		
		try {
		
			FileOutputStream fos = new FileOutputStream(new File("./output/timeStatistics.csv"),true);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
			
			List<Integer> iteration = data.stream().map(e->e.getNumIt()).collect(Collectors.toList());
			
			for(Integer i: iteration) {
				
				List<ResponseDataBean> round = data.stream().filter(e->e.getNumIt() == i).collect(Collectors.toList());
				
				String result = "";
				
				if (round.size() > 0) {
					result = round.get(0).getConsole();
					for(ResponseDataBean rDB: round) {
						result += ";" + rDB.getTime();
					}
					bw.write(result);
					bw.newLine();
				}
				
			}
			
		
			bw.close();
			fos.close();
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		while(run) {
			
			try { 
				 int flag = 0;
				 if (!queue.isEmpty()) {
					 while(!queue.isEmpty() && (flag <= 100)) {
						 ResponseDataBean rDB = queue.poll();
						 data.add(rDB);
						 flag++;
					 }
				 }
                 Thread.sleep(1000);
             } catch (Exception e) {
                 e.printStackTrace();
             }
				
		}
	}

}
