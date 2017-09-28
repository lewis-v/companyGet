package data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CompanySampleData<T,V,C> {
	 private String regno;
	 private String capital;
	 private String shortstatus;
	 private String city;
	 private String comname;
	 private String addtime;
	 private String startdate;
	 private String address;
	 private String province;
	 private String registcapi;
     private T industry;
     private String opername;
     private String investnum;
     private String _id;
     private V stockholders;
     private C contactinfo;
     private String logo;
	public String getRegno() {
		return regno;
	}
	public void setRegno(String regno) {
		this.regno = regno;
	}
	public String getCapital() {
		return capital;
	}
	public void setCapital(String capital) {
		this.capital = capital;
	}
	public String getShortstatus() {
		return shortstatus;
	}
	public void setShortstatus(String shortstatus) {
		this.shortstatus = shortstatus;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getComname() {
		return comname;
	}
	public void setComname(String comname) {
		this.comname = comname;
	}
	public String getAddtime() {
		return addtime;
	}
	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}
	public String getStartdate() {
		return startdate;
	}
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public T getIndustry() {
		return industry;
	}
	public void setIndustry(T industry) {
		this.industry = industry;
	}
	public String getOpername() {
		return opername;
	}
	public void setOpername(String opername) {
		this.opername = opername;
	}
	public String getInvestnum() {
		return investnum;
	}
	public void setInvestnum(String investnum) {
		this.investnum = investnum;
	}
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public V getStockholders() {
		return stockholders;
	}
	public void setStockholders(V stockholders) {
		this.stockholders = stockholders;
	}
	public C getContactinfo() {
		return contactinfo;
	}
	public void setContactinfo(C contactinfo) {
		this.contactinfo = contactinfo;
	}
	public String getRegistcapi() {
		return registcapi;
	}
	public void setRegistcapi(String registcapi) {
		this.registcapi = registcapi;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	@Override
	public String toString() {
		return "CompanySampleData [regno=" + regno + ", capital=" + capital + ", shortstatus=" + shortstatus + ", city="
				+ city + ", comname=" + comname + ", addtime=" + addtime + ", startdate=" + startdate + ", address="
				+ address + ", province=" + province + ", registcapi=" + registcapi + ", industry=" + industry
				+ ", opername=" + opername + ", investnum=" + investnum + ", _id=" + _id + ", stockholders="
				+ stockholders + ", contactinfo=" + contactinfo + ", logo=" + logo + "]";
	}

     
}
