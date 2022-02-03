package com.gcu.data;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import com.gcu.model.OrderModel;

@Service
public class OrdersDataService implements DataAccessInterface<OrderModel> {

	@SuppressWarnings("unused")
	@Autowired
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;

	@Override
	public List<OrderModel> findAll() {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM orders";
		List<OrderModel> orders = new ArrayList<OrderModel>();
		try {
			SqlRowSet srs = jdbcTemplateObject.queryForRowSet(sql);
			while (srs.next()) {
				
				orders.add(new OrderModel(srs.getInt("ID"), srs.getString("ORDER_NO"), srs.getString("PRODUCT_NAME"),
						srs.getFloat("PRICE"), srs.getInt("Quantity")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return orders;
	}

	@Override
	public OrderModel findById(int id) {
				String sql = "SELECT * FROM orders WHERE ID = '" + id + "'";
				OrderModel order = new OrderModel(id, null, null, null, 0);
				try {
					SqlRowSet srs = jdbcTemplateObject.queryForRowSet(sql);
					// Loop through results
					while (srs.next()) {
						order.setId(srs.getInt("ID"));
						order.setOrderNo(srs.getString("ORDER_NO"));
						order.setProductName(srs.getString("PRODUCT_NAME"));
						order.setPrice(srs.getFloat("PRICE"));
						order.setQuantity(srs.getInt("QUANTITY"));
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return order;
	}

	@Override
	public boolean create(OrderModel order) {
		System.out.println(order.getProductName());
		String sql = "INSERT INTO orders (ORDER_NO, PRODUCT_NAME, PRICE, QUANTITY) VALUES(?,?,?,?)";
		try {

			int rows = jdbcTemplateObject.update(sql, order.getOrderNo(), order.getProductName(), order.getPrice(),
					order.getQuantity());
			return rows == 1 ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * function for updating an item in the database
	 */
	@Override
	public int update(OrderModel t) {
		// SQL query to update specific item in the DB
		String sql = "UPDATE orders SET ORDER_NO = ?, PRODUCT_NAME = ?, PRICE = ?, QUANTITY = ? WHERE ID = ?";
		try {

			// return num rows affected
			return jdbcTemplateObject.update(sql, t.getOrderNo(), t.getProductName(), t.getPrice(), t.getQuantity(), t.getId());
		} catch (Exception e) {
			e.printStackTrace();
			// return -1 if cannot connect to database
			return -1;
		}
	}

	/**
	 * deletes and item from the database
	 */
	@Override
	public int delete(int t) {
		// SQL query to delete item from DB
		String sql = "DELETE FROM orders WHERE ID = ?";
		try {
			// return num rows affected
			return jdbcTemplateObject.update(sql, t);
		} catch (Exception e) {
			e.printStackTrace();
			// return -1 if cannot connect to database
			return -1;
		}
	}

	public OrdersDataService(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);

	}

}
