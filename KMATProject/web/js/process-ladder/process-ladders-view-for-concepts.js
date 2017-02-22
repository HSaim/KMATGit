/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var xhttp = new XMLHttpRequest();
var allProcessLadders = [];
var allCompositionLadders = [];
var isDelete = false;
var response;

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
                response = xhttp.responseText;
                index = response.lastIndexOf('][');
                process = response.substring(0,index+1);
                composition = response.substring(index+1);
                
		allProcessLadders = JSON.parse(process);
		//alert(allProcessLadders.length);
		for(var i = 0; i < allProcessLadders.length; i++)
		{
			//console.log(allProcessLadders[i].name);

			var rowDiv = document.createElement('div');
			rowDiv.setAttribute("class", "row ladder-view-table-row");

			//** Ladder names **//
			var col1 = document.createElement('div');
                        //col1.setAttribute("id", "editIt");
                        //Hyperlink Text Selector
                        //col1.setAttribute("contentEditable", true);
                        //document.getElementById('editIt').contentEditable = true;
			col1.setAttribute("class", "col-md-6 table-row-div-align");
			var col1href = document.createElement('a');
			col1href.setAttribute("class", "ladder-view-table-link w3-display-middle");
			col1href.innerHTML = "\n" + allProcessLadders[i].name;
                        col1href.href = "view-a-process-ladder?ladder=" + JSON.stringify(allProcessLadders[i]);
                        col1href.setAttribute('target', '_blank');
//TODO: check if window.sessionStorage would be a better option
			//set link to view ladder 
			///var url1 = "window.location.href='view-a-process-ladder?ladder=" + JSON.stringify(allProcessLadders[i]) + "'";
			//col1href.setAttribute("onclick", "window.location.href='view-a-process-ladder'");
			///col1href.setAttribute("onclick", url1);
			var col1h5 = document.createElement('h5');
			col1.style.left = "20px";
			col1h5.appendChild(col1href);
			col1.appendChild(col1h5);
                        
			rowDiv.appendChild(col1);
			document.getElementById("inner-body").appendChild(rowDiv);
		}
                
                allCompositionLadders = JSON.parse(composition);
		//alert(allCompositionLadders.length);
		for(var i = 0; i < allCompositionLadders.length; i++)
		{
			//console.log(allCompositionLadders[i].name);

			var rowDivc = document.createElement('div');
			rowDivc.setAttribute("class", "row ladder-view-table-row");

			//** Ladder names **//
			var col1c = document.createElement('div');
			col1c.setAttribute("class", "col-md-6 table-row-div-align");
			var col1chref = document.createElement('a');
			col1chref.setAttribute("class", "ladder-view-table-link w3-display-middle");
			col1chref.innerHTML = allCompositionLadders[i].name;
                        col1chref.href = "view-a-composition-ladder?ladder=" + JSON.stringify(allCompositionLadders[i]);
                        col1chref.setAttribute('target', '_blank');
//TODO: check if window.sessionStorage would be a better option
			//set link to view ladder 
			///var url1c = "window.location.href='view-a-composition-ladder?ladder=" + JSON.stringify(allCompositionLadders[i]) + "'";
			//col1chref.setAttribute("onclick", "window.location.href='view-a-composition-ladder'");
			///col1chref.setAttribute("onclick", url1c);
			var col1ch5 = document.createElement('h5');
			col1c.style.left = "20px";
			col1ch5.appendChild(col1chref);
			col1c.appendChild(col1ch5);

			rowDivc.appendChild(col1c);
			document.getElementById("inner-body1").appendChild(rowDivc);
		}
	}
	else
	{
		//alert('Request failed.  Returned status of ' + xhttp.status);
	}
};

xhttp.open("POST", "GetLaddersController", true);
xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
var action = "action=get-all-process-composition-ladders";
xhttp.send(action);