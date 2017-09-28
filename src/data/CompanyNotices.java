package data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 法院通知
 * @author yw
 *
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class CompanyNotices {
	private String _id;
	private String comguid;
	private String type;
	private String date;
	private String court;
	private String content;
	private String people;
	private String create_time;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getCourt() {
		return court;
	}
	public void setCourt(String court) {
		this.court = court;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPeople() {
		return people;
	}
	public void setPeople(String people) {
		this.people = people;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
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
		return "CompanyNotices [_id=" + _id + ", comguid=" + comguid + ", type=" + type + ", date=" + date + ", court="
				+ court + ", content=" + content + ", people=" + people + ", create_time=" + create_time + ", uptime="
				+ uptime + ", status=" + status + "]";
	}
	
	
}
