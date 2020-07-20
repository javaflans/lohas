package com.paralucent.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.paralucent.model.Member;
import com.paralucent.model.Status;
import com.paralucent.model.VerifyAccount;

public class VerifyDaoImpl extends BaseDao implements VerifyDao {

	public VerifyDaoImpl() {
		super();
		genLogger();
	}

	@Override
	public Member searchByMember(Member member) {
		Criteria criteria = getCriteria(Member.class);
		log.info("userMail: " + member.getUserMail());
		if (StringUtils.isNotBlank(member.getUserMail())) {
			criteria.add(Restrictions.eq("userMail", member.getUserMail()));
		} else {
			return null;
		}
		log.info("password: " + member.getUserPassword());
		if (StringUtils.isNotBlank(member.getUserPassword())) {
			criteria.add(Restrictions.eq("userPassword", member.getUserPassword()));
		} else {
			return null;
		}
		
		criteria.createCriteria("mailStatus").add(Restrictions.and(Restrictions.eq("stuType", "validated"),Restrictions.eq("usageTable", "member")));
		
		@SuppressWarnings("unchecked")
		List<Member> result = criteria.list();
		log.info("login search result: " + result);
		clearSession();
		if (!result.isEmpty() && result.size() > 0) {
			return result.get(0);
		}
		return null;
	}

	@Override
	public int insertVerify(VerifyAccount verify) {
		log.info("enter insert");
		Session session = beginTransaction();
		session.saveOrUpdate(verify);
		commit();
		Serializable id = session.getIdentifier(verify);
		clearSession();
		return (Integer) id;

	}

	@Override
	public List<Member> queryDuplicateMember(Member member) {
		log.info("enter queryDuplicateMember");
		List<Member> result = new ArrayList<Member>(0);
		Criteria criteria = getCriteria(Member.class);
		if (StringUtils.isNotBlank(member.getUserMail())) {
			criteria.add(Restrictions.eq("userMail", member.getUserMail()));
		} else {
			return null;
		}
		result = criteria.list();
		log.info("Query duplicate member size: " + result.size());		
		clearSession();
		return result;
	}

	@Override
	public int insertUpdateMember(Member member) {
		log.info("enter insertMember");
		Session session = beginTransaction();
		session.saveOrUpdate(member);
		commit();
		Serializable id = session.getIdentifier(member);
		clearSession();
		return (Integer) id;
	}

	@Override
	public Status searchStatusByCode(int code) {
		log.info("enter searchStatusByCode");
		List<Status> result = new ArrayList<Status>(0);
		Criteria criteria = getCriteria(Status.class);
		criteria.add(Restrictions.eq("stuCode", code));
		result = criteria.list();
		clearSession();
		if (!result.isEmpty() && result.size() > 0) {
			return result.get(0);
		}
		return null;
	}

	@Override
	public Member searchByKOL(Member memberData) {
		Criteria criteria = getCriteria(Member.class);
		if (StringUtils.isNotBlank(memberData.getUserMail())) {
			criteria.add(Restrictions.eq("userMail", memberData.getUserMail()));
		}
		if (StringUtils.isNotBlank(memberData.getUserUuid())) {
			criteria.add(Restrictions.eq("userUuid", memberData.getUserUuid()));
		}
		if (StringUtils.isNotBlank(memberData.getUserName())) {
			criteria.add(Restrictions.eq("userName", memberData.getUserName()));
		}
		
		@SuppressWarnings("unchecked")
		List<Member> result = criteria.list();
		clearSession();
		if (!result.isEmpty() && result.size() > 0) {
			return result.get(0);
		}
		return null;
	}
}
