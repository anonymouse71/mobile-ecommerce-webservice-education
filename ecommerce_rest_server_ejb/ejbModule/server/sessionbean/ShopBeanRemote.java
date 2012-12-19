package server.sessionbean;

import java.util.Collection;
import java.util.ArrayList;

import javax.ejb.Remote;

import server.entitybean.Product;

@Remote
public interface ShopBeanRemote {
	void addProduct(String name, String picture, double price,
			Integer quantity, String description);

	void delProduct(Integer id);

	Product findProduct(Integer id);

	Collection<Product> getProducts(String keyword);

	void buyProduct(Integer id, Integer quantity);

	void checkout(ArrayList<Product> basket);

}
