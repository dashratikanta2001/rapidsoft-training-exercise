package com.sp.event.listener;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.sp.entity.User;
import com.sp.event.RegistrationCompleteEvent;
import com.sp.service.UserService;

@Component
public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent>{

	
	@Autowired
	private UserService userService;
	
	@Override
	public void onApplicationEvent(RegistrationCompleteEvent event) {
		// TODO Auto-generated method stub
		//Create varification token for the User with the link
		
		User user = event.getUser();
		String token = UUID.randomUUID().toString();
		
		userService.saveVarificationTokenForUser(token, user);
		
		//send mail to the user
		
		String url = event.getApplicationUrl()+"/verifyRegistration?token="+token;
		//sendVerificationEmail() implement here.
		
		System.out.println("Click the link to verify your account: \n"+url+"\n");
	}

}
