//package com.test.Service;
//
//import java.util.Properties;
//
//import javax.mail.Authenticator;
//import javax.mail.Message;
//import javax.mail.PasswordAuthentication;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.env.Environment;
//import org.springframework.stereotype.Service;
//
//@Service
//public class EmailServiceV2 {
//
//	@Autowired
//	private Environment env;
//	
//	public boolean sendEmail(String email)
//	{
//		String subject = "Test Mail";
//		Properties properties = new Properties();
//		
//		properties.put("mail.smtp.host", env.getProperty("spring.mail.host"));
//		properties.put("mail.smtp.port", env.getProperty("spring.mail.port"));
//		properties.put("mail.smtp.starttls.enable", "true");
//		properties.put("mail.smtp.auth", "true");
//		
//		Session session = Session.getInstance(properties,new Authenticator() {
//
//			@Override
//			protected PasswordAuthentication getPasswordAuthentication() {
//				// TODO Auto-generated method stub
////				System.out.println("Passsword = "+env.getProperty("spring.mail.password") );
//				return new PasswordAuthentication(env.getProperty("spring.mail.username"), env.getProperty("spring.mail.password"));
//			}
//			
//			
//		});
//		
//		MimeMessage m = new MimeMessage(session);
//		
//		try {
//			//from email
//			m.setFrom(env.getProperty("spring.mail.username"));
//			
//			//adding recipient to message
//			m.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
//			
//			//adding subject to message
//			m.setSubject(subject);
//			
//			m.setContent("<html>\n"
//					+ "<head>\n"
//					+ "<style>\n"
//					+ "   \n"
//					+ "\n"
//					+ "\n"
//					+ "\n"
//					+ ".custom-table {\n"
//					+ "    width: 100%;\n"
//					+ "    border-radius: 10px; /* Set border-radius for round corners */\n"
//					+ "    overflow: hidden; /* Ensure rounded corners are applied */\n"
//					+ "    position: relative;\n"
//					+ "    background-image: url('your-logo-image.jpg'); /* Replace with your logo image path */\n"
//					+ "    background-size: cover;\n"
//					+ "    background-repeat: no-repeat;\n"
//					+ "    background-color: #343a40\n"
//					+ "}\n"
//					+ "\n"
//					+ "\n"
//					+ "\n"
//					+ "th, td {\n"
//					+ "    border-collapse: collapse;\n"
//					+ "    color: white; /* Text color */\n"
//					+ "    padding: 10px;\n"
//					+ "    text-align:center;\n"
//					+ "}\n"
//					+ "\n"
//					+ "   </style>\n"
//					+ "</head>\n"
//					+ "<body>\n"
//					+ "\n"
//					+ "\n"
//					+ "<div class=\"main-container\">\n"
//					+ "<center><h2>Hello User</h2></center>\n"
//					+ "    <table class=\"custom-table\">\n"
//					+ "        <thead>\n"
//					+ "        <tr>\n"
//					+ "            <th>Column 1</th>\n"
//					+ "            <th>Column 2</th>\n"
//					+ "        </tr>\n"
//					+ "        </thead>\n"
//					+ "        <tbody>\n"
//					+ "        <tr>\n"
//					+ "            <td>Row 1, Cell 1</td>\n"
//					+ "            <td>Row 1, Cell 2</td>\n"
//					+ "        </tr>\n"
//					+ "        <tr>\n"
//					+ "            <td>Row 2, Cell 1</td>\n"
//					+ "            <td>Row 2, Cell 2</td>\n"
//					+ "        </tr>\n"
//					+ "        <tr>\n"
//					+ "            <td>Row 3, Cell 1</td>\n"
//					+ "            <td>Row 3, Cell 2</td>\n"
//					+ "        </tr>\n"
//					+ "        <tr>\n"
//					+ "            <td>Row 4, Cell 1</td>\n"
//					+ "            <td>Row 4, Cell 2</td>\n"
//					+ "        </tr>\n"
//					+ "        </tbody>\n"
//					+ "    </table>\n"
//					+ "</div>\n"
//					+ "</body>\n"
//					+ "</html>"
//					, "text/html");
//			
//			Transport.send(m);
//			System.out.println("Sent success...............");
//			return true;
//		}
//		catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//			return false;
//		}
//		
//	}
//	
//}
