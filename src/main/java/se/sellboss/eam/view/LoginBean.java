package se.sellboss.eam.view;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import se.sellboss.eam.domain.User;
import se.sellboss.eam.service.UserService;

@Component
@Scope("session")
public class LoginBean {

	@Autowired
	private UserService userService;

	private User newUser;
	private List<User> allUsers;
	private String password;
	private String message, uname;

	public LoginBean() {
		newUser = new User();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String loginProject() {
		User user = userService.findUser(uname);
		if (user.getPassword().equals(password)) {
			// get Http Session and store username
			HttpSession session = Util.getSession();
			session.setAttribute("username", uname);

			return "dashboard";
		} else {

			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Invalid Login!", "Please Try Again!"));

			// invalidate session, and redirect to other pages

			// message = "Invalid Login. Please Try Again!";
			return "login";
		}
	}

	public String logout() {
		HttpSession session = Util.getSession();
		session.invalidate();
		return "logout";
	}

	public void doSaveUser(ActionEvent event) {

		try {
			userService.saveUser(newUser);

			FacesContext context = FacesContext.getCurrentInstance();
			//context.addMessage(null, new FacesMessage("Save Successful!"));
			newUser = new User();
		} catch (DataAccessException e) {
			e.printStackTrace();

			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "Error when saving asset!",
					null));

		}
	}

	public List<User> getAllUsers() {
		return userService.getAll();
	}

	public void setAllUsers(List<User> allUsers) {
		this.allUsers = allUsers;
	}

	public User getNewUser() {
		return newUser;
	}

	public void setNewUser(User newUser) {
		this.newUser = newUser;
	}
}