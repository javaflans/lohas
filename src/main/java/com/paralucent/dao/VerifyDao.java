package com.paralucent.dao;

import java.util.List;

import com.paralucent.model.Member;
import com.paralucent.model.Status;
import com.paralucent.model.VerifyAccount;

public interface VerifyDao {


    public Member searchByMember(Member member);
    public int insertVerify(VerifyAccount verify);
	public List<Member> queryDuplicateMember(Member member);
	public int insertUpdateMember(Member member);
	public Status searchStatusByCode(int code);
	public Member searchByKOL(Member memberData);
}
