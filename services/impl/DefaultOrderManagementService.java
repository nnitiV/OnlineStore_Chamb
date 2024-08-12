package com.itbulls.learnit.cunha.javacore.examsection43.backendonlineshop.services.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.itbulls.learnit.cunha.javacore.examsection43.backendonlineshop.entities.Order;
import com.itbulls.learnit.cunha.javacore.examsection43.backendonlineshop.services.OrderManagementService;

public class DefaultOrderManagementService implements OrderManagementService {

	private static DefaultOrderManagementService instance;
	private File database;
	private List<Order> orders;

	{
		orders = new ArrayList<>();

		database = new File("database", "orders.txt");
		if (!database.exists()) {
			try {
				database.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			if(Files.size(database.toPath()) != 0) {
				orders = deserializeObject(database.toPath());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
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

		orders.add(order);
		serializeOrders(orders, database.toPath());
	}

	@Override
	public ArrayList<Order> getOrdersByUserId(int userId) {
		return orders.stream().filter(order -> order != null).filter(order -> order.getCustomerId() == userId)
				.collect(Collectors.toCollection(ArrayList<Order>::new));
	}

	@Override
	public ArrayList<Order> getOrders() {
		return orders.stream().filter(order -> order != null).collect(Collectors.toCollection(ArrayList<Order>::new));
	}

	void clearServiceState() {
		orders = new ArrayList<Order>();
	}

	private void serializeOrders(List<Order> orders, Path pathName) {
		try (var fileOutputStream = new FileOutputStream(pathName.toString());
				var oos = new ObjectOutputStream(fileOutputStream)) {
			oos.writeObject(orders);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<Order> deserializeObject(Path pathName) {
		List<Order> listOfOrdersDeserialized = new ArrayList<>();
		
	    try (var fileInputStream = new FileInputStream(pathName.toString());
	         var ois = new ObjectInputStream(fileInputStream)) {
	        // Check if file is empty
	        if (fileInputStream.available() > 0) {
	        	listOfOrdersDeserialized = (List<Order>) ois.readObject();
	        }
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listOfOrdersDeserialized;
	}
}