/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var xhttp = new XMLHttpRequest();
var allConceptMaps = [];
var isDelete = false;
xhttp.onreadystatechange = function() 
{
	if (xhttp.readyState === 4 || xhttp.readyState === "complete")
	{
		//if deleted, notify user and refresh page
		if(isDelete)
		{
			isDelete = false;
			window.location.reload();
			//alert("Ladder deleted successfully!");
			return;
		}
		allConceptMaps = JSON.parse(xhttp.responseText);
		//alert(allProcessLadders.length);
                
                var listScrollDiv = document.createElement('div');
		listScrollDiv.style.overflowY = "scroll";
		listScrollDiv.style.height= (document.getElementById("navbar").clientHeight - (2*document.getElementById("list-header-row-id").clientHeight)) +"px";
                
		for(var i = 0; i < allConceptMaps.length; i++)
		{
			//console.log(allProcessLadders[i].name);

			var rowDiv = document.createElement('div');
			rowDiv.setAttribute("class", "row ladder-view-table-row");

			//** Ladder names **//
			var col1 = document.createElement('div');
			col1.setAttribute("class", "col-md-6 table-row-div-align");
			var col1href = document.createElement('a');
			col1href.setAttribute("class", "ladder-view-table-link w3-display-middle");
			col1href.innerHTML = "\n" + allConceptMaps[i].name;
//TODO: check if window.sessionStorage would be a better option
			//set link to view ladder 
			var url1 = "window.location.href='view-a-concept-map?ladder=" + JSON.stringify(allConceptMaps[i]) + "'";
			//col1href.setAttribute("onclick", "window.location.href='view-a-process-ladder'");
			col1href.setAttribute("onclick", url1);
			var col1h5 = document.createElement('h5');
			col1.style.left = "20px";
			col1h5.appendChild(col1href);
			col1.appendChild(col1h5);

			//** Edit Ladder **//
			var col2 = document.createElement('div');
			col2.setAttribute("class", "col-md-3 center-block text-center talign-center table-row-div-align");
			var col2href = document.createElement('a');
			//set link to edit process ladder - passing on the ladder
			var url2 = "window.location.href='edit-concept-map?ladder=" + JSON.stringify(allConceptMaps[i]) + "'";
			col2href.setAttribute("onclick", url2);
			col2href.setAttribute("class", "ladder-view-table-link");
			var col2h3 = document.createElement('h5');
			var col2i = document.createElement('i');
			col2i.setAttribute("class", "icon-edit ladder-view-table-link ladder-view-table-icons");
			col2href.appendChild(col2i);
			col2h3.appendChild(col2href);
			col2.appendChild(col2h3);

			//** Delete Ladder **//
			var col3 = document.createElement('div');
			col3.setAttribute("class", "col-md-3 center-block text-center talign-center table-row-div-align");
			var col3href = document.createElement('a');
			//set link to get ladders controller with action: delete-process-ladder-from-view-ladders
			col3href.setAttribute("onclick", "deleteLadder("+ allConceptMaps[i].id + "); return false;");
			//var url3 = "view-process-ladders";
			//col3href.setAttribute("href", url3);
			col3href.setAttribute("class", "ladder-view-table-link");
			var col3h3 = document.createElement('h5');
			var col3i = document.createElement('i');
			col3i.setAttribute("class", "icon-trash ladder-view-table-link ladder-view-table-icons");
			col3href.appendChild(col3i);
			col3h3.appendChild(col3href);
			col3.appendChild(col3h3);

			rowDiv.appendChild(col1);
			rowDiv.appendChild(col2);
			rowDiv.appendChild(col3);

			//document.getElementById("inner-body").appendChild(rowDiv);
                        listScrollDiv.appendChild(rowDiv);
		}
                document.getElementById("inner-body").appendChild(listScrollDiv);
	}
	else
	{
		//alert('Request failed.  Returned status of ' + xhttp.status);
	}
};

function deleteLadder(ladderId)
{
	var userResponse = window.confirm("Delete Map?");
	if(userResponse)
	{
		//alert("TODO: delete ladder: " + ladderId);
		//TODO: delet ladder according to ladder id and refresh this page
		isDelete = true;
		xhttp.open("POST", "GetLaddersController", true);
		xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		var action = "action=delete-concept-map-from-view-ladders&ladder_id=" + ladderId;
		xhttp.send(action);
	}
}
xhttp.open("POST", "GetLaddersController", true);
xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
var action = "action=get-all-concept-maps";
xhttp.send(action);