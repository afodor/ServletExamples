package biolockJSim;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class BiolockJSim
{
	private final static AtomicInteger nodeNumber = new AtomicInteger(0);
	
	private final Random random = new Random();
	
	public final static Map<Integer, BiolockJSim> runMap = 
			Collections.synchronizedMap(new HashMap<Integer,BiolockJSim>());
	
	private final int runID;
	private final int numNodes;
	
	public int getRunID()
	{
		return runID;
	}
	
	public int getNumNodes()
	{
		return numNodes;
	}
	
	private final List<BiolockJSimNode> list;
	
	public BiolockJSimNode getNode(int nodeNumber)
	{
		return list.get(nodeNumber);
	}
	
	private class UpdateWorker implements Runnable
	{
		@Override
		public void run()
		{
			boolean keepGoing = true;
			
			while(keepGoing)
			{
				int toUpdate = random.nextInt(numNodes);
					
				list.get(toUpdate).increment();
				
				try
				{
						
					Thread.sleep(100);
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
				
				keepGoing = false;
				
				for( int x=0; ! keepGoing &&  x < numNodes; x++)
					if( ! list.get(x).isFinished())
						keepGoing = true;
			}
		}
		
	}
	
	public void startUpdates() 
	{
		new Thread( new UpdateWorker()).start();
	}
	
	public BiolockJSim(int runID, int numNodes)
	{
		this.runID = runID;
		this.numNodes = numNodes;
		List<BiolockJSimNode> tempList = new ArrayList<>();
		
		for( int x=0; x < numNodes;x++)
		{
			tempList.add(new BiolockJSimNode("Node_" + nodeNumber.incrementAndGet(), "RDP job"));
		}
		
		this.list = Collections.unmodifiableList(tempList);
	}
	
}
