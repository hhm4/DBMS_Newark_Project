package com.edu.njit;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class UpdateCustomerInfoSavedServlet
 */
@WebServlet("/UpdateCustomerInfoSavedServlet")
public class UpdateCustomerInfoSavedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	PrintWriter pw = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	Connection conn = null;
	String address, email, firstName, lastName;
	Long phoneNumber;
	HttpSession session;
	String username;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			conn = DBManager.getConnection();
			username = (String) request.getSession().getAttribute("username");
			pstmt = conn.prepareStatement("select Address, Email, FName, LName, Phone from customer where username = ?");
			pstmt.setString(1, username);
			pstmt.execute();
			rs = pstmt.getResultSet();
			while(rs.next()) {
				address = rs.getString(1);
				email = rs.getString(2);
				firstName = rs.getString(3);
				lastName = rs.getString(4);
				phoneNumber = rs.getLong(5);
			}
			pw = response.getWriter();
			pw.println("<html>");
			pw.println("<img src='/Newark_IT_Company/Images/shopping_logo.jpg' width=140 height=120>");
			pw.println("<br><br>");
			pw.println("<title>Update Customer Information</title>");
			pw.println("<h4> <b> Customer details saved successfully! </b></h4>");
			pw.println("<form method=post action='UpdateCustomerDetailsServlet'");
			pw.println("<div id='big_wrapper'>");
			pw.println("<header id='top_header'>");
			pw.println("<table> <tr> <td>");
			pw.println("<img src='/Newark_IT_Company/Images/shopping_logo.jpg' width=140 height=120> </td>");
			pw.println("<td/> <td/> <td/> <td/> <td/> <td/> <td/> <td/> <td/> <td/> <td/> <td/> <td/> <td/>");
			pw.println("<td align=center> <h1> Newark IT Company </h1></td> </tr>");
			pw.println("</table>");
			pw.println("</header>");
			pw.println("<link rel='stylesheet' type='text/css' href='main.css'>");
			pw.println("<ul id='drop-nav'>");
			pw.println("<li> <a href='#'>Profile </a>");
			pw.println("<ul>");
			pw.println("<li><a href='UpdateCustomerInfoServlet'>User Profile</a></li>");
			pw.println("<li><a href='CreditCardInfo.html'>Credit Card Info </a></li>");
			pw.println("<li><a href='ShippingDetails.html'>Shipping Card Details</a></li>");
			pw.println("</ul>");
			pw.println("</li>");
			pw.println("<li><a href='ViewCustomerCartDao'>View Cart</a></li>");
			pw.println("<li><a href='ViewTransactionHistoryDao'>Transaction History</a></li>");
			pw.println("<li><a href='ProductServlet'>View Products</a></li>");
			pw.println("</ul> </ul>");
			pw.println("</nav>");
			pw.println("</div>");
			pw.println("<table>");
			pw.println("<tr>");
			pw.println("<td>First Name*: <input type=text name=firstnametext value = "+firstName+"></td>");
			pw.println("</tr> <tr/>	<tr/> <tr/> <tr/> <tr/> <tr>");
			pw.println("<td>Last Name*: &nbsp;<input type=text name=lastnametext value = "+lastName+"></td>");
			pw.println("</tr> <tr/> <tr/> <tr/> <tr/> <tr/> <tr>");
			pw.println("<td>Email*: &nbsp;<input type=text name=emailtext value = "+email+"></td>");
			pw.println("</tr> <tr/> <tr/> <tr/> <tr/> <tr/> <tr>");
			pw.println("<td>Phone: &nbsp;<input type=text name=phonetext value = "+phoneNumber+"></td>");
			pw.println("</tr> <tr/> <tr/> <tr/> <tr/> <tr/> <tr>");
			pw.println("<td>Address: &nbsp;<input type=text name=addresstext value = "+address+"></td>");
			pw.println("</tr> <tr/> <tr/> <tr/> <tr/> <tr/> <tr/>");
			pw.println("</tr> <tr/> <tr/> <tr/> <tr/> <tr/> <tr>");
			pw.println("<td align=left><input type=submit value=Update Details>");
			pw.println("</tr> <tr/> <tr/> <tr/> <tr/> <tr/> <tr/>");
			pw.println("</tr> <tr/> <tr/> <tr/> <tr/> <tr/> <tr>");
			pw.println("<td> <a href = 'HomePage.html'> Go back to home page </a> </td>");
			pw.println("</tr>");
			pw.println("</table> </form>");
			pw.println("</center>");
			pw.println("</body>");
			pw.println("</html>");
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}
