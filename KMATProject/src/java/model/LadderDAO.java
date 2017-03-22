/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

/**
 *
 * @author Maryam Khalid
 */
public class LadderDAO
{
	static Connection conn = null;
    static ResultSet result = null;  
    //static Statement stmt = null;
	static PreparedStatement pst = null;
	
	private enum GetEntity
	{
		LADDERS,
		LADDER,
		NODES,
		EDGES,
                CONCEPTNODES,
                CONCEPTMAPS
	}
	
	private static final String SCHEMA_NAME = "kmat";
	private static final String LADDERS_TBL = "ladder_tbl";
	private static final String NODES_TBL = "node_tbl";
	private static final String EDGES_TBL = "edge_tbl";
	private static final String REL_LADDER_USER_TBL = "rel_ladder_user_tbl";
	private static final String REL_LADDER_RESOURCE_TBL = "rel_ladder_resource_tbl";
	private static final String REL_LADDER_TOOL_TBL = "rel_ladder_tool_tbl";
	private static final String REL_NODE_USER_TBL = "rel_node_user_tbl";
	private static final String REL_NODE_RESOURCE_TBL = "rel_node_resource_tbl";
	private static final String REL_NODE_TOOL_TBL = "rel_node_tool_tbl";
	private static final String REL_EDGE_USER_TBL = "rel_edge_user_tbl";
	private static final String REL_EDGE_RESOURCE_TBL = "rel_edge_resource_tbl";
	private static final String REL_EDGE_TOOL_TBL = "rel_edge_tool_tbl";
	
	public static int insertLadder(LadderBean ladder)
	{
		int newLadderId = 0;
		
		//form insert query
		String query = "INSERT INTO " + SCHEMA_NAME + "." + LADDERS_TBL + "(root_node_idfk, owner_idfk, ladder_name, description, ladder_type, create_dt, update_dt)";
		query += "VALUES('" + ladder.getRootNodeId() + "', '" + ladder.getOwnerId() + "', '" + ladder.getName() + "', '" + ladder.getDescription() + "', '" + ladder.getLadderType() + "', NOW(), NOW());";
		
		//call insertRow
		newLadderId = insertRow(query);
		if(newLadderId != 0)
		{
			//insertNodes
			for(int i = 0; i < ladder.getNodes().size(); i++)
			{
				ladder.getNodes().get(i).setLadderId(newLadderId);
				insertNode(ladder.getNodes().get(i));
			}
			
			//insertEdges
			for(int i = 0; i < ladder.getEdges().size(); i++)
			{
				ladder.getEdges().get(i).setParentId(newLadderId);
				insertEdge(ladder.getEdges().get(i));
			}
			
			//insert links to resources, tools and users
			for(int i = 0; i < ladder.getSharedUserIds().size(); i++)
			{
				String queryLinkUser = "INSERT INTO " + SCHEMA_NAME + "." + REL_LADDER_USER_TBL + "(ladder_idfk, user_idfk)";
				queryLinkUser += "VALUES('" + newLadderId + "', '" + ladder.getSharedUserIds().get(i) + "')";
				insertRow(queryLinkUser);
			}
			for(int i = 0; i < ladder.getToolIds().size(); i++)
			{
				String queryLinkTool = "INSERT INTO " + SCHEMA_NAME + "." + REL_LADDER_TOOL_TBL + "(ladder_idfk, tool_idfk)";
				queryLinkTool += "VALUES('" + newLadderId + "', '" + ladder.getToolIds().get(i) + "')";
				insertRow(queryLinkTool);
			}
			for(int i = 0; i < ladder.getResourceIds().size(); i++)
			{
				String queryLinkResource = "INSERT INTO " + SCHEMA_NAME + "." + REL_LADDER_RESOURCE_TBL + "(ladder_idfk, resource_idfk)";
				queryLinkResource += "VALUES('" + newLadderId + "', '" + ladder.getResourceIds().get(i) + "')";
				insertRow(queryLinkResource);
			}
		}
		return newLadderId;
	}
	
	public static void insertNode(NodeBean node)
	{
		int newNodeId = 0;
		
		//form insert query
		String query = "INSERT INTO " + SCHEMA_NAME + "." + NODES_TBL + "(node_id, ladder_idfk, owner_idfk, node_type, node_name, description, pos_x, pos_y, create_dt, update_dt)";
		query += "VALUES('" + node.getNodeId() + "', '" + node.getLadderId() + "', '" + node.getOwnerId() + "', '" + node.getNodeType() + "', '" + node.getName() + "', '" + node.getDescription() + "', '" + node.getNodePosition().getX() + "', '" + node.getNodePosition().getY() + "', NOW(), NOW())";
		
		//call insertRow
		newNodeId = insertRow(query);
		
		if(newNodeId != 0)
		{
			//insert links to resources, tools and users
			for(int i = 0; i < node.getSharedUserIds().size(); i++)
			{
				String queryLinkUser = "INSERT INTO " + SCHEMA_NAME + "." + REL_NODE_USER_TBL + "(node_tbl_idfk, user_idfk)";
				queryLinkUser += "VALUES('" + newNodeId + "', '" + node.getSharedUserIds().get(i) + "')";
				insertRow(queryLinkUser);
			}
			for(int i = 0; i < node.getToolIds().size(); i++)
			{
				String queryLinkTool = "INSERT INTO " + SCHEMA_NAME + "." + REL_NODE_TOOL_TBL + "(node_tbl_idfk, tool_idfk)";
				queryLinkTool += "VALUES('" + newNodeId + "', '" + node.getToolIds().get(i) + "')";
				insertRow(queryLinkTool);
			}
			for(int i = 0; i < node.getResourceIds().size(); i++)
			{
				String queryLinkResource = "INSERT INTO " + SCHEMA_NAME + "." + REL_NODE_RESOURCE_TBL + "(node_tbl_idfk, resource_idfk)";
				queryLinkResource += "VALUES('" + newNodeId + "', '" + node.getResourceIds().get(i) + "')";
				insertRow(queryLinkResource);
			}
		}
	}
	
	public static void insertEdge(EdgeBean edge)
	{
		int newEdgeId = 0;
		
		//form insert query
		String query = "INSERT INTO " + SCHEMA_NAME + "." + EDGES_TBL + "(edge_id, parent_idfk, owner_idfk, name, description, edge_type, from_node_idfk, to_node_idfk, create_dt, update_dt)";
		query += "VALUES('" + edge.getEdgeId() + "', '" + edge.getParentId() + "', '" + edge.getOwnerId() + "', '" + edge.getName() + "', '" + edge.getDescription() + "', '" + edge.getEdgeType() + "', '" + edge.getFromNode() + "', '" + edge.getToNode() + "', NOW(), NOW())";
				
		//call insertRow
		newEdgeId = insertRow(query);
		
		if(newEdgeId != 0)
		{
			//insert links to resources, tools and users
			for(int i = 0; i < edge.getSharedUserIds().size(); i++)
			{
				String queryLinkUser = "INSERT INTO " + SCHEMA_NAME + "." + REL_EDGE_USER_TBL + "(edge_tbl_idfk, user_idfk)";
				queryLinkUser += "VALUES('" + newEdgeId + "', '" + edge.getSharedUserIds().get(i) + "')";
				insertRow(queryLinkUser);
			}
			for(int i = 0; i < edge.getToolIds().size(); i++)
			{
				String queryLinkTool = "INSERT INTO " + SCHEMA_NAME + "." + REL_EDGE_TOOL_TBL + "(edge_tbl_idfk, tool_idfk)";
				queryLinkTool += "VALUES('" + newEdgeId + "', '" + edge.getToolIds().get(i) + "')";
				insertRow(queryLinkTool);
			}
			for(int i = 0; i < edge.getResourceIds().size(); i++)
			{
				String queryLinkResource = "INSERT INTO " + SCHEMA_NAME + "." + REL_EDGE_RESOURCE_TBL + "(edge_tbl_idfk, resource_idfk)";
				queryLinkResource += "VALUES('" + newEdgeId + "', '" + edge.getResourceIds().get(i) + "')";
				insertRow(queryLinkResource);
			}
		}
	}
	
	//get all ladders of given type
	public static ArrayList<LadderBean> getLadders(String ladderType)
	{
		ArrayList<LadderBean> laddersList = new ArrayList<>();
		//query according to ladder type
		String laddersQuery = "SELECT * FROM " + SCHEMA_NAME + "." + LADDERS_TBL + " WHERE ladder_type = '" + ladderType + "'";
		
		ArrayList<Object> objects = getObjects(laddersQuery, GetEntity.LADDERS);
		if(objects != null)
		{
			//convert each object to ladder
			for(int i = 0; i < objects.size(); i++)
			{
				LadderBean aLadder = (LadderBean)objects.get(i);

				//for each ladder - get users, resources and tools
				aLadder.setToolIds(getLadderTools(aLadder.getId()));
				aLadder.setResourceIds(getLadderResources(aLadder.getId()));
				aLadder.setSharedUserIds(getLadderUsers(aLadder.getId()));

				//for each ladder - get nodes
				aLadder.setNodes(getNodes(aLadder.getId()));

				//for each ladder - get edges
				aLadder.setEdges(getEdges(aLadder.getId()));
				
				laddersList.add(aLadder);
			}
		}
		return laddersList;
	}
	
	//get ladder by ladder id
	public static LadderBean getLadder(int id)
	{
		LadderBean ladder = null;
		
		//getLadder query
		String ladderQuery = "SELECT * FROM " + SCHEMA_NAME + "." + LADDERS_TBL + " WHERE ladder_id = " + id;
		
		ArrayList<Object> object = getObjects(ladderQuery, GetEntity.LADDER);
		if(object != null && object.size() == 1)
		{
			ladder = (LadderBean) object.get(0);
			
			//get ladder tools, resources and users
			ladder.setToolIds(getLadderTools(id));
			ladder.setResourceIds(getLadderResources(id));
			ladder.setSharedUserIds(getLadderUsers(id));
		
			//get ladder nodes
			ladder.setNodes(getNodes(id));
		
			//get ladder edges
			ladder.setEdges(getEdges(id));
		}
		
		return ladder;
	}
	
	//get nodes by ladder id
	public static ArrayList<NodeBean> getNodes(int id)
	{
		ArrayList<NodeBean> nodesList = new ArrayList<>();
		String nodesQuery = "SELECT * FROM " + SCHEMA_NAME + "." + NODES_TBL + " WHERE ladder_idfk = " + id;
		
		ArrayList<Object> objects = getObjects(nodesQuery, GetEntity.NODES);
		if(objects != null)
		{
			//convert to nodes
			for(int i = 0; i < objects.size(); i++)
			{
				NodeBean aNode = (NodeBean)objects.get(i);
				
				//get node tools, resources and users
				aNode.setToolIds(getNodeTools(aNode.getId()));
				aNode.setResourceIds(getNodeResources(aNode.getId()));
				aNode.setSharedUserIds(getNodeUsers(aNode.getId()));
				
				nodesList.add(aNode);
			}
		}
		return nodesList;
	}
	
	//get edges by ladder id
	public static ArrayList<EdgeBean> getEdges(int id)
	{
		ArrayList<EdgeBean> edgesList = new ArrayList<>();
		String edgesQuery = "SELECT * FROM " + SCHEMA_NAME + "." + EDGES_TBL + " WHERE parent_idfk = " + id;
		
		ArrayList<Object> objects = getObjects(edgesQuery, GetEntity.EDGES);
		if(objects != null)
		{
			//convert to edges
			for(int i = 0; i < objects.size(); i++)
			{
				EdgeBean anEdge = (EdgeBean)objects.get(i);
				
				//get edge tools, resources and users
				anEdge.setToolIds(getEdgeTools(anEdge.getId()));
				anEdge.setResourceIds(getEdgeResources(anEdge.getId()));
				anEdge.setSharedUserIds(getEdgeUsers(anEdge.getId()));
				
				edgesList.add(anEdge);
			}
		}
		return edgesList;
	}
	
	public static ArrayList<Integer> getLadderTools(int id)
	{
		ArrayList<Integer> ladderTools = new ArrayList<>();
		String query = "SELECT tool_idfk FROM " + SCHEMA_NAME + "." + REL_LADDER_TOOL_TBL + " WHERE ladder_idfk = " + id;
		
		ladderTools = getRelations(query, "tool_idfk", REL_LADDER_TOOL_TBL);
		return ladderTools;
	}
	
	public static ArrayList<Integer> getLadderResources(int id)
	{
		ArrayList<Integer> ladderResources = new ArrayList<>();
		String query = "SELECT resource_idfk FROM " + SCHEMA_NAME + "." + REL_LADDER_RESOURCE_TBL + " WHERE ladder_idfk = " + id;
		
		ladderResources = getRelations(query, "resource_idfk", REL_LADDER_RESOURCE_TBL);
		return ladderResources;
	}
	
	public static ArrayList<Integer> getLadderUsers(int id)
	{
		ArrayList<Integer> ladderUsers = new ArrayList<>();
		String query = "SELECT user_idfk FROM " + SCHEMA_NAME + "." + REL_LADDER_USER_TBL + " WHERE ladder_idfk = " + id;
		
		ladderUsers = getRelations(query, "user_idfk", REL_LADDER_USER_TBL);
		return ladderUsers;
	}
	
	public static ArrayList<Integer> getNodeTools(int id)
	{
		ArrayList<Integer> nodeTools = new ArrayList<>();
		String query = "SELECT tool_idfk FROM " + SCHEMA_NAME + "." + REL_NODE_TOOL_TBL + " WHERE node_tbl_idfk = " + id;
		
		nodeTools = getRelations(query, "tool_idfk", REL_NODE_TOOL_TBL);
		return nodeTools;
	}
	
	public static ArrayList<Integer> getNodeResources(int id)
	{
		ArrayList<Integer> nodeResources = new ArrayList<>();
		String query = "SELECT resource_idfk FROM " + SCHEMA_NAME + "." + REL_NODE_RESOURCE_TBL + " WHERE node_tbl_idfk = " + id;
		
		nodeResources = getRelations(query, "resource_idfk", REL_NODE_RESOURCE_TBL);
		return nodeResources;
	}
        
        public static ArrayList<String> getNodeResourceNames(ArrayList<Integer> resourceIds)
	{
		ArrayList<String> nodeResources = new ArrayList<>();
                for (int i=0; i < resourceIds.size(); i++)
                {
                    String query = "SELECT resource_name FROM " + SCHEMA_NAME + ".resource_tbl WHERE resource_id = " + resourceIds.get(i);
                    
                    conn = ConnectionManager.getConnection();
                    if (conn != null)
                    {
                            try
                            {
                                    pst = conn.prepareStatement(query);
                                    boolean isExecuted = pst.execute();
                                    if (isExecuted)
                                    {
                                            result = pst.getResultSet();
                                            boolean isValid = result.first();
                                            while (isValid)
                                            {
                                                nodeResources.add(i, result.getString("resource_tbl" + "." + "resource_name"));
                                                break;
                                            }
                                    }
                                    else
                                    {
                                            System.out.println("Could not execute Query: " + query);
                                    }
                            }
                            catch (SQLException e)
                            {
                                    System.err.println("Values for get links query not executed with following error:");
                                    System.err.println(e.getMessage());
                                    //e.printStackTrace();
                            }
                            finally
                            {
                                    closeConnection();
                            }
                    }
                }
		return nodeResources;
	}
	
	public static ArrayList<Integer> getNodeUsers(int id)
	{
		ArrayList<Integer> nodeUsers = new ArrayList<>();
		String query = "SELECT user_idfk FROM " + SCHEMA_NAME + "." + REL_NODE_USER_TBL + " WHERE node_tbl_idfk = " + id;
		
		nodeUsers = getRelations(query, "user_idfk", REL_NODE_USER_TBL);
		return nodeUsers;
	}
	
	public static ArrayList<Integer> getEdgeTools(int id)
	{
		ArrayList<Integer> edgeTools = new ArrayList<>();
		String query = "SELECT tool_idfk FROM " + SCHEMA_NAME + "." + REL_EDGE_TOOL_TBL + " WHERE edge_tbl_idfk = " + id;
		
		edgeTools = getRelations(query, "tool_idfk", REL_EDGE_TOOL_TBL);
		return edgeTools;
	}
	
	public static ArrayList<Integer> getEdgeResources(int id)
	{
		ArrayList<Integer> edgeResources = new ArrayList<>();
		String query = "SELECT resource_idfk FROM " + SCHEMA_NAME + "." + REL_EDGE_RESOURCE_TBL + " WHERE edge_tbl_idfk = " + id;
		
		edgeResources = getRelations(query, "resource_idfk", REL_EDGE_RESOURCE_TBL);
		return edgeResources;
	}
	
	public static ArrayList<Integer> getEdgeUsers(int id)
	{
		ArrayList<Integer> edgeUsers = new ArrayList<>();
		String query = "SELECT user_idfk FROM " + SCHEMA_NAME + "." + REL_EDGE_USER_TBL + " WHERE edge_tbl_idfk = " + id;
		
		edgeUsers = getRelations(query, "user_idfk", REL_EDGE_USER_TBL);
		return edgeUsers;
	}
	
	//generic function to get relations from database
	private static ArrayList<Integer> getRelations(String query, String idfk2, String tableName)
	{
		ArrayList<Integer> relations= new ArrayList<>();
		conn = ConnectionManager.getConnection();
		if (conn != null)
		{
			try
			{
				pst = conn.prepareStatement(query);
				boolean isExecuted = pst.execute();
				if (isExecuted)
				{
					result = pst.getResultSet();
					boolean isValid = result.first();
					while (isValid)
					{
						//get relations using idfk2
						int retId = result.getInt(tableName + "." + idfk2);
						relations.add(retId);

						if (result.isLast())
						{
							break;
						}
						result.next();
					}
				}
				else
				{
					System.out.println("Could not execute Query: " + query);
				}
			}
			catch (SQLException e)
			{
				System.err.println("Values for get links query not executed with following error:");
				System.err.println(e.getMessage());
				//e.printStackTrace();
			}
			finally
			{
				closeConnection();
			}
		}
		else
		{
			System.err.println("The System is not connected to database. Cannot get links.");
		}
		return relations;
	}
	
	//generic function to get objects from database
	private static ArrayList<Object> getObjects(String query, GetEntity entity)
	{
		ArrayList<Object> objects= new ArrayList<>();
		conn = ConnectionManager.getConnection();
		if (conn != null)
		{
			try
			{
				pst = conn.prepareStatement(query);
				boolean isExecuted = pst.execute();
				if (isExecuted)
				{
					result = pst.getResultSet();
					boolean isValid = result.first();
					while (isValid)
					{
						switch(entity)
						{
							case LADDERS:
								objects.add(getLadderFromDatabase(result));
								break;
							case LADDER:
								objects.add(getLadderFromDatabase(result));
								break;
							case EDGES:
								objects.add(getEdgeFromDatabase(result));
								break;
							case NODES:
								objects.add(getNodeFromDatabase(result));
								break;
                                                        case CONCEPTNODES:
								objects.add(getConceptNodeFromDatabase(result));
								break;
                                                        case CONCEPTMAPS:
                                                                objects.add(getConceptMapsFromDatabase(result));
                                                                break;
						}

						if (result.isLast())
						{
							break;
						}
						result.next();
					}
				}
				else
				{
					System.out.println("Could not execute Query: " + query);
				}
			}
			catch (SQLException e)
			{
				System.err.println("Values for get object(s) query not executed with following error:");
				System.err.println(e.getMessage());
				//e.printStackTrace();
			}
			finally
			{
				closeConnection();
			}
		}
		else
		{
			System.err.println("The System is not connected to database. Cannot get object(s).");
		}
		
		return objects;
	}
	
	//helper functions to get objects from database
	private static LadderBean getLadderFromDatabase(ResultSet result) throws SQLException
	{
		LadderBean aLadder = null;
		int ladderId = result.getInt(LADDERS_TBL + "." + "ladder_id");
		int rootNodeId = result.getInt(LADDERS_TBL + "." + "root_node_idfk");
		int ownerId = result.getInt(LADDERS_TBL + "." + "owner_idfk");
		String ladderName = result.getString(LADDERS_TBL + "." + "ladder_name");
		String description = result.getString(LADDERS_TBL + "." + "description");
		LadderBean.LadderType ladderType = LadderBean.LadderType.valueOf(result.getString(LADDERS_TBL + "." + "ladder_type"));
		Timestamp createDt = result.getTimestamp(LADDERS_TBL + "." + "create_dt");
		Timestamp updateDt = result.getTimestamp(LADDERS_TBL + "." + "update_dt");
		
		aLadder = new LadderBean(ladderId, ownerId, rootNodeId, ladderName, description, ladderType, createDt, updateDt);
		return aLadder;
	}
	
	private static NodeBean getNodeFromDatabase(ResultSet result) throws SQLException
	{
		NodeBean aNode = null;
		int id = result.getInt(NODES_TBL + "." + "id");
		int nodeId = result.getInt(NODES_TBL + "." + "node_id");
		int ladderId = result.getInt(NODES_TBL + "." + "ladder_idfk");
		int ownerId = result.getInt(NODES_TBL + "." + "owner_idfk");
		NodeBean.NodeType nodeType = NodeBean.NodeType.valueOf(result.getString(NODES_TBL + "." + "node_type"));
		String nodeName = result.getString(NODES_TBL + "." + "node_name");
		String description = result.getString(NODES_TBL + "." + "description");
		double posX = result.getDouble("pos_x");
		double posY = result.getDouble("pos_y");
		Timestamp createDt = result.getTimestamp(NODES_TBL + "." + "create_dt");
		Timestamp updateDt = result.getTimestamp(NODES_TBL + "." + "update_dt");
                
                String classforSelection = result.getString(NODES_TBL + "." + "class_for_selection");
                String classforNode = result.getString(NODES_TBL + "." + "class_for_node");
		
		aNode = new NodeBean(id, nodeId, ladderId, ownerId, nodeType, nodeName, description, posX, posY, createDt, updateDt);
		return aNode;
	}
	
	private static EdgeBean getEdgeFromDatabase(ResultSet result) throws SQLException
	{
		EdgeBean anEdge = null;
		int id = result.getInt(EDGES_TBL + "." + "id");
		int edgeId = result.getInt(EDGES_TBL + "." + "edge_id");
		int parentId = result.getInt(EDGES_TBL + "." + "parent_idfk");
		int ownerId = result.getInt(EDGES_TBL + "." + "owner_idfk");
		String edgeName = result.getString(EDGES_TBL + "." + "name");
		String description = result.getString(EDGES_TBL + "." + "description");
		EdgeBean.EdgeType edgeType = EdgeBean.EdgeType.valueOf(result.getString("edge_type"));
		int fromNodeId = result.getInt(EDGES_TBL + "." + "from_node_idfk");
		int toNodeId = result.getInt(EDGES_TBL + "." + "to_node_idfk");
		Timestamp createDt = result.getTimestamp(EDGES_TBL + "." + "create_dt");
		Timestamp updateDt = result.getTimestamp(EDGES_TBL + "." + "update_dt");
		
		anEdge = new EdgeBean(id, edgeId, parentId, ownerId, edgeName, description, edgeType, fromNodeId, toNodeId, createDt, updateDt);
		return anEdge;
	}
	
	//generic function to insert row in database
	private static int insertRow(String query)
	{
		int id = 0;
		conn = ConnectionManager.getConnection();
		if (conn != null)
		{
			try
			{
				pst = conn.prepareStatement(query);
				pst = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
				pst.executeUpdate();
				result = pst.getGeneratedKeys();
				if (result.first())
				{
					id = result.getInt(1);
				}
				result.close();
			}
			catch (SQLException e)
			{
				System.err.println("Insert command not executed with following error:");
				System.err.println(e.getMessage());
				System.err.println("For Query: " + query);
				//e.printStackTrace();
			}
			finally
			{
				closeConnection();
			}
		}
		else
		{
			System.err.println("The System is not connected to database. Cannot insert row.");
		}
		return id;
	}
	
	public static LadderBean updateLadder(LadderBean aLadder)
	{
		//update ladder details
		Date utilDate = new java.util.Date();
		aLadder.setUpdateDate(new Timestamp(utilDate.getTime()));

		String updateLadderQuery = "UPDATE " + SCHEMA_NAME + "." + LADDERS_TBL
						+ " SET root_node_idfk = '" + aLadder.getRootNodeId() + "', owner_idfk = '" + aLadder.getOwnerId()
						+ "', ladder_name = '" + aLadder.getName() + "', description = '" + aLadder.getDescription() + "', ladder_type = '" + aLadder.getLadderType()
						+ "', create_dt = '" + aLadder.getCreateDate() + "', update_dt = '" + aLadder.getUpdateDate()
						+ "' WHERE ladder_id = " + aLadder.getId();
		updateRow(updateLadderQuery);
		//delete old links - ladder-user, ladder-tools, ladder-resources
		String delLadderToolsQuery = "DELETE FROM " + SCHEMA_NAME + "." + REL_LADDER_TOOL_TBL + " WHERE ladder_idfk = '" + aLadder.getId() + "'";
		deleteRow(delLadderToolsQuery);
		String delLadderResourceQuery = "DELETE FROM " + SCHEMA_NAME + "." + REL_LADDER_RESOURCE_TBL + " WHERE ladder_idfk = '" + aLadder.getId() + "'";
		deleteRow(delLadderResourceQuery);
		String delLadderUserQuery = "DELETE FROM " + SCHEMA_NAME + "." + REL_LADDER_USER_TBL + " WHERE ladder_idfk = '" + aLadder.getId() + "'";
		deleteRow(delLadderUserQuery);
		
		//DUMMY data
		/*aLadder.getSharedUserIds().add(1);
		aLadder.getSharedUserIds().add(2);
		aLadder.getSharedUserIds().add(3);
		aLadder.getToolIds().add(1);
		aLadder.getToolIds().add(2);
		aLadder.getResourceIds().add(1);*/
		
		//add new links - insert links to resources, tools and users
		for(int i = 0; i < aLadder.getSharedUserIds().size(); i++)
		{
			String queryLinkUser = "INSERT INTO " + SCHEMA_NAME + "." + REL_LADDER_USER_TBL + "(ladder_idfk, user_idfk)";
			queryLinkUser += "VALUES('" + aLadder.getId() + "', '" + aLadder.getSharedUserIds().get(i) + "')";
			insertRow(queryLinkUser);
		}
		for(int i = 0; i < aLadder.getToolIds().size(); i++)
		{
			String queryLinkTool = "INSERT INTO " + SCHEMA_NAME + "." + REL_LADDER_TOOL_TBL + "(ladder_idfk, tool_idfk)";
			queryLinkTool += "VALUES('" + aLadder.getId() + "', '" + aLadder.getToolIds().get(i) + "')";
			insertRow(queryLinkTool);
		}
		for(int i = 0; i < aLadder.getResourceIds().size(); i++)
		{
			String queryLinkResource = "INSERT INTO " + SCHEMA_NAME + "." + REL_LADDER_RESOURCE_TBL + "(ladder_idfk, resource_idfk)";
			queryLinkResource += "VALUES('" + aLadder.getId() + "', '" + aLadder.getResourceIds().get(i) + "')";
			insertRow(queryLinkResource);
		}
		
		//delete all old edges from database alongwith all links
		deleteEdgesWithLadderId(aLadder.getId());
		//insert all new edges with links to database
		for(int i = 0; i < aLadder.getEdges().size(); i++)
		{
			aLadder.getEdges().get(i).setParentId(aLadder.getId());
			insertEdge(aLadder.getEdges().get(i));
		}
		
		//delete all old nodes from database alongwith all links
		deleteNodesWithLadderId(aLadder.getId());
		//insert all new nodes with links to database
		for(int i = 0; i < aLadder.getNodes().size(); i++)
		{
			aLadder.getNodes().get(i).setLadderId(aLadder.getId());
			insertNode(aLadder.getNodes().get(i));
		}

		//match updated node with original
			//if no match - add node to database
			//if match - mark as matched in original
					//- update node details in database
					//- delete all entries from links
					//- add all entries of links
		return aLadder;
	}
	
	private static void updateRow(String query)
	{
		conn = ConnectionManager.getConnection();
		if (conn != null)
		{
			try
			{
				pst = conn.prepareStatement(query);
				pst.executeUpdate();
			}
			catch (SQLException e)
			{
				System.err.println("Update command not executed with following error:");
				System.err.println(e.getMessage());
				//e.printStackTrace();
			}
			finally
			{
				closeConnection();
			}
		}
		else
		{
			System.err.println("The System is not connected to database. Cannot update row.");
		}
	}
	
	public static void deleteLadder(int id)
	{
		//delete query
		String delLadderQuery = "DELETE FROM " + SCHEMA_NAME + "." + LADDERS_TBL + " WHERE ladder_id = '" + id + "'";
		deleteRow(delLadderQuery);
		
		//delete links - ladder-user, ladder-tools, ladder-resources
		String delLadderToolsQuery = "DELETE FROM " + SCHEMA_NAME + "." + REL_LADDER_TOOL_TBL + " WHERE ladder_idfk = '" + id + "'";
		deleteRow(delLadderToolsQuery);
		String delLadderResourceQuery = "DELETE FROM " + SCHEMA_NAME + "." + REL_LADDER_RESOURCE_TBL + " WHERE ladder_idfk = '" + id + "'";
		deleteRow(delLadderResourceQuery);
		String delLadderUserQuery = "DELETE FROM " + SCHEMA_NAME + "." + REL_LADDER_USER_TBL + " WHERE ladder_idfk = '" + id + "'";
		deleteRow(delLadderUserQuery);
		
		//delete nodes
		deleteNodesWithLadderId(id);
		//delete edges
		deleteEdgesWithLadderId(id);
	}
	
	private static void deleteEdgesWithLadderId(int ladderId)
	{
		//get all edges
		ArrayList<EdgeBean> allEdges = getEdges(ladderId);
		if(allEdges == null || allEdges.isEmpty())
			return;
		
		//delete all links for each edge id
		String delLinkQuery = null;
		for(int i = 0; i < allEdges.size(); i++)
		{
			EdgeBean anEdge = allEdges.get(i);
			delLinkQuery = "DELETE FROM " + SCHEMA_NAME + "." + REL_EDGE_USER_TBL + " WHERE edge_tbl_idfk = '" + anEdge.getId() + "'";
			deleteRow(delLinkQuery);
			delLinkQuery = "DELETE FROM " + SCHEMA_NAME + "." + REL_EDGE_RESOURCE_TBL + " WHERE edge_tbl_idfk = '" + anEdge.getId() + "'";
			deleteRow(delLinkQuery);
			delLinkQuery = "DELETE FROM " + SCHEMA_NAME + "." + REL_EDGE_TOOL_TBL + " WHERE edge_tbl_idfk = '" + anEdge.getId() + "'";
			deleteRow(delLinkQuery);
		}
		
		//delete all edges 
		String delEdgeQuery = "DELETE FROM " + SCHEMA_NAME + "." + EDGES_TBL + " WHERE parent_idfk = '" + ladderId + "'";
		deleteRow(delEdgeQuery);
	}
	
	private static void deleteNodesWithLadderId(int ladderId)
	{
		//get all nodes
		ArrayList<NodeBean> allNodes = getNodes(ladderId);
		if(allNodes == null || allNodes.isEmpty())
			return;
		
		//delete all links for each node id
		String delLinkQuery = null;
		for(int i = 0; i < allNodes.size(); i++)
		{
			NodeBean aNode = allNodes.get(i);
			delLinkQuery = "DELETE FROM " + SCHEMA_NAME + "." + REL_NODE_USER_TBL + " WHERE node_tbl_idfk = '" + aNode.getId() + "'";
			deleteRow(delLinkQuery);
			delLinkQuery = "DELETE FROM " + SCHEMA_NAME + "." + REL_NODE_RESOURCE_TBL + " WHERE node_tbl_idfk = '" + aNode.getId() + "'";
			deleteRow(delLinkQuery);
			delLinkQuery = "DELETE FROM " + SCHEMA_NAME + "." + REL_NODE_TOOL_TBL + " WHERE node_tbl_idfk = '" + aNode.getId() + "'";
			deleteRow(delLinkQuery);
		}
		
		//delete all nodes 
		String delEdgeQuery = "DELETE FROM " + SCHEMA_NAME + "." + NODES_TBL + " WHERE ladder_idfk = '" + ladderId + "'";
		deleteRow(delEdgeQuery);
	}
	
	private static void deleteRow(String query)
	{
		conn = ConnectionManager.getConnection();
		if (conn != null)
		{
			try
			{
				pst = conn.prepareStatement(query);
				pst.execute();
			}
			catch (SQLException e)
			{
				System.err.println("Delete command not executed with following error:");
				System.err.println(e.getMessage());
			}
			finally
			{
				closeConnection();
			}
		}
		else
		{
			System.err.println("The System is not connected to database. Cannot delete row.");
		}
	}
	
	private static void closeConnection()
	{
		if (result != null)
		{
			try 
			{
				result.close();
			} 
			catch (Exception e) 
			{
				System.err.println("Unable to close ResultSet: " + e.getMessage());
				e.printStackTrace();
			}
			result = null;
		}
		if (pst != null) 
		{
			try 
			{
				pst.close();
			} 
			catch (Exception e) 
			{
				System.err.println("Unable to close PreparedStatement: " + e.getMessage());
				e.printStackTrace();
			}
			pst = null;
		}   
		ConnectionManager.putConnection(conn);
	}
	
	public static JsonObject jsonifyLadder(LadderBean ladder)
	{
		//LadderBean ladder = LadderDAO.getLadder(5);
		//JSONIFY - add each attribute separately according to how it is stored on save in "process-ladder-creator.js"
		//jsonify nodes
		JsonArrayBuilder jsonNodesBuilder = Json.createArrayBuilder();
		for(int i = 0; i < ladder.getNodes().size(); i++)
		{
			NodeBean node = ladder.getNodes().get(i);
			JsonObjectBuilder nodeBuilder = Json.createObjectBuilder()
								.add("id", node.getNodeId())
								.add("nodeType", node.getNodeType().toString())
								.add("description", node.getDescription())
								.add("title", node.getName());
			
			//jsonify tools, users, resources linked to nodes
			JsonArrayBuilder jsonToolBuilder = Json.createArrayBuilder();
			for(int j = 0; j < node.getToolIds().size(); j++)
			{
				jsonToolBuilder.add(node.getToolIds().get(j));
			}
			JsonArrayBuilder jsonResourceBuilder = Json.createArrayBuilder();
			for(int j = 0; j < node.getResourceIds().size(); j++)
			{
				jsonResourceBuilder.add(node.getResourceIds().get(j));
			}
			JsonArrayBuilder jsonUserBuilder = Json.createArrayBuilder();
			for(int j = 0; j < node.getSharedUserIds().size(); j++)
			{
				jsonUserBuilder.add(node.getSharedUserIds().get(j));
			}
			nodeBuilder.add("tools", jsonToolBuilder.build())
					.add("resources", jsonResourceBuilder.build())
					.add("users", jsonUserBuilder.build())
					.add("x", node.getNodePosition().getX())
					.add("y", node.getNodePosition().getY());
			
			jsonNodesBuilder.add(nodeBuilder.build());
		}
		
		//jsonify edges
		JsonArrayBuilder jsonEdgesBuilder = Json.createArrayBuilder();
		for(int i = 0; i < ladder.getEdges().size(); i++)
		{
			EdgeBean edge = ladder.getEdges().get(i);
			JsonObjectBuilder edgeBuilder = Json.createObjectBuilder()
					.add("id", edge.getEdgeId())
					.add("title", edge.getName())
					.add("description", edge.getDescription())
					.add("edgeType", edge.getEdgeType().toString());
			
			//jsonify tools, resources, users linked with edges
			JsonArrayBuilder jsonToolBuilder = Json.createArrayBuilder();
			for(int j = 0; j < edge.getToolIds().size(); j++)
			{
				jsonToolBuilder.add(edge.getToolIds().get(j));
			}
			JsonArrayBuilder jsonResourceBuilder = Json.createArrayBuilder();
			for(int j = 0; j < edge.getResourceIds().size(); j++)
			{
				jsonResourceBuilder.add(edge.getResourceIds().get(j));
			}
			JsonArrayBuilder jsonUserBuilder = Json.createArrayBuilder();
			for(int j = 0; j < edge.getSharedUserIds().size(); j++)
			{
				jsonUserBuilder.add(edge.getSharedUserIds().get(j));
			}
			
			edgeBuilder.add("tools", jsonToolBuilder.build())
					.add("resources", jsonResourceBuilder.build())
					.add("users", jsonUserBuilder.build())
					.add("source", edge.getFromNode())
					.add("target", edge.getToNode());
			
			jsonEdgesBuilder.add(edgeBuilder.build());
		}
		
		JsonObject jsonLadderObject = Json.createObjectBuilder()
				.add("id", ladder.getId())
				.add("name", ladder.getName())
				.add("ladderType", ladder.getLadderType().toString())
				.add("description", ladder.getDescription())
				.add("rootNodeId", ladder.getRootNodeId())
				.add("ownerId", ladder.getOwnerId())
				.add("createDt", ladder.getCreateDate().getTime()+"")
				.add("updateDt", ladder.getUpdateDate().getTime()+"")
				.add("nodes", jsonNodesBuilder.build())
				.add("edges", jsonEdgesBuilder.build())
				.build();
		
		//System.out.println(jsonLadderObject.toString());
		return jsonLadderObject;
	}
	
	public static String jsonLadderString(LadderBean ladder)
	{
		return jsonifyLadder(ladder).toString();
	}
	
	public static JsonArray jsonifyAllLadders(ArrayList<LadderBean> ladders)
	{
		JsonArrayBuilder allLaddersJsonArray = Json.createArrayBuilder();
		for(int i = 0; i < ladders.size(); i++)
		{
			JsonObject aLadderJson = jsonifyLadder(ladders.get(i));
			allLaddersJsonArray.add(aLadderJson);
		}
		return allLaddersJsonArray.build();
	}
	
	public static String jsonAllLaddersString(ArrayList<LadderBean> ladders)
	{
		return jsonifyAllLadders(ladders).toString();
	}
        
        public static int ifLadderExists(String ladderType, String ladderTitle)
	{
		int ladderID = 0;
                int nodeID = -2;
                //String query = "Select ladder_id from " + SCHEMA_NAME + "." + LADDERS_TBL + " where ladder_type = '" + ladderType + "' and ladder_name = '" + ladderTitle + "'";
                
                String segments[] = ladderTitle.split("[\\|]");
                String ladderName = segments[0].trim();
                String nodeName = segments[segments.length -1].trim();
		
                String query = "Select ladder_id from " + SCHEMA_NAME + "." + LADDERS_TBL + " where ladder_type = '" + ladderType + "' and ladder_name = '" + ladderName + "'";
                
                conn = ConnectionManager.getConnection();
		if (conn != null)
		{
			try
			{
				pst = conn.prepareStatement(query);
				boolean isExecuted = pst.execute();
				if (isExecuted)
				{
					result = pst.getResultSet();
					boolean isValid = result.first();
					while (isValid)
					{
                                            ladderID = result.getInt(LADDERS_TBL + "." + "ladder_id");
                                            break;
                                        }
                                }
                        }
                        catch (Exception e) 
			{
				System.err.println("Unable to close ResultSet: " + e.getMessage());
				e.printStackTrace();
			}
                }
                
                if (ladderID > 0)
                {
                    String q = "Select node_id from " + SCHEMA_NAME + "." + NODES_TBL + " where ladder_idfk ="+ladderID + " and node_name = '" + nodeName + "'";
                    nodeID = -1;
                    conn = ConnectionManager.getConnection();
                    if (conn != null)
                    {
                            try
                            {
                                    pst = conn.prepareStatement(q);
                                    boolean isExecuted = pst.execute();
                                    if (isExecuted)
                                    {
                                            result = pst.getResultSet();
                                            boolean isValid = result.first();
                                            while (isValid)
                                            {
                                                nodeID = result.getInt(NODES_TBL + "." + "node_id");
                                                break;
                                            }
                                    }
                            }
                            catch (Exception e) 
                            {
                                    System.err.println("Unable to close ResultSet: " + e.getMessage());
                                    e.printStackTrace();
                            }
                    }
                }
		return nodeID;
        }
        
        public static int insertConceptMap(ConceptMapBean conceptMap)
	{
		int newLadderId = 0;
		
		//form insert query
		String query = "INSERT INTO " + SCHEMA_NAME + "." + LADDERS_TBL + "(root_node_idfk, owner_idfk, ladder_name, description, ladder_type, create_dt, update_dt)";
		query += "VALUES('" + conceptMap.getRootNodeId() + "', '" + conceptMap.getOwnerId() + "', '" + conceptMap.getName() + "', '" + conceptMap.getDescription() + "', '" + conceptMap.getLadderType() + "', NOW(), NOW());";
		
		//call insertRow
		newLadderId = insertRow(query);
		if(newLadderId != 0)
		{
			//insertNodes
			for(int i = 0; i < conceptMap.getNodes().size(); i++)
			{
				conceptMap.getNodes().get(i).setLadderId(newLadderId);
				insertNodeConceptMap(conceptMap.getNodes().get(i));
			}
			
			//insertEdges
			for(int i = 0; i < conceptMap.getEdges().size(); i++)
			{
				conceptMap.getEdges().get(i).setParentId(newLadderId);
				insertEdge(conceptMap.getEdges().get(i));
			}
			
			//insert links to resources, tools and users
			for(int i = 0; i < conceptMap.getSharedUserIds().size(); i++)
			{
				String queryLinkUser = "INSERT INTO " + SCHEMA_NAME + "." + REL_LADDER_USER_TBL + "(ladder_idfk, user_idfk)";
				queryLinkUser += "VALUES('" + newLadderId + "', '" + conceptMap.getSharedUserIds().get(i) + "')";
				insertRow(queryLinkUser);
			}
			for(int i = 0; i < conceptMap.getToolIds().size(); i++)
			{
				String queryLinkTool = "INSERT INTO " + SCHEMA_NAME + "." + REL_LADDER_TOOL_TBL + "(ladder_idfk, tool_idfk)";
				queryLinkTool += "VALUES('" + newLadderId + "', '" + conceptMap.getToolIds().get(i) + "')";
				insertRow(queryLinkTool);
			}
			for(int i = 0; i < conceptMap.getResourceIds().size(); i++)
			{
				String queryLinkResource = "INSERT INTO " + SCHEMA_NAME + "." + REL_LADDER_RESOURCE_TBL + "(ladder_idfk, resource_idfk)";
				queryLinkResource += "VALUES('" + newLadderId + "', '" + conceptMap.getResourceIds().get(i) + "')";
				insertRow(queryLinkResource);
			}
		}
		return newLadderId;
	}
        
        public static ConceptMapBean updateConceptMap(ConceptMapBean aLadder)
	{
		//update ladder details
		Date utilDate = new java.util.Date();
		aLadder.setUpdateDate(new Timestamp(utilDate.getTime()));

		String updateLadderQuery = "UPDATE " + SCHEMA_NAME + "." + LADDERS_TBL
						+ " SET root_node_idfk = '" + aLadder.getRootNodeId() + "', owner_idfk = '" + aLadder.getOwnerId()
						+ "', ladder_name = '" + aLadder.getName() + "', description = '" + aLadder.getDescription() + "', ladder_type = '" + aLadder.getLadderType()
						+ "', create_dt = '" + aLadder.getCreateDate() + "', update_dt = '" + aLadder.getUpdateDate()
						+ "' WHERE ladder_id = " + aLadder.getId();
		updateRow(updateLadderQuery);
		//delete old links - ladder-user, ladder-tools, ladder-resources
		String delLadderToolsQuery = "DELETE FROM " + SCHEMA_NAME + "." + REL_LADDER_TOOL_TBL + " WHERE ladder_idfk = '" + aLadder.getId() + "'";
		deleteRow(delLadderToolsQuery);
		String delLadderResourceQuery = "DELETE FROM " + SCHEMA_NAME + "." + REL_LADDER_RESOURCE_TBL + " WHERE ladder_idfk = '" + aLadder.getId() + "'";
		deleteRow(delLadderResourceQuery);
		String delLadderUserQuery = "DELETE FROM " + SCHEMA_NAME + "." + REL_LADDER_USER_TBL + " WHERE ladder_idfk = '" + aLadder.getId() + "'";
		deleteRow(delLadderUserQuery);
		
		//DUMMY data
		/*aLadder.getSharedUserIds().add(1);
		aLadder.getSharedUserIds().add(2);
		aLadder.getSharedUserIds().add(3);
		aLadder.getToolIds().add(1);
		aLadder.getToolIds().add(2);
		aLadder.getResourceIds().add(1);*/
		
		//add new links - insert links to resources, tools and users
		for(int i = 0; i < aLadder.getSharedUserIds().size(); i++)
		{
			String queryLinkUser = "INSERT INTO " + SCHEMA_NAME + "." + REL_LADDER_USER_TBL + "(ladder_idfk, user_idfk)";
			queryLinkUser += "VALUES('" + aLadder.getId() + "', '" + aLadder.getSharedUserIds().get(i) + "')";
			insertRow(queryLinkUser);
		}
		for(int i = 0; i < aLadder.getToolIds().size(); i++)
		{
			String queryLinkTool = "INSERT INTO " + SCHEMA_NAME + "." + REL_LADDER_TOOL_TBL + "(ladder_idfk, tool_idfk)";
			queryLinkTool += "VALUES('" + aLadder.getId() + "', '" + aLadder.getToolIds().get(i) + "')";
			insertRow(queryLinkTool);
		}
		for(int i = 0; i < aLadder.getResourceIds().size(); i++)
		{
			String queryLinkResource = "INSERT INTO " + SCHEMA_NAME + "." + REL_LADDER_RESOURCE_TBL + "(ladder_idfk, resource_idfk)";
			queryLinkResource += "VALUES('" + aLadder.getId() + "', '" + aLadder.getResourceIds().get(i) + "')";
			insertRow(queryLinkResource);
		}
		
		//delete all old edges from database alongwith all links
		deleteEdgesWithLadderId(aLadder.getId());
		//insert all new edges with links to database
		for(int i = 0; i < aLadder.getEdges().size(); i++)
		{
			aLadder.getEdges().get(i).setParentId(aLadder.getId());
			insertEdge(aLadder.getEdges().get(i));
		}
		
		//delete all old nodes from database alongwith all links
		deleteNodesWithLadderId(aLadder.getId());
		//insert all new nodes with links to database
		for(int i = 0; i < aLadder.getNodes().size(); i++)
		{
			aLadder.getNodes().get(i).setLadderId(aLadder.getId());
			insertNodeConceptMap(aLadder.getNodes().get(i));
		}

		//match updated node with original
			//if no match - add node to database
			//if match - mark as matched in original
					//- update node details in database
					//- delete all entries from links
					//- add all entries of links
		return aLadder;
	}
	
        public static void insertNodeConceptMap(ConceptNodeBean node)
	{
		int newNodeId = 0;
		
		//form insert query
		String query = "INSERT INTO " + SCHEMA_NAME + "." + NODES_TBL + "(node_id, ladder_idfk, owner_idfk, node_type, node_name, description, pos_x, pos_y, create_dt, update_dt, class_for_selection, class_for_node)";
		query += "VALUES('" + node.getNodeId() + "', '" + node.getLadderId() + "', '" + node.getOwnerId() + "', '" + node.getNodeType() + "', '" + node.getName() + "', '" + node.getDescription() + "', '" + node.getNodePosition().getX() + "', '" + node.getNodePosition().getY() + "', NOW(), NOW(), '" + node.getClassforSelection() + "', '" + node.getClassforNode()+ "')";
		
		//call insertRow
		newNodeId = insertRow(query);
		
		if(newNodeId != 0)
		{
			//insert links to resources, tools and users
			for(int i = 0; i < node.getSharedUserIds().size(); i++)
			{
				String queryLinkUser = "INSERT INTO " + SCHEMA_NAME + "." + REL_NODE_USER_TBL + "(node_tbl_idfk, user_idfk)";
				queryLinkUser += "VALUES('" + newNodeId + "', '" + node.getSharedUserIds().get(i) + "')";
				insertRow(queryLinkUser);
			}
			for(int i = 0; i < node.getToolIds().size(); i++)
			{
				String queryLinkTool = "INSERT INTO " + SCHEMA_NAME + "." + REL_NODE_TOOL_TBL + "(node_tbl_idfk, tool_idfk)";
				queryLinkTool += "VALUES('" + newNodeId + "', '" + node.getToolIds().get(i) + "')";
				insertRow(queryLinkTool);
			}
			for(int i = 0; i < node.getResourceIds().size(); i++)
			{
				String queryLinkResource = "INSERT INTO " + SCHEMA_NAME + "." + REL_NODE_RESOURCE_TBL + "(node_tbl_idfk, resource_idfk)";
				queryLinkResource += "VALUES('" + newNodeId + "', '" + node.getResourceIds().get(i) + "')";
				insertRow(queryLinkResource);
			}
                        
                        for(int i = 0; i < node.getResources().size(); i++)
			{
                            String resourceID = "Select resource_id from " + SCHEMA_NAME + ". resource_tbl where resource_name = '" + node.getResources().get(i) +"'";
                            int resID = 0;
                            conn = ConnectionManager.getConnection();
                            if (conn != null)
                            {
                                try
                                {
                                    pst = conn.prepareStatement(resourceID);
                                    boolean isExecuted = pst.execute();
                                    if (isExecuted)
                                    {
					result = pst.getResultSet();
					boolean isValid = result.first();
					while (isValid)
					{
                                            resID = result.getInt("resource_tbl" + "." + "resource_id");
                                            break;
                                        }
                                    }
                                }
                                catch (SQLException e)
                                {
                                        System.err.println("Values for get links query not executed with following error:");
                                        System.err.println(e.getMessage());
                                        //e.printStackTrace();
                                }
                                finally
                                {
                                        closeConnection();
                                }
				String queryLinkResource = "INSERT INTO " + SCHEMA_NAME + "." + REL_NODE_RESOURCE_TBL + "(node_tbl_idfk, resource_idfk)";
				//queryLinkResource += "VALUES('" + newNodeId + "', '" + node.getResourceIds().get(i) + "')";
                                queryLinkResource += "VALUES('" + newNodeId + "', '" + resID + "')";
				insertRow(queryLinkResource);
                            }
                        }
		}
	}
        
        public static ArrayList<ConceptNodeBean> getConceptNodes(int id)
	{
		ArrayList<ConceptNodeBean> nodesList = new ArrayList<>();
		String nodesQuery = "SELECT * FROM " + SCHEMA_NAME + "." + NODES_TBL + " WHERE ladder_idfk = " + id;
		
		ArrayList<Object> objects = getObjects(nodesQuery, GetEntity.CONCEPTNODES);
		if(objects != null)
		{
			//convert to nodes
			for(int i = 0; i < objects.size(); i++)
			{
				ConceptNodeBean aNode = (ConceptNodeBean)objects.get(i);
				
				//get node tools, resources and users
				aNode.setToolIds(getNodeTools(aNode.getId()));
				aNode.setResourceIds(getNodeResources(aNode.getId()));
                                aNode.setResources(getNodeResourceNames(aNode.getResourceIds()));
				aNode.setSharedUserIds(getNodeUsers(aNode.getId()));
				
				nodesList.add(aNode);
			}
		}
		return nodesList;
	}
        
        public static ArrayList<ConceptMapBean> getConceptLadders(String ladderType)
	{
		ArrayList<ConceptMapBean> laddersList = new ArrayList<>();
		//query according to ladder type
		String laddersQuery = "SELECT * FROM " + SCHEMA_NAME + "." + LADDERS_TBL + " WHERE ladder_type = '" + ladderType + "'";
		
		ArrayList<Object> objects = getObjects(laddersQuery, GetEntity.CONCEPTMAPS);
		if(objects != null)
		{
			//convert each object to ladder
			for(int i = 0; i < objects.size(); i++)
			{
				ConceptMapBean aLadder = (ConceptMapBean)objects.get(i);

				//for each ladder - get users, resources and tools
				aLadder.setToolIds(getLadderTools(aLadder.getId()));
				aLadder.setResourceIds(getLadderResources(aLadder.getId()));
                                aLadder.setSharedUserIds(getLadderUsers(aLadder.getId()));

				//for each ladder - get nodes
				aLadder.setNodes(getConceptNodes(aLadder.getId()));

				//for each ladder - get edges
				aLadder.setEdges(getEdges(aLadder.getId()));
				
				laddersList.add(aLadder);
			}
		}
		return laddersList;
	}
        
        private static ConceptNodeBean getConceptNodeFromDatabase(ResultSet result) throws SQLException
	{
		ConceptNodeBean aNode = null;
		int id = result.getInt(NODES_TBL + "." + "id");
		int nodeId = result.getInt(NODES_TBL + "." + "node_id");
		int ladderId = result.getInt(NODES_TBL + "." + "ladder_idfk");
		int ownerId = result.getInt(NODES_TBL + "." + "owner_idfk");
		ConceptNodeBean.NodeType nodeType = ConceptNodeBean.NodeType.valueOf(result.getString(NODES_TBL + "." + "node_type"));
		String nodeName = result.getString(NODES_TBL + "." + "node_name");
		String description = result.getString(NODES_TBL + "." + "description");
		double posX = result.getDouble("pos_x");
		double posY = result.getDouble("pos_y");
		Timestamp createDt = result.getTimestamp(NODES_TBL + "." + "create_dt");
		Timestamp updateDt = result.getTimestamp(NODES_TBL + "." + "update_dt");
                
                String classforSelection = result.getString(NODES_TBL + "." + "class_for_selection");
                String classforNode = result.getString(NODES_TBL + "." + "class_for_node");
		
		aNode = new ConceptNodeBean(id, nodeId, ladderId, ownerId, nodeType, nodeName, description, posX, posY, createDt, updateDt, classforSelection, classforNode);
		return aNode;
	}
        
        public static String jsonConceptLaddersString(ArrayList<ConceptMapBean> ladders)
	{
		return jsonifyConceptLadders(ladders).toString();
	}
        
        public static JsonArray jsonifyConceptLadders(ArrayList<ConceptMapBean> ladders)
	{
		JsonArrayBuilder allLaddersJsonArray = Json.createArrayBuilder();
		for(int i = 0; i < ladders.size(); i++)
		{
			JsonObject aLadderJson = jsonifyConceptLadder(ladders.get(i));
			allLaddersJsonArray.add(aLadderJson);
		}
		return allLaddersJsonArray.build();
	}
        
        public static JsonObject jsonifyConceptLadder(ConceptMapBean ladder)
	{
		//LadderBean ladder = LadderDAO.getLadder(5);
		//JSONIFY - add each attribute separately according to how it is stored on save in "process-ladder-creator.js"
		//jsonify nodes
		JsonArrayBuilder jsonNodesBuilder = Json.createArrayBuilder();
		for(int i = 0; i < ladder.getNodes().size(); i++)
		{
			ConceptNodeBean node = ladder.getNodes().get(i);
			JsonObjectBuilder nodeBuilder = Json.createObjectBuilder()
								.add("id", node.getNodeId())
								.add("nodeType", node.getNodeType().toString())
								.add("description", node.getDescription())
                                                                .add("classforSelection", node.getClassforSelection())
                                                                .add("classforNode", node.getClassforNode())
								.add("title", node.getName());
			
			//jsonify tools, users, resources linked to nodes
			JsonArrayBuilder jsonToolBuilder = Json.createArrayBuilder();
			for(int j = 0; j < node.getToolIds().size(); j++)
			{
				jsonToolBuilder.add(node.getToolIds().get(j));
			}
			JsonArrayBuilder jsonResourceBuilder = Json.createArrayBuilder();
			for(int j = 0; j < node.getResourceIds().size(); j++)
			{
				jsonResourceBuilder.add(node.getResourceIds().get(j));
			}
                        JsonArrayBuilder jsonResourceNamesBuilder = Json.createArrayBuilder();
			for(int j = 0; j < node.getResources().size(); j++)
			{
				jsonResourceNamesBuilder.add(node.getResources().get(j));
			}
			JsonArrayBuilder jsonUserBuilder = Json.createArrayBuilder();
			for(int j = 0; j < node.getSharedUserIds().size(); j++)
			{
				jsonUserBuilder.add(node.getSharedUserIds().get(j));
			}
			nodeBuilder.add("tools", jsonToolBuilder.build())
					//.add("resources", jsonResourceBuilder.build())
                                        .add("resources", jsonResourceNamesBuilder.build())
					.add("users", jsonUserBuilder.build())
					.add("x", node.getNodePosition().getX())
					.add("y", node.getNodePosition().getY());
			
			jsonNodesBuilder.add(nodeBuilder.build());
		}
		
		//jsonify edges
		JsonArrayBuilder jsonEdgesBuilder = Json.createArrayBuilder();
		for(int i = 0; i < ladder.getEdges().size(); i++)
		{
			EdgeBean edge = ladder.getEdges().get(i);
			JsonObjectBuilder edgeBuilder = Json.createObjectBuilder()
					.add("id", edge.getEdgeId())
					.add("title", edge.getName())
					.add("description", edge.getDescription())
					.add("edgeType", edge.getEdgeType().toString());
			
			//jsonify tools, resources, users linked with edges
			JsonArrayBuilder jsonToolBuilder = Json.createArrayBuilder();
			for(int j = 0; j < edge.getToolIds().size(); j++)
			{
				jsonToolBuilder.add(edge.getToolIds().get(j));
			}
			JsonArrayBuilder jsonResourceBuilder = Json.createArrayBuilder();
			for(int j = 0; j < edge.getResourceIds().size(); j++)
			{
				jsonResourceBuilder.add(edge.getResourceIds().get(j));
			}
			JsonArrayBuilder jsonUserBuilder = Json.createArrayBuilder();
			for(int j = 0; j < edge.getSharedUserIds().size(); j++)
			{
				jsonUserBuilder.add(edge.getSharedUserIds().get(j));
			}
			
			edgeBuilder.add("tools", jsonToolBuilder.build())
					.add("resources", jsonResourceBuilder.build())
					.add("users", jsonUserBuilder.build())
					.add("source", edge.getFromNode())
					.add("target", edge.getToNode());
			
			jsonEdgesBuilder.add(edgeBuilder.build());
		}
		
		JsonObject jsonLadderObject = Json.createObjectBuilder()
				.add("id", ladder.getId())
				.add("name", ladder.getName())
				.add("ladderType", ladder.getLadderType().toString())
				.add("description", ladder.getDescription())
				.add("rootNodeId", ladder.getRootNodeId())
				.add("ownerId", ladder.getOwnerId())
				.add("createDt", ladder.getCreateDate().getTime()+"")
				.add("updateDt", ladder.getUpdateDate().getTime()+"")
				.add("nodes", jsonNodesBuilder.build())
				.add("edges", jsonEdgesBuilder.build())
                                .add("classforSelection", ladder.getClassforSelection())
                                .add("classforNode", ladder.getClassforNode())
				.build();
		
		//System.out.println(jsonLadderObject.toString());
		return jsonLadderObject;
	}
        
        private static ConceptMapBean getConceptMapsFromDatabase(ResultSet result) throws SQLException
	{
		ConceptMapBean aLadder = null;
		int ladderId = result.getInt(LADDERS_TBL + "." + "ladder_id");
		int rootNodeId = result.getInt(LADDERS_TBL + "." + "root_node_idfk");
		int ownerId = result.getInt(LADDERS_TBL + "." + "owner_idfk");
		String ladderName = result.getString(LADDERS_TBL + "." + "ladder_name");
		String description = result.getString(LADDERS_TBL + "." + "description");
		ConceptMapBean.LadderType ladderType = ConceptMapBean.LadderType.valueOf(result.getString(LADDERS_TBL + "." + "ladder_type"));
		Timestamp createDt = result.getTimestamp(LADDERS_TBL + "." + "create_dt");
		Timestamp updateDt = result.getTimestamp(LADDERS_TBL + "." + "update_dt");
		
		aLadder = new ConceptMapBean(ladderId, ownerId, rootNodeId, ladderName, description, ladderType, createDt, updateDt, "", "");
		return aLadder;
	}
}
