package cn.com.company.main;

import java.io.IOException;
import java.util.List;

import Api.Api;
import data.AreaData;
import data.AreaVersion;
import data.BaseData;
import data.CompanyData;
import data.CompanySampleData;
import data.IndustryData;
import net.sf.json.JSONObject;
import sql.SqlConnection;

public class Main {

	public static void main(String[] args) {
		System.out.println("start......");
		String areaId = null;
		SqlConnection sql = null;
		try {
			Api.getInstance().getSysTime();
			System.out.println("对准服务器时间....");
			BaseData<AreaVersion<List<AreaData>>> areaData = Api.getInstance().getAreaData();
			System.out.println("获取地区信息...."+areaData.toString());
			sql = new SqlConnection();
			sql.TheSqlConnection();//初始化数据库连接
			List<JSONObject> js = sql.search("area_version",null,"version");//获取地区版本
			if(js.size() > 0 && js.get(0).containsKey("area_version") 
					&& !js.get(0).getString("area_version").equals(areaData.getData().getVersion())){
				for (AreaData data : areaData.getData().getData()){
					System.out.println("insert");
					sql.insert("('"+ data.getId()+"','"+data.getAreaname()+"','"+data.getShortname() +"')", "area(area_id,area_name,short_name)");
				}
				sql.updata("set area_version='"+areaData.getData().getVersion()+"'", "version");
				System.out.println("更新地区数据");
			}else{
				System.out.println("地址数据已是最新版");
			}
			Api.getInstance().login();
			
			for (int i = 1; (areaId = sql.searchId(i+""))!=null;){
				System.out.println("search areaId = "+i+":\n");
				//根据地区筛选公司的请求体
				JSONObject jsCompany = new JSONObject();
				jsCompany.put("area", areaId);
				jsCompany.put("startdate", "0");
				jsCompany.put("enddate", "0");
				jsCompany.put("capistart", "0");
				jsCompany.put("capiend", "0");
				jsCompany.put("sort", "0");
				//请求地区叶页数
				int page = 1;
				BaseData<List<CompanySampleData<List<IndustryData>>>> companyList =
						 Api.getInstance().getCompanySampleDataByIndustry(page+"",jsCompany.toString());
				//如请求的总数大于目前列表数,则继续下一页获取
				while(companyList.getCount() > companyList.getData().size()){
					page++;
					companyList.getData().addAll(
							Api.getInstance().getCompanySampleDataByIndustry(page+"",jsCompany.toString()).getData());
				}
				for(CompanySampleData<List<IndustryData>> company : companyList.getData()){
					BaseData<CompanyData> companyData = Api.getInstance().getCompanyDetail(company.get_id());
					sql.insert("('"+companyData.getData().get_id()+"','" +companyData.getData().getRegno()+"','" +companyData.getData().getRegistcapi()
							+"','" +companyData.getData().getShortstatus()+"','" +companyData.getData().getComname()+"','" +companyData.getData().getStartdate()
							+"','" +companyData.getData().getAddress()+"','" +companyData.getData().getOpername()+"','" +companyData.getData().getInvest()
							+"','" +companyData.getData().getEconkind()+"','" +companyData.getData().getScope()+"','" +companyData.getData().getUptime()
							+"','" +companyData.getData().getIs_attention()+"','" +companyData.getData().getPhonenumber()+"','"                                      +")"
							, "company(company_id,regno,registcapi,shortstatus,comname,startdate,address,opername,invest,econkind,scope,uptime,is_attention,phonenumber)");
				}
				
				
			}		
			sql.sqlClose();
		} catch (IOException e) {
			System.out.println("出错"+areaId);
			e.printStackTrace();
			sql.sqlClose();
		}
	}

}
