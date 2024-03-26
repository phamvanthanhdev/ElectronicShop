package onlineshop.controller;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import onlineshop.bean.UploadFile;
import onlineshop.dao.AccountDao;
import onlineshop.dao.CategoryDao;
import onlineshop.entity.CartEntity;
import onlineshop.entity.CartItemsEntity;
import onlineshop.entity.CategoryEntity;
import onlineshop.entity.CustomerEntity;
import onlineshop.entity.OrderEntity;

@Controller
@Transactional
public class AccountController {
	@Autowired
	SessionFactory factory;
	
	@Autowired
	CategoryDao categoryDao;
	
	@Autowired
	AccountDao accountDao;
	
	
	@Autowired
	JavaMailSender mailer;
	
	@Autowired
	 
	 @Qualifier("uploadfile") UploadFile baseUploadfile;
	
	private static int random_int;
	
	// hiển thị form Đăng nhập
	
	@RequestMapping(value = "/account/login", method = RequestMethod.GET)
	public String login(ModelMap model) {
		List<CategoryEntity> listCatelogies = categoryDao.getCategories();
		model.addAttribute("listCate", listCatelogies);
		
		return "user/account/login";
	}
	
	// submit form đăng nhập
	
	@RequestMapping(value = "/account/login", method = RequestMethod.POST)
	public String checkLogin(HttpServletRequest request, ModelMap model,
			@RequestParam("id") String id,
			@RequestParam("pw") String pw) {
		List<CategoryEntity> listCatelogies = categoryDao.getCategories();
		model.addAttribute("listCate", listCatelogies);
		
		if(id.trim().length() == 0 && pw.trim().length() == 0) {
			model.addAttribute("error_id","Vui lòng nhập tên tài khoản.");
			model.addAttribute("error_pw","Vui lòng nhập mật khẩu.");
			return "user/account/login";
		}else if(id.trim().length() == 0) {
			model.addAttribute("error_id","Vui lòng nhập tên tài khoản.");
			model.addAttribute("pw", pw);
			return "user/account/login";
		}else if(pw.trim().length() == 0) {
			model.addAttribute("error_pw","Vui lòng nhập mật khẩu.");
			model.addAttribute("id", id);
			return "user/account/login";
		}
		
		CustomerEntity customer = accountDao.getCustomerLogin(id, pw);
		if(customer != null) {
				if (customer.isActivated() == true) {
					HttpSession session = request.getSession();
					session.setAttribute("account", customer);

					// Hiển thị số lượng và tổng tiền sản phẩm trong giỏ hàng
					return "redirect:/";
				} else {
					model.addAttribute("message", "Tài khoản chưa được kích hoạt");
					return "user/account/login";
				}
			}
		
		model.addAttribute("message", "Kiểm tra lại tên đăng nhập hoặc mật khẩu");
		return "user/account/login";
	}
	
	// Đăng xuất
	
	@RequestMapping(value = "/account/logout" , method = RequestMethod.GET )
	public String logout(HttpServletRequest request, ModelMap model) {
		HttpSession session = request.getSession();

		session.removeAttribute("account");

		return "redirect:/account/login";
	}
	
	//Đổi mật khẩu
	
	@RequestMapping(value = "/account/change", method = RequestMethod.GET)
	public String showChangePassword(ModelMap model) {
		List<CategoryEntity> listCatelogies = categoryDao.getCategories();
		model.addAttribute("listCate", listCatelogies);
		
		
		return "user/account/change";
	}
	
	
	@RequestMapping(value = "/account/change", method = RequestMethod.POST)
	public String changePassword(ModelMap model,
			@RequestParam("id") String id,
			@RequestParam("pw") String pw,
			@RequestParam("pw1") String pw1,
			@RequestParam("pw2") String pw2) {
		List<CategoryEntity> listCatelogies = categoryDao.getCategories();
		model.addAttribute("listCate", listCatelogies);
		
		if(id.trim().length() == 0 || pw.trim().length() == 0 || pw1.trim().length() == 0 || pw2.trim().length() == 0) {
			if(id.trim().length() == 0) {
				model.addAttribute("error_id","Vui lòng nhập tên tài khoản.");
			}
			if(pw.trim().length() == 0) {
				model.addAttribute("error_pw","Vui lòng nhập mật khẩu.");
			}
			if(pw1.trim().length() == 0) {
				model.addAttribute("error_pw1","Vui lòng nhập mật khẩu mới.");
			}
			if(pw2.trim().length() == 0) {
				model.addAttribute("error_pw2","Vui lòng nhập mật khẩu xác nhận.");
			}
			model.addAttribute("id", id);
			model.addAttribute("pw", pw);
			model.addAttribute("pw1", pw1);
			model.addAttribute("pw2", pw2);
			return "user/account/change";
		}

		CustomerEntity cus = accountDao.getCustomerLogin(id, pw);
		
		if(cus == null) {
			model.addAttribute("message", "Tài khoản chưa chính xác");
			return "user/account/change";
		}
		else if(!pw1.equals(pw2)) {
			model.addAttribute("message", "Mật khẩu xác nhận chưa chính xác");
			return "user/account/change";
		}
		else {
			cus.setPassword(pw2);
			accountDao.updateCustomer(cus);
			model.addAttribute("message", "Thay đổi mật khẩu thành công");
			return "user/account/change";
		}
	}
	
	@RequestMapping(value = "/account/edit", method = RequestMethod.GET)
	public String showEditAccount(HttpServletRequest request, ModelMap model) {
		List<CategoryEntity> listCatelogies = categoryDao.getCategories();
		model.addAttribute("listCate", listCatelogies);
		
		HttpSession session = request.getSession();
		CustomerEntity cus = (CustomerEntity) session.getAttribute("account");
		
		model.addAttribute("Customers", cus);
		if(!cus.getPhoto().trim().equals(null)) {
			model.addAttribute("image", cus.getPhoto());
		}
		
		return "user/account/edit";
	}
	
	@RequestMapping(value = "/account/edit", method = RequestMethod.POST)
	public String editAccount(HttpServletRequest request, ModelMap model,
			@RequestParam("photo-file") MultipartFile file,
			@ModelAttribute("Customers") CustomerEntity customer,
			BindingResult errors) {
		List<CategoryEntity> listCatelogies = categoryDao.getCategories();
		model.addAttribute("listCate", listCatelogies);
		
		if(customer.getId().trim().length() == 0) {
			errors.rejectValue("id","Customers", "Vui lòng nhập tên tài khoản.");
		}
		if(customer.getFullname().trim().length() == 0) {
			errors.rejectValue("fullname","Customers", "Vui lòng nhập họ tên.");
		}
		if(customer.getEmail().trim().length() == 0) {
			errors.rejectValue("email","Customers", "Vui lòng nhập email");
		}
		if (errors.hasErrors()) {
			return "user/account/edit";
		}
		
		if(!file.isEmpty())
		  { 
		  try { 
			  String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMddHHmmss-"));
			  String fileName = date + file.getOriginalFilename(); // byte[] bytes =
			  String photoPath = baseUploadfile.basePath + File.separator + fileName; 
			  System.out.println("Pathfile is: "+ photoPath);
			  
			  file.transferTo(new File(photoPath));
			  
			  customer.setPhoto(fileName);
		  	} catch (Exception e) {
			  model.addAttribute("message","Lổi upload file"); 
			} 
		  }
		
		accountDao.updateCustomer(customer);
		HttpSession session = request.getSession();
		session.setAttribute("account", customer);
		
		model.addAttribute("message", "Cập nhật thông tin tài khoản thành công");
		return "user/account/edit";
	}
	
	
	@RequestMapping(value = "/account/signup", method = RequestMethod.GET)
	public String showSignup(ModelMap model) {
		List<CategoryEntity> listCatelogies = categoryDao.getCategories();
		model.addAttribute("listCate", listCatelogies);
		
		CustomerEntity cus = new CustomerEntity();
		model.addAttribute("Customers", cus);
		
		return "user/account/signup";
	}
	
	
	@RequestMapping(value = "/account/signup" , method = RequestMethod.POST)
	public String signUp(HttpServletRequest request, ModelMap model,
			@RequestParam("photo-file") MultipartFile file,
			@ModelAttribute("Customers") CustomerEntity customer,
			BindingResult errors) {
		List<CategoryEntity> listCatelogies = categoryDao.getCategories();
		model.addAttribute("listCate", listCatelogies);

		if(customer.getId().trim().length() == 0) {
			errors.rejectValue("id","Customers", "Vui lòng nhập tên tài khoản.");
		}
		if(customer.getPassword().trim().length() == 0) {
			errors.rejectValue("password","Customers", "Vui lòng nhập mật khẩu.");
		}
		if(customer.getFullname().trim().length() == 0) {
			errors.rejectValue("fullname","Customers", "Vui lòng nhập họ tên.");
		}
		if(customer.getEmail().trim().length() == 0) {
			errors.rejectValue("email","Customers", "Vui lòng nhập email");
		}
		if (errors.hasErrors()) {
			return "user/account/signup";
		}
		
		if(file.isEmpty())
		  { 
			  customer.setPhoto("c.jpg");
		  } 
		  else 
		  {
			  try { 
				  String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMddHHmmss-"));
				  String fileName = date + file.getOriginalFilename(); // byte[] bytes =
				  String photoPath = baseUploadfile.basePath + File.separator + fileName; 
				  
				  file.transferTo(new File(photoPath));
				  
				  customer.setPhoto(fileName);
			  	} catch (Exception e) {
				  model.addAttribute("message","Lổi upload file"); 
				} 
			  
		  }
		customer.setActivated(false);
		customer.setAdmin(false);

		CustomerEntity customerEntity = accountDao.createCustomer(customer);

		// Gửi mail kich hoạt
		try {
			MimeMessage mail = mailer.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mail, true);

			String name = "Online Shop";
			String mailTo = customerEntity.getEmail();
			String subject = "Kích hoạt tài khoản";
			String message = "Kích hoạt tài khoản Online Shop";

			helper.setFrom("thanhdever@gmail.com", name);
			helper.setTo(mailTo); // mail người nhận
			helper.setReplyTo("thanhdever@gmail.com");
			helper.setSubject(subject);

			//String link = request.getRequestURL().toString().replace("signup",
			//		"activate/" + customer.getId().toString());
			//helper.setText(message, "<hr><a href='" + link + "'>Kích hoạt tài khoản</a>");
			
			random_int = (int)(Math.random() * (9999 - 1000 + 1) + 1000);
			helper.setText(message, "Kích hoạt tài khoản : " + random_int);
			
			// Gửi mail
			mailer.send(mail);
			
			model.addAttribute("message", "Mail kích hoạt tài khoản gửi thành công tới " + mailTo);

		} catch (MessagingException | UnsupportedEncodingException e) {

			model.addAttribute("message", "Không thể gửi mail");
			return "user/account/signup";
		}
		
		
		model.addAttribute("message", "Tạo tài khoản thành công. Mail kích hoạt đã được gửi.");
		//return "user/account/login";
		return "user/account/activate";
	}
	
	@RequestMapping(value = "/account/activate", method = RequestMethod.POST)
	public String activate(ModelMap model, @RequestParam("id") String id,
			@RequestParam("makh") String makh) {
		List<CategoryEntity> listCatelogies = categoryDao.getCategories();
		model.addAttribute("listCate", listCatelogies);
		
		int makh1 = Integer.parseInt(makh);
		CustomerEntity cus = new CustomerEntity();
		cus = this.getCustomerById(id);
		if(cus == null) {
			model.addAttribute("message", "Tên tài khoản không chính xác.");
			return "user/account/activate";
		}
		if(makh1 != random_int) {
			model.addAttribute("message", "Sai mã kích hoạt.");
			return "user/account/activate";
		}
		cus.setActivated(true);
		this.updateCustomer(cus);
		model.addAttribute("message", "Kích hoạt tài khoản thành công.");
		
		return "user/account/login";
	}
	
	@RequestMapping(value = "/account/forgot", method = RequestMethod.GET)
	public String forgot(ModelMap model, @ModelAttribute("Customers") CustomerEntity customer) {
		List<CategoryEntity> listCatelogies = categoryDao.getCategories();
		model.addAttribute("listCate", listCatelogies);
		
		
		return "user/account/forgot";
	}
	
	@RequestMapping(value = "/account/forgot", method = RequestMethod.POST)
	public String forgotPassword(HttpServletRequest request, ModelMap model,
			@ModelAttribute("Customers") CustomerEntity customer,
			BindingResult errors) {
		List<CategoryEntity> listCatelogies = categoryDao.getCategories();
		model.addAttribute("listCate", listCatelogies);
		
		if(customer.getId().trim().length() == 0) {
			errors.rejectValue("id","Customers", "Vui lòng nhập tên tài khoản.");
		}
		if (errors.hasErrors()) {
			return "user/account/forgot";
		}

		CustomerEntity customerEntity = accountDao.getCustomerById(customer.getId());
			if (customerEntity.getId().trim().equals(customer.getId())) {
				try {
					MimeMessage mail = mailer.createMimeMessage();
					MimeMessageHelper helper = new MimeMessageHelper(mail, true);

					String name = "Online Shop";
					String mailTo = customerEntity.getEmail();
					String subject = "Quên mật khẩu";

					helper.setFrom("thanhdever@gmail.com", name);
					helper.setTo(mailTo); // mail người nhận
					helper.setReplyTo("thanhdever@gmail.com");
					helper.setSubject(subject);

					helper.setText("Mật khẩu của bạn là : " + customerEntity.getPassword());

					mailer.send(mail);
					model.addAttribute("message", "Mật khẩu của bạn đã được gửi tới email.");

				} catch (MessagingException | UnsupportedEncodingException e) {

					model.addAttribute("message", "Không thể gửi mail");
				}
				return "user/account/login";
			}
		
		model.addAttribute("message", "Tài khoản chưa chính xác");
		return "user/account/forgot";
	}
	
	public CustomerEntity getCustomerById(String id) {
		Session session = factory.getCurrentSession();
		String hql = "FROM CustomerEntity c where c.id=:id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		List<CustomerEntity> listcate = query.list();
		CustomerEntity cate  = new CustomerEntity();
		if(listcate.size() > 0) {
			cate = (CustomerEntity) query.list().get(0);
			return cate;
		}
		return null;
	}
	
	public CustomerEntity updateCustomer(CustomerEntity cate) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(cate);
			t.commit();
		} catch (Exception e) {
			t.rollback();
		}
		return cate;
	}
	
	public List<CustomerEntity> update(String id) {
		Session session = factory.getCurrentSession();
		Transaction t = session.beginTransaction();
		String hql = "FROM CustomerEntity";
		Query query = session.createQuery(hql);
		List<CustomerEntity> list = query.list();
		for (CustomerEntity customerEntity : list) {
			if(customerEntity.getId().equals(id)) {
				session.update(customerEntity);
			}
		}
		return list;
	}
}





