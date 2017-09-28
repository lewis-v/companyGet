package cn.com.company.main;

import java.io.IOException;
import java.util.List;

import Api.Api;
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
import data.ContactinfoData;
import data.EmployData;
import data.IndustryData;
import data.InvestData;
import data.StockholderData;
import net.sf.json.JSONObject;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
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
			System.out.println("获取地区信息....");
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
			//			Api.getInstance().login();
			sql.sqlClose();
			sql.TheSqlConnection();
			for (int i = 1; (areaId = sql.searchId(i+""))!=null;i++){
				System.out.println("search areaId = "+i+";"+areaId+"\n");
				//根据地区筛选公司的请求体				
				RequestBody requestBody = new MultipartBody.Builder()
						.setType(MultipartBody.FORM)
						.addFormDataPart("area", areaId)
						.addFormDataPart("startdate", "0")
						.addFormDataPart("enddate", "0")
						.addFormDataPart("capistart", "0")
						.addFormDataPart("capiend", "0")
						.addFormDataPart("sort", "0")
						.build();
				//请求地区叶页数
				int page = 2;
				BaseData<List<CompanySampleData<List<IndustryData>,List<StockholderData>,ContactinfoData>>> companyList =
						Api.getInstance().getCompanySampleDataByIndustry(1+"",requestBody);
				List<CompanySampleData<List<IndustryData>,List<StockholderData>,ContactinfoData>> data;
				//如请求下一页的列数大于0，则继续获取
				while((data = Api.getInstance().getCompanySampleDataByIndustry(page+"",requestBody).getData())!=null 
						&& data.size()>0){
					page++;
					companyList.getData().addAll(data);
					if(data.size() < 15){
						return;
					}
				}
				Api.getInstance().login();
				int num = 0;
				for(CompanySampleData<List<IndustryData>,List<StockholderData>,ContactinfoData> company : companyList.getData()){
					try{
						System.out.println(companyList.getData().size() +" / "+num);
						i++;
						//公司信息
						BaseData<CompanyData> companyData = Api.getInstance().getCompanyDetail(company.get_id());
						sql.insert("('"+companyData.getData().get_id()+"','" +companyData.getData().getRegno()+"','" +companyData.getData().getRegistcapi()
								+"','" +companyData.getData().getShortstatus()+"','" +companyData.getData().getComname()+"','" +companyData.getData().getStartdate()
								+"','" +companyData.getData().getAddress()+"','" +companyData.getData().getOpername()+"','" +companyData.getData().getInvest()
								+"','" +companyData.getData().getEconkind()+"','" +companyData.getData().getScope()+"','" +companyData.getData().getUptime()
								+"','" +companyData.getData().getIs_attention()+"','" +companyData.getData().getPhonenumber()     +"')"
								, "company(company_id,regno,registcapi,shortstatus,comname,startdate,address,opername,invest,econkind,scope,uptime,is_attention,phonenumber)");
						//工商信息
						BaseData<CompanyBusinessData<List<StockholderData>,List<EmployData>>> businessData = Api.getInstance().getCompanyBusinessData(company.get_id());
						if(businessData.getData() != null){
							sql.insert("('"+businessData.getData().get_id()+"','"+businessData.getData().getComname()+"','"+businessData.getData().getEconkind()
									+"','"+businessData.getData().getRegno()+"','"+businessData.getData().getShortstatus()+"','"+businessData.getData().getOpername()
									+"','"+businessData.getData().getStartdate()+"','"+businessData.getData().getTermstart()+"','"+businessData.getData().getTermend()
									+"','"+businessData.getData().getRegistcapi()+"','"+businessData.getData().getScope() +"')"
									, "info(company_id,comname,econkind,regno,shortstatus,opername,startdate,termstart,termend,registcapi,scope)");
						}
						//年报信息
						BaseData<List<CompanyReportData<List<CompanyPartnersData>>>> reportData = Api.getInstance().getCompanyReport(company.get_id());
						if(reportData.getData() != null){
							for(CompanyReportData<List<CompanyPartnersData>> report : reportData.getData()){
								sql.insert("('"+report.get_id()+"','"+report.getComguid()+"','"+report.getReportyear()+"','"+report.getRegno()+"','"+report.getComname()
								+"','"+report.getTel()+"','"+report.getZip()+"','"+report.getAddress()+"','"+report.getIs_equity()+"','"+report.getIs_invest()
								+"','"+report.getStatus()+"','"+report.getIs_website()+"','"+report.getEmployes()+"','"+report.getAddtime()+"','"+report.getUptime() +"')"
								, "reports(company_id,comguid,reportyear,regno,comname,tel,zip,address,is_equity,is_invest,status,is_website,employes,addtime,uptime)");
								for(CompanyPartnersData partner : report.getPartners()){
									sql.insert("('"+report.get_id()+"','"+partner.getStockname()+"','"+partner.getRealcapi()+"','"+partner.getCapidate()+"','"+partner.getInvesttype()
									+"','"+partner.getShoulddate()+"','"+partner.getShouldcapi()+"','"+partner.getShouldinvesttype()           +"')"
									, "partners(company_id,stockname,realcapi,capidate,investtype,shoulddate,shouldcapi,shouldinvesttype)");
								}
							}
						}
						//变更信息
						BaseData<List<CompanyChangeData>> changeData = Api.getInstance().getCompanyChange(company.get_id());
						if(changeData.getData() !=null){
							for(CompanyChangeData change : changeData.getData()){
								sql.insert("('"+company.get_id()+"','"+change.getChangedate()+"','"+change.getProjectname()+"','"+change.getBeforecontent()+"','"+change.getAftercontent()+"')"
										, "changerecord(company_id,changedate,projectname,beforecontent,aftercontent)");
							}
						}
						//投资信息
						BaseData<CompanyInvestData<List<StockholderData>,List<InvestData>>> inverstData = Api.getInstance().getCompanyInverst(company.get_id()); 
						if(inverstData.getData() != null){
							if(inverstData.getData().getStockholders() != null){
								for(StockholderData stock : inverstData.getData().getStockholders()){
									sql.insert("('"+company.get_id()+"','"+stock.getStockname()+"','"+stock.getStocktype() +"')"
											, "stockholders(company_id,stockname,stocktype)");
								}
							}
							if(inverstData.getData().getInvest() != null){
								for(InvestData inverst : inverstData.getData().getInvest()){
									sql.insert("('"+company.get_id()+"','"+inverst.getComname()+"','"+inverst.getOpername()+"','"+inverst.getStartdate()+"','"+inverst.getRegistcapi() +"')"
											, "invest(company_id,comname,opername,startdate,registcapi)");
								}
							}
						}
						//被执行人信息
						BaseData<List<CompanyExecuted>> executedData = Api.getInstance().getCompanyExecuted(company.get_id());
						if(executedData.getData() != null){
							for(CompanyExecuted executed : executedData.getData()){
								sql.insert("('"+company.get_id()+"','"+executed.getCasedate()+"','"+executed.getCaseno()+"','"+executed.getStatus()+"','"+executed.getCourt()+"','"+executed.getTarget() +"')"
										, "executed(company_id,casedate,caseno,status,court,target)");	
							}
						}
						//法院判决信息
						BaseData<List<CompanyLawsuitsData>> lawsuitsData = Api.getInstance().getConpanyLawsuitsData(company.get_id());
						if(lawsuitsData.getData() !=null){
							for(CompanyLawsuitsData lawsuits : lawsuitsData.getData()){
								sql.insert("('"+company.get_id()+"','"+lawsuits.getComguid()+"','"+lawsuits.getTitle()+"','"+lawsuits.getDate()+"','"+lawsuits.getAddtime()+"','"+lawsuits.getUptime()
								+"','"+lawsuits.getStatus()+"','"+lawsuits.getContent() +"')"
								, "lawsuits(company_id,comguid,title,date,addtime,uptime,status,content)");
							}
						}
						//法院公告信息
						BaseData<List<CompanyNotices>> noticesData = Api.getInstance().getCompanyNotices(company.get_id());
						if(noticesData.getData() != null){
							for(CompanyNotices notices : noticesData.getData()){
								sql.insert("('"+company.get_id()+"','"+notices.getComguid()+"','"+notices.getType()+"','"+notices.getDate()+"','"+notices.getCourt()+"','"+notices.getContent()
								+"','"+notices.getPeople()+"','"+notices.getCreate_time()+"','"+notices.getUptime()+"','"+notices.getStatus()+"')"
								,"notices(company_id,comguid,type,date,court,content,people,create_time,uptime,status)");
							}
						}
						//商标信息
						BaseData<List<CompanyTrademarkData>> trademarkData = Api.getInstance().getCompanyTrademark(company.get_id());
						if(trademarkData.getData() != null){
							for(CompanyTrademarkData trademark : trademarkData.getData()){
								sql.insert("('"+company.get_id()+"','"+trademark.getComguid()+"','"+trademark.getName()+"','"+trademark.getRegno()+"','"+trademark.getImageurl()
								+"','"+trademark.getBrandtype()+"','"+trademark.getApplydate()+"','"+trademark.getStarttime()+"','"+trademark.getEndtime()+"','"+trademark.getUptime()+"','"+trademark.getStatus() +"')"
								, "trademark(company_id,comguid,name,regno,imageurl,brandtype,applydate,starttime,endtime,addtime,uptime,status)");
							}
						}
						//专利信息
						BaseData<List<CompanyPatentsData>> patentsData = Api.getInstance().getCompanyPatents(company.get_id());
						if(patentsData.getData() != null){
							for(CompanyPatentsData patents : patentsData.getData()){
								sql.insert("('"+company.get_id()+"','"+patents.getComguid()+"','"+patents.getPatentname()+"','"+patents.getPatenttype()+"','"+patents.getPublishdate()+"','"+patents.getRequestno()
								+"','"+patents.getBrief()+"','"+patents.getPatentowner()+"','"+patents.getPatentimg()+"','"+patents.getDesigner()+"','"+patents.getUptime()+"','"+patents.getApplydate()+"','"+patents.getAddtime() +"')"
								, "patents(company_id,comguid,patentname,patenttype,publishdate,requestno,brief,patentowner,patenting,designer,uptime,applydate,addtime)");
							}
						}

						//一般专利权信息
						BaseData<List<CompanyCopyrightsData>> copyData = Api.getInstance().getCompanySampleCopy(company.get_id());
						if(copyData.getData() != null){
							for(CompanyCopyrightsData copy : copyData.getData()){
								sql.insert("('"+company.get_id()+"','"+copy.getComguid()+"','"+copy.getName()+"','"+copy.getTypename()+"','"+copy.getRegno()+"','"+copy.getAddtime()+"','"+copy.getUptime()
								+"','"+copy.getStatus()+"','"+copy.getVersion()+"','"+copy.getApprovaldate()+"')"
								,"copyrights(company_id,comguid,name,typename,regno,addtime,uptime,status,version,approvaldate)");
							}
						}
						//软件专利权信息
						BaseData<List<CompanyCopyrightsData>> softCopy = Api.getInstance().getCompanySoftCopy(company.get_id());
						if(softCopy.getData() != null){
							for(CompanyCopyrightsData soft : softCopy.getData()){
								sql.insert("('"+company.get_id()+"','"+soft.getComguid()+"','"+soft.getName()+"','"+soft.getTypename()+"','"+soft.getRegno()+"','"+soft.getAddtime()+"','"+soft.getUptime()
								+"','"+soft.getStatus()+"','"+soft.getVersion()+"','"+soft.getApprovaldate()+"')"
								,"copyrights(company_id,comguid,name,typename,regno,addtime,uptime,status,version,approvaldate)");
							}
						}
					}catch(NullPointerException e){

					}

					sql.sqlClose();
					sql.TheSqlConnection();
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
