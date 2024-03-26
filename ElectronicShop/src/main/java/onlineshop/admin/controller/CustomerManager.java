package onlineshop.admin.controller;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.jasper.tagplugins.jstl.core.Redirect;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import onlineshop.bean.UploadFile;
import onlineshop.dao.AccountDao;
import onlineshop.entity.CustomerEntity;

@Transactional
@Controller
public class CustomerManager {
	@Autowired
	SessionFactory factory;
	
	 @Autowired
	 
	 @Qualifier("uploadfile") UploadFile baseUploadfile;
	 
	 @Autowired
	AccountDao accountDao;
	 
	@RequestMapping(value="admin/customer/index")
	public String index(ModelMap model) {
		CustomerEntity Customer = new CustomerEntity();
		List<CustomerEntity> list = this.getCategories();
		
		model.addAttribute("entity", Customer);
		model.addAttribute("list", list);
		return "admin/customer/customer";
	}
	
	@RequestMapping(value="admin/customer/create") 
	public String create(ModelMap model,
	  @RequestParam("photo-file") MultipartFile file,
	  @ModelAttribute("entity") CustomerEntity Customer,
	  BindingResult errors) {
	  
		
		if(Customer.getId().trim().length() == 0) {
			errors.rejectValue("id","entity", "Vui lòng nhập tên tài khoản");
		}else if(accountDao.getCustomerById(Customer.getId().trim()) != null){
			errors.rejectValue("id","entity", "Tên tài khoản đã tồn tại");
		}
		if(Customer.getPassword().trim().length() == 0) {
			errors.rejectValue("password","entity", "Vui lòng nhập mật khẩu");
		}
		if(Customer.getFullname().trim().length() == 0) {
			errors.rejectValue("fullname","entity", "Vui lòng nhập họ tên người dùng");
		}
		if(Customer.getEmail().trim().length() == 0) {
			errors.rejectValue("email","entity", "Vui lòng nhập email");
		}
		if (errors.hasErrors()) {
			return "admin/customer/customer";
		}
		
	  if(file.isEmpty())
	  { 
		  Customer.setPhoto("c.jpg");
	  } 
	  else 
	  {
	  try { 
		  String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMddHHmmss-"));
		  String fileName = date + file.getOriginalFilename(); // byte[] bytes =
		  String photoPath = baseUploadfile.basePath + File.separator + fileName; 
		  System.out.println("Pathfile is: "+ photoPath);
		  
		  file.transferTo(new File(photoPath));
		  
		  Customer.setPhoto(fileName);
	  
	  } catch (Exception e) { model.addAttribute("message","Lổi upload file"); } 
	  }
	  this.createCustomer(Customer);
	  model.addAttribute("message","Thêm thành công !");
	  return "redirect:/admin/customer/index";

	}
	
	@RequestMapping(value="admin/customer/edit/{id}")
	public String edit(ModelMap model, @PathVariable("id") String id, @ModelAttribute("entity") CustomerEntity Customer) {
		CustomerEntity Customer1 = this.getCustomerById(id);
		List<CustomerEntity> list = this.getCategories();
		model.addAttribute("list", list);
		
		model.addAttribute("entity", Customer1);
		return "admin/customer/customer";
	}
	
	@RequestMapping(value="admin/customer/update")
	public String update(ModelMap model,
			  @RequestParam("photo-file") MultipartFile file,
			  @ModelAttribute("entity") CustomerEntity Customer ) {
		if(!file.isEmpty())
		  { 
		  try { 
			  String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMddHHmmss-"));
			  String fileName = date + file.getOriginalFilename(); // byte[] bytes =
			  String photoPath = baseUploadfile.basePath + File.separator + fileName; 
			  System.out.println("Pathfile is: "+ photoPath);
			  
			  file.transferTo(new File(photoPath));
			  
			  Customer.setPhoto(fileName);

		  
		  } catch (Exception e) {
			  model.addAttribute("message","Lổi upload file"); 
			  } 
		  }
		
		this.updateCustomer(Customer);
		model.addAttribute("message", "Cập nhật thành công!");
		
		return "redirect:/admin/customer/edit/"+ Customer.getId();
	}
	
	@RequestMapping(value="admin/customer/delete/{id}")
	public String delete(@PathVariable(value = "id") String id, ModelMap model) {
		CustomerEntity Customer = this.getCustomerById(id);
		this.deleteCustomer(Customer);
		 model.addAttribute("message", "Xóa thành công!"); 
		 return "redirect:/admin/customer/index";
	}
	
	
	@RequestMapping(value="admin/customer/delete")
	public String delete2(@ModelAttribute(value = "entity") CustomerEntity Customer, ModelMap model) {
		this.deleteCustomer(Customer);
			
		 model.addAttribute("message", "Xóa thành công!"); 
		 return "redirect:/admin/customer/index";
	}


	
	// DAO
	
	public void deleteCustomer(CustomerEntity cate) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.delete(cate);
			t.commit();
		} catch (Exception e) {
			t.rollback();
		}
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
	
	public CustomerEntity createCustomer(CustomerEntity cate) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(cate);
			t.commit();
		} catch (Exception e) {
			t.rollback();
		}
		return cate;
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
	
	public List<CustomerEntity> getCategories() {
		Session session = factory.getCurrentSession();
		String hql = "FROM CustomerEntity";
		Query query = session.createQuery(hql);
		List<CustomerEntity> list = query.list();
		return list;
	}
	
}

















