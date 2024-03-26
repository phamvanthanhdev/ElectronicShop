package onlineshop.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.Table;

@Entity @Table(name="OrderDetails")
public class OrderDetail {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	//private int orderId;
	//Integer productId;
	private float unitPrice;
	private int quantity;
	private int discount;
	
	
	@ManyToOne
	@JoinColumn(name="productId")
	ProductEntity productEntity;

	@ManyToOne
	@JoinColumn(name="orderId")
	OrderEntity orderEntity;


	public OrderEntity getOrderEntity() {
		return orderEntity;
	}


	public void setOrderEntity(OrderEntity orderEntity) {
		this.orderEntity = orderEntity;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}



	public float getUnitPrice() {
		return unitPrice;
	}


	public void setUnitPrice(float unitPrice) {
		this.unitPrice = unitPrice;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public int getDiscount() {
		return discount;
	}


	public void setDiscount(int discount) {
		this.discount = discount;
	}


	public ProductEntity getProductEntity() {
		return productEntity;
	}


	public void setProductEntity(ProductEntity productEntity) {
		this.productEntity = productEntity;
	}

	
	
}
