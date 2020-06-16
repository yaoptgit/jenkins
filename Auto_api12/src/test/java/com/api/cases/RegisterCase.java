package com.api.cases;


import org.testng.annotations.DataProvider;

import com.api.utils.CaseUtils;

public class RegisterCase extends BaseCase{

	@DataProvider(name = "datas")
	@Override
	public Object[][] datas() {
		Object[][] datas = CaseUtils.datas("1", cellNames);
		return datas;
	}
	
}
