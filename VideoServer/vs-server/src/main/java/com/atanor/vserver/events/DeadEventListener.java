package com.atanor.vserver.events;

import com.google.common.eventbus.DeadEvent;
import com.google.common.eventbus.Subscribe;

public class DeadEventListener {

	@Subscribe
	public void listen(DeadEvent event) {
		System.out.println("Event is not delivered!!");
	}
}
