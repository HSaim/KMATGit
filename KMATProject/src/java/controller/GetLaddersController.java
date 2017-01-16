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
		String action =request.getParameter("action");
		System.out.println("Get Ladders Controller: " + action);
		if(action.equals("get-all-process-ladders"))
		{
			ArrayList<LadderBean> processLadders = new ArrayList<LadderBean>();
            processLadders = LadderDAO.getLadders(LadderBean.LadderType.PROCESS.toString());
			//System.out.println("Number of Process Ladders: " + processLadders.size());
			
			String allProcessLaddersJson = null;
			if (processLadders != null)
				allProcessLaddersJson = LadderDAO.jsonAllLaddersString(processLadders);
            try
			{
				HttpSession session = request.getSession(true);
				if (allProcessLaddersJson != null)
				{                   
					session.setAttribute("process-ladders", allProcessLaddersJson);                                        
				}
				
				//response.sendRedirect("view-process-ladders");
			}
			catch(Exception e)
			{
					e.printStackTrace();
			}
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			//System.out.println(allProcessLaddersJson);
			out.print(allProcessLaddersJson);
		}
		else if(action.equals("delete-ladder-from-view-ladders"))
		{
			System.out.println("Delete Ladder");
			response.sendRedirect("/view-process-ladders");
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
