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
* Use: Yggdrasil Utils for YggdrasilAuthenticator;
*/

public class YggdrasilPayload {
	
	public static Session loginPassword(String username, String password)
    {
        if(username == null || username.length() <= 0 || password == null || password.length() <= 0)
            return null;

        YggdrasilAuthenticationService a = new YggdrasilAuthenticationService(Proxy.NO_PROXY, "");
        YggdrasilUserAuthentication b = (YggdrasilUserAuthentication)a.createUserAuthentication(Agent.MINECRAFT);
        b.setUsername(username);
        b.setPassword(password);
        try
        {
            b.logIn();
            return new Session(b.getSelectedProfile().getName(), b.getSelectedProfile().getId().toString(), b.getAuthenticatedToken(), "mojang");
        } catch (AuthenticationException e)
        {
            e.printStackTrace();
            System.out.println("Failed login: "+username+":"+password);
        }
        return null;
    } 
	
	public static Session loginCrack(String username)
    {
		return new Session(username, "", "", "mojang");
    } 
	
}
