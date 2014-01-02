package com.appsrox.messenger.server;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.appsrox.messenger.model.Contact;
import com.appsrox.messenger.model.EMFService;

@SuppressWarnings("serial")
public class RegisterServlet extends HttpServlet {
	
	private static final Logger logger = Logger.getLogger(RegisterServlet.class.getCanonicalName());
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/plain");
		resp.getWriter().println("Registers a device with the Demo server.");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter(Constants.SENDER_EMAIL);
		String regId = req.getParameter(Constants.REG_ID);
		logger.log(Level.WARNING, "regId: " + regId );
		logger.log(Level.WARNING, "email: " + email );

		EntityManager em = EMFService.get().createEntityManager();
		try {
			Contact contact = Contact.find(email, em);
			if (contact == null) {
				
				logger.log(Level.WARNING, "contact=0: " );
				contact = new Contact(email, regId);
			} else {
				contact.setRegId(regId);
			}
			em.persist(contact);
			logger.log(Level.WARNING, "Registered: " + contact.getId());
		} finally {
			em.close();
		}
	}	
	
}
