package org.apache.camel;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RecordObject 
{
	@JsonProperty("transId")
	public String transId;
	@JsonProperty("transTms")
	public String transTms;
	@JsonProperty("rcNum")
	public String rcNum;
	@JsonProperty("clientId")
	public String clientId;
	
	@JsonGetter
	public String getTransId() {
		return transId;
	}
	@JsonSetter
	public void setTransId(String transId) {
		this.transId = transId;
	}
	@JsonGetter
	public String getTransTms() {
		return transTms;
	}
	@JsonSetter
	public void setTransTms(String transTms) {
		this.transTms = transTms;
	}
	@JsonGetter
	public String getRcNum() {
		return rcNum;
	}
	@JsonSetter
	public void setRcNum(String rcNum) {
		this.rcNum = rcNum;
	}
	@JsonGetter
	public String getClientId() {
		return clientId;
	}
	@JsonSetter
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
}
