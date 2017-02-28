/* 
 * This javascript file contains functions for creating nodes and graphs in a svg
 * It also has code for toolbox displayed below the graph.
	* Taken from: https://bl.ocks.org/cjrd/6863459 and modified.
 */
/* 
    Created on : May 13, 2016, 4:13:59 PM
    Author     : Maryam Khalid
*/

var ladderChange = null;		//for keeping track of any changes made to the original ladder - is null if initial ladder is null
var selectedNode = null;		//for keeping track of last selected circle - for modal
var selectedEdge = null;			//for keeping track of last selected edge - for modal
var isNodeSelected = false;
var isEdgeSelected = false;
					
document.onload = (function(d3) 
{
	"use strict";
	
	//get json string from url
	var result = null,
	tmp = [];
	var items = location.search.substr(1).split("&");
	for (var index = 0; index < items.length; index++) 
	{
		tmp = items[index].split("=");
		if (tmp[0] === "ladder") 
			result = decodeURIComponent(tmp[1]);
	}
	
	var aLadder = {};
	var ladderAllNodes = [];
	var ladderAllEdges = [];
	
	//parse json string for ladder
	aLadder = JSON.parse(result);
	if(aLadder !== null)
	{
		//set ladder name, nodes and edges
		document.getElementById("view-ladder-name").innerHTML = "Name: " + aLadder.name;
		document.getElementById("graph-settings-description").innerHTML = aLadder.description;
		document.getElementById("graph-settings-description").readOnly = true;
		ladderAllNodes = JSON.parse(JSON.stringify(aLadder.nodes));
		ladderAllEdges = JSON.parse(JSON.stringify(aLadder.edges));
	}
	
	//set source and target of all edges (replace node ids with nodes)
	for(var i = 0; i < ladderAllEdges.length; i++)
	{
		var src = ladderAllEdges[i].source.valueOf();
		var target = ladderAllEdges[i].target.valueOf();
		ladderAllEdges[i].source = ladderAllNodes.filter(function(n){return n.id === src;})[0];
		ladderAllEdges[i].target = ladderAllNodes.filter(function(n){return n.id === target;})[0];
	}
	//console.log(ladderAllNodes);
	//console.log(ladderAllEdges);
	
	// define graphcreator object
	var GraphCreator = function(svg, nodes, edges, ladder) 
	{
		var thisGraph = this;
		thisGraph.idct = 0;		//keep trak of node ids
		thisGraph.idLinks = 0;	//keep track of link/edge ids

		thisGraph.nodes = nodes || [];
		thisGraph.edges = edges || [];
		thisGraph.selectedGraphCircle = null;		//for keeping track of last selected circle
		thisGraph.selectedGraphEdge = null;			//for keeping track of last selected edge
		thisGraph.isNodeSelected = false;
		thisGraph.isEdgeSelected = false;
		thisGraph.modalJustClosed = false;
		thisGraph.minZoom = 0.5;
		thisGraph.maxZoom = 3;
		thisGraph.firstNodeData = null;	//root node
		//set root node rootNodeId
		if(nodes.length !== 0)
			thisGraph.firstNodeData = JSON.parse(JSON.stringify(nodes.filter(function(n){return n.id === ladder.rootNodeId;})[0]));
		thisGraph.numTotalNodes = nodes.length;
		thisGraph.ladder = JSON.parse(JSON.stringify(ladder));
		
		thisGraph.state = 
		{
			selectedNode: null,
			selectedEdge: null,
			mouseDownNode: null,
			mouseDownLink: null,
			justDragged: false,
			justScaleTransGraph: false,
			lastKeyDown: -1,
			shiftNodeDrag: false,
			selectedText: null
		};

		// define arrow markers for graph links
		var defs = svg.append('svg:defs');
		defs.append('svg:marker')
			.attr('id', 'end-arrow')
			.attr('viewBox', '0 -5 10 10')
			.attr('refX', "62")
			.attr('markerWidth', 4.5)
			.attr('markerHeight', 4.5)
			.attr('orient', 'auto')
			.append('svg:path')
			.attr('d', 'M0,-5L10,0L0,5');
	
		var filter = defs.append("filter")
						.attr("id", "drop-shadow");
		filter.append("feGaussianBlur")
			.attr("in", "SourceAlpha")
			.attr("stdDeviation", 3)
			.attr("result", "blur");

		filter.append("feOffset")
			.attr("in", "blur")
			.attr("dx", 3)
			.attr("dy", 4)
			.attr("result", "offsetBlur");

		var feMerge = filter.append("feMerge");

		feMerge.append("feMergeNode")
			.attr("in", "offsetBlur");
		feMerge.append("feMergeNode")
			.attr("in", "SourceGraphic");


	   //gradient on normal node and node hover
		var radialGradientNormalNode = defs.append("radialGradient")
											.attr("id", "radial-gradient-normal")
											.attr("cx", "0.5")
											.attr("cy", "0.5")
											.attr("r", "0.5")
											.attr("fx", "0.25")
											.attr("fy", "0.25");
		radialGradientNormalNode.append("stop")
				.attr("offset", "0")
				.attr("stop-color", "#F6FBFF");
		radialGradientNormalNode.append("stop")
				.attr("offset", "100%")
				.attr("stop-color", "#885b8b");
	  
		//gradient on node select
		var radialGradientSelectNode = defs.append("radialGradient")
											.attr("id", "radial-gradient-select")
											.attr("cx", "0.5")
											.attr("cy", "0.5")
											.attr("r", "0.5")
											.attr("fx", "0.25")
											.attr("fy", "0.25");

		radialGradientSelectNode.append("stop")
			.attr("offset", "0")
			.attr("stop-color", "rgba(248,255,232,1)");

		radialGradientSelectNode.append("stop")
			.attr("offset", "100%")
			.attr("stop-color", "#c8a2c8");
	
		//Gradient for modal
		var linearGradientModal = defs.append("linearGradient")
			.attr("id", "linear-gradient-normal");

		linearGradientModal.append("stop")
			.attr("offset", "0")
			.attr("stop-color", "#F6FBFF");

		linearGradientModal.append("stop")
			.attr("offset", "100%")
			.attr("stop-color", "#85b2d3");
	
	
		// define arrow markers for leading arrow (removes extra part of arrow that goes to the center of the node without this script)
		defs.append('svg:marker')
			.attr('id', 'mark-end-arrow')
			.attr('viewBox', '0 -5 10 10')
			.attr('refX', 7)
			.attr('markerWidth', 4.5)
			.attr('markerHeight', 4.5)
			.attr('orient', 'auto')
			.append('svg:path')
			.attr('d', 'M0,-5L10,0L0,5');

		thisGraph.svg = svg;
		thisGraph.svgG = svg.append("g")
			.classed(thisGraph.consts.graphClass, true);
		
		var svgG = thisGraph.svgG;

		// displayed when dragging between nodes
		thisGraph.dragLine = svgG.append('svg:path')
			.attr('class', 'link dragline hidden')
			.attr('d', 'M0,0L0,0')
			.style('marker-end', 'url(#mark-end-arrow)');


		// svg nodes and edges 
		thisGraph.paths = svgG.append("g").selectAll("g");
		thisGraph.circles = svgG.append("g").selectAll("g");

		// listen for dragging on graph (both nodes and paths/edges)
		thisGraph.drag = d3.behavior.drag()
			.origin(function(d) 
			{
				return {x: d.x, y: d.y};
			})
			.on("drag", function(args)
			{
				thisGraph.state.justDragged = true;
				thisGraph.dragmove.call(thisGraph, args);
			});
		
		svg.on("mousedown", function(d) 
			{
				thisGraph.svgMouseDown.call(thisGraph, d);
			});
		
		svg.on("mouseup", function(d) 
			{
				thisGraph.svgMouseUp.call(thisGraph, d);
			});

		svg.on("focusout", function()
			{
				if(document.getElementById("node-modal").style.display === "block")
				{
					if(thisGraph.isNodeSelected)
						thisGraph.selectedGraphCircle.classed("selected circle", true);
					else if(thisGraph.isEdgeSelected)
						thisGraph.selectedGraphEdge.classed(thisGraph.consts.selectedClass, true);
				}
				else if(thisGraph.modalJustClosed && thisGraph.selectedGraphCircle !== null)
				{
					thisGraph.modalJustClosed = false;
					thisGraph.selectedGraphCircle.classed("selected circle", false);
				}
				else if(thisGraph.modalJustClosed && thisGraph.selectedGraphEdge !== null)
				{
					thisGraph.modalJustClosed = false;
					thisGraph.selectedGraphEdge.classed(thisGraph.consts.selectedClass, false);
				}
			});
		
		svg.on("focusin", function(d)
			{
				if(document.getElementById("node-modal").style.display === "block")
				{
					if(thisGraph.isNodeSelected)
						thisGraph.selectedGraphCircle.classed("selected circle", true);
					else if(thisGraph.isEdgeSelected)
						thisGraph.selectedGraphEdge.classed(thisGraph.consts.selectedClass, true);
				}
				else if(thisGraph.modalJustClosed && thisGraph.selectedGraphCircle !== null)
				{
					thisGraph.modalJustClosed = false;
					thisGraph.selectedGraphCircle.classed("selected circle", false);
				}
				else if(thisGraph.modalJustClosed && thisGraph.selectedGraphEdge !== null)
				{
					thisGraph.modalJustClosed = false;
					thisGraph.selectedGraphEdge.classed(thisGraph.consts.selectedClass, false);
				}
			});
			
		// listen for zooming on svg
		var zoomSvg = d3.behavior.zoom()
			.scaleExtent([thisGraph.minZoom, thisGraph.maxZoom])
			.on("zoom", function() 
			{
				if(d3.event.sourceEvent.shiftKey) 
				{
					// the internal d3 state is still changing
					return false;
				} 
				else 
				{
					thisGraph.zoomed.call(thisGraph);
				}
				return true;
			});

		svg.call(zoomSvg)
			.on("dblclick.zoom", null);
		
		thisGraph.zoomSvgParameter = zoomSvg;
	};
	
	GraphCreator.prototype.setIdCt = function(idct) 
	{
		this.idct = idct;
	};


	GraphCreator.prototype.consts = 
	{
		selectedClass: "selected",
		connectClass: "connect-node",
		circleGClass: "conceptG",
		graphClass: "graph",
		activeEditId: "active-editing",
		BACKSPACE_KEY: 8,
		DELETE_KEY: 46,
		ENTER_KEY: 13,
		nodeRadius: 50
	};

	/* PROTOTYPE FUNCTIONS */

	GraphCreator.prototype.dragmove = function(d)
	{
		var thisGraph = this;
		if (thisGraph.state.shiftNodeDrag)
		{
			//adding edge between two nodes
			thisGraph.dragLine.attr('d', 'M' + d.x + ',' + d.y + 'L' + d3.mouse(thisGraph.svgG.node())[0] + ',' + d3.mouse(this.svgG.node())[1]);
		}
		else
		{
			//dragging node
			d.x += d3.event.dx;
			d.y += d3.event.dy;

			if(d.id === thisGraph.firstNodeData.id)
			{
				thisGraph.firstNodeData = JSON.parse(JSON.stringify(d));
			}
			thisGraph.updateGraph();
		}
	};
	
	GraphCreator.prototype.deleteGraph = function(skipPrompt)
	{
		var thisGraph = this, 
				doDelete = true;
		
		if(!skipPrompt) 
		{
			doDelete = window.confirm("Press OK to delete this graph");
		}
		if(doDelete) 
		{
			thisGraph.nodes = [];
			thisGraph.edges = [];
			thisGraph.firstNodeData = null;
			thisGraph.selectedGraphCircle = null;
			thisGraph.selectedGraphEdge = null;
			selectedNode = null;
			selectedEdge = null;
			thisGraph.isNodeSelected = false;
			thisGraph.isEdgeSelected = false;
			thisGraph.ladder = null;
			thisGraph.numTotalNodes = 0;
			thisGraph.idLinks = 0;
			thisGraph.idct = 0;
			isNodeSelected = false;
			isEdgeSelected = false;
			thisGraph.state.selectedEdge = null;
			thisGraph.state.selectedNode = null;
			thisGraph.updateGraph();
			//clear ladder name + clear ladder settings
			document.getElementById("name-input").value = "";
			document.getElementById("graph-settings-description").value = "";
		}
	};

	/* insert svg line breaks: taken from http://stackoverflow.com/questions/13241475/how-do-i-include-newlines-in-labels-in-d3-charts */
	GraphCreator.prototype.insertTitleLinebreaks = function(gEl, title)
	{
		var words = title.split(/\s+/g), 
			nwords = words.length;
		var el = gEl.append("text")
					.attr("text-anchor", "middle")
					.attr("dy", "-" + 5);
					//.attr("dy", "-" + (nwords - 1) * 7.5);

		for (var i = 0; i < words.length; i++)
		{
			var tspan = el.append('tspan').text(words[i]);
			if (i > 0)
				tspan.attr('x', 0).attr('dy', '15');
		}
	};

	// remove edges associated with a node
	GraphCreator.prototype.spliceLinksForNode = function(node)
	{
		var thisGraph = this,
			toSplice = thisGraph.edges.filter(function(l)
			{
				return (l.source === node || l.target === node);
			});
			
		toSplice.map(function(l) 
		{
			thisGraph.edges.splice(thisGraph.edges.indexOf(l), 1);
		});
	};

	GraphCreator.prototype.replaceSelectEdge = function(d3Path, edgeData)
	{
		var thisGraph = this;
		d3Path.classed(thisGraph.consts.selectedClass, true);
		if(thisGraph.state.selectedEdge)
		{
			thisGraph.removeSelectFromEdge();
		}
		thisGraph.state.selectedEdge = edgeData;
	};

	GraphCreator.prototype.replaceSelectNode = function(d3Node, nodeData)
	{
		var thisGraph = this;
		d3Node.classed(this.consts.selectedClass, true);
		if(thisGraph.state.selectedNode)
		{
			thisGraph.removeSelectFromNode();
		}
		thisGraph.state.selectedNode = JSON.parse(JSON.stringify(nodeData));
	};

	GraphCreator.prototype.removeSelectFromNode = function()
	{
		var thisGraph = this;
		thisGraph.circles.filter(function(cd)
			{
				return cd.id === thisGraph.state.selectedNode.id;
			}).classed(thisGraph.consts.selectedClass, false);
		thisGraph.state.selectedNode = null;
	};

	GraphCreator.prototype.removeSelectFromEdge = function()
	{
		var thisGraph = this;
		thisGraph.paths.filter(function(cd)
			{
				return cd === thisGraph.state.selectedEdge;
			}).classed(thisGraph.consts.selectedClass, false);
		thisGraph.state.selectedEdge = null;
	};

	GraphCreator.prototype.pathMouseDown = function(d3path, d)
	{
		var thisGraph = this,
			state = thisGraph.state;
		d3.event.stopPropagation();
		state.mouseDownLink = d;

		if(state.selectedNode)
		{
			thisGraph.removeSelectFromNode();
		}

		var prevEdge = state.selectedEdge;
		if(!prevEdge || prevEdge !== d)
		{
			thisGraph.replaceSelectEdge(d3path, d);
		}
		else
		{
			thisGraph.removeSelectFromEdge();
		}
	};

	// mousedown on node
	GraphCreator.prototype.circleMouseDown = function (d3node, d)
	{
		var thisGraph = this,
			state = thisGraph.state;
		d3.event.stopPropagation();
		state.mouseDownNode = d;
		if(d3.event.shiftKey)
		{
			state.shiftNodeDrag = d3.event.shiftKey;
			return;
		}
	};

	/* place editable text on node in place of svg text */
	GraphCreator.prototype.changeTextOfNode = function(d3node, d)
	{
		var thisGraph = this,
			consts = thisGraph.consts,
			htmlEl = d3node.node();
		
		d3node.selectAll("text").remove();
		var nodeBCR = htmlEl.getBoundingClientRect(),
			curScale = nodeBCR.width / consts.nodeRadius,
			placePad = 5 * curScale,
			useHW = curScale > 1 ? nodeBCR.width * 0.71 : consts.nodeRadius * 1.42;
		// replace with editableconent text
		var d3txt = thisGraph.svg.selectAll("foreignObject")
			.data([d])
			.enter()
			.append("foreignObject")
			.attr("x", (d.x * thisGraph.zoomSvgParameter.scale()) + trans[0])
			.attr("y", (d.y * thisGraph.zoomSvgParameter.scale()) + trans[1])
			.attr("height", 2 * useHW)
			.attr("width", useHW)
			.append("xhtml:p")
			.attr("id", consts.activeEditId)
			.attr("contentEditable", "true")
			.text(d.title)
			.on("mousedown", function(d)
			{
				d3.event.stopPropagation();
			})
			.on("keydown", function(d)
			{
				d3.event.stopPropagation();
				if(d3.event.keyCode === consts.ENTER_KEY && !d3.event.shiftKey)
				{
					this.blur();
				}
			})
			.on("blur", function(d)
			{
				d.title = this.textContent;
				thisGraph.insertTitleLinebreaks(d3node, d.title);
				d3.select(this.parentElement).remove();
			});
			
			d3txt.style("font", "14px Helvetica, arial");
		return d3txt;
	};

	// mouseup on nodes
	GraphCreator.prototype.circleMouseUp = function(d3node, d)
	{
		var thisGraph = this,
			state = thisGraph.state,
			consts = thisGraph.consts;

		// reset the states
		state.shiftNodeDrag = false;
		d3node.classed(consts.connectClass, false);

		var mouseDownNode = state.mouseDownNode;

		if(!mouseDownNode)
			return;

		thisGraph.dragLine.classed("hidden", true);

		if(mouseDownNode === d)
		{
			// we're in the same node
			if (state.justDragged)
			{
				// dragged, not clicked
				state.justDragged = false;
			}
			else
			{
				// clicked, not dragged
				if(d3.event.shiftKey)
				{
				}
				else
				{
					//for any selected node
					if(state.selectedEdge)
					{
						//remove selection from edge(if any)
						thisGraph.removeSelectFromEdge();
					}
					
					var prevNode = JSON.parse(JSON.stringify(state.selectedNode));
					if(!prevNode || prevNode.id !== d.id)
					{
						//remove selection from other nodes
						thisGraph.replaceSelectNode(d3node, d);
					}
				}
			}
		}
		state.mouseDownNode = null;
		return;
	}; // end of circles mouseup

	// mousedown on main svg
	GraphCreator.prototype.svgMouseDown = function()
	{
		this.state.graphMouseDown = true;
	};

	// mouseup on main svg
	GraphCreator.prototype.svgMouseUp = function()
	{
		var thisGraph = this,
			state = thisGraph.state;
		if(state.justScaleTransGraph)
		{
			// dragged not clicked
			state.justScaleTransGraph = false;
		}
		else if(state.graphMouseDown && d3.event.shiftKey)
		{
		}
		else if(state.shiftNodeDrag)
		{
			// dragged from node
			state.shiftNodeDrag = false;
			thisGraph.dragLine.classed("hidden", true);
		}
		state.graphMouseDown = false;
	};
	
	GraphCreator.prototype.onClickModalOpen = function()
	{
		d3.select("body")
			.style("overflow", "hidden");
			
		document.getElementById("node-modal").style.display = "block";
		var thisGraph = this;
		thisGraph.modalJustClosed = true;
		if(thisGraph.isNodeSelected)
		{
			document.getElementById("modal-node-name").innerHTML = thisGraph.state.selectedNode.title;
			//copy node data to global variable
			isNodeSelected = thisGraph.isNodeSelected === false ? false : true;
			selectedNode = JSON.parse(JSON.stringify(thisGraph.state.selectedNode));
			
			document.getElementById("modal-description-id").value = selectedNode.description;
			//load linked tools, users and resources on modal
			onLoadModal("accordion1-panel", selectedNode.tools);
			onLoadModal("accordion2-panel", selectedNode.resources);
			onLoadModal("accordion3-panel", selectedNode.users);
		}
		else if(thisGraph.isEdgeSelected)
		{
			document.getElementById("modal-node-name").innerHTML = thisGraph.state.selectedEdge.title;
			isEdgeSelected = thisGraph.isEdgeSelected === false ? false : true;
			selectedEdge = JSON.parse(JSON.stringify(thisGraph.state.selectedEdge));
			
			document.getElementById("modal-description-id").value = selectedEdge.description;
			//load linked tools, users and resources on modal
			onLoadModal("accordion1-panel", selectedEdge.tools);
			onLoadModal("accordion2-panel", selectedEdge.resources);
			onLoadModal("accordion3-panel", selectedEdge.users);
		}
	};
	
	// keydown on main svg
	GraphCreator.prototype.svgKeyDown = function()
	{
		//if the current element in focus is not any other field
		if(document.getElementById("name-input") !== document.activeElement)
		{
			var thisGraph = this,
				state = thisGraph.state,
				consts = thisGraph.consts;
			// make sure repeated key presses don't register for each keydown
			if(state.lastKeyDown !== -1)
				return;

			state.lastKeyDown = d3.event.keyCode;
		}
	};

	GraphCreator.prototype.svgKeyUp = function()
	{
		this.state.lastKeyDown = -1;
	};

	// call to propagate changes to graph
	GraphCreator.prototype.updateGraph = function()
	{
		var thisGraph = this,
			consts = thisGraph.consts,
			state = thisGraph.state;

		thisGraph.paths = thisGraph.paths.data(thisGraph.edges, function(d)
			{
				return String(d.source.id) + "+" + String(d.target.id);
			});
		var paths = thisGraph.paths;
		// update existing paths
		paths.style('marker-end', 'url(#end-arrow)')
			.classed(consts.selectedClass, function(d)
			{
				return d === state.selectedEdge;
			})
			.attr("d", function(d)
			{
				return "M" + d.source.x + "," + d.source.y + "L" + d.target.x + "," + d.target.y;
			});

		// add new paths
		paths.enter()
			.append("path")
			.style('marker-end', 'url(#end-arrow)')
			.classed("link", true)
			.attr("d", function(d)
			{
				return "M" + d.source.x + "," + d.source.y + "L" + d.target.x + "," + d.target.y;
			})
			.on("dblclick", function(d)
			{
				if(!d3.event.shiftKey && !thisGraph.state.shiftNodeDrag)
				{
					d3.event.stopPropagation();
					
					if(thisGraph.state.selectedEdge)
					{
						thisGraph.removeSelectFromEdge();
					}
					if(thisGraph.state.selectedNode)
					{
						thisGraph.removeSelectFromNode();
					}
					
					d3.select(this).classed(thisGraph.consts.selectedClass, true);
					thisGraph.selectedGraphEdge = d3.select(this);
					thisGraph.state.selectedEdge = d;
					
					var dcx1 = (window.innerWidth/4-d.source.x*thisGraph.zoomSvgParameter.scale());
					var dcy1 = (window.innerHeight/2-d.source.y*thisGraph.zoomSvgParameter.scale());
					var dcx2 = (window.innerWidth/4-d.target.x*thisGraph.zoomSvgParameter.scale());
					var dcy2 = (window.innerHeight/2-d.target.y*thisGraph.zoomSvgParameter.scale());
					var dcx = (dcx1 + dcx2)/2;
					var dcy = (dcy1 + dcy2)/2;
					thisGraph.zoomSvgParameter.translate([dcx,dcy]);
					d3.select("." + thisGraph.consts.graphClass)
							.transition()
							.duration(750)
							.attr("transform", "translate("+ dcx + "," + dcy  + ")scale(" + thisGraph.zoomSvgParameter.scale() + ")")
							.each("end", function()
									{
										thisGraph.isEdgeSelected = true;
										thisGraph.isNodeSelected = false;
										thisGraph.onClickModalOpen(thisGraph);
									});
				}
			})
			.on("mousedown", function(d)
			{
				thisGraph.pathMouseDown.call(thisGraph, d3.select(this), d);
			})
			.on("mouseup", function(d)
			{
				state.mouseDownLink = null;
			});

		// remove old links
		paths.exit().remove();

		// update existing nodes
		thisGraph.circles = thisGraph.circles.data(thisGraph.nodes, function(d)
			{
				return d.id;
			});
		thisGraph.circles.attr("transform", function(d)
			{
				return "translate(" + d.x + "," + d.y + ")";
			});

		// add new nodes
		var newGs = thisGraph.circles.enter()
						.append("g");

		newGs.classed(consts.circleGClass, true)
			.attr("transform", function(d)
			{
				return "translate(" + d.x + "," + d.y + ")";
			})
			.on("mouseover", function(d)
			{
				if(state.shiftNodeDrag)
				{
					d3.select(this).classed(consts.connectClass, true);
				}
			})
			.on("mouseout", function(d)
			{
				d3.select(this).classed(consts.connectClass, false);
			})
			.on("mousedown", function(d)
			{
				thisGraph.circleMouseDown.call(thisGraph, d3.select(this), d);
			})
			.on("mouseup", function(d)
			{
				thisGraph.circleMouseUp.call(thisGraph, d3.select(this), d);
			})
			.on("dblclick", function(d)
			{
				if(!thisGraph.state.justDragged && !d3.event.shiftKey && !thisGraph.state.shiftNodeDrag)
				{
					d3.event.stopPropagation();
					
					if(thisGraph.state.selectedEdge)
					{
						thisGraph.removeSelectFromEdge();
					}
					if(thisGraph.state.selectedNode)
					{
						thisGraph.removeSelectFromNode();
					}
					
					d3.select(this).classed("selected circle", true);
					thisGraph.selectedGraphCircle = d3.select(this);
					thisGraph.state.selectedNode = JSON.parse(JSON.stringify(d));
					
					var dcx = (window.innerWidth/4-d.x*thisGraph.zoomSvgParameter.scale());
					var dcy = (window.innerHeight/2-d.y*thisGraph.zoomSvgParameter.scale());
					thisGraph.zoomSvgParameter.translate([dcx,dcy]);
					d3.select("." + thisGraph.consts.graphClass)
							.transition()
							.duration(750)
							.attr("transform", "translate("+ dcx + "," + dcy  + ")scale(" + thisGraph.zoomSvgParameter.scale() + ")")
							.each("end", function()
									{
										thisGraph.isEdgeSelected = false;
										thisGraph.isNodeSelected = true;
										thisGraph.onClickModalOpen(thisGraph);
									});
				}
			})
			.on("focus", function(d)
			{
				d3.select(this).style("outline", "none");
			})
			.call(thisGraph.drag);
	
		newGs.append("circle")
			.attr("r", String(consts.nodeRadius));
	
		newGs.each(function (d)
			{
				thisGraph.insertTitleLinebreaks(d3.select(this), d.title);
			});

		// remove old nodes
		thisGraph.circles.exit().remove();
	};

	GraphCreator.prototype.zoomed = function()
	{
		this.state.justScaleTransGraph = true;
		d3.select("." + this.consts.graphClass)
				.attr("transform", "translate(" + d3.event.translate + ") scale(" + d3.event.scale + ")");
	};

	/**** MAIN ****/
	// warn the user when leaving
	window.onbeforeunload = function()
	{
		return "Make sure to save your graph locally before leaving :-)";
	};

	/** MAIN SVG **/
	var svg = d3.select("#svg-row")
				.append("svg")
				.attr("id", "main-svg");
	
	var mainSVG = document.getElementById("main-svg");
	mainSVG.style.height= (document.getElementById("navbar").clientHeight - (2 * document.getElementById("ladder-header-row").clientHeight)) +"px";
	
	var graph = new GraphCreator(svg, ladderAllNodes, ladderAllEdges, aLadder);
	if(ladderAllNodes !== null)
	{
		graph.setIdCt(ladderAllNodes.length);
	}
	graph.updateGraph();
	
})(window.d3);