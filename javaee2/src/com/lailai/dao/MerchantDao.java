package com.lailai.dao;

import com.lailai.entity.Merchant;
import com.lailai.entity.Teacher;
import com.lailai.entity.User;

public interface MerchantDao {
	/**
	 * 从商家表中查询商家 条件账户、密码、状态
	 * @param merchant
	 * @return
	 */
	public abstract Merchant findMerchant(Merchant merchant);
	
	/**
	 * 
	 */
	public void changePwd(Merchant merchant);

}
