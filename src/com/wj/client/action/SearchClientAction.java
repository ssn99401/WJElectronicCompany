package com.wj.client.action;

import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wj.client.model.DAO.ClientDAO;
import com.wj.utils.CommandAction;

public class SearchClientAction implements CommandAction {
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		ClientDAO clientDao = ClientDAO.getInstance();

		String cmd = request.getParameter("cmd");

		if(cmd.equals("searchId")) {

			String name = request.getParameter("name");
			String email = request.getParameter("email");

			String result = clientDao.searchId(name, email);

			request.setAttribute("result", result);
			request.setAttribute("name", name);
			return "/client/findIdPro.jsp";
		} else {
			String id = request.getParameter("id");
			String email = request.getParameter("email");
			int result = clientDao.searchPassword(id, email);
			request.setAttribute("result", result);
			request.setAttribute("email", email);

			// 비밀번호 찾기 성공시 메일 보내기
			if(result == 1) {

				// 네이버일 경우 smtp.naver.com 을 입력합니다. 
				// Google일 경우 smtp.gmail.com 을 입력합니다. 
				try{
					String tmpPw = makePassword();
					String host = "smtp.gmail.com"; 
					final String username = "mini3jsp"; //네이버 아이디를 입력해주세요. @nave.com은 입력하지 마시구요. 
					final String password = "qwer11@@"; //네이버 이메일 비밀번호를 입력해주세요. 
					int port=465; //포트번호 // 메일 내용 
					String recipient = email;  //받는 사람의 메일주소를 입력해주세요. 
					String subject = "(주)WJ전자 비밀번호 찾기";  //메일 제목 입력해주세요. 
					String body = "(주)WJ전자 비밀번호 찾기 결과\n'" + id + "' 님의 임시 비밀번호는 " + tmpPw + "입니다."; //메일 내용 입력해주세요. 
					Properties props = System.getProperties(); // 정보를 담기 위한 객체 생성 // SMTP 서버 정보 설정 
					props.put("mail.smtp.host", host); 
					props.put("mail.smtp.port", port); 
					props.put("mail.smtp.auth", "true"); 
					props.put("mail.smtp.ssl.enable", "true"); 
					props.put("mail.smtp.ssl.trust", host); 
					//Session 생성
					Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() { 
						String un=username; String pw=password; protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
							return new javax.mail.PasswordAuthentication(un, pw); 
						} 
					}); session.setDebug(true); 
					//for debug 
					Message mimeMessage = new MimeMessage(session); //MimeMessage 생성
					mimeMessage.setFrom(new InternetAddress("hanhun20230@naver.com"));
					mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient)); 
					mimeMessage.setSubject(subject); //제목셋팅 
					mimeMessage.setText(body); //내용셋팅 
					Transport.send(mimeMessage); //javax.mail.Transport.send() 이용
					clientDao.updateToTemporaryPassword(id, tmpPw);
				} catch(Exception e){
					e.printStackTrace();
					// 오류 발생시 뒤로 돌아가도록
					System.out.println("error");
				}
			}


			return "/client/findPasswordPro.jsp";
		}
	}

	public String makePassword() {
		Random rnd = new Random();
		String first = String.valueOf((char) ((int) (rnd.nextInt(26)) + 65));
		rnd = new Random();
		String second = String.valueOf((char) ((int) (rnd.nextInt(26)) + 65));
		int num = (int)(Math.random()*100000);

		String pw = first + second + num;
		return pw;
	}
}
