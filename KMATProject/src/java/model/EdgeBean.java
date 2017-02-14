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
public class EdgeBean implements java.io.Serializable
{
	public enum EdgeType
	{
		EMPTY,
		PROCESS,
		COMPOSITION,
		ROLES,
		CONCEPT_LINK,
		CONCEPT_PARENT_TO_CHILD
	};
	
	private int id;
	private int edgeId;
	private int parentId;
	private int ownerId;
	private String name;
	private String description;
	private EdgeType edgeType;
	private ArrayList<Integer> toolIds;
	private ArrayList<Integer> resourceIds;
	private ArrayList<Integer> sharedUserIds;
	private int fromNode;
	private int toNode;
	private Timestamp createDate;
	private Timestamp updateDate;
	
	public EdgeBean(int id, int edgeId, int parentId, int ownerId, String name, String description, EdgeBean.EdgeType edgeType, int fromNode, int toNode, Timestamp createdt, Timestamp updatedt)
	{
		this.id = id; 
		this.edgeId = edgeId;
		this.parentId = parentId;
		this.ownerId = ownerId;
		this.name = name;
		this.description = description;
		this.edgeType = edgeType;
		this.fromNode = fromNode;
		this.toNode = toNode;
		this.createDate = createdt;
		this.updateDate = updatedt;
		toolIds = new ArrayList<>();
		resourceIds = new ArrayList<>();
		sharedUserIds = new ArrayList<>();
	}

	public EdgeBean()
	{
		
		toolIds = new ArrayList<>();
		resourceIds = new ArrayList<>();
		sharedUserIds = new ArrayList<>();
	}
	
	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int getEdgeId()
	{
		return edgeId;
	}

	public void setEdgeId(int edgeId)
	{
		this.edgeId = edgeId;
	}
	
	public int getParentId()
	{
		return parentId;
	}

	public void setParentId(int parentId)
	{
		this.parentId = parentId;
	}

	public int getOwnerId()
	{
		return ownerId;
	}

	public void setOwnerId(int ownerId)
	{
		this.ownerId = ownerId;
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

	public void setDescription(String description)
	{
		this.description = description;
	}

	public EdgeType getEdgeType()
	{
		return edgeType;
	}

	public void setEdgeType(EdgeType edgeType)
	{
		this.edgeType = edgeType;
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

	public int getFromNode()
	{
		return fromNode;
	}

	public void setFromNode(int fromNode)
	{
		this.fromNode = fromNode;
	}

	public int getToNode()
	{
		return toNode;
	}

	public void setToNode(int toNode)
	{
		this.toNode = toNode;
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
		return "EdgeBean{" + "id=" + id + "edgeId=" + edgeId + ", parentId=" + parentId + ", ownerId=" + ownerId + ", name=" + name + ", description=" + description + ", edgeType=" + edgeType + ", toolIds=" + toolIds + ", resourceIds=" + resourceIds + ", sharedUserIds=" + sharedUserIds + ", fromNode=" + fromNode + ", toNode=" + toNode + ", createDate=" + createDate + ", updateDate=" + updateDate + '}';
	}

}
