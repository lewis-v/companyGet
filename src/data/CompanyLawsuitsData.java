package data;

public class CompanyLawsuitsData {
	private String _id;
	private String comguid;
	private String title;
	private String date;
	private String addtime;
	private String uptime;
	private String status;
	private String content;
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "CompanyLawsuitsData [_id=" + _id + ", comguid=" + comguid + ", title=" + title + ", date=" + date
				+ ", addtime=" + addtime + ", uptime=" + uptime + ", status=" + status + ", content=" + content + "]";
	}
	
	
}
