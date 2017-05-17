/*

* LoginController : Classe del tipo controller per gestire il login e logout
*

* version 1.0

*

* 11/09/2015

*

* ©iTrio

*/
package com.progetto.fumetteria.controller;

import java.util.List;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import com.progetto.fumetteria.model.User;
import com.progetto.fumetteria.service.UserService;
import com.progetto.fumetteria.util.ValuesUtils;

@Controller
@SessionAttributes("login")

public class LoginController {

	/* l'attributo userService serve per usare i metodi della classe UserDao */
	@Autowired
	private UserService userService;

	// return login form
	@RequestMapping(value = "loginForm", method = RequestMethod.GET)
	public String loginForm(Model model) {
		model.addAttribute("user", new User());
		return "login/form-login";
	}

	// metodo che esegue il login di un utente
	@RequestMapping(value = "loginForm", method = RequestMethod.POST)
	public String login(User user, HttpSession session, HttpServletRequest request) {
		// crea l'oggetto utente
		User loggedUser = userService.restart().filterBy("email", user.getEmail()).pick();
		String returnurl = "";
		if (session.getAttribute("returnurl") != null) {
			returnurl = ((String) session.getAttribute("returnurl")).replace(request.getContextPath(), "");
		}
		// verifica se è un utente già loggatto
		if (loggedUser != null) {
			// esegue login se le credenziali sono correte
			if (loggedUser.getPassword().equals(ValuesUtils.encrypt(user.getPassword()))) {
				session.setAttribute("loggedUser", loggedUser);

				// verifica il tipo di utente = permissione d'accesso
				if (loggedUser.getTypeUser().equals("Admin")) {
					if (returnurl != "") {
						return "redirect:" + returnurl;
					} else {
						return "redirect:admin";
					}
				} else {
					if (returnurl != "") {
						return "redirect:" + returnurl;
					} else
						return "redirect:home";
				}

			} else {
				return "redirect:loginForm";
			}
		}
		return "redirect:loginForm";

	}

	// esegue il login via ajax
	@RequestMapping(value = "loginajax", method = RequestMethod.POST)
	public @ResponseBody String doLogin(@RequestParam String email, @RequestParam String password, HttpSession session,
			HttpServletRequest request) {

		User loggedUser = userService.restart().filterBy("email", email).pick();

		if (loggedUser != null) {
			if (loggedUser.getPassword().equals(ValuesUtils.encrypt(password))) {
				session.setAttribute("loggedUser", loggedUser);
				if (loggedUser.getTypeUser().equals("Admin")) {
					return "loginOk";
				} else {
					return "loginOk";
				}
			} else {
				return "error";
			}
		}
		return "error";
	}

	// metodo che gestice la funzionalità "forgot password"
	@RequestMapping(value = "forgotpassword", method = RequestMethod.GET)
	public @ResponseBody String forgotpassword(@RequestParam String email, HttpSession session,
			HttpServletRequest request) {
		/*
		 * invia una mail all'utente con quella mail (se esiste) con le
		 * istruzione per cambiare la password
		 */
		User user = userService.restart().filterBy("email", email).pick();
		user.setToken(ValuesUtils.encrypt(user.getEmail() + user.getId()));
		userService.save(user);
		sentEmail(user.getEmail(), getURLWithContextPath(request) + "/forgotpassword/change?id=" + user.getId()
				+ "&token=" + user.getToken());
		return "enviadoOK";
	}

	// return view to change password
	@RequestMapping(value = "forgotpassword/change", method = RequestMethod.GET)
	public String changePassword(@RequestParam Integer id, @RequestParam String token, Model model, HttpSession session,
			HttpServletRequest request) {
		model.addAttribute("user_id", id);
		User user = userService.restart().filterBy("id", id).pick();
		if (user.getToken().equals(token)) {

			model.addAttribute("contextPath", getURLWithContextPath(request));
			return "login/forgotepassword";
		} else {
			return "error";
		}
	}

	public String getURLWithContextPath(HttpServletRequest request) {
		return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
				+ request.getContextPath();
	}

	@RequestMapping(value = "forgotpassword/changed", method = RequestMethod.POST)
	public @ResponseBody String changePass(@RequestParam String newPassword, @RequestParam Integer user_id, Model model,
			HttpSession session, HttpServletRequest request) {

		User user = userService.restart().filterBy("id", user_id).pick();

		user.setPassword(ValuesUtils.encrypt(newPassword));
		userService.save(user);

		return "changeOk";
	}

	// metodo che invia l'email per fare il cambiamento di password dell'utente
	public void sentEmail(String destinatario, String url) {

		Properties props = new Properties();
		/** Parametri di conessione con il server Gmail */
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("comics.negozio@gmail.com", "progettoIS2");
			}
		});
		/** Debug for session */
		session.setDebug(true);

		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress("comics.negozio@gmail.com")); // mitente

			Address[] toUser = InternetAddress // destinatario
					.parse(destinatario);

			message.setRecipients(Message.RecipientType.TO, toUser);
			message.setSubject("Forgot Password");// contenuto

			/** metodo che invia il messaggio creato */

			// Create the message part
			MimeBodyPart messageBodyPart = new MimeBodyPart();

			// Fill the message
			messageBodyPart.setText("Salve! Per modificare la sua password fa un click su questo link: <a href='" + url
					+ "' >Forgot password</a> <br> Tanti saluti!", "UTF-8", "html");

			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);

			// Put parts in message
			message.setContent(multipart);

			// Send the message
			Transport.send(message);

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	// metodo che esegue l'azione di logout
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {

		session.setAttribute("loggedUser", null);

		return "redirect:home";

	}

}
