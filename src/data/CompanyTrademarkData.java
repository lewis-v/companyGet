package data;

public class CompanyTrademarkData {
	private String _id;
	private String comguid;
	private String name;
	private String regno;
	private String imageurl;
	private String brandtype;
	private String applydate;
	private String starttime;
	private String endtime;
	private String addtime;
	private String uptime;
	private String status;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRegno() {
		return regno;
	}
	public void setRegno(String regno) {
		this.regno = regno;
	}
	public String getImageurl() {
		return imageurl;
	}
	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}
	public String getBrandtype() {
		return brandtype;
	}
	public void setBrandtype(String brandtype) {
		this.brandtype = brandtype;
	}
	public String getApplydate() {
		return applydate;
	}
	public void setApplydate(String applydate) {
		this.applydate = applydate;
	}
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "CompanyTrademarkData [_id=" + _id + ", comguid=" + comguid + ", name=" + name + ", regno=" + regno
				+ ", imageurl=" + imageurl + ", brandtype=" + brandtype + ", applydate=" + applydate + ", starttime="
				+ starttime + ", endtime=" + endtime + ", addtime=" + addtime + ", uptime=" + uptime + ", status="
				+ status + "]";
	}
	
	
}
