package biolockJSim;

import java.util.Random;

public class BiolockJSimNode
{
	public static final int MAX_PROGRESS = 100;
	private static final Random random = new Random();
	
	private final String name;
	private final String jobName;
	private int progress =0;
	
	public BiolockJSimNode(String name, String jobName)
	{
	
		this.name = name;
		this.jobName = jobName;
	}
	
	public synchronized void increment()
	{
		progress += random.nextInt(5);
		
		if( progress > MAX_PROGRESS)
			progress = MAX_PROGRESS;
	}
	
	public synchronized boolean isFinished()
	{
		return progress >= MAX_PROGRESS;
	}
	
	public synchronized int getProgress()
	{
		return this.progress;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getJobName()
	{
		return jobName;
	}
}
