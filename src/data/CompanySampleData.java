package data;

public class CompanySampleData<T> {
	 private String regno;
	 private String capital;
	 private String shortstatus;
	 private String city;
	 private String comname;
	 private String addtime;
	 private String startdate;
	 private String address;
	 private String province;
     private T industry;
     private String opername;
     private String investnum;
     private String _id;
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
	@Override
	public String toString() {
		return "CompanySampleData [regno=" + regno + ", capital=" + capital + ", shortstatus=" + shortstatus + ", city="
				+ city + ", comname=" + comname + ", addtime=" + addtime + ", startdate=" + startdate + ", address="
				+ address + ", province=" + province + ", industry=" + industry + ", opername=" + opername
				+ ", investnum=" + investnum + ", _id=" + _id + "]";
	}

     
}
