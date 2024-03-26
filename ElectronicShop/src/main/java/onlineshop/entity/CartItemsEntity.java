package onlineshop.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity @Table(name="CartItems")
public class CartItemsEntity {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	//private int cartId;
	//private int productId;
	private int quantity;
	
	@ManyToOne
	@JoinColumn(name="cartId")
	CartEntity cartEntity;

	@ManyToOne
	@JoinColumn(name="productId")
	ProductEntity products;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public CartEntity getCartEntity() {
		return cartEntity;
	}

	public void setCartEntity(CartEntity cartEntity) {
		this.cartEntity = cartEntity;
	}

	public ProductEntity getProducts() {
		return products;
	}

	public void setProducts(ProductEntity products) {
		this.products = products;
	}
	
	
}
