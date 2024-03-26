package onlineshop.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import onlineshop.entity.CategoryEntity;

@Transactional
@Controller
public class SendMailController {
	@Autowired
	SessionFactory factory;
	
	@Autowired
	JavaMailSender mailer;
	
	@RequestMapping(value = "/product/form-mail/{id}")
	public String showFormMail(@PathVariable("id") int id, ModelMap model) {
		List<CategoryEntity> listCatelogies = this.getCategories();
		model.addAttribute("listCate", listCatelogies);
		
		model.addAttribute("id", id);
		return "user/product/sendMail";
	}
	
	@RequestMapping(value = "/product/send-mail", method = RequestMethod.POST)
	public String sendMail(HttpServletRequest request,ModelMap model,
			@RequestParam("name") String name,
			@RequestParam("to") String to,
			@RequestParam("subject") String subject,
			@RequestParam("message") String message,
			@RequestParam("id") int id) {
		List<CategoryEntity> listCatelogies = this.getCategories();
		model.addAttribute("listCate", listCatelogies);
		
		if(name.trim().length() == 0 || to.trim().length() == 0 || subject.trim().length() == 0 || message.trim().length() == 0) {
			if(name.trim().length() == 0) {
				model.addAttribute("error_name","Vui lòng nhập họ tên người gửi.");
			}
			if(to.trim().length() == 0) {
				model.addAttribute("error_to","Vui lòng nhập email nhận.");
			}
			if(subject.trim().length() == 0) {
				model.addAttribute("error_subject","Vui lòng nhập tiêu đề email.");
			}
			model.addAttribute("name", name);
			model.addAttribute("to", to);
			model.addAttribute("subject", subject);
			model.addAttribute("message", message);
			return "user/product/sendMail";
		}
		
		try {
			MimeMessage mail = mailer.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mail, true);
			
			helper.setFrom("thanhdever@gmail.com", name); //mail người gửi và họ tên
			helper.setTo(to); // mail người nhận
			helper.setReplyTo("thanhdever@gmail.com");
			helper.setSubject(subject);
			
			String link = request.getRequestURL().toString().replace("send-mail", "detail/" + id);
			
			helper.setText(message , "<hr><a href='"+link+"'>Xem chi tiết ...</a>");
			System.out.println(link);
			//Gửi mail
			mailer.send(mail);
			model.addAttribute("message", "Mail đã gửi thành công.");
			
		} catch (MessagingException | UnsupportedEncodingException e) {
			
			model.addAttribute("message", "Không thể gửi mail");
		}
		
		return "user/product/sendMail";
	}
	
	//		--- DAO ---
	
	public List<CategoryEntity> getCategories() {
		Session session = factory.getCurrentSession();
		String hql = "FROM CategoryEntity";
		Query query = session.createQuery(hql);
		List<CategoryEntity> list = query.list();
		return list;
	}
	
}
