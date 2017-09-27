package Api;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import data.AreaData;
import data.AreaVersion;
import data.BaseData;
import data.CompanyBusinessData;
import data.CompanyChangeData;
import data.CompanyCopyrightsData;
import data.CompanyData;
import data.CompanyExecuted;
import data.CompanyInvestData;
import data.CompanyLawsuitsData;
import data.CompanyNotices;
import data.CompanyPartnersData;
import data.CompanyPatentsData;
import data.CompanyReportData;
import data.CompanySampleData;
import data.CompanyTrademarkData;
import data.EmployData;
import data.IndustryData;
import data.InvestData;
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
	private static Api api;
	
	public static Api getInstance(){//获取单例对象
		if(api == null){
			api = new Api();
		}
		return api;
	}

	public String httpGet(String url,String uid) throws IOException {
		long time = System.currentTimeMillis()/1000 - DIFFERENCE_TIME;
		String token = MD5.md5(url+uid+time+"35c41a91").substring(8,16).toLowerCase();
		System.out.println("token:"+token);
		OkHttpClient httpClient = new OkHttpClient();
		Request request = new Request.Builder()
				.addHeader("token", token)
				.addHeader("timestamp", time+"")
				.addHeader("dev-type", "2")
				.addHeader("uid",uid)
				.addHeader("app-version", "1.1.1")
				.url(HOST+url)
				.build();
		System.out.println("Get:等待回复");
		Response response = httpClient.newCall(request).execute();
		System.out.println("GET:数据转换");
		return response.body().string(); // 返回的是string 类型，json的mapper可以直接处理
	}

	public String httpPost(String url,String uid, String json) throws IOException {
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
		System.out.println("POST:等待回复");
		Response response = httpClient.newCall(request).execute();
		System.out.println("POST:数据转换");
		return response.body().string();
	}
	
	public String httpPostByPage(String url,String uid, String page,String json) throws IOException {
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
		System.out.println("POST:等待回复");
		Response response = httpClient.newCall(request).execute();
		System.out.println("POST:数据转换");
		return response.body().string();
	}

	@SuppressWarnings("rawtypes")
	/**
	 * 获取地区信息
	 * @return
	 * @throws IOException
	 */
	public BaseData<AreaVersion<List<AreaData>>> getAreaData() throws IOException{
		ObjectMapper mapper = new ObjectMapper(); 
		BaseData<AreaVersion<List<AreaData>>> data = mapper.readValue( httpGet("/v1/area/0","0")
				, new TypeReference<BaseData<AreaVersion<List<AreaData>>>>(){});
		return data;
	}
	
	/**
	 * 与服务器对准时间
	 * @throws IOException
	 */
	public BaseData<TimeData> getSysTime() throws IOException{
		ObjectMapper mapper = new ObjectMapper();
		BaseData<TimeData> data = mapper.readValue(httpGet("/v1/timestamp","0")
				,new TypeReference<BaseData<TimeData>>(){});
		DIFFERENCE_TIME = (int) (data.getData().getTimestamp() - System.currentTimeMillis()/1000);
		return data;
	}
	
	/**
	 * 登录
	 * @return
	 * @throws IOException
	 */
	public BaseData<UserData> login() throws IOException{
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
	public BaseData<List<CompanySampleData<List<IndustryData>>>> getCompanySampleDataByIndustry(String page,String json) throws IOException{
		ObjectMapper mapper = new ObjectMapper();
		BaseData<List<CompanySampleData<List<IndustryData>>>> data = mapper.readValue(httpPostByPage("/v1/search/region",UID,page,json)
				,new TypeReference<BaseData<List<CompanySampleData<List<IndustryData>>>>>(){});
		return data;
	}
	
	/**
	 * 通过id获取公司信息
	 * @param id 公司id
	 * @return
	 * @throws IOException
	 */
	public BaseData<CompanyData> getCompanyDetail(String id) throws IOException{
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
	public BaseData<CompanyBusinessData<List<StockholderData>,List<EmployData>>> getCompanyBusinessData(String id) throws IOException{
		ObjectMapper mapper = new ObjectMapper();
		BaseData<CompanyBusinessData<List<StockholderData>,List<EmployData>>> data = mapper.readValue(httpGet("/v1/company/info/"+id,UID)
				,new TypeReference<BaseData<CompanyBusinessData<List<StockholderData>,List<EmployData>>>>(){});
		return data;
	}
	
	/**
	 * 通过id获取公司年报信息
	 * @param id
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public BaseData<List<CompanyReportData<List<CompanyPartnersData>>>> getCompanyReport(String id) throws JsonParseException, JsonMappingException, IOException{
		ObjectMapper mapper = new ObjectMapper();
		BaseData<List<CompanyReportData<List<CompanyPartnersData>>>> data = mapper.readValue(httpGet("/v1/company/reports/"+id,UID)
				,new TypeReference<BaseData<List<CompanyReportData<List<CompanyPartnersData>>>>>(){});
		return data;
	} 
	
	/**
	 *  通过id获取公司变更信息
	 * @param id 公司id
	 * @return
	 * @throws IOException
	 */
	public BaseData<List<CompanyChangeData>> getCompanyChange(String id) throws IOException{
		ObjectMapper mapper = new ObjectMapper();
		BaseData<List<CompanyChangeData>> data = mapper.readValue(httpGet("/v1/company/changerecord/"+id,UID)
				,new TypeReference<BaseData<List<CompanyChangeData>>>(){});
		return data;
	}
	
	/**
	 * 通过id获取公司投资信息
	 * @param id 公司id
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public BaseData<CompanyInvestData<List<StockholderData>,List<InvestData>>> getCompanyInverst(String id) throws JsonParseException, JsonMappingException, IOException{
		ObjectMapper mapper = new ObjectMapper();
		BaseData<CompanyInvestData<List<StockholderData>,List<InvestData>>> data = mapper.readValue(httpGet("/v1/company/invest/"+id,UID)
				,new TypeReference<BaseData<CompanyInvestData<List<StockholderData>,List<InvestData>>>>(){});
		return data;
	}
	
	/**
	 * 通过id获取公司被执行人
	 * @param id 公司id
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public BaseData<List<CompanyExecuted>> getCompanyExecuted(String id) throws JsonParseException, JsonMappingException, IOException{
		ObjectMapper mapper = new ObjectMapper();
		BaseData<List<CompanyExecuted>> data = mapper.readValue(httpGet("/v1/company/executed/"+id,UID)
				,new TypeReference<BaseData<List<CompanyExecuted>>>(){});
		return data;
	}
	
	/**
	 * 通过id获取公司法院判决信息
	 * @param id 公司id
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public BaseData<List<CompanyLawsuitsData>> getConpanyLawsuitsData(String id) throws JsonParseException, JsonMappingException, IOException{
		ObjectMapper mapper = new ObjectMapper();
		BaseData<List<CompanyLawsuitsData>> data = mapper.readValue(httpGet("/v1/company/lawsuits/"+id,UID)
				,new TypeReference<BaseData<List<CompanyLawsuitsData>>>(){});
		return data;
	}
	
	/**
	 * 通过id获取公司法院通知
	 * @param id 公司id
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public BaseData<List<CompanyNotices>> getCompanyNotices(String id) throws JsonParseException, JsonMappingException, IOException{
		ObjectMapper mapper = new ObjectMapper();
		BaseData<List<CompanyNotices>> data = mapper.readValue(httpGet("/v1/company/notices/"+id,UID)
				,new TypeReference<BaseData<List<CompanyNotices>>>(){});
		return data;
	}
	
	/**
	 * 通过id获取公司商标信息
	 * @param id 公司id
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public BaseData<List<CompanyTrademarkData>> getCompanyTrademark(String id) throws JsonParseException, JsonMappingException, IOException{
		ObjectMapper mapper = new ObjectMapper();
		BaseData<List<CompanyTrademarkData>> data = mapper.readValue(httpGet("/v1/company/trademark/"+id,UID)
				,new TypeReference<BaseData<List<CompanyTrademarkData>>>(){});
		return data;
	}
	
	/**
	 * 通过id获取公司专利信息
	 * @param id 公司id
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public BaseData<List<CompanyPatentsData>> getCompanyPatents(String id) throws JsonParseException, JsonMappingException, IOException{
		ObjectMapper mapper = new ObjectMapper();
		BaseData<List<CompanyPatentsData>> data = mapper.readValue(httpGet("/v1/company/copyrights/"+id,UID)
				,new TypeReference<BaseData<List<CompanyPatentsData>>>(){});
		return data;
	}
	
	/**
	 * 通过id获取公司一般著作权
	 * @param id 公司id
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public BaseData<List<CompanyCopyrightsData>> getCompanySampleCopy(String id) throws JsonParseException, JsonMappingException, IOException{
		ObjectMapper mapper = new ObjectMapper();
		BaseData<List<CompanyCopyrightsData>> data = mapper.readValue(httpGet("/v1/company/copyrights/"+id,UID)
				,new TypeReference<BaseData<List<CompanyCopyrightsData>>>(){});
		return data;
	}
	
	/**
	 * 通过id获取公司软件著作权
	 * @param id 公司id
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public BaseData<List<CompanyCopyrightsData>> getCompanySoftCopy(String id) throws JsonParseException, JsonMappingException, IOException{
		ObjectMapper mapper = new ObjectMapper();
		BaseData<List<CompanyCopyrightsData>> data = mapper.readValue(httpGet("/v1/company/softwareright/"+id,UID)
				,new TypeReference<BaseData<List<CompanyCopyrightsData>>>(){});
		return data;
	}
	
}
