package data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CompanyBusinessData <T,V> {
	private String _id;
	private String comname;
	private String econkind;
	private String regno;
	private String shortstatus;
	private String opername;
	private String startdate;
	private String termstart;
	private String termend;
	private String registcapi;
	private String scope;
    private T stockholders;
    private V employs;
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getComname() {
		return comname;
	}
	public void setComname(String comname) {
		this.comname = comname;
	}
	public String getEconkind() {
		return econkind;
	}
	public void setEconkind(String econkind) {
		this.econkind = econkind;
	}
	public String getRegno() {
		return regno;
	}
	public void setRegno(String regno) {
		this.regno = regno;
	}
	public String getShortstatus() {
		return shortstatus;
	}
	public void setShortstatus(String shortstatus) {
		this.shortstatus = shortstatus;
	}
	public String getOpername() {
		return opername;
	}
	public void setOpername(String opername) {
		this.opername = opername;
	}
	public String getStartdate() {
		return startdate;
	}
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	public String getTermstart() {
		return termstart;
	}
	public void setTermstart(String termstart) {
		this.termstart = termstart;
	}
	public String getTermend() {
		return termend;
	}
	public void setTermend(String termend) {
		this.termend = termend;
	}
	public String getRegistcapi() {
		return registcapi;
	}
	public void setRegistcapi(String registcapi) {
		this.registcapi = registcapi;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	public T getStockholders() {
		return stockholders;
	}
	public void setStockholders(T stockholders) {
		this.stockholders = stockholders;
	}
	public V getEmploys() {
		return employs;
	}
	public void setEmploys(V employs) {
		this.employs = employs;
	}
	@Override
	public String toString() {
		return "CompanyBusinessData [_id=" + _id + ", comname=" + comname + ", econkind=" + econkind + ", regno="
				+ regno + ", shortstatus=" + shortstatus + ", opername=" + opername + ", startdate=" + startdate
				+ ", termstart=" + termstart + ", termend=" + termend + ", registcapi=" + registcapi + ", scope="
				+ scope + ", stockholders=" + stockholders + ", employs=" + employs + "]";
	}

    
}
