/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var xhttpc = new XMLHttpRequest();
var allCompositionLadders = [];
var isDeletec = false;

xhttpc.onreadystatechange = function() 
{
	if (xhttpc.readyState === 4 || xhttpc.readyState === "complete")
	{
            setTimeout(function(){ 
                }, 3000); 
		//if deleted, notify user and refresh page
		if(isDeletec)
		{
			isDeletec = false;
			window.location.reload();
			//alert("Ladder deleted successfully!");
			return;
		}
		allCompositionLadders = JSON.parse(xhttpc.responseText);
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
//TODO: check if window.sessionStorage would be a better option
			//set link to view ladder 
			var url1c = "window.location.href='view-a-composition-ladder?ladder=" + JSON.stringify(allCompositionLadders[i]) + "'";
			//col1chref.setAttribute("onclick", "window.location.href='view-a-composition-ladder'");
			col1chref.setAttribute("onclick", url1c);
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
		//alert('Request failed.  Returned status of ' + xhttpc.status);
	}
};

xhttpc.open("POST", "GetLaddersController", true);
xhttpc.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
var action = "action=get-all-composition-ladders";
xhttpc.send(action);