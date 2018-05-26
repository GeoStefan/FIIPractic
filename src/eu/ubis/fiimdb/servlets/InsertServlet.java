package eu.ubis.fiimdb.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eu.ubis.fiimdb.controller.MovieBean;
import eu.ubis.fiimdb.model.Movie;
@WebServlet(value="/InsertServlet")
public class InsertServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * This method will be called when the application will be started
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		MovieBean bean = new MovieBean();
		Movie movie = new Movie();
		movie.setId(Integer.parseInt(request.getParameter("movieId")));
		movie.setName(request.getParameter("name"));
		movie.setRating(Double.parseDouble(request.getParameter("rating")));
		movie.setLength(Integer.parseInt(request.getParameter("length")));
		movie.setCasting(request.getParameter("casting"));
		movie.setDirector(request.getParameter("director"));
		movie.setDescription(request.getParameter("description"));
		movie.setWriter(request.getParameter("writer"));
		String [] genres = request.getParameter("genres").trim().split("\\s+");
		int[] genresId = new int[100];
		for(int i = 0;i < genres.length;i++)
			genresId[i] = Integer.parseInt(genres[i]); 
		if(bean.insertMovie(movie,genresId)) {
			response.sendRedirect("movies");
		}
		else {
			request.setAttribute("movie", movie);
			getServletContext().getRequestDispatcher("/movie-insert.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
	}
}
