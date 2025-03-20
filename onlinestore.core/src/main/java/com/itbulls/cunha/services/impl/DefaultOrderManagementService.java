package com.itbulls.cunha.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.itbulls.cunha.dao.OrderDAO;
import com.itbulls.cunha.dao.impl.MySqlJdbcOrderDao;
import com.itbulls.cunha.dto.converters.OrderDtoToOrderConverter;
import com.itbulls.cunha.entities.Order;
import com.itbulls.cunha.services.OrderManagementService;

@Service
public class DefaultOrderManagementService implements OrderManagementService {

	private static DefaultOrderManagementService instance;

	private List<Order> orders;
	private OrderDAO orderDAO;
	private OrderDtoToOrderConverter orderConverter;

	{
		orders = new ArrayList<>();
		orderDAO = new MySqlJdbcOrderDao();
		orderConverter = new OrderDtoToOrderConverter();
	}

	public static OrderManagementService getInstance() {
		if (instance == null) {
			instance = new DefaultOrderManagementService();
		}
		return instance;
	}

	@Override
	public void addOrder(Order order) {
		if (order == null) {
			return;
		}
		orderDAO.saveOrder(orderConverter.convertOrderToOrderDto(order));
		orders.add(order);
	}

	@Override
	public List<Order> getOrdersByUserId(int userId) {
		List<Order> ordersFilteredByUserId = orderConverter.convertListOfOrdersDtoToListOfOrder(orderDAO.getOrders());
		return ordersFilteredByUserId;
	}

	@Override
	public List<Order> getOrders() {
		return orderConverter.convertListOfOrdersDtoToListOfOrder(orderDAO.getOrders());
	}

	void clearServiceState() {
		orders = new ArrayList<>();
	}
	
	@Override
	public void retrieveAllOrders() {
		orders = orderConverter.convertListOfOrdersDtoToListOfOrder(orderDAO.getOrders());
	}

	
//	public void serializeOrders() {
//		try(var oos = new ObjectOutputStream(new FileOutputStream("database\\orders.txt"))) {
//			orders.forEach(order -> {
//				try {
//					oos.writeObject(order);
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			});
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//	public void deserializeOrders() {
//		File file = new File("database\\orders.txt");
//		if (!file.exists()) {
//			try {
//				file.createNewFile();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//		if(file.length() == 0) {
//			return;
//		}
//		try (var ois = new ObjectInputStream(new FileInputStream("database\\orders.txt"))) {
//	        while (true) {
//	        	try {
//	                Order order = (Order) ois.readObject(); // Read each order
//	                this.orders.add(order);
//	            } catch (EOFException e) {
//	                break; // End of file reached
//	            }
//	        }
//	    } catch (IOException | ClassNotFoundException e) {
//	        e.printStackTrace();
//	    }
//	}
}