package Api;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import data.AreaData;
import data.AreaVersion;
import data.BaseData;
import data.CompanyBusinessData;
import data.CompanyData;
import data.CompanySampleData;
import data.EmployData;
import data.StockholderData;
import data.TimeData;
import data.UserData;
import net.sf.json.JSONObject;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import utils.MD5;

public class Api {
	public static final MediaType JSON = MediaType.parse("application/json;charset=utf-8");
	public static final String HOST = "http://api.xizhi.com";
	public static int DIFFERENCE_TIME = 0;//与服务器的时间差
	public static String UID = "0";//用户登录的uid

	public static String httpGet(String url,String uid) throws IOException {
		long time = System.currentTimeMillis()/1000 - DIFFERENCE_TIME;
		String token = MD5.md5(url+uid+time+"35c41a91").substring(8,16).toLowerCase();
		OkHttpClient httpClient = new OkHttpClient();
		Request request = new Request.Builder()
				.addHeader("token", token)
				.addHeader("timestamp", time+"")
				.addHeader("dev-type", "2")
				.addHeader("uid",uid)
				.addHeader("app-version", "1.1.1")
				.url(HOST+url)
				.build();
		Response response = httpClient.newCall(request).execute();
		return response.body().string(); // 返回的是string 类型，json的mapper可以直接处理
	}

	public static String httpPost(String url,String uid, String json) throws IOException {
		long time = System.currentTimeMillis()/1000 - DIFFERENCE_TIME;
		String token = MD5.md5(url+uid+time+"35c41a91").substring(8,16).toLowerCase();
		OkHttpClient httpClient = new OkHttpClient();
		RequestBody requestBody = RequestBody.create(JSON, json);
		Request request = new Request.Builder()
				.addHeader("token", token)
				.addHeader("timestamp", time+"")
				.addHeader("dev-type", "2")
				.addHeader("uid",uid)
				.addHeader("app-version", "1.1.1")
				.url(HOST+url)
				.post(requestBody)
				.build();
		Response response = httpClient.newCall(request).execute();
		return response.body().string();
	}
	
	public static String httpPostByPage(String url,String uid, String page,String json) throws IOException {
		long time = System.currentTimeMillis()/1000 - DIFFERENCE_TIME;
		String token = MD5.md5(url+uid+time+"35c41a91").substring(8,16).toLowerCase();
		OkHttpClient httpClient = new OkHttpClient();
		RequestBody requestBody = RequestBody.create(JSON, json);
		Request request = new Request.Builder()
				.addHeader("token", token)
				.addHeader("timestamp", time+"")
				.addHeader("dev-type", "2")
				.addHeader("uid",uid)
				.addHeader("app-version", "1.1.1")
				.addHeader("X-Pagination-Request-Page", page)
				.addHeader("X-Pagination-Request-PageSiz", "15")
				.url(HOST+url)
				.post(requestBody)
				.build();
		Response response = httpClient.newCall(request).execute();
		return response.body().string();
	}

	@SuppressWarnings("rawtypes")
	/**
	 * 获取地区信息
	 * @return
	 * @throws IOException
	 */
	public static BaseData<AreaVersion<List<AreaData>>> getAreaData() throws IOException{
		ObjectMapper mapper = new ObjectMapper(); 
		BaseData<AreaVersion<List<AreaData>>> data = mapper.readValue( httpGet("/v1/area/0","0")
				, new TypeReference<BaseData<AreaVersion<List<AreaData>>>>(){});
		return data;
	}
	
	/**
	 * 与服务器对准时间
	 * @throws IOException
	 */
	public static BaseData<TimeData> getSysTime() throws IOException{
		ObjectMapper mapper = new ObjectMapper();
		BaseData<TimeData> data = mapper.readValue(httpGet("/v1/timestamp","0")
				,new TypeReference<BaseData<UserData>>(){});
		DIFFERENCE_TIME = (int) (data.getData().getTimestamp() - System.currentTimeMillis()/1000);
		return data;
	}
	
	/**
	 * 登录
	 * @return
	 * @throws IOException
	 */
	public static BaseData<UserData> login() throws IOException{
		JSONObject json = new JSONObject();
		json.put("mobile", "15622106207");
		json.put("password", "778899");
		ObjectMapper mapper = new ObjectMapper();
		BaseData<UserData> data = mapper.readValue(httpPost("/v1/user/login","0",json.toString())
				,new TypeReference<BaseData<UserData>>(){});
		UID = data.getData().getUid();
		return data;
	}
	
	/**
	 * 通过行业/地区筛选公司
	 * @param page 页
	 * @param json 消息体
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("rawtypes")
	public static BaseData<List<CompanySampleData>> getCompanySampleDataByIndustry(String page,String json) throws IOException{
		ObjectMapper mapper = new ObjectMapper();
		BaseData<List<CompanySampleData>> data = mapper.readValue(httpPostByPage("/v1/search/region",UID,page,json)
				,new TypeReference<BaseData<List<CompanySampleData>>>(){});
		return data;
	}
	
	/**
	 * 通过id获取公司信息
	 * @param id 公司id
	 * @return
	 * @throws IOException
	 */
	public static BaseData<CompanyData> getCompanyDetail(String id) throws IOException{
		ObjectMapper mapper = new ObjectMapper();
		BaseData<CompanyData> data = mapper.readValue(httpGet("/v1/company/"+id,UID)
				,new TypeReference<BaseData<CompanyData>>(){});
		return data;
	}
	
	/**
	 * 通过id获取公司工商信息
	 * @param id 公司id
	 * @return
	 * @throws IOException
	 */
	public static BaseData<CompanyBusinessData<List<StockholderData>,List<EmployData>>> getCompanyBusinessData(String id) throws IOException{
		ObjectMapper mapper = new ObjectMapper();
		BaseData<CompanyBusinessData<List<StockholderData>,List<EmployData>>> data = mapper.readValue(httpGet("/v1/company/info/"+id,UID)
				,new TypeReference<BaseData<CompanyBusinessData<List<StockholderData>,List<EmployData>>>>(){});
		return data;
	}
	
	
	
	
}
