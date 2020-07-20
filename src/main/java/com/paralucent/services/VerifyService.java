package com.paralucent.services;

import java.util.List;

import com.paralucent.model.Member;
import com.paralucent.model.Status;
import com.paralucent.model.VerifyAccount;
import com.paralucent.viewmodel.EventBO;

public interface VerifyService {
	public boolean logAccoundData(VerifyAccount verify);

	public Member login(Member employee);

	public boolean checkDuplicateMember(Member member);

	public boolean insertUpdateMember(Member member);

	public Status searchStatusByCode(int code);

	public Member checkMemberVerifyed(String kolUuid, String kolName, String kolmail);

	public List<Member> queryDuplicateMember(Member member);

	public Member checkMemberVerifyed(String kolmail);
 

}
