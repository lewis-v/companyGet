package data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AreaData<T> {
	private String id;
	private String areaname;
	private String shortname;
	private T child;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAreaname() {
		return areaname;
	}
	public void setAreaname(String areaname) {
		this.areaname = areaname;
	}
	public String getShortname() {
		return shortname;
	}
	public void setShortname(String shortname) {
		this.shortname = shortname;
	}
	public T getChild() {
		return child;
	}
	public void setChild(T child) {
		this.child = child;
	}
	@Override
	public String toString() {
		return "AreaData [id=" + id + ", areaname=" + areaname + ", shortname=" + shortname + ", child=" + child + "]";
	}
	
	
}
