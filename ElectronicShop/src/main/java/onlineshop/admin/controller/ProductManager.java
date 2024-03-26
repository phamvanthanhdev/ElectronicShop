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
import onlineshop.entity.CategoryEntity;
import onlineshop.entity.ProductEntity;

@Transactional
@Controller
public class ProductManager {
	@Autowired
	SessionFactory factory;
	
	 @Autowired
	 
	 @Qualifier("uploadfile") UploadFile baseUploadfile;
	
	
	@RequestMapping(value="admin/product/index")
	public String index(ModelMap model) {
		ProductEntity product = new ProductEntity();
		List<ProductEntity> list = this.getProducts();
		
		List<CategoryEntity> listCate = this.getCategories();
		model.addAttribute("listCate", listCate);
		
		model.addAttribute("entity", product);
		model.addAttribute("list", list);
		return "admin/product/product";
	}
	
	@RequestMapping(value="admin/product/create") 
	public String create(ModelMap model,
	  @RequestParam("photo-file") MultipartFile file,
	  @ModelAttribute("entity") ProductEntity product,
	  BindingResult errors) {
	
		if(product.getName().trim().length() == 0) {
			errors.rejectValue("name","entity", "Vui lòng nhập tên sản phẩm");
		}
		if(product.getPrice() == 0.0) {
			errors.rejectValue("price","entity", "Vui lòng nhập giá");
		}
		if(product.getSale() == 0.0) {
			errors.rejectValue("sale","entity", "Vui lòng nhập phần trăm giảm giá");
		}
		if (errors.hasErrors()) {
			return "admin/product/product";
		}
		
	  if(file.isEmpty())
	  { 
		  product.setImg_url("12.jpg");
	  } 
	  else 
	  {
	  try { 
		  String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMddHHmmss-"));
		  String fileName = date + file.getOriginalFilename(); // byte[] bytes =
		  String photoPath = baseUploadfile.basePath + File.separator + fileName; 
		  System.out.println("Pathfile is: "+ photoPath);
		  
		  file.transferTo(new File(photoPath));
		  
		  product.setImg_url(fileName);
	  
	  } catch (Exception e) { model.addAttribute("message","Lổi upload file"); } 
	  }
	  this.createProduct(product);
	  model.addAttribute("message","Thêm thành công !");
	  return "redirect:/admin/product/index";

	}
	
	@RequestMapping(value="admin/product/edit/{id}")
	public String edit(ModelMap model, @PathVariable("id") int id, @ModelAttribute("entity") ProductEntity product) {
		List<CategoryEntity> listCate = this.getCategories();
		model.addAttribute("listCate", listCate);
		
		ProductEntity product1 = this.getProductById(id);
		List<ProductEntity> list = this.getProducts();
		model.addAttribute("list", list);
		
		model.addAttribute("entity", product1);
		return "admin/product/product";
	}
	
	@RequestMapping(value="admin/product/update")
	public String update(ModelMap model,
			  @RequestParam("photo-file") MultipartFile file,
			  @ModelAttribute("entity") ProductEntity product ) {
		if(!file.isEmpty())
		  { 
		  try { 
			  String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMddHHmmss-"));
			  String fileName = date + file.getOriginalFilename(); // byte[] bytes =
			  String photoPath = baseUploadfile.basePath + File.separator + fileName; 
			  System.out.println("Pathfile is: "+ photoPath);
			  
			  file.transferTo(new File(photoPath));
			  
			  product.setImg_url(fileName);

		  
		  } catch (Exception e) {
			  model.addAttribute("message","Lổi upload file"); 
			  } 
		  }
		
		this.updateProduct(product);
		model.addAttribute("message", "Cập nhật thành công!");
		
		return "redirect:/admin/product/edit/"+ product.getId();
	}
	
	@RequestMapping(value="admin/product/delete/{id}")
	public String delete(@PathVariable(value = "id") int id, ModelMap model) {
		
		ProductEntity product = this.getProductById(id);
		this.deleteProduct(product);
		 model.addAttribute("message", "Xóa thành công!"); 
		 return "redirect:/admin/product/index";
	}
	
	
	@RequestMapping(value="admin/product/delete")
	public String delete2(@ModelAttribute(value = "entity") ProductEntity product, ModelMap model) {
		this.deleteProduct(product);
			
		 model.addAttribute("message", "Xóa thành công!"); 
		 return "redirect:/admin/product/index";
	}


	
	// DAO
	
	public void deleteProduct(ProductEntity cate) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.delete(cate);
			t.commit();
		} catch (Exception e) {
			t.rollback();
		}
	}
	
	public ProductEntity updateProduct(ProductEntity cate) {
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
	
	public ProductEntity createProduct(ProductEntity cate) {
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
	
	public ProductEntity getProductById(int id) {
		Session session = factory.getCurrentSession();
		String hql = "FROM ProductEntity c where c.id=:id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		List<ProductEntity> listcate = query.list();
		ProductEntity cate  = new ProductEntity();
		if(listcate.size() > 0) {
			cate = (ProductEntity) query.list().get(0);
			return cate;
		}
		return null;
	}
	
	public List<ProductEntity> getProducts() {
		Session session = factory.getCurrentSession();
		String hql = "FROM ProductEntity";
		Query query = session.createQuery(hql);
		List<ProductEntity> list = query.list();
		return list;
	}
	
	public List<CategoryEntity> getCategories() {
		Session session = factory.getCurrentSession();
		String hql = "FROM CategoryEntity";
		Query query = session.createQuery(hql);
		List<CategoryEntity> list = query.list();
		return list;
	}
}

















