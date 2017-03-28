/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
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

import javax.json.*;
import java.io.StringReader;
import java.io.StringWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.json.stream.JsonGenerator;
import javax.json.stream.JsonGeneratorFactory;
import javax.servlet.ServletContext;
import model.ConceptMapBean;
import model.ConceptNodeBean;
import model.EmailUtility;
/**
 *
 * @author Maryam Khalid
 */
public class InsertLadderController extends HttpServlet
{
    private String host;
    private String port;
    private String kmatUsername;
    private String kmatPassword;
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
		if(action.equals("save-ladder"))
		{
			String ladderString = request.getParameter("newLadder");
			//System.out.println(ladderString);

			//System.out.println(LadderDAO.getLadder(5));
			//LadderBean ladder = LadderDAO.getLadder(5);
			/*for(int i = 0; i < ladder.getNodes().size(); i++)
			{
				System.out.println("Node" + i + ": " + ladder.getNodes().get(i));
			}*/
			/*for(int i = 0; i < ladder.getEdges().size(); i++)
			{
				System.out.println("Edge" + i + ": " + ladder.getEdges().get(i));
			}*/

			int newLadderId = 0;

			//JSON readers
			try (JsonReader jsonReader = Json.createReader(new StringReader(ladderString)))
			{
				JsonObject ladderObject = jsonReader.readObject();

				LadderBean newLadder = new LadderBean(ladderObject.getString("name"));
				newLadder.setId(ladderObject.getInt("id"));
				newLadder.setLadderType(LadderBean.LadderType.valueOf(ladderObject.getString("ladderType")));

				if(!ladderObject.isNull("description"))
					newLadder.setDescription(ladderObject.getString("description"));
				else
					newLadder.setDescription("");
				
				newLadder.setRootNodeId(ladderObject.getInt("rootNodeId"));
				
				if(!ladderObject.isNull("ownerId"))
					newLadder.setOwnerId(ladderObject.getInt("ownerId"));
				else
					newLadder.setOwnerId(0);
				
				if(!ladderObject.isNull("createDt"))
				{	
					newLadder.setCreateDate(new Timestamp(Long.parseLong(ladderObject.getString("createDt"))));
				}
				else
				{
					Date utilDate = new java.util.Date();
					newLadder.setCreateDate(new Timestamp(utilDate.getTime()));
				}
				
				if(!ladderObject.isNull("updateDt"))
				{	
					newLadder.setUpdateDate(new Timestamp(Long.parseLong(ladderObject.getString("updateDt"))));
				}
				else
				{
					Date utilDate = new java.util.Date();
					newLadder.setUpdateDate(new Timestamp(utilDate.getTime()));
				}
				
				JsonArray jsonNodesArray = ladderObject.getJsonArray("nodes");
				ArrayList<NodeBean> nodes = new ArrayList<>();
				for(int i = 0; i < jsonNodesArray.size(); i++)
				{
					JsonObject newNodeObject = jsonNodesArray.getJsonObject(i);
					double posX = newNodeObject.getJsonNumber("x").doubleValue();
					double posY = newNodeObject.getJsonNumber("y").doubleValue();
					//new node
					NodeBean newNode = new NodeBean(newNodeObject.getString("title"), posX, posY);
					//id
					newNode.setNodeId(newNodeObject.getInt("id"));
					//type
					newNode.setNodeType(NodeBean.NodeType.valueOf(newNodeObject.getString("nodeType")));
					//descripton
					if(newNodeObject.isNull("description"))
						newNode.setDescription("");
					else
						newNode.setDescription(newNodeObject.getString("description"));

					//tools, users and resources
					if(!newNodeObject.isNull("tools"))
					{
						JsonArray nodeToolsJson = newNodeObject.getJsonArray("tools");
						for(int j = 0; j < nodeToolsJson.size(); j++)
						{
							newNode.getToolIds().add(nodeToolsJson.getInt(j));
						}
					}
					if(!newNodeObject.isNull("resources"))
					{
						JsonArray nodeResourcesJson = newNodeObject.getJsonArray("resources");
						for(int j = 0; j < nodeResourcesJson.size(); j++)
						{
							newNode.getResourceIds().add(nodeResourcesJson.getInt(j));
						}
					}
					if(!newNodeObject.isNull("users"))
					{
						JsonArray nodeUsersJson = newNodeObject.getJsonArray("users");
						for(int j = 0; j < nodeUsersJson.size(); j++)
						{
							newNode.getSharedUserIds().add(nodeUsersJson.getInt(j));
						}
					}
					nodes.add(newNode);
				}

				JsonArray jsonEdgesArray = ladderObject.getJsonArray("edges");
				ArrayList<EdgeBean> edges = new ArrayList<>();
				for(int i = 0; i < jsonEdgesArray.size(); i++)
				{
					JsonObject newEdgeObject = jsonEdgesArray.getJsonObject(i);
					EdgeBean newEdge = new EdgeBean();
					newEdge.setEdgeId(newEdgeObject.getInt("id"));

					if(newEdgeObject.isNull("title"))
						newEdge.setName("");
					else
						newEdge.setName(newEdgeObject.getString("title"));

					if(newEdgeObject.isNull("description"))
						newEdge.setDescription("");
					else
						newEdge.setDescription(newEdgeObject.getString("description"));
					newEdge.setEdgeType(EdgeBean.EdgeType.valueOf(newEdgeObject.getString("edgeType")));

					//to (target) and from (source)
					newEdge.setFromNode(newEdgeObject.getInt("source"));
					newEdge.setToNode(newEdgeObject.getInt("target"));

					//tools, users and resources
					if(!newEdgeObject.isNull("tools"))
					{
						JsonArray edgeToolsJson = newEdgeObject.getJsonArray("tools");
						for(int j = 0; j < edgeToolsJson.size(); j++)
						{
							newEdge.getToolIds().add(edgeToolsJson.getInt(j));
						}
					}

					if(!newEdgeObject.isNull("resources"))
					{
						JsonArray edgeResourcesJson = newEdgeObject.getJsonArray("resources");
						for(int j = 0; j < edgeResourcesJson.size(); j++)
						{
							newEdge.getResourceIds().add(edgeResourcesJson.getInt(j));
						}
					}

					if(!newEdgeObject.isNull("users"))
					{
						JsonArray edgeUsersJson = newEdgeObject.getJsonArray("users");
						for(int j = 0; j < edgeUsersJson.size(); j++)
						{
							newEdge.getSharedUserIds().add(edgeUsersJson.getInt(j));
						}
					}
					edges.add(newEdge);
				}

				//add nodes and edges to ladder
				newLadder.setNodes(nodes);
				newLadder.setEdges(edges);

				//System.out.println("Parameter Names: " + request.getParameterNames());

				if(newLadder.getId() == 0)
				{
					//save new ladder
					newLadderId = LadderDAO.insertLadder(newLadder);
				}
				else
				{
					//System.out.println("Updating Ladder!! - in insert ladder controller");
					LadderDAO.updateLadder(newLadder);
				}
			}
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write(newLadderId+"");
		}
                
                else if(action.equals("save-concept-map"))
		{
			String ladderString = request.getParameter("newLadder");
			int newLadderId = 0;

			//JSON readers
			try (JsonReader jsonReader = Json.createReader(new StringReader(ladderString)))
			{
				JsonObject ladderObject = jsonReader.readObject();

				ConceptMapBean newLadder = new ConceptMapBean(ladderObject.getString("name"));
				newLadder.setId(ladderObject.getInt("id"));
				newLadder.setLadderType(ConceptMapBean.LadderType.valueOf(ladderObject.getString("ladderType")));

				if(!ladderObject.isNull("description"))
					newLadder.setDescription(ladderObject.getString("description"));
				else
					newLadder.setDescription("");
				
				newLadder.setRootNodeId(ladderObject.getInt("rootNodeId"));
				
				if(!ladderObject.isNull("ownerId"))
					newLadder.setOwnerId(ladderObject.getInt("ownerId"));
				else
					newLadder.setOwnerId(0);
				
				if(!ladderObject.isNull("createDt"))
				{	
					newLadder.setCreateDate(new Timestamp(Long.parseLong(ladderObject.getString("createDt"))));
				}
				else
				{
					Date utilDate = new java.util.Date();
					newLadder.setCreateDate(new Timestamp(utilDate.getTime()));
				}
				
				if(!ladderObject.isNull("updateDt"))
				{	
					newLadder.setUpdateDate(new Timestamp(Long.parseLong(ladderObject.getString("updateDt"))));
				}
				else
				{
					Date utilDate = new java.util.Date();
					newLadder.setUpdateDate(new Timestamp(utilDate.getTime()));
				}
				
				JsonArray jsonNodesArray = ladderObject.getJsonArray("nodes");
				ArrayList<ConceptNodeBean> nodes = new ArrayList<>();
                                ArrayList<Integer> users = new ArrayList<>();
                                
				for(int i = 0; i < jsonNodesArray.size(); i++)
				{
					JsonObject newNodeObject = jsonNodesArray.getJsonObject(i);
					double posX = newNodeObject.getJsonNumber("x").doubleValue();
					double posY = newNodeObject.getJsonNumber("y").doubleValue();
					//new node
					ConceptNodeBean newNode = new ConceptNodeBean(newNodeObject.getString("title"), posX, posY);
					//id
					newNode.setNodeId(newNodeObject.getInt("id"));
					//type
					newNode.setNodeType(ConceptNodeBean.NodeType.valueOf(newNodeObject.getString("nodeType")));
                                        newNode.setClassforSelection(newNodeObject.getString("classforSelection"));
                                        newNode.setClassforNode(newNodeObject.getString("classforNode"));
                                        
                                        if (newNodeObject.getString("nodeType").equals("PROCESS"))
                                        {
                                            int nodeID = LadderDAO.ifLadderExists("PROCESS", newNodeObject.getString("title"));
                                            //if (LadderDAO.ifLadderExists("PROCESS", newNodeObject.getString("title")) <= 0)
                                            if (nodeID == -2)
                                            {
                                                response.setContentType("text/html;charset=UTF-8");
                                                PrintWriter out = response.getWriter();
                                                out.write("Process Ladder: " + newNodeObject.getString("title") + " does not exist."+"");
                                                return;
                                            }
                                            else if (nodeID == -1)
                                            {
                                                response.setContentType("text/html;charset=UTF-8");
                                                PrintWriter out = response.getWriter();
                                                out.write("Process Ladder: " + newNodeObject.getString("title") + " NODE does not exist."+"");
                                                return;
                                            }
                                            else
                                            {
                                                //fill users here
                                                users = LadderDAO.getNodeUsers(nodeID);
                                            }
                                        }
                                        else if(newNodeObject.getString("nodeType").equals("COMPOSITION"))
                                        {
                                            int nodeID = LadderDAO.ifLadderExists("COMPOSITION", newNodeObject.getString("title"));
                                            if (nodeID == -2)
                                            {
                                                response.setContentType("text/html;charset=UTF-8");
                                                PrintWriter out = response.getWriter();
                                                out.write("Composition Ladder: " + newNodeObject.getString("title") + " does not exist."+"");
                                                return;
                                            }
                                            else if (nodeID == -1)
                                            {
                                                response.setContentType("text/html;charset=UTF-8");
                                                PrintWriter out = response.getWriter();
                                                out.write("Composition Ladder: " + newNodeObject.getString("title") + " | NODE does not exist."+"");
                                                return;
                                            }
                                            else
                                            {
                                                users = LadderDAO.getNodeUsers(nodeID);
                                            }
                                        }
					//descripton
					if(newNodeObject.isNull("description"))
						newNode.setDescription("");
					else
						newNode.setDescription(newNodeObject.getString("description"));

					//tools, users and resources
					if(!newNodeObject.isNull("tools"))
					{
						JsonArray nodeToolsJson = newNodeObject.getJsonArray("tools");
						for(int j = 0; j < nodeToolsJson.size(); j++)
						{
							newNode.getToolIds().add(nodeToolsJson.getInt(j));
						}
					}
					if(!newNodeObject.isNull("resources"))
					{
						JsonArray nodeResourcesJson = newNodeObject.getJsonArray("resources");
						for(int j = 0; j < nodeResourcesJson.size(); j++)
						{
							newNode.getResources().add(nodeResourcesJson.getString(j));
						}
					}
					if(!newNodeObject.isNull("users"))
					{
						JsonArray nodeUsersJson = newNodeObject.getJsonArray("users");
						for(int j = 0; j < nodeUsersJson.size(); j++)
						{
							newNode.getSharedUserIds().add(nodeUsersJson.getInt(j));
						}
					}
					nodes.add(newNode);
				}

				JsonArray jsonEdgesArray = ladderObject.getJsonArray("edges");
				ArrayList<EdgeBean> edges = new ArrayList<>();
				for(int i = 0; i < jsonEdgesArray.size(); i++)
				{
					JsonObject newEdgeObject = jsonEdgesArray.getJsonObject(i);
					EdgeBean newEdge = new EdgeBean();
					newEdge.setEdgeId(newEdgeObject.getInt("id"));

					if(newEdgeObject.isNull("title"))
						newEdge.setName("");
					else
						newEdge.setName(newEdgeObject.getString("title"));

					if(newEdgeObject.isNull("description"))
						newEdge.setDescription("");
					else
						newEdge.setDescription(newEdgeObject.getString("description"));
					newEdge.setEdgeType(EdgeBean.EdgeType.valueOf(newEdgeObject.getString("edgeType")));

					//to (target) and from (source)
					newEdge.setFromNode(newEdgeObject.getInt("source"));
					newEdge.setToNode(newEdgeObject.getInt("target"));

					//tools, users and resources
					if(!newEdgeObject.isNull("tools"))
					{
						JsonArray edgeToolsJson = newEdgeObject.getJsonArray("tools");
						for(int j = 0; j < edgeToolsJson.size(); j++)
						{
							newEdge.getToolIds().add(edgeToolsJson.getInt(j));
						}
					}

					if(!newEdgeObject.isNull("resources"))
					{
						JsonArray edgeResourcesJson = newEdgeObject.getJsonArray("resources");
						for(int j = 0; j < edgeResourcesJson.size(); j++)
						{
							newEdge.getResourceIds().add(edgeResourcesJson.getInt(j));
						}
					}

					if(!newEdgeObject.isNull("users"))
					{
						JsonArray edgeUsersJson = newEdgeObject.getJsonArray("users");
						for(int j = 0; j < edgeUsersJson.size(); j++)
						{
							newEdge.getSharedUserIds().add(edgeUsersJson.getInt(j));
						}
					}
					edges.add(newEdge);
				}

				//add nodes and edges to ladder
				newLadder.setNodes(nodes);
				newLadder.setEdges(edges);

				//System.out.println("Parameter Names: " + request.getParameterNames());

				if(newLadder.getId() == 0)
				{
					//save new ladder
					newLadderId = LadderDAO.insertConceptMap(newLadder);
				}
				else
				{
					//System.out.println("Updating Ladder!! - in insert ladder controller");
					LadderDAO.updateConceptMap(newLadder);
				}
                                
                        ServletContext context = getServletContext();
                        setEmailHeader(context);
                        
                        ArrayList<String> recipientEmail = new ArrayList<>();
                        recipientEmail = getEmailAddresses(users);
                        
                        //String recipientEmail = users.getPriEmail();
                        String subject = "Concept Map Created/Updated";
                        String content = "Dear User, the Concept Map: " + newLadder.getName() + 
                            " has been created/updated." +
                            "\n\nBest Regards,\n\nKMAT Team" +
                            "\n\n\n_________________________________________________________" +
                            "\nThis is an autogenerated email, kindly do not reply.";
                        try
                        {
                                EmailUtility.sendEmail(host, port, kmatUsername, kmatPassword, recipientEmail, subject, content);                    
                            }
                            catch (Exception e){
                                e.printStackTrace();
                            }
			}
                        
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write(newLadderId+"");
		}
                
		else if(action.equals("delete-ladder"))
		{
			String idString = request.getParameter("ladderId");
			if(idString != null)
			{
				int ladderId = Integer.parseInt(idString);
				LadderDAO.deleteLadder(ladderId);
			}
		}
		//out.println("<script type=\"text/javascript\">");
		//out.println("alert('Tool Opened Up!');");
		//out.println("location='add-process-ladder';");
		//out.println("</script>");
	}
        
        private void setEmailHeader(ServletContext context){
        host = context.getInitParameter("host");
        port = context.getInitParameter("port");
        kmatUsername = context.getInitParameter("user");
        kmatPassword = context.getInitParameter("pass");
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

    private ArrayList<String> getEmailAddresses(ArrayList<Integer> users) {
        ArrayList<String> emailAddresses = new ArrayList<>();
        for (int i=0; i<users.size(); i++)
        {
            emailAddresses.add(i, LadderDAO.getEmailAddress(users.get(i)));
        }
        return emailAddresses;
    }

}
