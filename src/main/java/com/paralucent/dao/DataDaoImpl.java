package com.paralucent.dao;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.alibaba.druid.sql.dialect.oracle.ast.stmt.OracleIfStatement.Else;
import com.paralucent.model.Beacon;
import com.paralucent.model.Customer;
import com.paralucent.model.Event;
import com.paralucent.model.EventView;
import com.paralucent.model.HistoryView;
import com.paralucent.model.Member;
import com.paralucent.model.Menus;
import com.paralucent.model.VerifyAccount;
import com.paralucent.viewmodel.HistoryBO;

public class DataDaoImpl extends BaseDao implements DataDao {

	public DataDaoImpl() {
		super();
		genLogger();
	}

	@Override
	@Transactional
	public int insert(Member member) {
		Session session = beginTransaction();
		session.saveOrUpdate(member);
		commit();
		Serializable id = session.getIdentifier(member);
		clearSession();
		return (Integer) id;
	}

	@Override
	public List<Member> search() {
		beginTransaction();
		Criteria criteria = getCriteria(Member.class);
		@SuppressWarnings("unchecked")
		List<Member> list = criteria.list();
		commit();
		clearSession();
		return list;
	}

	@Override
	public Member searchById(String id) {
		Criteria criteria = getCriteria(Member.class);
		criteria.setCacheable(Boolean.TRUE);
		criteria.add(Restrictions.eq("id", id));
		Member result = (Member) criteria.uniqueResult();
		clearSession();
		return result;
	}

	@Override
	public int update(Member member) {
		Session session = beginTransaction();
		session.saveOrUpdate(member);
		commit();
		Serializable id = session.getIdentifier(member);
		clearSession();
		return (Integer) id;
	}

	@Override
	public int delete(Member member) {
		Session session = beginTransaction();
		session.delete(member);
		commit();
		Serializable id = session.getIdentifier(member);
		clearSession();
		return (Integer) id;
	}

	@Override
	public int deleteById(String id) {
		Session session = beginTransaction();
		Member member = searchById(id);
		session.delete(member);
		commit();
		Serializable ids = session.getIdentifier(member);
		clearSession();
		return (Integer) ids;
	}

	@Override
	public List<Customer> searchCustomers() {
		beginTransaction();
		Criteria criteria = getCriteria(Customer.class);
		@SuppressWarnings("unchecked")
		List<Customer> result = criteria.list();
		commit();
		clearSession();
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Menus> searhMenus() {
		Criteria criteria = getCriteria(Menus.class);
		criteria.createCriteria("status").add(Restrictions.and(Restrictions.eq("stuType", "enabled"), Restrictions.eq("usageTable", "menus")));
		List<Menus> result = criteria.list();
		clearSession();
		return result;
	}

	@Override
	public int insert(VerifyAccount verify) {
		Session session = beginTransaction();
		session.saveOrUpdate(verify);
		commit();
		Serializable id = session.getIdentifier(verify);
		clearSession();
		return (Integer) id;

	}

	@Override
	public List<Beacon> queryBeaconList() {
		Criteria criteria = getCriteria(Beacon.class);
		criteria.add(Restrictions.eq("canbeEvent", Boolean.TRUE));
		List<Beacon> result = criteria.list();
		clearSession();
		return result;
	}

	@Override
	public List<Event> queryDuplicateEvent(Event eventBO) {
		log.info("enter queryDuplicateEvent");
		List<Event> result = new ArrayList<Event>(0);
		Criteria criteria = getCriteria(Event.class);

		log.info("event start: " + eventBO.getEventStartDate());
		log.info("event end: " + eventBO.getEventEndDate());

		if (eventBO.getEventStartDate() != null && eventBO.getEventEndDate() != null) {
			criteria.add(Restrictions.or(
					Restrictions.and(Restrictions.le("eventStartDate", eventBO.getEventStartDate()),
							Restrictions.ge("eventEndDate", eventBO.getEventStartDate())),
					Restrictions.and(Restrictions.le("eventStartDate", eventBO.getEventEndDate()),
							Restrictions.ge("eventEndDate", eventBO.getEventEndDate()))));
		}

		if (eventBO.getBeaconID() != null) {
			criteria.add(Restrictions.eq("beaconID", eventBO.getBeaconID()));
		} else {
			return null;
		}

		result = criteria.list();
		log.info("Query duplicate event size: " + result.size());
		clearSession();
		return result;
	}

	@Override
	public int insertUpdateEvent(Event event) {
		log.info("enter insertUpdateEvent");
		Session session = beginTransaction();
		session.saveOrUpdate(event);
		commit();
		Serializable id = session.getIdentifier(event);
		clearSession();
		return (Integer) id;
	}

	@Override
	public List<EventView> searchEvents(Event eventBO) {
		log.info("enter searchEvents");
		List<EventView> result = new ArrayList<EventView>(0);
		Criteria criteria = getCriteria(EventView.class);
		if (StringUtils.isNotBlank(eventBO.getEventType())) {
			criteria.add(Restrictions.eq("eventType", eventBO.getEventType()));
		}

		if (eventBO.getEventStartDate() != null) {
			criteria.add(Restrictions.ge("eventStartDate", eventBO.getEventStartDate()));
		}

		if (eventBO.getBeaconID() != null) {
			criteria.add(Restrictions.eq("beaconID", eventBO.getBeaconID()));
		}

		result = criteria.list();
		log.info("Search event size: " + result.size());
		clearSession();
		return result;
	}

	@Override
	public List<HistoryView> searchHistorys(HistoryBO historyBO) {
		log.info("enter searchHistorys");
		List<HistoryView> result = new ArrayList<HistoryView>(0);
		Criteria criteria = getCriteria(HistoryView.class);
		if (StringUtils.isNotBlank(historyBO.getContactLocalName())) {
			criteria.add(Restrictions.eq("localName", historyBO.getContactLocalName()));
		}

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (historyBO.getStartDate() != null && historyBO.getEndDate() != null) {
			criteria.add(Restrictions.and(Restrictions.ge("tagTime", formatter.format(historyBO.getStartDate())), Restrictions.le("tagTime", formatter.format(historyBO.getEndDate()))));
		}

		result = criteria.list();
		
		// Analysis
		int count = 0;
		String name = "-1";
		log.info("Search history size: " + result.size());
		List<HistoryView> views = new ArrayList<HistoryView>(0);
		for (int c = 0; c < result.size(); c++) {
//			log.info(result.get(c).getResidentLocalName() + " TagTime: " + result.get(c).getTagTime() + " beaconID: " + result.get(c).getBeaconID());
			if (StringUtils.equals(result.get(c).getLinkedName(), name)) {
//				if (count < 1 && !StringUtils.equals(result.get(c).getBeaconID(), "00000")) {
				if (count < 1) {
					count++;
				}
			} else {
				count = 0;
				name = result.get(c).getLinkedName();
				if (StringUtils.equals(result.get(c).getBeaconID(), "00000")) {
					views.add(result.get(c));
					int i = 1;
					while (StringUtils.equals((result.get(c+i).getBeaconID()),"00000")) {
						i++;
					}
					views.add(result.get(c+i));
				} else {
					views.add(result.get(c));
				}
			}
		}
		
//		log.info("==========================================================");
		
//		for(HistoryView view : views) {
//			log.info(view);
//			log.info(view.getResidentLocalName() + " TagTime: " + view.getTagTime() + " beaconID: " + view.getBeaconID());
//		}
		
		log.info("Search history size: " + views.size());
		clearSession();
		
		if(views!=null && views.size()>0){
			Object[] ids = new Object[views.size()];
			for(int i = 0;i<views.size();i++){
				ids[i] = views.get(i).getId();
			}
			
			criteria = getCriteria(HistoryView.class);
			criteria.add(Restrictions.in("id", ids));
			criteria.addOrder(Order.desc("isSOS"));
			criteria.addOrder(Order.desc("isFreezed2"));
			criteria.addOrder(Order.desc("isFreezed1"));
			criteria.addOrder(Order.desc("isLost"));
			
			views = criteria.list();
			clearSession();
		}
		return views;
	}

	@Override
	public Event queryEventByTagTime(String tagTime, String beaconID) {
		log.info("enter queryEventByTagTime");
		List<Event> result = new ArrayList<Event>(0);
		Criteria criteria = getCriteria(Event.class);
		if (StringUtils.isNotBlank(beaconID)) {
			criteria.add(Restrictions.eq("beaconID", beaconID));
		}

		if (tagTime != null) {
			try {
				criteria.add(Restrictions.and(Restrictions.le("eventStartDate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(tagTime)), Restrictions.ge("eventEndDate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(tagTime))));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		result = criteria.list();
		
		clearSession();
		
		for(Event event : result) {
			log.info("queryEventByTagTime result: " + event.getEventType());
		}
		
		
		if(result!=null && result.size()>0){
			return result.get(0);
		} else {
			return null;
		}
	}

	@Override
	public void deleteEvent(EventView event) {
		Session session = beginTransaction();
		Criteria criteria = getCriteria(Event.class);
		criteria.add(Restrictions.eq("id", event.getId()));
		List<Event> evts = criteria.list();
		if( evts!=null && evts.size()>0){
			for(Event evt : evts){
				session.delete(evt);
			}
			commit();
		}
		clearSession();
	}
}
