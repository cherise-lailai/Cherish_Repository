package com.lailai.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.lailai.common.enums.CheckStateEnum;
import com.lailai.common.enums.Permission;
import com.lailai.common.enums.ReStudyCourse;
import com.lailai.dao.ReStuCourseDao;
import com.lailai.dao.ReStudyDao;
import com.lailai.dao.impl.ReStuCourseDaoImpl;
import com.lailai.dao.impl.ReStudyDaoImpl;
import com.lailai.dto.ReStuNoticeDto;
import com.lailai.entity.Check;
import com.lailai.entity.ReStuCourse;
import com.lailai.entity.ReStudy;
import com.lailai.entity.User;
import com.lailai.service.CheckService;
import com.lailai.service.ReStudyService;

public class ReStudyServiceImpl implements ReStudyService{
	private ReStudyDao reStudyDao=new ReStudyDaoImpl();
	private ReStuCourseDao reStuCourseDao=new ReStuCourseDaoImpl();
	private CheckService checkService=new CheckServiceImpl();
	
	@Override
	public List<String> getReStudyList(DetachedCriteria dc) {
		Date beginTime = null;
		Date endTime = null;
		Calendar c = Calendar.getInstance();
		int i = c.get(Calendar.DAY_OF_MONTH);
		System.out.println(i);
		if (i > 0 && i < 16) {
			c.set(Calendar.DAY_OF_MONTH, 1);
			beginTime = c.getTime();
			System.out.println(beginTime);
			c.set(Calendar.DAY_OF_MONTH, 15);
			endTime = c.getTime();
			System.out.println(endTime);
		} else {
			c.set(Calendar.DAY_OF_MONTH, 16);
			beginTime = c.getTime();
			System.out.println(beginTime);
			c.set(Calendar.DAY_OF_MONTH, 1);
			c.roll(Calendar.DATE, -1);
			endTime = c.getTime();
			System.out.println(endTime);
		}
		dc.add(Restrictions.between("checkDate", beginTime, endTime));
		dc.add(Restrictions.eq("checkState",CheckStateEnum.askForLeave.state));
		List<String> reStudyCourseList = reStudyDao.getReStudyCourseList(dc);
		return reStudyCourseList;
	}
	
	@Override
	public boolean addReStuCourse(ReStuCourse reStuCourse) {
		//添加期度与状态
		Calendar c = Calendar.getInstance();
		int month = c.get(Calendar.MONDAY)+1;
		int dayIndex = c.get(Calendar.DAY_OF_MONTH);
		String periodStr=""+month+"月的";
		if(dayIndex>0&&dayIndex<16){
			periodStr+="上半月";
		}else{
			periodStr+="下半月";
		}
		reStuCourse.setPeriod(periodStr);
		reStuCourse.setState(ReStudyCourse.reStuCourseAble.state);
		boolean isOk = reStuCourseDao.insertReStuCourse(reStuCourse);
		
		
		//找出该半月内需要补课的课程
		DetachedCriteria dc = DetachedCriteria.forClass(Check.class);
		List<String> reStudyList = getReStudyList(dc);
		
		
		
		//直接使用reStuCourse
		String course=reStuCourse.getCourseName();
//		for (String course : reStudyList) {
			//根据补课课程找到需要补课的学生
			
			//1、获得时间段
			Date beginTime = null;
			Date endTime = null;
			Calendar cal = Calendar.getInstance();
			int i = cal.get(Calendar.DAY_OF_MONTH);
			System.out.println(i);
			if (i > 0 && i < 16) {
				cal.set(Calendar.DAY_OF_MONTH, 1);
				beginTime = cal.getTime();
				System.out.println(beginTime);
				cal.set(Calendar.DAY_OF_MONTH, 15);
				endTime = cal.getTime();
				System.out.println(endTime);
			} else {
				cal.set(Calendar.DAY_OF_MONTH, 16);
				beginTime = cal.getTime();
				System.out.println(beginTime);
				cal.set(Calendar.DATE, 1); 
				cal.roll(Calendar.DATE, -1);
				endTime = cal.getTime();
				System.out.println(endTime);
			}
			//条件：时间段+courseName+checkState(4)
			DetachedCriteria dc2 = DetachedCriteria.forClass(Check.class);
			dc2.add(Restrictions.between("checkDate", beginTime, endTime));
			dc2.add(Restrictions.eqOrIsNull("courseName",course));
			dc2.add(Restrictions.eq("checkState", CheckStateEnum.askForLeave.state));
			List<Check>checkList=checkService.findNeedReStuUser(dc2);
			//获得出数据格式如： cherish 听力  2018-5-12  ； cherish 听力 2018-5-13；  张三  听力 2018-5-13 
			
			
			//计算出补课期度  例如：5月的上半月
			Calendar calen = Calendar.getInstance();
			int month2 =calen.get(Calendar.MONDAY)+1;
			int dayIndex2 =calen.get(Calendar.DAY_OF_MONTH);
			String periodStr2=""+month2+"月的";
			if(dayIndex2>0&&dayIndex2<16){
				periodStr2+="上半月";
			}else{
				periodStr2+="下半月";
			}
			//根据某期补课的某个课程来查询补课信息id
			DetachedCriteria dc3 = DetachedCriteria.forClass(ReStuCourse.class);
			dc3.add(Restrictions.eq("period", periodStr2));
			dc3.add(Restrictions.eq("courseName", course));
			String reStudyCoId=reStudyDao.findIdByCourseName(dc3);
			
			
			HashSet<String> userNameSet = new HashSet<String>();
			for (Check check : checkList) {
				//1、去重  得到 cherish  ；张三 
				userNameSet.add(check.getUserName());
			}
			
			for (String un : userNameSet) {
				int num=0;
				for(Check check : checkList) {
					if(un.equals(check.getUserName())){
						num++;
					}
				}
				if(num>2){
					num=2;
				}
				//保存到ReStudy表中
				//public ReStudy(String id, String userName, String restucourseid, Integer num, Integer state) {
				String uuidStr = UUID.randomUUID().toString().replaceAll("-", "");
				ReStudy reStudy = new ReStudy(uuidStr,un,reStudyCoId,num,new Integer(ReStudyCourse.reStudyAble.state));
				boolean isOK = reStudyDao.insertReStudy(reStudy);
			}
//		} course for 循环
		
		
		
		
		
		return isOk;
	}

	@Override
	public List<ReStuCourse> findReStuCourseList(DetachedCriteria dc) {
		return reStuCourseDao.findReStuCourseList(dc);
	}

	@Override
	public boolean deleteReStuCourseByid(String rscid) {
		ReStuCourse reStuCourse = new ReStuCourse();
		
		reStuCourse.setId(rscid);
		//TODO 需要事务
//		reStuCourse.setState(ReStudyCourse.reStuCourseDisable.state);
		//删除reStuCourse
		boolean isOK=reStuCourseDao.deleteById(reStuCourse);
		//删除补课下的学生
		boolean idOK2 = reStuCourseDao.deleteUserByreStuCouId(rscid);
		if(isOK==true&&isOK==true){
			return true;
		}
		return false;
	}

	@Override
	public boolean addReStuCourseByTea(ReStuCourse reStuCourse) {
		boolean isOK=reStuCourseDao.updateStudyTime(reStuCourse);
		return isOK;
	}

	@Override
	public boolean reSetStuTimeBytea(ReStuCourse reStuCourse) {
		reStuCourse.setState(ReStudyCourse.reStuCourseAble.state);
		boolean isOK=reStuCourseDao.updateState2Active(reStuCourse);
		return isOK;
	}

	@Override
	public List<ReStuCourse> getAleadyopen(String periodStr) {
		return 	reStuCourseDao.getAll(periodStr);
	}

	@Override
	public ArrayList<ReStuNoticeDto> getReStuPreHalfMonth(User user) {
		//查找出上半个月的补课列表
		Calendar calen = Calendar.getInstance();
		int month2 =calen.get(Calendar.MONDAY)+1;
		int dayIndex2 =calen.get(Calendar.DAY_OF_MONTH);
		String periodStr2=""+month2+"月的";
		if(dayIndex2>0&&dayIndex2<16){
			periodStr2+="上半月";
		}else{
			periodStr2+="下半月";
		}
		String findStr="";
		
		if(("1月的上半月").equals(periodStr2)){
			return null;
		}
		if((month2+"月的下半月").equals(periodStr2)){
			findStr=(month2)+"月的上半月";
		}
		if((month2+"月的上半月").equals(periodStr2)){
			findStr=(month2-1)+"月的下半月";
		}

		DetachedCriteria dc = DetachedCriteria.forClass(ReStuCourse.class);
		dc.add(Restrictions.eq("state",new Integer(ReStudyCourse.teacherOK.state)));
		dc.add(Restrictions.eq("period",findStr ));
		List<ReStuCourse> ReStuList=reStuCourseDao.getAllPreHalfMonth(dc);
		ArrayList<ReStuNoticeDto> reStudyList = new ArrayList<ReStuNoticeDto>();
		
		if(ReStuList.size()>0){
			for (ReStuCourse reStuCourse : ReStuList) {
				String reStuid = reStuCourse.getId();
				DetachedCriteria dc2 = DetachedCriteria.forClass(ReStudy.class);
				dc2.add(Restrictions.eq("restucourseid",reStuid ));
				dc2.add(Restrictions.eq("userName", user.getName()));
				List<ReStudy>list= reStudyDao.findUserNeedReStu(dc2);
				if(list.size()>0){
					for (ReStudy reStudy : list) {
						ReStuNoticeDto reStuNoticeDto = new ReStuNoticeDto();
						//补课课程的老师
						reStuNoticeDto.setTeacher(reStuCourse.getTeacher());
						reStuNoticeDto.setCourse(reStuCourse.getCourseName());
						reStuNoticeDto.setReStuNotice(reStuCourse.getStudyTime());
						//学生补该课程的节数
						reStuNoticeDto.setReStuNum(reStudy.getNum());
						reStudyList.add(reStuNoticeDto);
					}
				}
			}
		}
		return reStudyList;
	}


	
	

}
