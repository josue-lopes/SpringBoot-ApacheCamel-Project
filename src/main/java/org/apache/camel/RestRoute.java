package org.apache.camel;

import java.util.Map;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.model.dataformat.JsonDataFormat;
import org.apache.camel.model.dataformat.CsvDataFormat;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.camel.processor.aggregate.AggregationStrategy;
import org.apache.camel.processor.aggregate.GroupedExchangeAggregationStrategy;
import org.apache.camel.processor.aggregate.UseLatestAggregationStrategy;
import org.apache.camel.processor.aggregate.UseOriginalAggregationStrategy;
import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class RestRoute extends SpringRouteBuilder 
{
	@Override
	public void configure() throws Exception 
	{
		// TODO: implement REST interface
		//restConfiguration()
		//.bindingMode(RestBindingMode.json);
		
		//rest("src/data/")
		//.get("sample-payload")
		//.consumes("application/json")
		//.to("direct:restOutput");
		
		from("file:src/data?fileName=sample-payload.json&noop=true")
		.to("direct:processRoute");
		
		from("direct:processRoute")
		.unmarshal().json(JsonLibrary.Jackson, Map.class)	// unmarshall data to JSON map to process
		.split().jsonpath("$.records[*]")					// split up each record
		.streaming()
		.bean(ProcessorRemoveEvent.class, "process")		// remove events from each record
		.to("direct:outputRoute");

		from("direct:outputRoute")
		.marshal().csv()											// marshall data to a csv to output
		.aggregate(constant(true), new RecordAggregationStrategy()) // aggregate each record together into one file
		.completionSize(10)
		.completionTimeout(60000)
		.to("file:src/output?fileName=testOutput");
		
	}

}
