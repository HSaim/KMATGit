/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import matlabcontrol.*;
import model.LadderBean;
import model.NodeBean;
import model.EdgeBean;
import model.LadderDAO;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Maryam Khalid
 */
public class RunToolController extends HttpServlet
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
		LadderBean ladder = new LadderBean("New Ladder1");
		ladder.setDescription("Testing insert");
		ladder.setLadderType(LadderBean.LadderType.PROCESS);
		
		NodeBean node1 = new NodeBean("Node1", 5, 6.5);
		node1.setNodeType(NodeBean.NodeType.PROCESS);
		NodeBean node2 = new NodeBean("Node2", 10, 11);
		node2.setNodeType(NodeBean.NodeType.PROCESS);
		
		EdgeBean edge1 = new EdgeBean();
		edge1.setName("Edge1");
		edge1.setDescription("description edge1");
		edge1.setEdgeType(EdgeBean.EdgeType.PROCESS);
		edge1.setToNode(1);
		edge1.setFromNode(2);
		
		EdgeBean edge2 = new EdgeBean();
		edge2.setName("Edge2");
		edge2.setDescription("description edge1");
		edge2.setEdgeType(EdgeBean.EdgeType.PROCESS);
		edge2.setToNode(5);
		edge2.setFromNode(1);
		
		edge1.getResourceIds().add(13);
		edge1.getResourceIds().add(14);
		edge1.getSharedUserIds().add(11);
		edge1.getToolIds().add(15);
		edge1.getToolIds().add(17);
		edge1.getToolIds().add(19);
		
		node1.getResourceIds().add(23);
		node1.getResourceIds().add(24);
		node1.getSharedUserIds().add(21);
		node1.getToolIds().add(25);
		node1.getToolIds().add(27);
		node1.getToolIds().add(29);
		
		ladder.getNodes().add(node1);
		ladder.getNodes().add(node2);
		ladder.getEdges().add(edge1);
		ladder.getEdges().add(edge2);
		/*ladder.getResourceIds().add(3);
		ladder.getResourceIds().add(4);
		ladder.getSharedUserIds().add(1);
		ladder.getToolIds().add(15);
		ladder.getToolIds().add(7);
		ladder.getToolIds().add(9);*/
		
		LadderDAO.insertLadder(ladder);
		
		/*
		//matlabcontrol commented code: https://github.com/diffplug/matconsolectl/tree/master/src/matlabcontrol
		MatlabProxyFactoryOptions.Builder op = new MatlabProxyFactoryOptions.Builder();
		//op.setUsePreviouslyControlledSession(true);
		MatlabProxyFactory factory = new MatlabProxyFactory();
		MatlabProxy proxy = null;
		try 
		{
			int parameter = Integer.parseInt(request.getParameter("input"));
			proxy = factory.getProxy();
			op.setHidden(true);
			proxy.toString();
					
			//other useful settings:
			//op.setMatlabStartingDirectory(null);
			
			//Display 'hello world' just like when using the demo
			//proxy.eval("disp('hello world')");
			
			//used your own function/file!
			//proxy.eval("texture");
			
			//Set a variable, add to it, retrieve it, and print the result
			proxy.setVariable("a", parameter);
			proxy.eval("a = a + 6");
			//Object result = proxy.getVariable("a");
			Object result = ((double[]) proxy.getVariable("a"))[0];
			System.out.println("Result: " + result);

			HttpSession session = request.getSession(true);
            session.setAttribute("output", result);
			//request.setAttribute("output", result);
			proxy.exit();*/
			
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script type=\"text/javascript\">");
			//out.println("alert('Tool Opened Up!');");
			out.println("location='view-process-ladders';");
			out.println("</script>");
		/*}
		catch (Exception e)
		{
			System.out.println("Alert could not be generated. Error: " + e);
		}
		finally
		{
			if(proxy != null)
			{
				proxy.disconnect();
				//TODO: Check how to use disconnection listener
			}
		}
		*/
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
