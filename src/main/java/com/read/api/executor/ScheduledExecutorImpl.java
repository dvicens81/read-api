package com.read.api.executor;

import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HttpsURLConnection;

import org.springframework.stereotype.Component;

import com.read.api.exception.CustomizedException;
import com.read.api.helper.impl.JsonHelperImpl;

@Component
public class ScheduledExecutorImpl implements IScheduledExecutor {
	
	private int number = 0;
	@SuppressWarnings("rawtypes")
	public int getCountsByReleaseGroup(HttpsURLConnection connection, String elementToGet) {		
		ScheduledExecutorService es = Executors.newScheduledThreadPool(1);
		Collection<Future<?>> futures = new LinkedList<>();	
		
		Runnable task = () -> {
			try {
				number = new JsonHelperImpl().getJsonElement(connection, elementToGet).getAsInt();
			} catch (IOException e) {
				throw new CustomizedException();
			}
		};
		
		futures.add(es.schedule(task, 1, TimeUnit.SECONDS));		
		
	   try {
		   futures.iterator().next().get();
	   } catch (InterruptedException e ) {
		   Thread.currentThread().interrupt();
	       throw new CustomizedException();
	   } catch (ExecutionException e) {
		   throw new CustomizedException();
	   }finally {
		   if (!es.isTerminated()) es.shutdown();
	   }
	   return number;
	}
}
