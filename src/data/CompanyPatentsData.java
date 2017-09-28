package data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CompanyPatentsData {
	private String _id;
	private String comguid;
	private String patentname;
	private String patenttype;
	private String publishdate;
	private String requestno;
	private String brief;
	private String patentowner;
	private String patentimg;
	private String designer;
	private String addtime;
	private String uptime;
	private String applydate;
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
	public String getPatentname() {
		return patentname;
	}
	public void setPatentname(String patentname) {
		this.patentname = patentname;
	}
	public String getPatenttype() {
		return patenttype;
	}
	public void setPatenttype(String patenttype) {
		this.patenttype = patenttype;
	}
	public String getPublishdate() {
		return publishdate;
	}
	public void setPublishdate(String publishdate) {
		this.publishdate = publishdate;
	}
	public String getRequestno() {
		return requestno;
	}
	public void setRequestno(String requestno) {
		this.requestno = requestno;
	}
	public String getBrief() {
		return brief;
	}
	public void setBrief(String brief) {
		this.brief = brief;
	}
	public String getPatentowner() {
		return patentowner;
	}
	public void setPatentowner(String patentowner) {
		this.patentowner = patentowner;
	}
	public String getPatentimg() {
		return patentimg;
	}
	public void setPatentimg(String patentimg) {
		this.patentimg = patentimg;
	}
	public String getDesigner() {
		return designer;
	}
	public void setDesigner(String designer) {
		this.designer = designer;
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
	public String getApplydate() {
		return applydate;
	}
	public void setApplydate(String applydate) {
		this.applydate = applydate;
	}
	@Override
	public String toString() {
		return "CompanyPatentsData [_id=" + _id + ", comguid=" + comguid + ", patentname=" + patentname
				+ ", patenttype=" + patenttype + ", publishdate=" + publishdate + ", requestno=" + requestno
				+ ", brief=" + brief + ", patentowner=" + patentowner + ", patentimg=" + patentimg + ", designer="
				+ designer + ", addtime=" + addtime + ", uptime=" + uptime + ", applydate=" + applydate + "]";
	}
	
	
}
