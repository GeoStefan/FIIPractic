package eu.ubis.fiimdb.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eu.ubis.fiimdb.controller.MovieBean;
import eu.ubis.fiimdb.model.Movie;
@WebServlet(value="/movie-delete")
public class DeleteServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * This method will be called when the application will be started
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		MovieBean bean = new MovieBean();
		String modieId = request.getParameter("movieId");
		Movie movie = bean.getSingleMovie(Integer.parseInt(modieId));
		request.setAttribute("movie", movie);
		getServletContext().getRequestDispatcher("/movie-delete.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MovieBean bean = new MovieBean();
		bean.deleteMovie(Integer.parseInt(request.getParameter("movieId")));
	}
}
