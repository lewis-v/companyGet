package data;

public class CompanyInvestData<T,V> {
	private String _id;
	private T stockholders;
	private V invest;
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public T getStockholders() {
		return stockholders;
	}
	public void setStockholders(T stockholders) {
		this.stockholders = stockholders;
	}
	public V getInvest() {
		return invest;
	}
	public void setInvest(V invest) {
		this.invest = invest;
	}
	@Override
	public String toString() {
		return "CompanyInvestData [_id=" + _id + ", stockholders=" + stockholders + ", invest=" + invest + "]";
	}
	
	
}
