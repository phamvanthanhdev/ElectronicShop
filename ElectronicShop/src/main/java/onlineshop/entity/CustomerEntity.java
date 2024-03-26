package onlineshop.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity @Table(name="Customers")
public class CustomerEntity {
	@Id
	private String id;
	private String password;
	private String fullname;
	private String email;
	private String photo;
	private boolean activated;
	private boolean admin;
	
	@OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
	List<OrderEntity> listOrders;
	
	@OneToMany(mappedBy = "cusEntity", fetch = FetchType.EAGER)
	List<CartEntity> listCarts;
	
	@OneToMany(mappedBy = "customer")
	List<FavouriteEntity> favourites;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public boolean isActivated() {
		return activated;
	}

	public void setActivated(boolean activated) {
		this.activated = activated;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public List<OrderEntity> getListOrders() {
		return listOrders;
	}

	public void setListOrders(List<OrderEntity> listOrders) {
		this.listOrders = listOrders;
	}

	public List<CartEntity> getListCarts() {
		return listCarts;
	}

	public void setListCarts(List<CartEntity> listCarts) {
		this.listCarts = listCarts;
	}

	public List<FavouriteEntity> getFavourites() {
		return favourites;
	}

	public void setFavourites(List<FavouriteEntity> favourites) {
		this.favourites = favourites;
	}

	

	
	
}
