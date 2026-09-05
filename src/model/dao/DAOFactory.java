package model.dao;

import db.DB;
import model.dao.impl.SellerDAO_JDBC;

public class DAOFactory {

	public static SellerDAO createSellerDao() {
		return new SellerDAO_JDBC(DB.getConnection());
	}
}
