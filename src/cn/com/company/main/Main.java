package cn.com.company.main;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import data.AreaData;
import data.AreaVersion;
import data.BaseData;

public class Main {

	public static void main(String[] args) {
		while(true){
		try {
			String test = Api.Api.httpGet("/v1/area/0","0");
			ObjectMapper mapper = new ObjectMapper(); 
			@SuppressWarnings("rawtypes")
			BaseData<AreaVersion<List<AreaData>>> data = mapper.readValue(test
					, new TypeReference<BaseData<AreaVersion<List<AreaData>>>>(){});
			System.out.println(data.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}

}
