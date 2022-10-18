package com.email.services;



import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.email.dao.EmailRepository;
import com.email.model.Email;

@Service
public class EmailService {
	
	@Autowired
	EmailRepository ER;

	public boolean sendemail(String subj,String message,String to,Email E)
	{
		
		boolean f=false;
		
		String from="hamzajamil8041@gmail.com";
		//Gmail Host
        String host="smtp.gmail.com";

        //get system properties
        Properties properties = System.getProperties();
        System.out.println("Properties" + properties);

        //Setting imp info

        properties.put("mail.smtp.host",host);

        properties.put("mail.smtp.port","465");
        properties.put("mail.smtp.ssl.enable","true");
        properties.put("mail.smtp.auth","true");

        //Step1 get session obj

       Session session= Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("hamzajamil8041@gmail.com","ouducttjyslgqwnx");
            }
        });

       session.setDebug(true);


       //Step 2 Compose Message

        MimeMessage M=new MimeMessage(session);


        try

        {

        	InternetAddress I =new InternetAddress(to);
        	
            M.setFrom(from);

            M.addRecipient(Message.RecipientType.TO, I);
      //M.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            M.setSubject(subj);
            M.setText(message);


            //Step 3 send message using Transport class

            Transport.send(M);

            System.out.println("Sent Successfully");


            f=true;

            
            Email E1=this.ER.save(E);
            
        }
catch (Exception e)
{
	System.out.println("Error sending email");
    e.printStackTrace();
}

return f;

		
	}
}
