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

@Entity @Table(name="Carts")
public class CartEntity {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	//private String CustomerId;
	private String description;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date createAt;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date updateAt;
	
	@ManyToOne
	@JoinColumn(name="CustomerId")
	CustomerEntity cusEntity;
	
	@OneToMany(mappedBy = "cartEntity")
	List<CartItemsEntity> listItems;
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public Date getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
	}

	public CustomerEntity getCustomerEntity() {
		return cusEntity;
	}

	public void setCustomerEntity(CustomerEntity cartEntity) {
		this.cusEntity = cartEntity;
	}

	public List<CartItemsEntity> getListItems() {
		return listItems;
	}

	public void setListItems(List<CartItemsEntity> listItems) {
		this.listItems = listItems;
	}

	
	
	
}
