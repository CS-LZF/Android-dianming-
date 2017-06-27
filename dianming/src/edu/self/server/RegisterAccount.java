package edu.self.server;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Servlet implementation class RegisterAccount
 * 
 * @author mzba
 */
public class RegisterAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterAccount() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String result = "";
		String parameter = request.getParameter("register");
		try {
			JSONObject userObject = new JSONObject(parameter);
			if (!DBControl.isExistsUser(userObject.getString("userId"))) {
				boolean userResult = DBControl.insertUser(userObject);
				 
				if (userResult) { 
					result = "SUCCESS";
				} else {
					result = "ERROR";
				}
			} else {
				result = "EXISTSUSER";
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		response.getWriter().println(result);
	}

}
