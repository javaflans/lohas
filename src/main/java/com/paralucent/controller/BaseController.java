package com.paralucent.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.accessibility.AccessibleRelation;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import com.paralucent.model.Member;
import com.paralucent.model.Menus;
import com.paralucent.model.VerifyAccount;
import com.paralucent.services.DataService;
import com.paralucent.services.VerifyService;

public class BaseController {

	@Autowired
	private DataService dataService;

	@Autowired
	private VerifyService verifyService;

	@Autowired
	private HttpServletRequest req;

	protected Logger log;

	public final static String LOGIN_TYPE = "login";
	public final static String LOGOUT_TYPE = "logout";
	protected HttpSession session;

	protected HttpSession getSession() {
		if (session == null || req.getSession(Boolean.FALSE) == null) {
			session = req.getSession();
			session.setMaxInactiveInterval(691200);
		}
		return session;
	}

	protected void clearSession() {
		req.getSession().invalidate();
		session = req.getSession(Boolean.TRUE);
	}

	protected String firstUplocate(String data) {
		if (StringUtils.isNotBlank(data)) {
			data = data.toUpperCase().charAt(0) + data.substring(1);
		}
		return data;
	}

	protected VerifyAccount validateAccount(Member account) {
		VerifyAccount verify = new VerifyAccount();
		Member result = verifyService.login(account);
		if (result != null && result.getId() > 0) {
			verify.setVerifyed(Boolean.TRUE);
			verify.setMemberUsername(account.getUserMail());
			verify.setMemberLocalName(result.getUserLocalName());
			verify.setMember(result);
			verify.setRemoteIpAddress(req.getRemoteAddr());
			verify.setRemoteHost(req.getRemoteHost() != null ? req.getRemoteHost() : "N/A");
			verify.setRemoteUser(req.getRemoteUser() != null ? req.getRemoteUser() : "N/A");
		} else {
			verify.setVerifyed(Boolean.FALSE);
			verify.setMemberUsername(account.getUserMail());
			verify.setRemoteIpAddress(req.getRemoteAddr());
			verify.setRemoteHost(req.getRemoteHost() != null ? req.getRemoteHost() : "N/A");
			verify.setRemoteUser(req.getRemoteUser() != null ? req.getRemoteUser() : "N/A");
		}
		return verify;
	}

	protected boolean logAccount(VerifyAccount verify, String type) {
		boolean result = Boolean.FALSE;
		if (StringUtils.equals(type, LOGIN_TYPE)) {
			verify.setLoginDate(new Date());
			result = verifyService.logAccoundData(verify);
		} else if (StringUtils.equals(type, LOGOUT_TYPE)) {
			verify.setLogoutDate(new Date());
			result = verifyService.logAccoundData(verify);
		}

		return result;
	}

	protected Session getMailSession() {
		final String username = "paralucent.service@gmail.com";
		final String password = "3I7UWT9D#";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session mailSession = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		return mailSession;
	}

	protected ModelAndView returnView(String forward) {
		session = getSession();
		if (session != null) {
			log.info("original session");
			if (session.getAttribute("loginAccount") != null) {
				@SuppressWarnings("unchecked")
				List<Menus> myMenus = (ArrayList<Menus>) session.getAttribute("menus");
				for (Menus thisMenu : myMenus) {
					if (thisMenu.getName().equals(forward)) {
						getSession().setAttribute("pageLabel", thisMenu.getLabel());
						getSession().setAttribute("menuName", forward);
						getSession().setAttribute("breadCrumb",
								thisMenu.getMenus() != null ? thisMenu.getMenus().getLabel() : "Home");
						break;
					}
				}
				log.info("Forward:  "+ forward);
				return new ModelAndView(forward);
			} else {
				return new ModelAndView("redirect:/redirectLogin");
			}

		} else if (session.isNew()) {
			log.info("new session");
			if (session == null | session.getAttribute("loginMessage") == null) {
				clearSession();
			}
			return new ModelAndView("redirect:/redirectLogin");
		} else {
			log.info("old session");
			if (session == null | session.getAttribute("loginMessage") == null) {
				clearSession();
			}
			return new ModelAndView("redirect:/redirectLogin");
		}
	}

	protected void genLogger() {
		log = Logger.getLogger(BaseController.class.getClass().getName());
	}

}
