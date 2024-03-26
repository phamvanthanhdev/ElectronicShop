package onlineshop.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "favorite")
public class FavouriteEntity {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	//private String customerId;
	//private int productid;
	@ManyToOne
	@JoinColumn(name="customerId")
	CustomerEntity customer;
	
	@ManyToOne
	@JoinColumn(name="productid")
	ProductEntity product;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public CustomerEntity getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerEntity customer) {
		this.customer = customer;
	}

	public ProductEntity getProduct() {
		return product;
	}

	public void setProduct(ProductEntity product) {
		this.product = product;
	}
	
	
	
}
