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
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.itbulls.learnit.cunha.javacore.examsection43.backendonlineshop.config.ApplicationContext;
import com.itbulls.learnit.cunha.javacore.examsection43.backendonlineshop.entities.User;
import com.itbulls.learnit.cunha.javacore.examsection43.backendonlineshop.services.UserManagementService;

public class DefaultUserManagementService implements UserManagementService {

	private static String NOT_UNIQUE_EMAIL_ERROR_MESSAGE;
	private static String EMPTY_EMAIL_ERROR_MESSAGE;
	private static final String NO_ERROR_MESSAGE = "";

	private static DefaultUserManagementService instance;
	private File database;
	private List<User> users;
	private ApplicationContext context;
	private ResourceBundle bundle;
	
	{
		context = ApplicationContext.getInstance();
		bundle = context.getBundle();
		NOT_UNIQUE_EMAIL_ERROR_MESSAGE = bundle.getString("not_unique_email_error_message");
		EMPTY_EMAIL_ERROR_MESSAGE = bundle.getString("empty_email_error_message");
	}
	

	{
		users = new ArrayList<>();
		database = new File("database");

		if (!database.exists()) {
			database.mkdirs();
		}

		database = new File("database", "users.txt");
		if (!database.exists()) {
			try {
				database.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			if (Files.size(database.toPath()) != 0) {
				users = deserializeObject(database.toPath());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public String registerUser(User user) {
		if (user == null)
			return NO_ERROR_MESSAGE;
		if (user.getPassword().length() < 4) {
			return bundle.getString("minimal_password_length");
		}
		Pattern pattern = Pattern.compile("[A-Z]");
		Matcher matcher = pattern.matcher(user.getPassword());
		if (!matcher.find()) {
			return bundle.getString("at_least_one_uppercase_password_message");
		}
		pattern = Pattern.compile("[0-9]");
		matcher = pattern.matcher(user.getPassword());
		if (!matcher.find()) {
			return bundle.getString("at_least_one_nmber_password_message");
		}
		pattern = Pattern.compile("\\W");
		matcher = pattern.matcher(user.getPassword());
		if (!matcher.find()) {
			return bundle.getString("at_least_one_special_character_password_message");
		}
		if (!user.getEmail().contains("@") || !user.getEmail().contains(".com")) {
			return bundle.getString("invalid_email_message");
		}

		String errorMessage = checkUniqueEmail(user.getEmail());
		if (errorMessage != null && !errorMessage.isEmpty())
			return errorMessage;

		users.add(user);
		serializeUsers(users, database.toPath());

		return NO_ERROR_MESSAGE;
	}

	private String checkUniqueEmail(String email) {
		return (email == null || email.isEmpty()) ? EMPTY_EMAIL_ERROR_MESSAGE
				: (users.stream().filter(Objects::nonNull).anyMatch(user -> user.getEmail().equalsIgnoreCase(email))
						? NOT_UNIQUE_EMAIL_ERROR_MESSAGE
						: NO_ERROR_MESSAGE);
	}

	public static UserManagementService getInstance() {
		return (instance == null) ? instance = new DefaultUserManagementService() : instance;
	}

	@Override
	public ArrayList<User> getUsers() {
		return users.stream().filter(Objects::nonNull).collect(Collectors.toCollection(ArrayList<User>::new));
	}

	@Override
	public User getUserByEmail(String userEmail) {
		return users.stream().filter(user -> user != null && user.getEmail().equalsIgnoreCase(userEmail)).findFirst()
				.orElse(null);
	}

	void clearServiceState() {
		users = new ArrayList<User>();
	}

	private void serializeUsers(List<User> users, Path pathName) {
		try (var fileOutputStream = new FileOutputStream(pathName.toString());
				var oos = new ObjectOutputStream(fileOutputStream)) {
			oos.writeObject(users);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<User> deserializeObject(Path pathName) {
		List<User> listOfOrdersDeserialized = new ArrayList<>();

		try (var fileInputStream = new FileInputStream(pathName.toString());
				var ois = new ObjectInputStream(fileInputStream)) {
			// Check if file is empty
			if (fileInputStream.available() > 0) {
				listOfOrdersDeserialized = (List<User>) ois.readObject();
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