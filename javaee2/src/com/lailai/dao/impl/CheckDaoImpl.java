package com.lailai.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lailai.dao.CheckDao;
import com.lailai.entity.Check;
import com.lailai.util.HibernateUtils;

public class CheckDaoImpl implements CheckDao{
	private Logger logger = LoggerFactory.getLogger(CheckDaoImpl.class);
	@Override
	public boolean addBatch(List<Check> checkList) {
		
		if(checkList!=null&&checkList.size()>0){
			Transaction ts = null;
			try {
				Session currentSession = HibernateUtils.getCurrentSession();
				ts = currentSession.beginTransaction();
				for (int i = 0; i < checkList.size(); i++) {
					Check check = checkList.get(i);
					currentSession.save(check);
					// 批插入的对象立即写入数据库并释放内存
					if(i%2==0){
						currentSession.flush();
						currentSession.clear();
					}
					
				}
				ts.commit();
			} catch (HibernateException e) {
				logger.error("批量添加考勤数据失败，事务已经回滚！");
				e.printStackTrace();
				ts.rollback();
				return false;
			}finally{
				//资源关闭，由于是currentSession所以不需要关闭
			}
		}
		return true;
	}

}
