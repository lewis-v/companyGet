package data;

public class CompanyChangeData {
	private String changedate;
	private String projectname;
	private String beforecontent;
	private String aftercontent;
	public String getChangedate() {
		return changedate;
	}
	public void setChangedate(String changedate) {
		this.changedate = changedate;
	}
	public String getProjectname() {
		return projectname;
	}
	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}
	public String getBeforecontent() {
		return beforecontent;
	}
	public void setBeforecontent(String beforecontent) {
		this.beforecontent = beforecontent;
	}
	public String getAftercontent() {
		return aftercontent;
	}
	public void setAftercontent(String aftercontent) {
		this.aftercontent = aftercontent;
	}
	@Override
	public String toString() {
		return "CompanyChangeData [changedate=" + changedate + ", projectname=" + projectname + ", beforecontent="
				+ beforecontent + ", aftercontent=" + aftercontent + "]";
	}
	
	
}
