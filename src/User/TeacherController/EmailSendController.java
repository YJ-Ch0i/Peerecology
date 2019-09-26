package User.TeacherController;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Controller.Controller;
import Service.TeacherService;
import Util.Gmail;
import Util.SHA256;

public class EmailSendController implements Controller {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		TeacherService dao = TeacherService.getInstance();
		HttpSession session = request.getSession();
		String tea_id = null;
		if(session.getAttribute("tea_id") != null)
		{
			tea_id = (String) session.getAttribute("tea_id");
		}
		if(tea_id == null)
		{
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('Please Login First!');");
			script.println("location.href = '/PeerSys/index.jsp'");
			script.println("</script>");
			script.close();
			return;
		}

		//Timestamp curTime = new Timestamp(System.currentTimeMillis());
		boolean emailChecked = dao.getEamilChecked(tea_id);
		if(emailChecked == true)
		{
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('Already Certified!');");
			script.println("location.href = '/PeerSys/teacherIndex.jsp'");
			script.println("</script>");
			script.close();
			return;
		}
		
		//로컬용 주소 호스트
		//String host = "http://localhost:8080/PeerSys/";
		
		//서버용 주소 호스트
		String host = "http://peerecology.cafe24.com/PeerSys/";
		String href = "emailCheckAction.tc";
		String from = "yeong_4310@yi.ac.kr";
		String to = tea_id;
		String subject = "설문조사 페이지의 이메일 인증 안내 메일입니다.";
		String content = "안녕하세요, " + subject + "다음 링크로 접속하여 인증을 진행해 주세요." + "<a href='" + host + href + "?code=" + new SHA256().getSHA256(to) + "'>이메일 인증하러 가기</a>";
							
		Properties p = new Properties();
		p.put("mail.smtp.user", from);
		p.put("mail.smtp.host", "smtp.gmail.com");
		p.put("mail.smtp.port", "465");
		p.put("mail.smtp.starttls.enable", "true");
		p.put("mail.smtp.auth", "true");
		p.put("mail.smtp.debug", "true");
		p.put("mail.smtp.socketFactory.port", "465");
		p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		p.put("mail.smtp.socketFactory.fallback", "false");
		
		try
		{
			Authenticator auth = new Gmail();
		    Session ses = Session.getInstance(p, auth);
		    ses.setDebug(true);
		    MimeMessage msg = new MimeMessage(ses); 
		    msg.setSubject(subject);
		    Address fromAddr = new InternetAddress(from);
		    msg.setFrom(fromAddr);
		    Address toAddr = new InternetAddress(to);
		    msg.addRecipient(Message.RecipientType.TO, toAddr);
		    msg.setContent(content, "text/html;charset=UTF-8");
		    Transport.send(msg);
		    
		    PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('Please Email Check!!.');");
			script.println("location.href = '/PeerSys/index.jsp'");
			script.println("</script>");
			script.close();
			return;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('Error!!!!!');");
			script.println("history.back();");
			script.println("</script>");
			script.close();
			return;
		}
	}
}
