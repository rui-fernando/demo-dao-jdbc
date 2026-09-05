package model.dao;

import model.dao.impl.SellerDAO_JDBC;

public class DAOFactory {

	public static SellerDAO createSellerDao() {
		return new SellerDAO_JDBC();
	}
}
