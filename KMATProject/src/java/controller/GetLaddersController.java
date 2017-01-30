/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.LadderBean;
import model.LadderDAO;

/**
 *
 * @author Maryam Khalid
 */
public class GetLaddersController extends HttpServlet
{

	// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
	/**
	 * Handles the HTTP <code>GET</code> method.
	 *
	 * @param request  servlet request
	 * @param response servlet response
	 *
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		
	}

	/**
	 * Handles the HTTP <code>POST</code> method.
	 *
	 * @param request  servlet request
	 * @param response servlet response
	 *
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		String action = request.getParameter("action");
		if(action.equals("get-all-process-ladders") || action.equals("get-all-composition-ladders") || action.equals("get-all-roles-hierarchies"))
		{
			ArrayList<LadderBean> allLadders = new ArrayList<LadderBean>();
			if(action.equals("get-all-process-ladders"))
				allLadders = LadderDAO.getLadders(LadderBean.LadderType.PROCESS.toString());
			else if(action.equals("get-all-composition-ladders"))
				allLadders = LadderDAO.getLadders(LadderBean.LadderType.COMPOSITION.toString());
			else if(action.equals("get-all-roles-hierarchies"))
				allLadders = LadderDAO.getLadders(LadderBean.LadderType.ROLES.toString());
			
			String allLaddersJson = null;
			if (allLadders != null)
				allLaddersJson = LadderDAO.jsonAllLaddersString(allLadders);
            try
			{
				HttpSession session = request.getSession(true);
				if (allLaddersJson != null)
				{
					if(action.equals("get-all-process-ladders"))
						session.setAttribute("process-ladders", allLaddersJson);
					else if(action.equals("get-all-composition-ladders"))
						session.setAttribute("composition-ladders", allLaddersJson);
					else if(action.equals("get-all-roles-hierarchies"))
						session.setAttribute("roles-hierarchies", allLaddersJson);
				}
			}
			catch(Exception e)
			{
					e.printStackTrace();
			}
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(allLaddersJson);
		}
		else if(action.equals("delete-process-ladder-from-view-ladders") || action.equals("delete-composition-ladder-from-view-ladders") || action.equals("delete-roles-hierarchy-from-view-ladders"))
		{
			String ladderId = request.getParameter("ladder_id");
			LadderDAO.deleteLadder(Integer.parseInt(ladderId));
			
			/*response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script type=\"text/javascript\">");
			out.println("location='view-process-ladders';");
			out.println("</script>");*/
			//response.sendRedirect("/view-process-ladders");
		}
	}

	/**
	 * Returns a short description of the servlet.
	 *
	 * @return a String containing servlet description
	 */
	@Override
	public String getServletInfo()
	{
		return "Short description";
	}// </editor-fold>

}
