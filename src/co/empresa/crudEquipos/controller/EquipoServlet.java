package co.empresa.crudEquipos.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.empresa.crudEquipos.dao.EquipoDao;
import co.empresa.crudEquipos.modelo.Equipo;

/**
 * Servlet implementation class EquipoServlet
 */
@WebServlet("/")
public class EquipoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private EquipoDao equipoDao;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EquipoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		this.equipoDao= new EquipoDao();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		try {
		switch(action) {
		case "/new":
			showNewForm(request, response);
			break;
		case "/insert":
			insertarEquipo(request, response);
			break;
		case "/delete":
			eliminarEquipo(request, response);
			break;
		case "/edit":
			showEditForm(request, response);
			break;
		case "/update":
			actualizarEquipo(request, response);
			break;
		default:
			listEquipo(request,response);	
				
		}
		}catch(SQLException e) {
			throw new ServletException(e);
		}
		
	}

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("equipo.jsp");
		dispatcher.forward(request, response);
	}
	
	private void insertarEquipo(HttpServletRequest request, HttpServletResponse response) throws ServletException, SQLException, IOException {
		String nombre= request.getParameter("nombre");
		String pais= request.getParameter("pais");
		String trofeos= request.getParameter("trofeos");
		Equipo equipo = new Equipo(nombre, pais, trofeos);
		equipoDao.insert(equipo);
		
		response.sendRedirect("list");
	}
	
	private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id= Integer.parseInt(request.getParameter("id"));
		Equipo equipoActual = equipoDao.select(id);
		request.setAttribute("equipo", equipoActual);
		RequestDispatcher dispatcher = request.getRequestDispatcher("equipo.jsp");
		dispatcher.forward(request, response);
	}
	
	private void actualizarEquipo(HttpServletRequest request, HttpServletResponse response) throws ServletException, SQLException, IOException {
		int id= Integer.parseInt(request.getParameter("id"));
		String nombre= request.getParameter("nombre");
		String pais= request.getParameter("pais");
		String trofeos= request.getParameter("trofeos");
		
		Equipo equipo = new Equipo(id,nombre, pais, trofeos);
		equipoDao.update(equipo);
		
		response.sendRedirect("list");
	}
	
	private void eliminarEquipo(HttpServletRequest request, HttpServletResponse response) throws ServletException, SQLException, IOException {
		int id= Integer.parseInt(request.getParameter("id"));
	
		equipoDao.delete(id);
		
		response.sendRedirect("list");
	}
	
	private void listEquipo(HttpServletRequest request, HttpServletResponse response) throws ServletException, SQLException, IOException {
		List <Equipo> listEquipos = equipoDao.selectAll();
		
		request.setAttribute("listEquipos", listEquipos);
		RequestDispatcher dispatcher = request.getRequestDispatcher("equipolist.jsp");
		dispatcher.forward(request, response);
	}

}
