package com.paralucent.services;

import java.util.Date;
import java.util.List;

import com.paralucent.model.Beacon;
import com.paralucent.model.Customer;
import com.paralucent.model.Event;
import com.paralucent.model.EventView;
import com.paralucent.model.HistoryView;
import com.paralucent.model.Menus;
import com.paralucent.viewmodel.HistoryBO;

public class DataServiceImpl extends BaseService implements DataService {
	
	public DataServiceImpl() {
		super();
		genLogger();
	}

	@Override
	public List<Customer> searchCustomer() {
		return dataDao.searchCustomers();
	}

	@Override
	public List<Menus> searchMenus() {
		return dataDao.searhMenus();
	}

	@Override
	public List<Beacon> queryBeaconList() {
		return dataDao.queryBeaconList();
	}

	@Override
	public boolean checkDuplicateEvent(Event event) {
		List<Event> events = (List<Event>) dataDao.queryDuplicateEvent(event);
		log.info("query Duplicate size: " + (events!=null?events.size():0));
		return (events != null ? events.size() <= 0 : Boolean.TRUE);
	}

	@Override
	public boolean insertUpdateEvent(Event event) {
		int insertId = dataDao.insertUpdateEvent(event);
		return Boolean.TRUE;
	}

	@Override
	public List<EventView> searchEvents(Event eventBO) {
		List<EventView> result = dataDao.searchEvents(eventBO);
		return result;
	}

	@Override
	public List<HistoryView> searchHistorys(HistoryBO historyBO) {
		List<HistoryView> result = dataDao.searchHistorys(historyBO);
		return result;
	}

	@Override
	public Event queryEventByTagTime(String tagTime, String beaconID) {
		return dataDao.queryEventByTagTime(tagTime,beaconID);
	}

	@Override
	public void deleteEvent(EventView event) {
		dataDao.deleteEvent(event);
	}
	
	

}