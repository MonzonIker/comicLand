package api;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import modelo.bean.Comic;
import modelo.dao.ModeloComic;

/**
 * Servlet implementation class ApiSumarLike
 */
@WebServlet("/ApiSumarLike")
public class ApiSumarLike extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApiSumarLike() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		int id =Integer.parseInt(request.getParameter("id"));
		Comic comic = new Comic();
        comic.setId(id);
        System.out.print(comic);
        ModeloComic mComic = new ModeloComic();
        mComic.updateLike(comic);
        response.setHeader("Access-Control-Allow-Origin","*");
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);
        
		
	}

}
