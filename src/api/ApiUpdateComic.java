package api;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import modelo.bean.Comic;
import modelo.bean.Genero;
import modelo.dao.ModeloComic;
import modelo.dao.ModeloGenero;

/**
 * Servlet implementation class ApiUpdateComic
 */
@WebServlet("/ApiUpdateComic")
public class ApiUpdateComic extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApiUpdateComic() {
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
		
		String jsonUsuario = request.getParameter("comic");
		JSONObject jsonObject = new JSONObject(jsonUsuario);
		Date fecha = null;
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			fecha = formato.parse(jsonObject.getString("fecha_publicacion"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
        
        Comic c1 = new Comic();
        c1.setId(Integer.parseInt(jsonObject.getString("id")));
        c1.setNombre(jsonObject.getString("nombre"));
        c1.setTitulo(jsonObject.getString("titulo"));
        c1.setNum(Integer.parseInt(jsonObject.getString("num")));
        c1.setFecha_publicacion(fecha);
        c1.setImagen(jsonObject.getString("imagen"));
        c1.setNum_likes(Integer.parseInt(jsonObject.getString("num_likes")));
        ModeloGenero MG = new ModeloGenero();
        Genero g1 = MG.get(Integer.parseInt(jsonObject.getString("genero_id")));
        c1.setGenero(g1);
        
        System.out.print(c1);
        
        ModeloComic mComic = new ModeloComic();
        mComic.update(c1);
        response.setHeader("Access-Control-Allow-Origin","*");
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);
	}

}
