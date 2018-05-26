package eu.ubis.fiimdb.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eu.ubis.fiimdb.controller.MovieBean;

/**
 * Servlet implementation class StartServlet
 * 
 */
@WebServlet(value="/movies")
public class StartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * This method will be called when the application will be started
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. create a bean as the one from the jsp page (see movies.jsp)		
		MovieBean bean = new MovieBean();
		
		//2. call the method which retrieves all the movies from the database
		bean.getAllMovies();
		
		//3. set the bean as attribute to the request in order to list the movies in the jsp 
		// make sure that it is the name from movies.jsp (see tag <jsp:useBean>)
		request.setAttribute("movieBean", bean);
		
		//4. set which radio button will be checked (see movies.jsp)
		request.setAttribute("searchType", "name");
		
		//5. redirect to movies.jsp
		getServletContext().getRequestDispatcher("/movies.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
	}
	

}
