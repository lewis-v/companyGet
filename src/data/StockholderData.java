package data;

public class StockholderData {
	 private String stockname;
	 private String stocktype;
	public String getStockname() {
		return stockname;
	}
	public void setStockname(String stockname) {
		this.stockname = stockname;
	}
	public String getStocktype() {
		return stocktype;
	}
	public void setStocktype(String stocktype) {
		this.stocktype = stocktype;
	}
	@Override
	public String toString() {
		return "StockholderData [stockname=" + stockname + ", stocktype=" + stocktype + "]";
	}
	 
	 

}
