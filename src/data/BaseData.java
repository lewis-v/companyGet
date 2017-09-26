package data;

public class BaseData<T> {
	private int code;
	private String runTime;
	private T data;
	private int count;
	
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getRunTime() {
		return runTime;
	}
	public void setRunTime(String runTime) {
		this.runTime = runTime;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	@Override
	public String toString() {
		return "BaseData [code=" + code + ", runTime=" + runTime + ", data=" + data + ", count=" + count + "]";
	}
	
	
	
}
