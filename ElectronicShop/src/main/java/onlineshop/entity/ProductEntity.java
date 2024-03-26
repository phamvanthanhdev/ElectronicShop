package onlineshop.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "products")
public class ProductEntity {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	//private int id_category;
	private String name;
	private float price;
	private int sale;
	private String title;
	private boolean highlight;
	private boolean new_product;
	private int quantity;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date created_at;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date updated_at;
	private String img_url;
	private int viewCount;
	
	@ManyToOne
	@JoinColumn(name="id_category")
	CategoryEntity categoryEntity;
	
	
	@OneToMany(mappedBy = "productEntity")
	List<OrderDetail> orderDs;
	
	@OneToMany(mappedBy = "products")
	List<CartItemsEntity> listItems;
	
	@OneToMany(mappedBy = "product")
	List<FavouriteEntity> favourites;
	

	public List<OrderDetail> getOrderDs() {
		return orderDs;
	}

	public void setOrderDs(List<OrderDetail> orderDs) {
		this.orderDs = orderDs;
	}

	

	public String getImg_url() {
		return img_url;
	}

	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getSale() {
		return sale;
	}

	public void setSale(int sale) {
		this.sale = sale;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isHighlight() {
		return highlight;
	}

	public void setHighlight(boolean highlight) {
		this.highlight = highlight;
	}

	public boolean isNew_product() {
		return new_product;
	}

	public void setNew_product(boolean new_product) {
		this.new_product = new_product;
	}

	

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public Date getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}
	

	public int getViewCount() {
		return viewCount;
	}

	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}

	public CategoryEntity getCategoryEntity() {
		return categoryEntity;
	}

	public void setCategoryEntity(CategoryEntity categoryEntity) {
		this.categoryEntity = categoryEntity;
	}

	public List<CartItemsEntity> getListItems() {
		return listItems;
	}

	public void setListItems(List<CartItemsEntity> listItems) {
		this.listItems = listItems;
	}

	public List<FavouriteEntity> getFavourites() {
		return favourites;
	}

	public void setFavourites(List<FavouriteEntity> favourites) {
		this.favourites = favourites;
	}

	
}











