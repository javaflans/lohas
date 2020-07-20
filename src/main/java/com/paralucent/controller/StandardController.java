package com.paralucent.controller;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.paralucent.model.Beacon;
import com.paralucent.model.Event;
import com.paralucent.model.EventView;
import com.paralucent.model.HistoryView;
import com.paralucent.model.Member;
import com.paralucent.model.Menus;
import com.paralucent.model.Status;
import com.paralucent.model.VerifyAccount;
import com.paralucent.services.DataService;
import com.paralucent.services.VerifyService;
import com.paralucent.utils.BeaconUtils;
import com.paralucent.utils.DateUtils;
import com.paralucent.utils.EncrypSHA256;
import com.paralucent.viewmodel.EventBO;
import com.paralucent.viewmodel.HistoryBO;
import com.paralucent.viewmodel.HistoryViewBO;


@Controller
public class StandardController extends BaseController {
	
	private final static String DOMAIN_URL = "http://localhost:8080/lohas";
	@Autowired
	private DataService dataService;
	
	@Autowired
	private VerifyService verifyService;
	

	public StandardController() {
		super();
		genLogger();
	}

	/**
	 * 共用功能
	 * 
	 * @param member
	 * @return
	 */

	/**
	 * 登入審核
	 */
	@RequestMapping(value = "loginAction", method = RequestMethod.POST)
	public ModelAndView login(@ModelAttribute("member") Member member) {
		HttpSession session = null;
		clearSession();
		session = getSession();
		VerifyAccount result = validateAccount(member);
		logAccount(result, LOGIN_TYPE);
		log.info("message: " + result.getVerifyed());
		if (!result.getVerifyed()) {
			session.setAttribute("loginMessage", "帳號密碼錯誤, 請重新登入");
			session.setAttribute("loginAccount", null);
			log.info("Exit loginAction");
		} else {
			result.getMember().setUserLocalName(firstUplocate(result.getMember().getUserLocalName()));
			session.setAttribute("loginAccount", result.getMember());
			session.setAttribute("header_type", "kendo");
			session.setAttribute("menus", prepareMenu());
			log.info("Exit loginAction");
		}
		return returnView("redirect:/overview");
	}


//	/**
//	 * 註冊KOL ID 
//	 * Email 驗證信發送
//	 */
//	@RequestMapping(value = "registFormSubmit", method = RequestMethod.POST)
//	public @ResponseBody Map<String, Object> registFormSubmit(@RequestBody ArrayList<Member> members) {
//
//		Map<String, Object> result = new HashMap<String, Object>();
//		if (members != null && members.size() > 0) {
//			Member member = members.get(0);
//			boolean checkDuplicate = verifyService.checkDuplicateMember(member);
//			log.info("check Result: " + checkDuplicate);
//			if (!checkDuplicate) {
//				result.put("code", 204);
//				result.put("message", "此Email已經正在使用,請重新登入或使用其他Email註冊!");
//				result.put("status", "warning");
//			} else {
//				Status unvalidatedStatus = verifyService.searchStatusByCode(0);
//				if (unvalidatedStatus != null) {
//					member.setUserUuid(UUID.randomUUID().toString());
//					member.setUserStatus(unvalidatedStatus);
//					member.setMailStatus(unvalidatedStatus);
//					member.setUserCreated("Regist System");
//					member.setUserDateCreated(new Date());
//
//					// SimpleDateFormat sdf = new SimpleDateFormat("yyy/MM/dd");
//					// log.info("Member: " + member.getUserMail());
//					// log.info("Member: " + member.getUserName());
//					// log.info("Member: " + member.getUserLocalName());
//					// log.info("Member: " + member.getUserPassword());
//					// log.info("Member: " + member.getUserPhone());
//					// log.info("Member: " + member.getUserCity());
//					// log.info("Member: " + member.getUserArea());
//					// log.info("Member: " + member.getUserAddress());
//					// log.info("Member: " + member.getZipCode());
//					// log.info("Member: " +
//					// sdf.format(member.getUserBirthday()));
//					// log.info("Member: " + member.getStatus().getStuType() + " / " + member.getStatus().getStuDesc());
//				}
//				boolean insertResult = verifyService.insertUpdateMember(member);
//
//				if (insertResult) {
//					try {
//						if (sendVerifyMail(member,"分享達人 ID 帳戶驗證信",prepareRegistMailContent(member))) {
//							result.put("code", 200);
//							result.put("title", "帳號註冊確認");
//							result.put("message", "恭喜你, 註冊成功, 請到您的電子郵件收取驗證郵件, 並依照指示完成驗證");
//							result.put("status", "Successfuly");
//						}
//					} catch (UnsupportedEncodingException | MessagingException e) {
//						result.put("code", 204);
//						result.put("message", "因特殊原因造成例外, 請與服務人員聯繫");
//						result.put("status", "error");
//					}
//				} else {
//					result.put("code", 204);
//					result.put("message", "因特殊原因造成例外, 請與服務人員聯繫");
//					result.put("status", "error");
//				}
//			}
//		} else {
//			result.put("code", 204);
//			result.put("message", "輸入資料錯誤,請重新確認");
//			result.put("status", "error");
//		}
//		return result;
//	}
	
	private Event convertToEvent(EventBO eventView) {
		Event event = new Event();
		event.setEventType(eventView.getEventType());
		event.setEventDesc(eventView.getEventDesc());
		event.setBeaconID(eventView.getBeaconID());
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Calendar dateCal = Calendar.getInstance();
		dateCal.setTime(eventView.getEventDate());
		
		Calendar startCal = Calendar.getInstance();
		startCal.setTime(eventView.getEventStartTime());
		
		Calendar endCal = Calendar.getInstance();
		endCal.setTime(eventView.getEventEndTime());
		
		Calendar startTimeCal = Calendar.getInstance();
		startTimeCal.set(Calendar.YEAR, dateCal.get(Calendar.YEAR));
		startTimeCal.set(Calendar.MONTH, dateCal.get(Calendar.MONTH));
		startTimeCal.set(Calendar.DAY_OF_MONTH, dateCal.get(Calendar.DAY_OF_MONTH));
		startTimeCal.set(Calendar.HOUR_OF_DAY, startCal.get(Calendar.HOUR_OF_DAY));
		startTimeCal.set(Calendar.MINUTE, startCal.get(Calendar.MINUTE));
		startTimeCal.set(Calendar.SECOND, startCal.get(Calendar.SECOND));
		
		log.info("eventStartDate: " +sdf.format(startTimeCal.getTime()));
		
		
		Calendar endTimeCal = Calendar.getInstance();
		endTimeCal.set(Calendar.YEAR, dateCal.get(Calendar.YEAR));
		endTimeCal.set(Calendar.MONTH, dateCal.get(Calendar.MONTH));
		endTimeCal.set(Calendar.DAY_OF_MONTH, dateCal.get(Calendar.DAY_OF_MONTH));
		endTimeCal.set(Calendar.HOUR_OF_DAY, endCal.get(Calendar.HOUR_OF_DAY));
		endTimeCal.set(Calendar.MINUTE, endCal.get(Calendar.MINUTE));
		endTimeCal.set(Calendar.SECOND, endCal.get(Calendar.SECOND));
		
		log.info("eventEndDate: " +sdf.format(endTimeCal.getTime()));
		
		event.setEventStartDate(startTimeCal.getTime());
		event.setEventEndDate(endTimeCal.getTime());
		event.setUserCreated("EventConfigSystem");
		event.setUserDateCreated(new Date());
		return event;
	}

//	/**
//	 * KOL ID 忘記密碼 
//	 * Email 發送
//	 */
//	@RequestMapping(value = "forgetFormSubmit", method = RequestMethod.POST)
//	public @ResponseBody Map<String, Object> forgetFormSubmit(@RequestBody ArrayList<Member> members) {
//
//		Map<String, Object> result = new HashMap<String, Object>();
//		if (members != null && members.size() > 0) {
//			Member member = members.get(0);
//			List<Member> dataResults = verifyService.queryDuplicateMember(member);
//			if (dataResults!=null && dataResults.size() > 0){
//				Member data = dataResults.get(0);
//				if(!data.getMailStatus().getStuType().equals("unvalidated")){
//					
//					try {
//						if( sendVerifyMail(data, "分享達人 ID 密碼重設要求", prepareForgetMailContent(data))){
//							result.put("code", 200);
//							result.put("title", "密碼重設確認");
//							result.put("message", "感謝您，我們已將包含如何重設密碼相關指示的電子郵件傳送至 " + member.getUserMail() + "。");
//							result.put("status", "Successfuly");
//						}
//					} catch (UnsupportedEncodingException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					} catch (MessagingException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//				} else {
//					result.put("code", 204);
//					result.put("message", "該帳號Email尚未驗證, 請重新驗證");
//					result.put("status", "error");
//				}
//			} else {
//				result.put("code", 204);
//				result.put("message", "該帳號不存在, 請重新確認");
//				result.put("status", "error");
//			}
//		} else {
//			result.put("code", 204);
//			result.put("message", "輸入資料錯誤,請重新確認");
//			result.put("status", "error");
//		}
//		return result;
//	}
	
//	/**
//	 * KOL 密碼重設 
//	 */
//	@RequestMapping(value = "resetPasswordSubmit", method = RequestMethod.POST)
//	public @ResponseBody Map<String, Object> resetPasswordSubmit(@RequestBody ArrayList<Member> members) {
//		
//		Map<String, Object> result = new HashMap<String, Object>();
//		if (members != null && members.size() > 0) {
//			Member member = members.get(0);
//			log.info(member.getUserPassword());
//			log.info(member.getUserMail());
//			Member data = verifyService.checkMemberVerifyed(member.getUserMail());
//			if(data!=null && StringUtils.isNotBlank(data.getUserName())){
//				data.setUserPassword(member.getUserPassword());
//				data.setUserLastDateModify(new Date());
//				data.setUserLastModify("Forget PWD System");
//				boolean resetPWDResult = verifyService.insertUpdateMember(data);
//				if(resetPWDResult) {
//					result.put("code", 200);
//					result.put("title", "密碼重設");
//					result.put("message", "密碼重設成功! 請使用新的密碼重新登入!");
//					result.put("status", "error");
//				} else {
//					result.put("code", 204);
//					result.put("title", "例外錯誤");
//					result.put("message", "因特殊原因造成例外, 請與服務人員聯繫");
//					result.put("status", "error");
//				}
//			}
//		}
//		return result;
//		
//	}
	
//	/**
//	 * Email 驗證
//	 */
//	@RequestMapping(value="verifyApi/EmailVerify" , method = RequestMethod.GET)
//	public ModelAndView verifyEmail(HttpServletRequest req){
//		HttpSession session = getSession();
//		String kolUuid = req.getParameter("koluuid");
//		String kolName = req.getParameter("kolname");
//		String kolmail = req.getParameter("kolmail");
//		Member member = verifyService.checkMemberVerifyed(kolUuid,kolName,kolmail);
//		if(member!=null && member.getMailStatus().getStuType().equals("unvalidated")) {
//			Status validated = verifyService.searchStatusByCode(1);
//			member.setMailStatus(validated);
//			member.setUserLastModify("Email Verify System");
//			member.setUserLastDateModify(new Date());
//			boolean emailVerifyResult = verifyService.insertUpdateMember(member);
//			if(emailVerifyResult) {
//				session.setAttribute("code", 200);
//				session.setAttribute("title", "認證帳戶");
//				session.setAttribute("message", "Email 認證成功! 請重新登入, 並靜候團主開通 <span style='color: #ffbe23;'>分享達人</span> 任用權限!");
//				session.setAttribute("status", "error");
//			} else {
//				session.setAttribute("code", 204);
//				session.setAttribute("title", "例外錯誤");
//				session.setAttribute("message", "因特殊原因造成例外, 請與服務人員聯繫");
//				session.setAttribute("status", "error");
//			}
//		} else if(member!=null && member.getMailStatus().getStuDesc().equals("validated")) {
//			session.setAttribute("code", 204);
//			session.setAttribute("title", "使用中帳戶");
//			session.setAttribute("message", "Email 認證失敗! 該帳號可能已經啟用過了!");
//			session.setAttribute("status", "error");
//		} else {
//			session.setAttribute("code", 204);
//			session.setAttribute("title", "失效帳戶");
//			session.setAttribute("message", "Email 認證失敗! 請與服務人員聯繫!");
//			session.setAttribute("status", "error");
//		}
//		
//		return returnView("redirect:/redirectkolverfyresult");
//	}
//	
//	
//	
//	
//	/**
//	 * 重設密碼
//	 * Email 驗證
//	 */
//	@RequestMapping(value="verifyApi/ForgetPassword" , method = RequestMethod.GET)
//	public ModelAndView forgetPassword(HttpServletRequest req){
//		HttpSession session = getSession();
//		String kolsha = req.getParameter("kolsha");
//		String koldd = req.getParameter("koldd");
//		String kolmail = req.getParameter("kolmail");
//		SimpleDateFormat sdf = new SimpleDateFormat("SSSssmm'T'HHddMMyyyy");
//		Date before = new Date();
//		try {
//			before = sdf.parse(koldd);
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		Date after = new Date();
//		DateUtils dateUtils = new DateUtils();
//		dateUtils.calDateDiff(after, before);
//		if(dateUtils.getDay()<1){
//			Member member = verifyService.checkMemberVerifyed(kolmail);
//			if(member!=null && member.getMailStatus().getStuType().equals("validated")) {
//				EncrypSHA256 sha256 = new EncrypSHA256();
//				String shaCode = sha256.genEncrypSHA256(member.getUserUuid()+koldd);
//				log.info("mail sha: "+kolsha);
//				log.info("qury sha: "+shaCode);
//				if(kolsha.equals(shaCode)) {
//					session.setAttribute("code", 200);
//					session.setAttribute("title", "重設密碼");
//					session.setAttribute("member", member);
//					session.setAttribute("message", "請使用此表單來重設您的帳戶密碼");
//					session.setAttribute("status", "error");
//				} else {
//					session.setAttribute("code", 204);
//					session.setAttribute("title", "例外錯誤");
//					session.setAttribute("message", "可能因帳戶資訊有誤! 無法進行驗證, 請與服務人員聯繫");
//					session.setAttribute("status", "error");
//				}
//			} else if(member.getMailStatus().getStuType().equals("unvalidated")){
//				session.setAttribute("code", 204);
//				session.setAttribute("title", "帳戶尚未驗證");
//				session.setAttribute("message", "該帳號Email尚未驗證! 請重新驗證");
//				session.setAttribute("status", "error");
//			} else {
//				session.setAttribute("code", 204);
//				session.setAttribute("title", "失效帳戶");
//				session.setAttribute("message", "Email 連結認證失敗! 請與服務人員聯繫");
//				session.setAttribute("status", "error");
//			}
//		} else {
//			session.setAttribute("code", 204);
//			session.setAttribute("title", "失效驗證");
//			session.setAttribute("message", "Email 連結認證日期已經失效! 請重新申請密碼重設");
//			session.setAttribute("status", "error");
//		}
//		
//		return returnView("redirect:/redirectForgetPassword");
//	}
//	
//	
//	/**
//	 * 發送 Email 驗證信
//	 */
//	private boolean sendVerifyMail(Member member, String subject, StringBuffer htmlContent) throws MessagingException,UnsupportedEncodingException {
//		
//		Message message = new MimeMessage(getMailSession());
//		
//		message.setFrom(new InternetAddress("paralucent.service@gmail.com","Fanshopping Support"));
//		message.setRecipients(Message.RecipientType.TO,
//			InternetAddress.parse(member.getUserMail()));
//		message.setSubject(subject);
//		message.setContent(htmlContent.toString(), "text/html; charset=utf-8");
//		Transport.send((MimeMessage) message);
//		return Boolean.TRUE;
//	}

	
	
	/**
	 * 本地共用功能清單
	 */
	
	/**
	 * 準備 Menu 目錄
	 */
	private List<Menus> prepareMenu() {
		List<Menus> menus = new ArrayList<Menus>(0);
		menus = dataService.searchMenus();
		return menus;
	}
	
	private Date time(int hours, int minutes) {
        Calendar cal = Calendar.getInstance();
        
        cal.set(Calendar.HOUR_OF_DAY, hours);
        cal.set(Calendar.MINUTE, minutes);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        
        return cal.getTime();
    }
	
	//取得當周周一起始時間
    private Date getTimesWeekmorning() {  
        Calendar cal = Calendar.getInstance();  
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);  
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);  
        return cal.getTime();  
    }  
  
    //取得當周週日結束時間 
    private Date getTimesWeeknight() {  
        Calendar cal = Calendar.getInstance();  
        cal.setTime(getTimesWeekmorning());  
        cal.add(Calendar.DAY_OF_WEEK, 6);  
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 23, 59, 59);  
        return cal.getTime();  
    } 
	
//	/**
//	 * 產生註冊驗證信內容
//	 * @param member
//	 * @return 
//	 */
//	private StringBuffer prepareRegistMailContent(Member member){
//		StringBuffer htmlContent = new StringBuffer(""); 
//		htmlContent.append("<html style='height: 100%;' >");
//		htmlContent.append("	<head>");
//		htmlContent.append("		<meta http-equiv='Content-Type' content='text/html; charset=utf-8'>");
//		htmlContent.append("	</head>");
//		htmlContent.append("	<body style='height:100%; padding:0; margin: 0; '>");
//		htmlContent.append("		<div style='width:100%; height: 100%; margin: 0 auto; -webkit-box-shadow: 0 4px 20px -5px rgba(0,0,0,0.75); -moz-box-shadow: 0 4px 20px -5px rgba(0,0,0,0.75); box-shadow: 0 4px 20px -5px rgba(0,0,0,0.75);'>");
//		htmlContent.append("			<div style='width:100%; height:30px; background:#ffbe23; '>");
//		htmlContent.append("				<img style='height: 50px;' src='http://www.fanshopping.com.tw//uploads/logo/home/logo.png' alt='logo' />");
//		htmlContent.append("			</div>");
//		htmlContent.append("			<br/><br/>");
//		htmlContent.append("			<div style='font-family: Microsoft JhengHei; width: 290px; font-size: 25px; margin: 0 auto;'>");
//		htmlContent.append("				<span>確認您的</span>  <span style='color: #ffbe23;'>分享達人 ID</span>");
//		htmlContent.append("			</div><br/><br/><br/>");
//		htmlContent.append("			<div style='margin: 0;'>");
//		htmlContent.append("				<div style='word-break:break-all; font-size: 13px; width: 95%; margin: 0 auto;'>");
//		htmlContent.append("					" + member.getUserLocalName() + "  先生/女士 您好，<br/><br/><br/>");
//		htmlContent.append("					感謝您註冊 分享達人 ID。您還差一步就可以完成設定。為了安全起見並確定您是真人，請 <a href='" + DOMAIN_URL + "/verifyApi/EmailVerify?koluuid="+member.getUserUuid()+"&kolname=" + member.getUserName() + "&kolmail="+member.getUserMail()+"'>按一下這裡</a> 以確認您的帳戶。<br/><br/>");
//		htmlContent.append("					您的 分享達人 ID 為：" + member.getUserMail() + "<br/><br/>");
//		htmlContent.append("					如果上述連結無法運作，請將下方的 URL 複製並貼到新的瀏覽器視窗中，以完成設定。<br/><br/><br/><br/><br/>");
//		htmlContent.append("					<a href='" + DOMAIN_URL + "/verifyApi/EmailVerify?koluuid="+member.getUserUuid()+"&kolname=" + member.getUserName() + "&kolmail="+member.getUserMail()+"'>");
//		htmlContent.append("						" + DOMAIN_URL + "/verifyApi/EmailVerify?koluuid="+member.getUserUuid()+"&kolname=" + member.getUserName() + "&kolmail="+member.getUserMail());
//		htmlContent.append("					</a><br/><br/><br/><br/><br/><br/><br/><br/>");
//		htmlContent.append("					感謝您註冊使用 分享達人 服務。");
//		htmlContent.append("					<br/><br/>");
//		htmlContent.append("					開團樂 KOL ID 團隊 敬上");
//		htmlContent.append("				</div>");
//		htmlContent.append("			</div>");
//		htmlContent.append("			<div style='position:fixed; bottom: 0px; width: 100%; padding:0; height:23px; background:#ffbe23; margin: 0 auto;'>");
//		htmlContent.append("				<footer role='contentinfo'>");
//		htmlContent.append("			        <div style='color: #fff'>");
//		htmlContent.append("			            <ul style='padding-top: 3px; padding-left: 15px;'>");
//		htmlContent.append("			                <li style='list-style-type:none; font-weight: bolder; letter-spacing: 2px;'>");
//		htmlContent.append("			                	丞易國際有限公司&copy; 2017");
//		htmlContent.append("			                </li>");
//		htmlContent.append("			            </ul>");
//		htmlContent.append("			        </div>");
//		htmlContent.append("			    </footer>");
//		htmlContent.append("			</div>");
//		htmlContent.append("		</div>");
//		htmlContent.append("	</body>");
//		htmlContent.append("</html>");
//		return htmlContent;
//	}
//	
//	
//	/**
//	 * 產生忘記密碼驗證信內容
//	 * @param member
//	 * @return
//	 */
//	private StringBuffer prepareForgetMailContent(Member member){
//		EncrypSHA256 sha256 = new EncrypSHA256();
//		SimpleDateFormat sdf = new SimpleDateFormat("SSSssmm'T'HHddMMyyyy");
//		String dd= sdf.format(new Date()); 
//		String shaCode = sha256.genEncrypSHA256(member.getUserUuid()+dd);
//		
//		StringBuffer htmlContent = new StringBuffer(""); 
//		htmlContent.append("<html style='height: 100%;' >");
//		htmlContent.append("	<body style='height:100%; padding:0; margin: 0; '>");
//		htmlContent.append("		<div style='width:100%; height: 100%; margin: 0 auto; -webkit-box-shadow: 0 4px 20px -5px rgba(0,0,0,0.75); -moz-box-shadow: 0 4px 20px -5px rgba(0,0,0,0.75); box-shadow: 0 4px 20px -5px rgba(0,0,0,0.75);'>");
//		htmlContent.append("			<div style='width:100%; height:30px; background:#ffbe23; '>");
//		htmlContent.append("				<img style='height: 50px;' src='http://www.fanshopping.com.tw//uploads/logo/home/logo.png' alt='logo' />");
//		htmlContent.append("			</div>");
//		htmlContent.append("			<br/><br/>");
//		htmlContent.append("			<div style='font-family: Microsoft JhengHei; width: 290px; font-size: 25px; margin: 0 auto;'>");
//		htmlContent.append("				<span>忘記您的</span>  <span style='color: #ffbe23;'>分享達人 密碼?</span>");
//		htmlContent.append("			</div><br/><br/><br/>");
//		htmlContent.append("			<div style='margin: 0;'>");
//		htmlContent.append("				<div style='word-break:break-all; font-size: 13px; width: 95%; margin: 0 auto;'>");
//		htmlContent.append("					我們最近收到您的 分享達人 ID帳戶密碼重設要求：" + member.getUserMail() + "<br/><br/>");
//		htmlContent.append("					請點擊下面的連結完成此需求。<br/><br/><br/><br/><br/><br/><br/>");
//		htmlContent.append("					<a href='" + DOMAIN_URL + "/verifyApi/ForgetPassword?kolsha=" + shaCode + "&koldd=" + dd + "&kolmail=" + member.getUserMail() + "'>");
//		htmlContent.append("						" + DOMAIN_URL + "/verifyApi/ForgetPassword?kolsha=" + shaCode + "&koldd=" + dd + "&kolmail=" + member.getUserMail());
//		htmlContent.append("					</a><br/><br/><br/><br/><br/><br/>");
//		htmlContent.append("					<div style='background:#eee;width: 98%; padding: 1%;'>");
//		htmlContent.append("						<p>");
//		htmlContent.append("							如果您在重設密碼過程中遇到任何問題，可以複製連結文字，並直接貼到網絡瀏覽器的 URL 欄中。");
//		htmlContent.append("						</p>");
//		htmlContent.append("						<p>");
//		htmlContent.append("							如果您並未提交 密碼重設 需求，請忽略此電子郵件。");
//		htmlContent.append("						</p>");
//		htmlContent.append("					</div>");
//		htmlContent.append("					<br/><br/><br/><br/><br/><br/>");
//		htmlContent.append("					謝謝。<br/>");
//		htmlContent.append("					開團樂 KOL ID 團隊 敬上<br/>");
//		htmlContent.append("					請勿直接回覆此電子郵件，生成此電子郵件的郵箱將不會進行回覆。");
//		htmlContent.append("				</div>");
//		htmlContent.append("			</div>");
//		htmlContent.append("			<div style='position:fixed; bottom: 0px; width: 100%; padding:0; height:23px; background:#ffbe23; margin: 0 auto;'>");
//		htmlContent.append("				<footer role='contentinfo'>");
//		htmlContent.append("			        <div style='color: #fff'>");
//		htmlContent.append("			            <ul style='padding-top: 3px; padding-left: 15px;'>");
//		htmlContent.append("			                <li style='list-style-type:none; font-weight: bolder; letter-spacing: 2px;'>");
//		htmlContent.append("			                	丞易國際有限公司&copy; 2017");
//		htmlContent.append("			                </li>");
//		htmlContent.append("			            </ul>");
//		htmlContent.append("			        </div>");
//		htmlContent.append("			    </footer>");
//		htmlContent.append("			</div>");
//		htmlContent.append("		</div>");
//		htmlContent.append("	</body>");
//		htmlContent.append("</html>");
//		return htmlContent;
//	}
	
	/**
	 * API
	 */
    
    /**
	 * 設定活動內容 
	 */
	@RequestMapping(value = "api/eventFormSubmit", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> eventFormSubmit(@RequestBody ArrayList<EventBO> events) {
		
		Map<String, Object> result = new HashMap<String, Object>();
		if (events != null && events.size() > 0) {
			EventBO eventView = events.get(0);
			log.info("EventView: "+eventView.getBeaconID());
			log.info("EventView: "+eventView.getEventType());
			log.info("EventView: "+eventView.getEventDesc());
			log.info("EventView: "+eventView.getEventDate());
			log.info("EventView: "+eventView.getEventStartTime());
			log.info("EventView: "+eventView.getEventEndTime());
			
			Event event = convertToEvent(eventView);
			boolean checkDuplicate = dataService.checkDuplicateEvent(event);
			log.info("check Result: " + checkDuplicate);
			
			if(checkDuplicate){
				boolean insertResult = dataService.insertUpdateEvent(event);
				
				if (insertResult) {
					result.put("code", 200);
					result.put("title", "活動已經建立成功");
					result.put("message", "活動已經建立成功");
					result.put("status", "Successfuly");
				}
			}else{
				result.put("code", 204);
				result.put("message", "本活動已經於同樣時段中重複了, 請重新選擇時段");
				result.put("status", "error");
			}
			
		} else {
			result.put("code", 204);
			result.put("message", "輸入資料錯誤,請重新確認");
			result.put("status", "error");
		}
		return result;
	}
	
	/**
	 * 取得住民活動資料
	 */
	@RequestMapping(value = "api/getTrackHistory", method = RequestMethod.POST)
	public @ResponseBody List<HistoryViewBO> getTrackHistory() {
		
		HistoryBO historyBO;
		if(session!=null && session.getAttribute("historyBO")!=null){
			historyBO = (HistoryBO) session.getAttribute("historyBO");
		}else{
			historyBO = new HistoryBO();
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
			
			historyBO.setStartDate(cal.getTime());
			
			cal.set(Calendar.HOUR_OF_DAY, 23);
			cal.set(Calendar.MINUTE, 59);
			cal.set(Calendar.SECOND, 59);
			cal.set(Calendar.MILLISECOND, 0);
			
			historyBO.setEndDate(cal.getTime());
		}
		
		List<HistoryView> lists = dataService.searchHistorys(historyBO);
		List<HistoryViewBO> results = convertToHistoryViewBO(lists);
		return results;
	}
	
	private List<HistoryViewBO> convertToHistoryViewBO(List<HistoryView> lists) {
		List<HistoryViewBO> results = new ArrayList<HistoryViewBO>(0);
		for(HistoryView view : lists) {
			HistoryViewBO bo = new HistoryViewBO();
			try {
				BeanUtils.copyProperties(bo, view);
				Event event = dataService.queryEventByTagTime(bo.getTagTime(),bo.getBeaconID());
				bo.setEventType(event != null ? event.getEventType() : "無活動");
			} catch (IllegalAccessException | InvocationTargetException e) {
				e.printStackTrace();
			}
			results.add(bo);
		}
		return results;
	}

	/**
	 * 取得活動清單
	 */
	@RequestMapping(value = "api/getEventList", method = RequestMethod.POST)
	public @ResponseBody List<EventView> getEventList() {
		HttpSession session = getSession();
		Event eventParas = null;
		if(session!=null && session.getAttribute("eventBO")!=null){
			eventParas = (Event) session.getAttribute("eventBO");
		}else{
			eventParas = new Event();
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
			
			eventParas.setEventStartDate(cal.getTime());
			
			cal.set(Calendar.HOUR_OF_DAY, 23);
			cal.set(Calendar.MINUTE, 59);
			cal.set(Calendar.SECOND, 59);
			cal.set(Calendar.MILLISECOND, 0);
			
			eventParas.setEventEndDate(cal.getTime());
		}
		List<EventView> lists = dataService.searchEvents(eventParas);
		
		return lists;
	}
	
	/**
	 * 刪除活動
	 */
	@RequestMapping(value = "api/deleteEventView", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> deleteEventView(@RequestBody List<EventView> events) {
		Map<String, Object> result = new HashMap<String, Object>();
		if (events != null && events.size() > 0) {
			EventView event = events.get(0);
			HttpSession session = getSession();
			dataService.deleteEvent(event);
			// reset prodShareConfigLista
			result.put("code", 200);
			result.put("message", "活動刪除成功");
			result.put("status", "Successfuly");
		} else {
			result.put("code", 204);
			result.put("message", "輸入資料錯誤,請重新確認");
			result.put("status", "error");
		}
		return result;
	}
	
	/**
	 * 取得住民即時動態資料
	 */
	@RequestMapping(value = "api/getTrackHistoryDynamic", method = RequestMethod.GET)
	public @ResponseBody List<HistoryView> getTrackHistoryDynamic() {
		
		HistoryBO historyBO;
		if(session!=null && session.getAttribute("historyBO")!=null){
			historyBO = (HistoryBO) session.getAttribute("historyBO");
		}else{
			historyBO = new HistoryBO();
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
			
			historyBO.setStartDate(cal.getTime());
			
			cal.set(Calendar.HOUR_OF_DAY, 23);
			cal.set(Calendar.MINUTE, 59);
			cal.set(Calendar.SECOND, 59);
			cal.set(Calendar.MILLISECOND, 0);
			
			historyBO.setEndDate(cal.getTime());
		}
		
		List<HistoryView> lists = dataService.searchHistorys(historyBO);
		
		BigDecimal cm = null, px = null;
		for( HistoryView history : lists){
//			cm = new BigDecimal(history.getRssi()).multiply(new BigDecimal("-1.7"));
			cm = BeaconUtils.calculateAccuracy(new BigDecimal(history.getRssi())).multiply(new BigDecimal("100"));
			px = cm.multiply(new BigDecimal("23")).divide(new BigDecimal("100"),2,BigDecimal.ROUND_HALF_UP);
			history.setPosX(history.getPosX()+random(px).intValue());
			history.setPosY(history.getPosY()+random(px).intValue());
		}
		return lists;
	}
	
	public BigDecimal random(BigDecimal range){
		int[] i = new int[2];
		i[0] = -1;
		i[1] = 1;
		
		Random rd = new Random();
		
		return new BigDecimal(Math.random()).multiply(range).add(BigDecimal.ONE).divide(BigDecimal.ONE,2,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(i[rd.nextInt(i.length)])); 
	}
		
	/**
	 *  功能清單
	 */

	/**
	 * 取得Beacon主檔清單
	 */
	private List<Beacon> prepareBeacons(){
		List<Beacon> result = new ArrayList<Beacon>(0);
		result = dataService.queryBeaconList();
		return result;
	}
	
	
	/**
	 * 頁面轉址
	 */
	
	/**
	 * 總覽頁面 view: overview
	 * 
	 */
	@RequestMapping(value = "overview")
	public ModelAndView overview() {
		if (session!=null && session.getAttribute("loginAccount") != null) {
			return returnView("overview");
		} else {
			clearSession();
			return returnView("redirect:/redirectLogin");
		}
	}
	
	
	/**
	 * 儀器定位 - 儀器位置 view: instrPosisList
	 */
	@RequestMapping(value = "/instrPosisList")
	public ModelAndView instrPosisList() {
		if (session!=null && session.getAttribute("loginAccount") != null) {
			return returnView("instrPosisList");
		} else {
			clearSession();
			return returnView("redirect:/redirectLogin");
		}
	}

	/**
	 * 儀器定位 - 儀器即時位置 view: instrPosisDynamic
	 */
	@RequestMapping(value = "/instrPosisDynamic")
	public ModelAndView instrPosisDynamic() {
		if (session!=null && session.getAttribute("loginAccount") != null) {
			return returnView("instrPosisDynamic");
		} else {
			clearSession();
			return returnView("redirect:/redirectLogin");
		}
	}

	/**
	 * 病患定位 - 病患位置 view: patiPositionList
	 */
	@RequestMapping(value = "/patiPositionList")
	public ModelAndView patiPositionList() {
		if (session!=null && session.getAttribute("loginAccount") != null) {
			return returnView("patiPositionList");
		} else {
			clearSession();
			return returnView("redirect:/redirectLogin");
		}
	}

	/**
	 * 病患定位 - 病患即時位置 view: patiPositionDynamic
	 */
	@RequestMapping(value = "/patiPositionDynamic")
	public ModelAndView patiPositionDynamic() {
		if (session!=null && session.getAttribute("loginAccount") != null) {
			return returnView("patiPositionDynamic");
		} else {
			clearSession();
			return returnView("redirect:/redirectLogin");
		}
	}

	/**
	 * 基本資料維護 - 定位器設置 view: positionConfig
	 */
	@RequestMapping(value = "/positionConfig")
	public ModelAndView positionConfig() {
		if (session!=null && session.getAttribute("loginAccount") != null) {
			return returnView("positionConfig");
		} else {
			clearSession();
			return returnView("redirect:/redirectLogin");
		}
	}
	
	/**
	 * 設定 - 活動設置 view: eventConfig
	 */
	@RequestMapping(value = "eventConfig")
	public ModelAndView eventConfig() {
		if (session!=null && session.getAttribute("loginAccount") != null) {
			HttpSession session = getSession();
			log.info("=====================>" + Calendar.getInstance().getTime());
			session.setAttribute("today", new Date());
			session.setAttribute("minDay", getTimesWeekmorning());
			session.setAttribute("maxDay", getTimesWeeknight());
			
			
			session.setAttribute("startValue", time(8, 0));
			session.setAttribute("startMin", time(8, 0));
			session.setAttribute("startMax", time(22, 0));
	        
			session.setAttribute("endValue", time(9, 00));
			session.setAttribute("endMin", time(8, 0));
			session.setAttribute("endMax", time(22, 0));
			
			List<Beacon> beacons = prepareBeacons();
			session.setAttribute("beaconList", beacons);
			
			return returnView("eventConfig");
		} else {
			clearSession();
			return returnView("redirect:/redirectLogin");
		}
	}
	
	/**
	 * 登入 view: login
	 */
	@RequestMapping("login")
	public ModelAndView getLogin(@ModelAttribute Member member) {
		clearSession();
		ModelAndView view = new ModelAndView("login");
		view.addObject("member", new Member());
		return view;
	}
	
	
	/**
	 *  重新導向登入(登出後回到登入頁面用)
	 */
	@RequestMapping("redirectLogin")
	public ModelAndView getRedirectLogin(@ModelAttribute Member member) {
		getSession();
		ModelAndView view = new ModelAndView("login");
		return view;
	}
	
	
//	/**
//	 * Email驗證信, 連結通知頁面
//	 * @param req
//	 * @param res
//	 * @return
//	 */
//	@RequestMapping("redirectkolverfyresult")
//	public ModelAndView redirectkolverfyresult(HttpServletRequest req, HttpServletResponse res){
//		if(session==null){
//			return returnView("redirect:/login");
//		} else if(session!=null && session.getAttribute("code")==null){
//			return returnView("redirect:/login");
//		}
//		return returnView("kolverfyresult");
//	}
//	
//	/**
//	 * 密碼重設頁面
//	 * @param req
//	 * @param res
//	 * @return
//	 */
//	@RequestMapping("redirectForgetPassword")
//	public ModelAndView redirectForgetPassword(HttpServletRequest req, HttpServletResponse res){
//		if(session==null){
//			return returnView("redirect:/login");
//		} else if(session!=null && session.getAttribute("code")==null){
//			return returnView("redirect:/login");
//		}
//		return returnView("forgetpasswordresult");
//	}
	
	/**
	 * 登出回到首頁(Login)
	 */
	@RequestMapping("logout")
	public ModelAndView logout() {
		clearSession();
		return returnView("login");
	}
	
	
	/**
	 * 未知頁面(404)
	 * 
	 */
	@RequestMapping("undefined")
	public ModelAndView undefined() {
		return returnView("undefined");
	}
	
}
