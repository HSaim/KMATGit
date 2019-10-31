/* 
 * Code from:
 * https://www.sitepoint.com/building-custom-right-click-context-menu-javascript/
 * 
 * Simple idea:
 * http://stackoverflow.com/questions/4235426/how-can-i-capture-the-right-click-event-in-javascript
 */

	/**
	* Function to check if we clicked inside an element with a particular class
	* name.
	* 
	* @param {Object} e The event
	* @param {String} className The class name to check against
	* @return {Boolean}
	*/
	function clickInsideElement(e, className)
	{
		var el = e.srcElement || e.target;
		if(el.classList.contains(className))
		{
			return el;
		}
		else
		{
			while(el === el.parentNode)
			{
				if(el.classList && el.classList.contains(className))
				{
					return el;
				}
			}
		}

		return false;
	}

	/**
	* Variables.
	*/
	var contextMenuLinkClassName = "context-menu__link";
	var contextMenuActive = "context-menu--active";
//TODO: make new class: context-menu-disabled and use it similar to the above

	var clickCoordsX;
	var clickCoordsY;

	var menu = document.querySelector("#context-menu-node");
	var menuState = 0;
	var menuWidth;
	var menuHeight;

	var menuWindowWidth;
	var menuWindowHeight;

	/**
	* Listens for click events.
	*/
	function clickListener()
	{
		document.addEventListener("click", function(e)
		{
			var clickeElIsLink = clickInsideElement(e, contextMenuLinkClassName);

			if(clickeElIsLink)
			{
				e.preventDefault();
				menuItemListener(clickeElIsLink);
			}
			else
			{
				var button = e.which || e.button;
				if(button === 1)
				{
					toggleMenuOff();
				}
			}
		});
	}

	/**
	* Turns the custom context menu on.
	*/
	function toggleMenuOn()
	{
		if(menuState !== 1)
		{
			menuState = 1;
			menu.classList.add(contextMenuActive);
		}
	}

	/**
	* Turns the custom context menu off.
	*/
	function toggleMenuOff()
	{
		if(menuState !== 0)
		{
			menuState = 0;
			menu.classList.remove(contextMenuActive);
		}
	}

   /**
	* Positions the menu properly.
	* 
	* @param {float} x The x position
	* @param {float} y The y position
	* @param {float} scale The scaled value for the parent object
	* @param {float} xOffset The offset relative to the starting of svg
	*/
	//function positionMenu(e)
	function positionMenu(x, y, scale, xOffset)
	{
		menu.style.transform = "scale(" + scale + ")";
		//menu.style.transform = 0;
		
		clickCoordsX = x - xOffset - 15;
		clickCoordsY = y - 25;
		menuWidth = menu.offsetWidth + 100;
		menuHeight = menu.offsetHeight + 4;

		menuWindowWidth = window.innerWidth;
		menuWindowHeight = window.innerHeight;

		if((menuWindowWidth - clickCoordsX) < menuWidth)
		{
			menu.style.left = menuWindowWidth - menuWidth - 30 + "px";
		}
		else
		{
			menu.style.left = clickCoordsX + "px";
		}

		if((menuWindowHeight - clickCoordsY) < menuHeight)
		{
			menu.style.top = menuWindowHeight - menuHeight + "px";
		}
		else
		{
			menu.style.top = clickCoordsY + "px";
		}
	}

	/**
	* Dummy action function that logs an action when a menu item link is clicked
	* 
	* @param {HTMLElement} link The link that was clicked
	*/
	function menuItemListener(link)
	{
		console.log("Task attribute- " + link.getAttribute("data-action"));
		
//TODO: [set root node]
		// 1. Remove previous root
		//		i) Reset node color to normal  [add/remove css class]
		//		ii) Reset title and text of "link"
		// 2. Set current root
		//		i) Set node color to root node  [add/remove css class]
		//		ii) Set title and text of "link" to "Root Node"
		//		
		// 3. Reset levels of each node
		// 4. Check for flaws/errors in ladder structure
		
		toggleMenuOff();
	}
	
	function setRootNode()
	{
		console.log("Set Root Node");
	}
	
	function setSourceNode()
	{
		console.log("Reset Source Node");
	}
	
	function setTargetNode()
	{
		console.log("Reset Target Node");
	}
	
	clickListener();