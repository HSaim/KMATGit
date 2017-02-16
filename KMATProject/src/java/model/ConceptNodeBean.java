/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 *
 * @author Maryam Khalid
 */
public class ConceptNodeBean implements java.io.Serializable
{
	public enum NodeType
	{
		PROCESS,
		COMPOSITION,
		ROLES,
		CONCEPT
	};
	
	private int id;
	private int nodeId;
	private int ladderId;
	private int ownerId;
	private NodeType nodeType;
	private String name;
	private String description;
	private ArrayList<Integer> toolIds;
	private ArrayList<Integer> resourceIds;
	private ArrayList<Integer> sharedUserIds;
	private Position nodePosition;
	private Timestamp createDate;
	private Timestamp updateDate;
        private String classforSelection;
        private String classforNode;
	
	public ConceptNodeBean(String name, double posX, double posY)
	{
		this.name = name;
		toolIds = new ArrayList<>();
		resourceIds = new ArrayList<>();
		sharedUserIds = new ArrayList<>();
		this.nodeId = 0;
		this.ladderId = 0;
		this.ownerId = 0;
		this.nodePosition = new Position(posX, posY);
	}

	public ConceptNodeBean(int id, int nodeId, int ladderId, int ownerId, ConceptNodeBean.NodeType nodeType, String name, String description, double posX, double posY, Timestamp createdt, Timestamp updatedt, String classforSelection, String classforNode)
	{
		this.id = id;
		this.nodeId = nodeId;
		this.ladderId = ladderId;
		this.ownerId = ownerId;
		this.nodeType = nodeType;
		this.name = name;
		this.description = description;
		this.nodePosition = new Position(posX, posY);
		this.createDate = createdt;
		this.updateDate = updatedt;
		toolIds = new ArrayList<>();
		resourceIds = new ArrayList<>();
		sharedUserIds = new ArrayList<>();
                this.classforSelection = classforSelection;
                this.classforNode = classforNode;
	}
	
	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int getNodeId()
	{
		return nodeId;
	}

	public void setNodeId(int nodeId)
	{
		this.nodeId = nodeId;
	}

	public int getLadderId()
	{
		return ladderId;
	}

	public void setLadderId(int ladderId)
	{
		this.ladderId = ladderId;
	}

	public int getOwnerId()
	{
		return ownerId;
	}

	public void setOwnerId(int ownerId)
	{
		this.ownerId = ownerId;
	}

	public NodeType getNodeType()
	{
		return nodeType;
	}

	public void setNodeType(NodeType nodeType)
	{
		this.nodeType = nodeType;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getDescription()
	{
		return description;
	}

        public String getClassforSelection()
	{
		return classforSelection;
	}

	public void setClassforSelection(String classforSelection)
	{
		this.classforSelection = classforSelection;
	}
        
        public String getClassforNode()
	{
		return classforNode;
	}

	public void setClassforNode(String classforNode)
	{
		this.classforNode = classforNode;
	}
        
	public void setDescription(String description)
	{
		this.description = description;
	}

	public ArrayList<Integer> getToolIds()
	{
		return toolIds;
	}

	public void setToolIds(ArrayList<Integer> toolIds)
	{
		this.toolIds = toolIds;
	}

	public ArrayList<Integer> getResourceIds()
	{
		return resourceIds;
	}

	public void setResourceIds(ArrayList<Integer> resourceIds)
	{
		this.resourceIds = resourceIds;
	}

	public ArrayList<Integer> getSharedUserIds()
	{
		return sharedUserIds;
	}

	public void setSharedUserIds(ArrayList<Integer> sharedUserIds)
	{
		this.sharedUserIds = sharedUserIds;
	}
	
	public Position getNodePosition()
	{
		return nodePosition;
	}

	public void setNodePosition(Position nodePosition)
	{
		this.nodePosition = nodePosition;
	}

	public Timestamp getCreateDate()
	{
		return createDate;
	}

	public void setCreateDate(Timestamp createDate)
	{
		this.createDate = createDate;
	}

	public Timestamp getUpdateDate()
	{
		return updateDate;
	}

	public void setUpdateDate(Timestamp updateDate)
	{
		this.updateDate = updateDate;
	}

	@Override
	public String toString()
	{
		return "ConceptNodeBean{" + "id=" + id + ", nodeId=" + nodeId + ", ladderId=" + ladderId + ", ownerId=" + ownerId + ", nodeType=" + nodeType + ", name=" + name + ", description=" + description + ", toolIds=" + toolIds + ", resourceIds=" + resourceIds + ", sharedUserIds=" + sharedUserIds + ", nodePosition=(" + nodePosition.getX() + "," + nodePosition.getY() + "), createDate=" + createDate + ", updateDate=" + updateDate + ", classforSelection=" + classforSelection + ", classforNode=" + classforNode + '}';
	}

	//Inner class for setting position of node
	public class Position
	{
		private double x;
		private double y;
		
		public Position(double x, double y)
		{
			this.x = x;
			this.y = y;
		}
		
		public void setX(double x)
		{
			this.x = x;
		}
		
		public double getX()
		{
			return this.x;
		}
		
		public void setY(double y)
		{
			this.y = y;
		}
		
		public double getY()
		{
			return this.y;
		}
	}
}
