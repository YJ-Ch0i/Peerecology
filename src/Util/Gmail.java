package Util;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class Gmail extends Authenticator{

	@Override
	protected PasswordAuthentication getPasswordAuthentication()
	{
		//이메일을 보낼 아이디와 비밀번호.
		return new PasswordAuthentication("yeong_4310@yi.ac.kr", "likEasTar$310");
	}
	
}
