package data;
/**
 * 公司投资信息
 * @author yw
 *
 */
public class InvestData {
	private String comname;
	private String opername;
	private String startdate;
	private String registcapi;
	public String getComname() {
		return comname;
	}
	public void setComname(String comname) {
		this.comname = comname;
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
	public String getRegistcapi() {
		return registcapi;
	}
	public void setRegistcapi(String registcapi) {
		this.registcapi = registcapi;
	}
	@Override
	public String toString() {
		return "CompanyInvestData [comname=" + comname + ", opername=" + opername + ", startdate=" + startdate
				+ ", registcapi=" + registcapi + "]";
	}
	
	
}
