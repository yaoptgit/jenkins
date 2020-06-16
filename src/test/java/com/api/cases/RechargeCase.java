package com.api.cases;


import org.testng.annotations.DataProvider;

import com.api.utils.CaseUtils;

public class RechargeCase extends BaseCase {

	@DataProvider(name = "datas")
	@Override
	public Object[][] datas() {
		Object[][] datas = CaseUtils.datas("3", cellNames);
		return datas;
	}
	
}
