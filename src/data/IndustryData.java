package data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class IndustryData {
	private String industrycode;
	private String subindustrycode;
	public String getIndustrycode() {
		return industrycode;
	}
	public void setIndustrycode(String industrycode) {
		this.industrycode = industrycode;
	}
	public String getSubindustrycode() {
		return subindustrycode;
	}
	public void setSubindustrycode(String subindustrycode) {
		this.subindustrycode = subindustrycode;
	}
	@Override
	public String toString() {
		return "IndustryData [industrycode=" + industrycode + ", subindustrycode=" + subindustrycode + "]";
	}
	
	
}
