package com.api.cases;

import org.testng.annotations.DataProvider;

import com.api.utils.CaseUtils;

public class LoginCase extends BaseCase{
	
	@DataProvider(name = "datas")
	@Override
	public Object[][] datas() {
		//获取login case而不是所有case
		Object[][] datas = CaseUtils.datas("2", cellNames);
		return datas;
	}
}
