package data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CompanyPartnersData {
	private String stockname;
	private String realcapi;
	private String capidate;
	private String investtype;
	private String shoulddate;
	private String shouldcapi;
	private String shouldinvesttype;
	public String getStockname() {
		return stockname;
	}
	public void setStockname(String stockname) {
		this.stockname = stockname;
	}
	public String getRealcapi() {
		return realcapi;
	}
	public void setRealcapi(String realcapi) {
		this.realcapi = realcapi;
	}
	public String getCapidate() {
		return capidate;
	}
	public void setCapidate(String capidate) {
		this.capidate = capidate;
	}
	public String getInvesttype() {
		return investtype;
	}
	public void setInvesttype(String investtype) {
		this.investtype = investtype;
	}
	public String getShoulddate() {
		return shoulddate;
	}
	public void setShoulddate(String shoulddate) {
		this.shoulddate = shoulddate;
	}
	public String getShouldcapi() {
		return shouldcapi;
	}
	public void setShouldcapi(String shouldcapi) {
		this.shouldcapi = shouldcapi;
	}
	public String getShouldinvesttype() {
		return shouldinvesttype;
	}
	public void setShouldinvesttype(String shouldinvesttype) {
		this.shouldinvesttype = shouldinvesttype;
	}
	@Override
	public String toString() {
		return "CompanyPartnersData [stockname=" + stockname + ", realcapi=" + realcapi + ", capidate=" + capidate
				+ ", investtype=" + investtype + ", shoulddate=" + shoulddate + ", shouldcapi=" + shouldcapi
				+ ", shouldinvesttype=" + shouldinvesttype + "]";
	}
	
	
}
