package net.yawk.client.utils.yggdrasil;

import java.net.Proxy;

import net.minecraft.util.Session;

import com.mojang.authlib.Agent;
import com.mojang.authlib.exceptions.AuthenticationException;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import com.mojang.authlib.yggdrasil.YggdrasilUserAuthentication;

/*
* Author: Opim10
* Version: 1
* Use: Login to Minecraft with Yggdrasil;
*/

public class YggdrasilAuthenticator {
	
	public YggdrasilPayload Payload = new YggdrasilPayload();
	public String Username;
	public String Password;
	private Session session;
	
	public YggdrasilAuthenticator(String Username, String Password)
	{
		this.Username = Username;
		this.Password = Password;
	}
	
	public boolean login(){
		if(Password != "" && Password != null)
		{
			Session AuthResponse = Payload.loginPassword(this.Username, this.Password);
			if(AuthResponse != null){
				session = (AuthResponse);
				return true;
			}
			
		}else{
			Session AuthResponseCrack = Payload.loginCrack(this.Username);
			session = (AuthResponseCrack);
			return true;
		}
		
		return false;
	}
	
	public Session getSession(){
		return session;
	}
}
