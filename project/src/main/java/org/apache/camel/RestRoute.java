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
		restConfiguration()
		.component("servlet")
		.bindingMode(RestBindingMode.json);
		
		rest("/api/")
		.consumes("application/json")
		.produces("application/json")
		.post("/request")
		.type(PayloadObject.class)
		.to("direct:processRoute");
		
		//from("file:src/data?fileName=sample-payload.json&noop=true")
		//.to("direct:processRoute");
		
		from("direct:processRoute")
		.split().jsonpath("$.records[*]")					// split up each record
		.streaming()
		.to("direct:outputRoute");

		from("direct:outputRoute")
		.marshal().csv()											// marshall data to a csv to output
		.aggregate(constant(true), new RecordAggregationStrategy()) // aggregate each record together into one file
		.completionSize(10)
		.completionTimeout(60000)
		.to("file:src/output?fileName=testOutput");
		
	}

}
