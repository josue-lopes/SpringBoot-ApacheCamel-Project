package org.apache.camel;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;


public class PayloadObject 
{
	@JsonProperty("batchId")
	private String batchID;
	
	@JsonProperty("records")
	private List<RecordObject> records;
	
	@JsonGetter
	public String getBatchID() {
		return batchID;
	}

	@JsonSetter
	public void setBatchID(String batchID) {
		this.batchID = batchID;
	}

	@JsonGetter
	public List<RecordObject> getRecords() {
		return records;
	}

	@JsonSetter
	public void setRecords(List<RecordObject> records) {
		this.records = records;
	}
}

