/* 
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

//For testing - list of dummy items used in search
//Tools: {id, name, description}
//Resources: {id, name, description}
//Users: {id, name, description, image details} -- check what details are required for image

//TODOs: 
// 1. get data from database
// 2. add/update data to database
// 

//test cases: - get from database
var listOfTools =	[{id: 1, name: "Adobe Acrobat", description: ""}, 
					//{id: 2, name: "Test Tool2", description: ""},
					{id: 3, name: "Test Long Long Long Tool3 Test Long Long Long Tool3", description: ""},
					//{id: 4, name: "Test Tool3", description: ""},
					{id: 5, name: "Netbeans", description: ""}];
var listOfResources = [{id: 2, name: "Resource2", description: ""},
					{id: 3, name: "Test Long Long Long Resource3", description: ""}];
var listOfUsers = [{id: 1, name: "User 1", description: "", email:"maryam.khalid.86@gmail.com"}];

// get these links from database
var searchResultTools = [];
var searchResultResources = [];
var searchResultUsers = [];

function changeNameTextFieldColor()
{
	//change attributes of label
	document.getElementById("id-header-label").style.boxShadow = "inset 0 1px 1px rgba(0,0,0,.075), 0 0 8px rgba(102, 175, 233, 0.6)";
	document.getElementById("id-header-label").style.borderColor = "#66afe9";
	document.getElementById("label-ladder-name").style.backgroundColor = "rgba(31, 69, 252, 0.1)";
	document.getElementById("label-ladder-name").style.color = "#66afe9";
}

function nameTextFieldColorOriginal()
{
	//change attributes of label back to default
	document.getElementById("label-ladder-name").style.color = "#767676";
	document.getElementById("label-ladder-name").style.background = "none";
	document.getElementById("id-header-label").style.borderColor = "#dddddd";
	document.getElementById("id-header-label").style.boxShadow = "inset 0 1px 2px rgba(0,0,0,0.075)";
}

function changeSearchTextFieldColor()
{
	//change style of search field on click
	document.getElementById("search-header").style.boxShadow = "inset 2px 0 8px rgba(0,0,0,.075), 1px 0 8px rgba(102, 175, 233, 0.6)";
	//document.getElementById("search-header").style.border = "solid #66afe9";
	document.getElementById("search-header").style.border = "solid #2554C7";
	document.getElementById("search-header").style.borderWidth = "0.1px";
	document.getElementById("search-input").placeholder = "";
}

function searchTextFieldColorOriginal()
{
	//change attributes of label back to default
	if(!((document.activeElement === document.getElementById("search-input")) || (document.activeElement === document.getElementById("search-header"))))
	{
		document.getElementById("search-header").style.boxShadow = "inset 0 0px 2px rgba(0,0,0,0.075)";
		document.getElementById("search-header").style.border = "solid #ddd";
		document.getElementById("search-header").style.borderWidth = "1px";
		if(document.getElementById("search-input").placeholderText !== null)
			document.getElementById("search-input").placeholder = document.getElementById("search-input").placeholderText;
	}
}

function searchTextFieldMouseover()
{
	document.getElementById("search-header").style.boxShadow = "inset 2px 0 8px rgba(0,0,0,.075), 1px 0 8px rgba(0,0,0, 0.2)";
}

function searchEnterPressed(e)
{
	if(e.keyCode === 13)
	{
		document.getElementById("search-input").blur();
	}
}

//Dynamic Search "On keyup" for the input text field
function searchKeyUp(el, ev)
{
	if(ev.keyCode === 13)
	{
		return;
	}
	var val = el.value.trim().toLowerCase();
	
	var searchListItems = document.getElementsByClassName("search-inner-panel");
	if(searchListItems !== null)
	{
		for(var i = 0 ; i < searchListItems.length; i++)
		{
			var compareTo = searchListItems[i].item.name.trim().toLowerCase();
			if(compareTo === val || compareTo.indexOf(val) >= 0)
			{
				//show
				searchListItems[i].style.display = "block";
			}
			else
			{
				//hide
				searchListItems[i].style.display = "none";
			}
		}
	}
}

//onClick for accordions used in the modal
var acc = document.getElementsByClassName("accordion");
var acc3 = document.getElementById("accordion-share");
var i;
var openedLastAccordion = false;
for (i = 0; i < acc.length; i++) 
{
	acc[i].isClose = false;
	acc[i].onclick = function()
	{
		this.classList.toggle("active");
		this.nextElementSibling.classList.toggle("show");
		
		if(this.isClose)
			this.isClose = false;
		else
			this.isClose = true;
			
		if(this.id === acc3.id)
		{
			openedLastAccordion = !openedLastAccordion;
			if(openedLastAccordion)
			{
				acc3.style.borderBottomLeftRadius = "0px";
				acc3.style.borderBottomRightRadius = "0px";
			}
			else
			{
				acc3.style.borderBottomLeftRadius = "10px";
				acc3.style.borderBottomRightRadius = "10px";
			}
		}
	};
}

//function for onclick of viewing an item in the accordion
function viewAccordionItem()
{
	//TODO: 
	console.log("View details of accordion item");
}

//function for onclick of deleting an item in the accordion
function delAccordionItem(el)
{
	if(document.getElementById(el.parentNode.parentNode.id).numListElements === 1)
	{
		//check if this is the last accordion - reset edit button accordingly
		resetAccordionEditButton(el.parentNode.parentNode.id);
		document.getElementById(el.parentNode.parentNode.id).isEditAccordionItemsSelected = 0;
	}
	document.getElementById(el.parentNode.parentNode.id).numListElements--;

	//update searchResult list accordingly
	updateSearchResultList(el.id, el.parentNode.parentNode.id);
	
	//delete the accordion item
	el.parentNode.parentNode.removeChild(el.parentNode);
	//el.parentNode.style.display = "none";
}

//In outer modal accordions: set "Done" to "Edit" and disable it - fade
function resetAccordionEditButton(accordionId)
{
	var editButtonText;
	if(accordionId === "accordion1-panel")
	{
		editButtonText = document.getElementById("tools-edit-button");
		editButtonText.style.left = "77%";
	}
	else if(accordionId === "accordion2-panel")
	{
		editButtonText = document.getElementById("resources-edit-button");
		editButtonText.style.left = "69.5%";
	}
	else if(accordionId === "accordion3-panel")
	{
		editButtonText = document.getElementById("share-edit-button");
		editButtonText.style.left = "81.5%";
	}
	editButtonText.innerHTML = "Edit";
}

//utility function for resetting value of searchResultList
function updateSearchResultList(itemId, accordionId)
{
	if(accordionId === "accordion1-panel")
	{
		if(searchResultTools !== null && searchResultTools.length > itemId)
		{
			searchResultTools[itemId] = 0;
		}
	}
	else if(accordionId === "accordion2-panel")
	{
		if(searchResultResources !== null && searchResultResources.length > itemId)
		{
			searchResultResources[itemId] = 0;
		}
	}
	else if(accordionId === "accordion3-panel")
	{
		if(searchResultUsers !== null && searchResultUsers.length > itemId)
		{
			searchResultUsers[itemId] = 0;
		}
	}
}

//function for editing in the accordion of the outer modal
function editAccordionItems(panelId)
{
	var editButtonText,
		doneLeft,
		editLeft;
	if(panelId === "accordion1-panel")
	{
		editButtonText = document.getElementById("tools-edit-button");
		doneLeft = "75%";
		editLeft = "77%";
	}
	else if(panelId === "accordion2-panel")
	{
		editButtonText = document.getElementById("resources-edit-button");
		doneLeft = "67.5%";
		editLeft = "69.5%";
	}
	else if(panelId === "accordion3-panel")
	{
		editButtonText = document.getElementById("share-edit-button");
		doneLeft = "80%";
		editLeft = "81.5%";
	}
	
	var delClassName = "accordion-delete-icon-" + panelId;
	var viewClassName = "accordion-view-icon-" + panelId;
	var viewIcons = document.getElementsByClassName(delClassName);
	var deleteIcons = document.getElementsByClassName(viewClassName);
	
	//check if no items present
	if(viewIcons === null || viewIcons.length === 0)
	{
		return;
	}
	
	var accordionPanel = document.getElementById(panelId);
	if(isNaN(accordionPanel.isEditAccordionItemsSelected) || accordionPanel.isEditAccordionItemsSelected === null)
		accordionPanel.isEditAccordionItemsSelected = 0;
	
	//change Edit to Delete and then back
	//hide preview icons and show delete icons + change icon img to a red cross
	if(!accordionPanel.isEditAccordionItemsSelected)
	{
		accordionPanel.isEditAccordionItemsSelected = true;
		editButtonText.innerHTML = "Done";
		editButtonText.style.left = doneLeft;
		for(i = 0; i < viewIcons.length; i++)
		{
			viewIcons[i].style.display = "inline";
			deleteIcons[i].style.display = "none";
		}
	}
	else
	{
		accordionPanel.isEditAccordionItemsSelected = false;
		editButtonText.innerHTML = "Edit";
		editButtonText.style.left = editLeft;
		for(i = 0; i < viewIcons.length; i++)
		{
			viewIcons[i].style.display = "none";
			deleteIcons[i].style.display = "inline";
		}
	}
}

//function for opening a modal (inner modal) to search and add tools/resources to node or share node with people
function addToNode(panelId)
{
	var searchField = document.getElementById("search-input");
	if(panelId === "accordion1-panel")
	{
		searchField.placeholder = "Search Tools";
		searchField.placeholderText = "Search Tools";
		if(searchResultTools === null || searchResultTools.length === 0)
		{
			searchResultTools.length = listOfTools.length;
			searchResultTools.fill(0);
		}
		addSearchItemsToList("accordion1-panel", listOfTools, searchResultTools, "Tools");
	}
	else if(panelId === "accordion2-panel")
	{
		searchField.placeholder = "Search Resources";
		searchField.placeholderText = "Search Resources";
		if(searchResultResources === null || searchResultResources.length === 0)
		{
			searchResultResources.length = listOfResources.length;
			searchResultResources.fill(0);
		}
		addSearchItemsToList("accordion2-panel", listOfResources, searchResultResources, "Resources");
	}
	else if(panelId === "accordion3-panel")
	{
		searchField.placeholder = "Search People";
		searchField.placeholderText = "Search People";
		if(searchResultUsers === null || searchResultUsers.length === 0)
		{
			searchResultUsers.length = listOfUsers.length;
			searchResultUsers.fill(0);
		}
		addSearchItemsToList("accordion3-panel", listOfUsers, searchResultUsers, "Shares");
	}
	
	searchField.parentId = panelId;
	var innerModal = document.getElementById("inner-modal-id");
	innerModal.style.display = "block";
}

//dynamically generate div items for accordions list - adding tools/reources/users to the modal
//Used for adding items from inner modal to outer modal
function addNewItem(panelId, item, itemId)
{
	var accordionPanel = document.getElementById(panelId);
	//keep track of number of items (except for the default last item)
	if(isNaN(accordionPanel.numListElements) || accordionPanel.numListElements === null)
		accordionPanel.numListElements = 0;
	accordionPanel.numListElements++;
	
	//create div element
	var newDiv = document.createElement('div');
	newDiv.item = item;
	var newDivClass = "accordion-inner-panel accordion-inner-panel-" + panelId;
	newDiv.setAttribute("class", newDivClass);
	//newDiv.setAttribute("class", "accordion-inner-panel");
	newDiv.innerHTML = item.name;
	var newDivId = "accordion-inner-panel-" + panelId + itemId;
	newDiv.setAttribute("id", newDivId);
	newDiv.id = itemId;
	
	//create view and delete icons for the div element
	var delClassName = "accordion-delete-icon-" + panelId;
	var viewClassName = "accordion-view-icon-" + panelId;
	var iconDelete = createAccordionActionItemIcons("image", delClassName, "delAccordionItem(this); return false;", "Delete", "resources/icons/accordion-delete-icon.png", "D");
	iconDelete.id = itemId;
	var iconView = createAccordionActionItemIcons("image", viewClassName, "viewAccordionItem(); return false;", "View Details", "resources/icons/accordion-preview-icon.png", "V");
	
	if(isNaN(accordionPanel.isEditAccordionItemsSelected) || accordionPanel.isEditAccordionItemsSelected === null)
		accordionPanel.isEditAccordionItemsSelected = 0;
	if(accordionPanel.isEditAccordionItemsSelected)
	{
		iconView.style.display = "none";
		iconDelete.style.display = "inline";
	}
	newDiv.appendChild(iconDelete);
	newDiv.appendChild(iconView);
	
	accordionPanel.insertBefore(newDiv, accordionPanel.childNodes[accordionPanel.numListElements]);
}

//Setting edge/node data on modal - when modal opens
function onLoadModal(callingNodeId, linkedObjectsList)
{
	if(linkedObjectsList === null || linkedObjectsList.length === 0)
	{
		return;
	}
	
	var completeItemsList = [];
	
	if(callingNodeId === "accordion1-panel")
	{
		completeItemsList = listOfTools;
	}
	else if(callingNodeId === "accordion2-panel")
	{
		completeItemsList = listOfResources;
	}
	else if(callingNodeId === "accordion3-panel")
	{
		completeItemsList = listOfUsers;
	}
	document.getElementById(callingNodeId).isEditAccordionItemsSelected = 0;
	document.getElementById(callingNodeId).numListElements = 0;
	
	//check if id from linkedObjectsList matches id of completeItemsList
	for(var i = 0; i < linkedObjectsList.length; i++)
	{
		for(var j = 0; j < completeItemsList.length; j++)
		{
			if(linkedObjectsList[i] === completeItemsList[j].id)
			{
				addNewItem(callingNodeId, completeItemsList[j], j);
			}
		}
	}
}

//helper function for the function "addNewTool" to create view and delete icons for the new div element
function createAccordionActionItemIcons(iconType, iconClass, iconOnclick, iconTitle, iconSrc, iconAlt)
{
	var newIcon = document.createElement('input');
	newIcon.setAttribute("type", iconType);
	newIcon.setAttribute("class", iconClass);
	newIcon.setAttribute("onclick", iconOnclick);
	newIcon.setAttribute("title", iconTitle);
	newIcon.setAttribute("src", iconSrc);
	newIcon.setAttribute("alt", iconAlt);
	return newIcon;
}

//Outer Modal - Save Button onclick for both edges and nodes
function saveNodeDetails()
{
	//console.log("Save Button Pressed - default method");
	if(isNodeSelected)
	{
		if(selectedNode === null)
		{
			alert("Add details to save");
		}
		else
		{
			selectedNode.title = document.getElementById("modal-node-name").innerHTML;
			var description = "";
			description = document.getElementById("modal-description-id").value;
			selectedNode.description = description;
			if(selectedNode.tools === null)
				selectedNode.tools = [];
			if(selectedNode.resources === null)
				selectedNode.resources = [];
			if(selectedNode.users === null)
				selectedNode.users = [];
			for(var i = 0; i < searchResultTools.length; i++)
			{
				if(searchResultTools[i] === 1)
				{
					selectedNode.tools.push(listOfTools[i].id);
				}
			}
			for(var i = 0; i < searchResultResources.length; i++)
			{
				if(searchResultResources[i] === 1)
				{
					selectedNode.resources.push(listOfResources[i].id);
				}
			}
			for(var i = 0; i < searchResultUsers.length; i++)
			{
				if(searchResultUsers[i] === 1)
				{
					selectedNode.users.push(listOfUsers[i].id);
				}
			}
		}
	}
	else if(isEdgeSelected)
	{
		if(selectedEdge === null)
		{
			alert("Add details to save");
		}
		else
		{
			selectedEdge.title = document.getElementById("modal-node-name").innerHTML;
			selectedEdge.description = document.getElementById("modal-description-id").value;
			if(selectedEdge.tools === null)
				selectedEdge.tools = [];
			if(selectedEdge.resources === null)
				selectedEdge.resources = [];
			if(selectedEdge.users === null)
				selectedEdge.users = [];
			for(var i = 0; i < searchResultTools.length; i++)
			{
				if(searchResultTools[i] === 1)
				{
					selectedEdge.tools.push(listOfTools[i].id);
				}
			}
			for(var i = 0; i < searchResultResources.length; i++)
			{
				if(searchResultResources[i] === 1)
				{
					selectedEdge.resources.push(listOfResources[i].id);
				}
			}
			for(var i = 0; i < searchResultUsers.length; i++)
			{
				if(searchResultUsers[i] === 1)
				{
					selectedEdge.users.push(listOfUsers[i].id);
				}
			}
		}
	}
}

function closeCompositionModal()
{
	//remove description
	document.getElementById("modal-description-id").value = "";
	document.getElementById('node-modal').style.display = "none";
	document.getElementById("add-process-ladder-body").style.overflow = "scroll";
}

function closeModal()
{	
	var graphModal = document.getElementById('node-modal');
	
	var acc = document.getElementsByClassName("accordion");
	var i;

	for (i = 0; i < acc.length; i++)
	{
		if(acc[i].isClose)
		{
			acc[i].classList.toggle("active");
			acc[i].nextElementSibling.classList.toggle("show");
			acc[i].isClose = false;
		}
	}
	
	openedLastAccordion = false;
	acc3.style.borderBottomLeftRadius = "10px";
	acc3.style.borderBottomRightRadius = "10px";
	
	searchResultTools.length = 0;
	searchResultResources.length = 0;
	searchResultUsers.length = 0;
	
	resetModalState();
	graphModal.style.display = "none";
	document.getElementById("add-process-ladder-body").style.overflow = "scroll";
	
	//TODO: prompt if details have changed but have not been saved
}

//utility function called on closing outer modal
function resetModalState()
{
	//remove description
	document.getElementById("modal-description-id").value = "";
	
	//for each accordion, remove accordion items (same as in delete) + set state of accordions (as in delete)
	var accordionEls = document.getElementsByClassName("accordion-inner-panel");
	//console.log("Number of items: " + accordionEls.length);
	while(accordionEls[0])
	{
		accordionEls[0].parentNode.removeChild(accordionEls[0]);
	}
	
	var accordion1 = document.getElementById("accordion1-panel");
	var accordion2 = document.getElementById("accordion2-panel");
	var accordion3 = document.getElementById("accordion3-panel");
	
	if(document.getElementById("view-ladder-name") === null)
	{
		var editButtonText1 = document.getElementById("tools-edit-button");
		editButtonText1.style.left = "77%";
		editButtonText1.innerHTML = "Edit";

		var editButtonText2 = document.getElementById("resources-edit-button");
		editButtonText2.style.left = "69.5%";
		editButtonText2.innerHTML = "Edit";

		var editButtonText3 = document.getElementById("share-edit-button");
		editButtonText3.style.left = "81.5%";
		editButtonText3.innerHTML = "Edit";
	}
	accordion1.isEditAccordionItemsSelected = 0;
	accordion2.isEditAccordionItemsSelected = 0;
	accordion3.isEditAccordionItemsSelected = 0;
	
	accordion1.numListElements = 0;
	accordion2.numListElements = 0;
	accordion3.numListElements = 0;
	
	//remove selection from checkboxes
	//document.getElementById("modal-checkbox1").checked = false;
	//document.getElementById("modal-checkbox2").checked = false;
	//document.getElementById("modal-checkbox3").checked = false;
}

function closeInnerModal()
{
	//display searchList items in the inner modal to the appropriate accordions in the parent modal
	var callingNodeId = document.getElementById("search-modal-body").calledFromId;
	var searchList = [];
	var searchListItem = [];
	
	if(callingNodeId === "accordion1-panel")
	{
		searchList = searchResultTools;
		searchListItem = listOfTools;
	}
	else if(callingNodeId === "accordion2-panel")
	{
		searchList = searchResultResources;
		searchListItem = listOfResources;
	}
	else if(callingNodeId === "accordion3-panel")
	{
		searchList = searchResultUsers;
		searchListItem = listOfUsers;
	}
	
	//remove all items from parent/calling accordion (in the outer modal)
	var divClass = "accordion-inner-panel-" + callingNodeId;
	var accordionPanels = document.getElementsByClassName(divClass);
	if(accordionPanels !== null)
	{
		while(accordionPanels[0])
		{
			accordionPanels[0].parentNode.removeChild(accordionPanels[0]);
		}
	}
	resetAccordionEditButton(callingNodeId);
	document.getElementById(callingNodeId).isEditAccordionItemsSelected = 0;
	document.getElementById(callingNodeId).numListElements = 0;
	
	//add item to the outer modal, if it has been selected in search
	if(searchList !== null && searchList.length !== 0)
	{
		for(var i = 0; i < searchList.length; i++)
		{
			if(searchList[i] === 1)
			{
				//only add item if it is in the search list and does not already exist
				addNewItem(callingNodeId, searchListItem[i], i);
			}
		}
	}
	
	var graphModal = document.getElementById('inner-modal-id');
	resetInnerModalState();
	graphModal.style.display = "none";
}

//utility function called on closing inner modal
function resetInnerModalState()
{
	//reset search field and remove all items
	document.getElementById("search-input").value = "";
	searchTextFieldColorOriginal();
	
	var labelItem = document.getElementById("no-item-found-label");
	labelItem.style.display = "none";
	
	var searchItems = document.getElementsByClassName("search-inner-panel");
	while(searchItems[0])
	{
		searchItems[0].parentNode.removeChild(searchItems[0]);
	}
}

//add all available items to search list in the inner modal
function addSearchItemsToList(calledFromId, itemsList, searchItemsList, itemsLabel)
{
	var searchListDiv = document.getElementById("search-modal-body");
	searchListDiv.calledFromId = calledFromId;
	searchListDiv.itemsLabel = itemsLabel;
	
	if(itemsList === null || itemsList.length === 0)
	{
		//label that displays: No "itemsLabel" found
		var labelText = "No " + itemsLabel + " Found";
		var labelItem = document.getElementById("no-item-found-label");
		labelItem.style.display = "block";
		labelItem.innerHTML = labelText;
	}
	
	for(var i = 0; i < itemsList.length; i++)
	{
		//create div element
		var newItemDiv = document.createElement('div');
		newItemDiv.setAttribute("class", "search-inner-panel");
		newItemDiv.innerHTML = itemsList[i].name;
		
		//add view, delete and add icons
		var addIconTitleString = "Add to " + itemsLabel;
		var deleteIconTitleString = "Delete from " + itemsLabel;
		
//TODO: if the calling accordion is for Sharing - this button should display access rights

		var iconAdd = createAccordionActionItemIcons("image", "search-add-icon", "addSearchItem(this); return false;", addIconTitleString, "resources/icons/search-add-icon.png", "A");
		var iconDelete = createAccordionActionItemIcons("image", "search-delete-icon", "deleteSearchItem(this); return false;", deleteIconTitleString, "resources/icons/search-delete-icon.png", "V");
		var iconView = createAccordionActionItemIcons("image", "search-view-icon", "viewSearchItem(); return false;", "View Details", "resources/icons/accordion-preview-icon.png", "V");

		//set data so that add and delete icons can be obained by id in other functions
		iconAdd.parentId = calledFromId;
		iconAdd.itemNumber = i;
		var addId = "search-add-icon" + iconAdd.parentId + iconAdd.itemNumber;
		iconAdd.setAttribute("id", addId);
		
		iconDelete.parentId = calledFromId;
		iconDelete.itemNumber = i;
		var addDelete= "search-delete-icon" + iconDelete.parentId + iconDelete.itemNumber;
		iconDelete.setAttribute("id", addDelete);
		
		newItemDiv.appendChild(iconAdd);
		newItemDiv.appendChild(iconDelete);
		newItemDiv.appendChild(iconView);
		
		//check if an item is already added to the list, make the delete button display instead of add button
		if(searchItemsList[i] === 1)
			iconAdd.style.display = "none";
		else
			iconDelete.style.display = "none";
		
		newItemDiv.item = itemsList[i];
		searchListDiv.appendChild(newItemDiv);
	}
}

//select items to be added to the list in the outer modal according to the 
//parent/calling accordion in the outer modal
function addSearchItem(el)
{
	//display the delete button instead and add item to search list
	var deleteId = "search-delete-icon" + el.parentId + el.itemNumber;
	el.style.display = "none";
	document.getElementById(deleteId).style.display = "inline";
	if(el.parentId === "accordion1-panel")
	{
		searchResultTools[el.itemNumber] = 1;
	}
	else if(el.parentId === "accordion2-panel")
	{
		searchResultResources[el.itemNumber] = 1;
	}
	else if(el.parentId === "accordion3-panel")
	{
		searchResultUsers[el.itemNumber] = 1;
	}
}

//deselect items so that they no longer appear in the outer modal and are not linked with the node
function deleteSearchItem(el)
{
	//display the add button instead and remove item from search list
	var addId = "search-add-icon" + el.parentId + el.itemNumber;
	el.style.display = "none";
	document.getElementById(addId).style.display = "inline";
	
	//remove - use another list to keep track of included and removed items - since removing (splice) is a costly operation
	if(el.parentId === "accordion1-panel")
	{
		searchResultTools[el.itemNumber] = 0;
	}
	else if(el.parentId === "accordion2-panel")
	{
		searchResultResources[el.itemNumber] = 0;
	}
	else if(el.parentId === "accordion3-panel")
	{
		searchResultUsers[el.itemNumber] = 0;
	}
}

//view details of the item in the search list
function viewSearchItem()
{
	//TODO: 
	console.log("View search item");
}

function modalHeaderFull()
{
	if(isNodeSelected && selectedNode.title !== null)
	{
		document.getElementById("modal-node-name").innerHTML = selectedNode.title;
	}
	else if(isEdgeSelected && selectedEdge.title !== null)
	{
		document.getElementById("modal-node-name").innerHTML = selectedEdge.title;
	}
}

function modalHeaderClip()
{
	var headerName = document.getElementById("modal-node-name").innerHTML;
	headerName = headerName.trim();
	
	if(isNodeSelected && selectedNode.title !== null)
	{
		selectedNode.title = headerName;
	}
	else if(isEdgeSelected && selectedEdge.title !== null)
	{
		selectedEdge.title = headerName;
	}
	
	var headerLines = headerName.split("<div>");
	
	var headerFirstLine = headerLines[0];
	
	if(headerFirstLine.length > 48)
		document.getElementById("modal-node-name").innerHTML = headerFirstLine.slice(0, 48) + "...";
	else
	{
		if(headerLines.length > 1)
			document.getElementById("modal-node-name").innerHTML = headerFirstLine + "...";
		else
			document.getElementById("modal-node-name").innerHTML = headerFirstLine;
	}
}

function closeViewModal()
{	
	var graphModal = document.getElementById('node-modal');
	
	var acc = document.getElementsByClassName("accordion");
	var i;

	for (i = 0; i < acc.length; i++)
	{
		if(acc[i].isClose)
		{
			acc[i].classList.toggle("active");
			acc[i].nextElementSibling.classList.toggle("show");
			acc[i].isClose = false;
		}
	}
	
	openedLastAccordion = false;
	acc3.style.borderBottomLeftRadius = "10px";
	acc3.style.borderBottomRightRadius = "10px";
	
	searchResultTools.length = 0;
	searchResultResources.length = 0;
	searchResultUsers.length = 0;

	//remove description
	document.getElementById("modal-description-id").value = "";
	
	//for each accordion, remove accordion items (same as in delete) + set state of accordions (as in delete)
	var accordionEls = document.getElementsByClassName("accordion-inner-panel");
	//console.log("Number of items: " + accordionEls.length);
	while(accordionEls[0])
	{
		accordionEls[0].parentNode.removeChild(accordionEls[0]);
	}
	
	var accordion1 = document.getElementById("accordion1-panel");
	var accordion2 = document.getElementById("accordion2-panel");
	var accordion3 = document.getElementById("accordion3-panel");
	
	accordion1.isEditAccordionItemsSelected = 0;
	accordion2.isEditAccordionItemsSelected = 0;
	accordion3.isEditAccordionItemsSelected = 0;
	
	accordion1.numListElements = 0;
	accordion2.numListElements = 0;
	accordion3.numListElements = 0;
	
	graphModal.style.display = "none";
}