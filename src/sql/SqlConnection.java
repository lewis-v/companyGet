package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

public class SqlConnection {  
	//这里是SqlConnection 类  

	/* 
	 *java连接mysql数据库 
	 *1、加载驱动程序 
	 *2、数据库连接字符串"jdbc:mysql://localhost:3306/数据库名?" 
	 *3、数据库登录名 
	 *3、数据库登录密码 
	 */  

	private static final String URL="jdbc:mysql://localhost:3306/company?";//数据库连接字符串，这里的deom为数据库名  
	private static final String NAME="root";//登录名  
	private static final String PASSWORD="admin211";//密码  

	private Connection conn = null;
	private String log = "";//日志,断开连接时返回

	/**
	 * 连接数据库
	 */
	public void TheSqlConnection()  
	{  
		//1.加载驱动  
		try {  
			Class.forName("com.mysql.jdbc.Driver");  
		} catch (ClassNotFoundException e) {  
			System.out.println( "未能成功加载驱动程序，请检查是否导入驱动程序！\n");  
			//添加一个println，如果加载驱动异常，检查是否添加驱动，或者添加驱动字符串是否错误  
			e.printStackTrace();  
		} 
		try {  
			conn = DriverManager.getConnection(URL, NAME, PASSWORD);  
		} catch (SQLException e) {  
			log = log + "获取数据库连接失败！\n";  
			//添加一个println，如果连接失败，检查连接字符串或者登录名以及密码是否错误  
			e.printStackTrace();  
		}  


	}  

	/**
	 * 数据库关闭
	 */
	public String sqlClose(){
		if(conn!=null)  
		{  
			try {  
				conn.close();  
			} catch (SQLException e) {  
				// TODO Auto-generated catch block  
				e.printStackTrace();  
				conn=null;  
			}  
		}  
		System.out.println(log);
		return log;
	}

	/**
	 * 搜索数据库
	 * @param id 需要返回的数据列名","分隔
	 * @param where 筛选条件
	 * @param formName 表名
	 * @return
	 */
	public List<JSONObject> search(String id,String where,String formName){
		List<JSONObject> list = new ArrayList<JSONObject>();
		String[] idName = id.split(",");
		String sql = "select "+id+" from "+formName;
		if(where != null && where.length()>0){
			sql = sql +" where "+where;  
		}
		log = log + sql + "\n";
		try {
			ResultSet rs = conn.createStatement().executeQuery(sql);
			while (rs.next()) { 
				JSONObject js = new JSONObject();
				for (int i = 0;i<idName.length; i++){
					js.put(idName[i],rs.getString(i+1));
				}
				list.add(js);
			}  
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log = log + e.getMessage()+"\n";
			return list;
		}//创建数据对象 
		return list;
	}
	
	/**
	 * 插入数据
	 * @param data 插入的数据
	 * @param formName 插入的表名(可在此指定插入的列名)
	 * @return
	 */
	public boolean insert(String data,String formName){
		String sql = "insert into "+formName+" values"+data;
		int result = 0;
		try {
			Statement st = conn.createStatement();  
			result = st.executeUpdate(sql);
		} catch (SQLException e) {
			log = log + "ERR: "+sql +"\n"+e.getMessage()+"\n";
		}  
		if (result == 0){
			return false;
		}
		log = log + sql + "\n";
		System.out.println(log);
		return true;
	}
	
	/**
	 * 获取地区编号
	 * @param id 获取对应id的地区编号
	 * @return 返回area_id
	 */
	public String searchId(int id){
		String sql = "select area_id from area where _id=" +id;
		log = log + sql + "\n";
		try {
			ResultSet rs = conn.createStatement().executeQuery(sql);
			rs.next();
			return rs.getString(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log = log + e.getMessage()+"\n";
			return null;
		}//创建数据对象 
	}
	
	/**
	 * 修改数据库
	 * @param data 修改的数据及筛选条件  "set a="1" where b>0"
	 * @param formName 表名
	 * @return
	 */
	public boolean updata(String data,String formName){
		String sql = "updata "+formName+" "+data;
		int result = 0;
		try {
			Statement st = conn.createStatement();  
			result = st.executeUpdate(sql);
		} catch (SQLException e) {
			log = log + "ERR: "+sql +"\n"+e.getMessage()+"\n";
		}  
		if (result == 0){
			return false;
		}
		log = log + sql + "\n";
		System.out.println(log);
		return true;
	}
}  