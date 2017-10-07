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
	//������SqlConnection ��  

	/* 
	 *java����mysql���ݿ� 
	 *1�������������� 
	 *2�����ݿ������ַ���"jdbc:mysql://localhost:3306/���ݿ���?" 
	 *3�����ݿ��¼�� 
	 *3�����ݿ��¼���� 
	 */  

	private static final String URL="jdbc:mysql://localhost:3306/company?";//���ݿ������ַ����������deomΪ���ݿ���  
	private static final String NAME="root";//��¼��  
	private static final String PASSWORD="admin211";//����  

	private Connection conn = null;
	private String log = "";//��־,�Ͽ�����ʱ����

	/**
	 * �������ݿ�
	 */
	public void TheSqlConnection()  
	{  
		//1.��������  
		try {  
			Class.forName("com.mysql.jdbc.Driver");  
		} catch (ClassNotFoundException e) {  
			System.out.println( "δ�ܳɹ������������������Ƿ�����������\n");  
			//���һ��println��������������쳣������Ƿ����������������������ַ����Ƿ����  
			e.printStackTrace();  
		} 
		try {  
			conn = DriverManager.getConnection(URL, NAME, PASSWORD);  
		} catch (SQLException e) {  
			log = log + "��ȡ���ݿ�����ʧ�ܣ�\n";  
			//���һ��println���������ʧ�ܣ���������ַ������ߵ�¼���Լ������Ƿ����  
			e.printStackTrace();  
		}  


	}  

	/**
	 * ���ݿ�ر�
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
	 * �������ݿ�
	 * @param id ��Ҫ���ص���������","�ָ�
	 * @param where ɸѡ����
	 * @param formName ����
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
		}//�������ݶ��� 
		return list;
	}
	
	/**
	 * ��������
	 * @param data ���������
	 * @param formName ����ı���(���ڴ�ָ�����������)
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
	 * ��ȡ�������
	 * @param id ��ȡ��Ӧid�ĵ������
	 * @return ����area_id
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
		}//�������ݶ��� 
	}
	
	/**
	 * �޸����ݿ�
	 * @param data �޸ĵ����ݼ�ɸѡ����  "set a="1" where b>0"
	 * @param formName ����
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