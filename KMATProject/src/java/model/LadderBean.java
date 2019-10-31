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

public class LadderBean implements java.io.Serializable
{
	private int id;
	private int ownerId;
	private int rootNodeId;
	private String name;
	private String description;
	private LadderType ladderType;
	private ArrayList<NodeBean> nodes;
	private ArrayList<EdgeBean> edges;
	private ArrayList<Integer> toolIds;
	private ArrayList<Integer> resourceIds;
	private ArrayList<Integer> sharedUserIds;
	private Timestamp createDate;
	private Timestamp updateDate;

	public enum LadderType
	{
		PROCESS,
		COMPOSITION,
                CONCEPT,
		ROLES
	};
	
	public LadderBean(String name)
	{
		this.name = name;
		this.ownerId = 0;
		this.rootNodeId = 0;
		this.nodes = new ArrayList<>();
		this.edges = new ArrayList<>();
		this.toolIds = new ArrayList<>();
		this.resourceIds = new ArrayList<>();
		this.sharedUserIds = new ArrayList<>();
	}

	public LadderBean(int id, int ownerId, int rootNodeId, String name, String description, LadderType ladderType, Timestamp createDate, Timestamp updateDate)
	{
		this.id = id;
		this.ownerId = ownerId;
		this.rootNodeId = rootNodeId;
		this.name = name;
		this.description = description;
		this.ladderType = ladderType;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.nodes = new ArrayList<>();
		this.edges = new ArrayList<>();
		this.toolIds = new ArrayList<>();
		this.resourceIds = new ArrayList<>();
		this.sharedUserIds = new ArrayList<>();
	}
	
	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int getOwnerId()
	{
		return ownerId;
	}

	public void setOwnerId(int ownerId)
	{
		this.ownerId = ownerId;
	}

	public int getRootNodeId()
	{
		return rootNodeId;
	}

	public void setRootNodeId(int rootNodeId)
	{
		this.rootNodeId = rootNodeId;
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

	public LadderType getLadderType()
	{
		return ladderType;
	}

	public void setLadderType(LadderType ladderType)
	{
		this.ladderType = ladderType;
	}
        
	public ArrayList<NodeBean> getNodes()
	{
		return nodes;
	}

	public void setNodes(ArrayList<NodeBean> nodes)
	{
		this.nodes = nodes;
	}

	public ArrayList<EdgeBean> getEdges()
	{
		return edges;
	}

	public void setEdges(ArrayList<EdgeBean> edges)
	{
		this.edges = edges;
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
		return "LadderBean{" + "id=" + id + ", ownerId=" + ownerId + ", rootNodeId=" + rootNodeId + ", name=" + name + ", description=" + description + ", ladderType=" + ladderType + ", nodes=" + nodes + ", edges=" + edges + ", toolIds=" + toolIds + ", resourceIds=" + resourceIds + ", sharedUserIds=" + sharedUserIds + ", createDate=" + createDate + ", updateDate=" + updateDate + '}';
	}

}
