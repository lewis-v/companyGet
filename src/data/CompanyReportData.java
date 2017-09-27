package data;

public class CompanyReportData<T> {
	private String _id;
	private String comguid;
	private String reportyear;
	private String regno;
	private String comname;
	private String tel;
	private String zip;
	private String address;
	private String is_equity;
	private String is_invest;
	private String status;
	private String is_website;
	private String employes;
	private String addtime;
	private String uptime;
	private T partners;
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getComguid() {
		return comguid;
	}
	public void setComguid(String comguid) {
		this.comguid = comguid;
	}
	public String getReportyear() {
		return reportyear;
	}
	public void setReportyear(String reportyear) {
		this.reportyear = reportyear;
	}
	public String getRegno() {
		return regno;
	}
	public void setRegno(String regno) {
		this.regno = regno;
	}
	public String getComname() {
		return comname;
	}
	public void setComname(String comname) {
		this.comname = comname;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getIs_equity() {
		return is_equity;
	}
	public void setIs_equity(String is_equity) {
		this.is_equity = is_equity;
	}
	public String getIs_invest() {
		return is_invest;
	}
	public void setIs_invest(String is_invest) {
		this.is_invest = is_invest;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getIs_website() {
		return is_website;
	}
	public void setIs_website(String is_website) {
		this.is_website = is_website;
	}
	public String getEmployes() {
		return employes;
	}
	public void setEmployes(String employes) {
		this.employes = employes;
	}
	public String getAddtime() {
		return addtime;
	}
	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}
	public String getUptime() {
		return uptime;
	}
	public void setUptime(String uptime) {
		this.uptime = uptime;
	}
	public T getPartners() {
		return partners;
	}
	public void setPartners(T partners) {
		this.partners = partners;
	}
	@Override
	public String toString() {
		return "CompanyReportData [_id=" + _id + ", comguid=" + comguid + ", reportyear=" + reportyear + ", regno="
				+ regno + ", comname=" + comname + ", tel=" + tel + ", zip=" + zip + ", address=" + address
				+ ", is_equity=" + is_equity + ", is_invest=" + is_invest + ", status=" + status + ", is_website="
				+ is_website + ", employes=" + employes + ", addtime=" + addtime + ", uptime=" + uptime + ", partners="
				+ partners + "]";
	}
	
	
}
