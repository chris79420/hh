package co.micol.controller;

import java.sql.SQLException;

import co.micol.bean.ProductsBean;
import co.micol.dao.ProductsDao;

public class Start {

	public static void main(String[] args) throws SQLException {

		ProductsBean bean = new ProductsBean();
		ProductsDao dao = new ProductsDao();
		dao.EditPro(bean);
		
		
	}

}
