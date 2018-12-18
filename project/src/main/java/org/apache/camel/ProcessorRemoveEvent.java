package org.apache.camel;

import java.util.Map;
import java.util.HashMap;
public class ProcessorRemoveEvent implements Processor
{
	public void process(Exchange exchange) throws Exception
	{
		// create and store
		Map<String, Object> record = new HashMap<String, Object>();
		record = exchange.getIn().getBody(Map.class);
		
		// remove event from record json file
		record.remove("event");
		
		// send changed json file back into the route
		exchange.getIn().setBody(record);
	}
}
