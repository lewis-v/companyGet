package data;

public class CompanyExecuted {
	 private String casedate;
	 private String caseno;
	 private String status;
	 private String court;
	 private String target;
	public String getCasedate() {
		return casedate;
	}
	public void setCasedate(String casedate) {
		this.casedate = casedate;
	}
	public String getCaseno() {
		return caseno;
	}
	public void setCaseno(String caseno) {
		this.caseno = caseno;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCourt() {
		return court;
	}
	public void setCourt(String court) {
		this.court = court;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	@Override
	public String toString() {
		return "CompanyExecuted [casedate=" + casedate + ", caseno=" + caseno + ", status=" + status + ", court="
				+ court + ", target=" + target + "]";
	}
	 
	 
}
