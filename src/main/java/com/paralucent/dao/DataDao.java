package com.paralucent.dao;

import java.util.Date;
import java.util.List;

import com.paralucent.model.Beacon;
import com.paralucent.model.Customer;
import com.paralucent.model.Event;
import com.paralucent.model.EventView;
import com.paralucent.model.HistoryView;
import com.paralucent.model.Member;
import com.paralucent.model.Menus;
import com.paralucent.model.VerifyAccount;
import com.paralucent.viewmodel.EventBO;
import com.paralucent.viewmodel.HistoryBO;

public interface DataDao {

	public int insert(Member member);

	public List<Member> search();

	public Member searchById(String id);

	public int update(Member member);

	public int delete(Member member);

	public int deleteById(String id);

	public List<Menus> searhMenus();
	
	public List<Customer> searchCustomers();

	public int insert(VerifyAccount verify);

	public List<Beacon> queryBeaconList();

	public List<Event> queryDuplicateEvent(Event event);

	public int insertUpdateEvent(Event event);

	public List<EventView> searchEvents(Event eventBO);

	public List<HistoryView> searchHistorys(HistoryBO historyBO);

	public Event queryEventByTagTime(String tagTime, String beaconID);

	public void deleteEvent(EventView event);
	

}
