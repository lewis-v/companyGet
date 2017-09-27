package data;
/**
 * 公司著作权
 * @author yw
 *
 */
public class CompanyCopyrightsData {
	 private String _id;
	 private String comguid;
	 private String name;
	 private String typename;
	 private String regno;
	 private String addtime;
	 private String uptime;
	 private String status;
	 private String version;
	 
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
	public String getTypename() {
		return typename;
	}
	public void setTypename(String typename) {
		this.typename = typename;
	}
	public String getRegno() {
		return regno;
	}
	public void setRegno(String regno) {
		this.regno = regno;
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
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	@Override
	public String toString() {
		return "CompanyCopyrightsData [_id=" + _id + ", comguid=" + comguid + ", name=" + name + ", typename="
				+ typename + ", regno=" + regno + ", addtime=" + addtime + ", uptime=" + uptime + ", status=" + status
				+ ", version=" + version + "]";
	}
	 
	 
	 
}
