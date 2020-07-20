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

public interface DataService {

	public List<Customer> searchCustomer();
	public List<Menus> searchMenus();
	public List<Beacon> queryBeaconList();
	public boolean checkDuplicateEvent(Event event);
	public boolean insertUpdateEvent(Event event);
	public List<EventView> searchEvents(Event eventBO);
	public List<HistoryView> searchHistorys(HistoryBO historyBO);
	public Event queryEventByTagTime(String tagTime, String beaconID);
	public void deleteEvent(EventView event);
}
