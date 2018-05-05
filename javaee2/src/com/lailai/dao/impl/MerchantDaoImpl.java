package com.lailai.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.lailai.common.enums.StateEnum;
import com.lailai.dao.MerchantDao;
import com.lailai.entity.Merchant;
import com.lailai.entity.Teacher;
import com.lailai.entity.User;
import com.lailai.util.HibernateUtils;

public class MerchantDaoImpl implements MerchantDao{

	@Override
	public Merchant findMerchant(Merchant merchant) {
		Session currentSession = HibernateUtils.getCurrentSession();
		Transaction ts = currentSession.beginTransaction();
		Query query = currentSession.createQuery("from Merchant where name=:name and password=:password and state=:state");
		query.setParameter("name", merchant.getName());
		query.setParameter("password",merchant.getPassword());
		query.setParameter("state", StateEnum.merchantAble.state);
		List list = query.list();
		ts.commit();
		if(list.size()>0){
			return (Merchant)list.get(0);
		}
		return null;
	}

	@Override
	public void changePwd(Merchant merchant) {
		Session currentSession = HibernateUtils.getCurrentSession();
		Transaction ts = currentSession.beginTransaction();
		Merchant merchant2 = currentSession.get(Merchant.class, merchant.getId());
		merchant2.setPassword(merchant.getPassword());
		ts.commit();
	}




}
