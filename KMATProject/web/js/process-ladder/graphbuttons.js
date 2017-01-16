/* 
 * Utility Functions for handling settings, help and error buttons on Roles, Process and Ladder creation page
 */


//		HELP BUTTON functions

function onMouseDownHelpButton(callerGraphType)
{
	loadHelpButtonPressed(callerGraphType);
}

function onMouseUpHelpButton(callerGraphType)
{
	loadHelpButtonOriginal(callerGraphType);
}

function onClickHelpButton(callerGraphType, x, y)
{	
	loadHelpButtonPressed(callerGraphType);
	setTimeout(function()
		{
			onClickHelpButtonCallBack(callerGraphType, x, y);
		}, 200);
}

function onClickHelpButtonCallBack(callerGraphType, x, y)
{
	loadHelpButtonOriginal(callerGraphType);
	var helpModal = document.getElementById("help-tooltiptext");
	
	//make visible
	helpModal.style.display = "inline";
	
	//position modal onclick
	helpModal.style.left = (x - helpModal.offsetWidth) + "px";
	helpModal.style.top = y + "px";
}

function onBlurHelpButton()
{
	document.getElementById("help-tooltiptext").style.display = "none";
}

//loading images of Help button
function loadHelpButtonOriginal(callerGraphType)
{
	if(callerGraphType === "Roles")
		document.getElementById('graph-help-icon').src='images/help-icons/help-icon18.png';
	else if(callerGraphType === "Composition")
		document.getElementById('graph-help-icon').src='images/help-icons/help-icon19.png';
	else if(callerGraphType === "Process")
		document.getElementById('graph-help-icon').src='images/help-icons/help-icon20.png';
}

function loadHelpButtonPressed(callerGraphType)
{
	if(callerGraphType === "Roles")
		document.getElementById('graph-help-icon').src='images/help-icons/help-icon18-pressed.png';
	else if(callerGraphType === "Composition")
		document.getElementById('graph-help-icon').src='images/help-icons/help-icon19-pressed.png';
	else if(callerGraphType === "Process")
		document.getElementById('graph-help-icon').src='images/help-icons/help-icon20-pressed.png';
}


//		SETTINGS BUTTON functions

function onMouseDownSettingsButton(callerGraphType)
{
	loadSettingsButtonPressed();
}

function onMouseUpSettingsButton(callerGraphType)
{
	loadSettingsButtonOriginal();
}

function onClickSettingsButton(callerGraphType)
{
	loadSettingsButtonPressed();
	setTimeout(function()
	{
		onClickSettingsButtonCallBack(callerGraphType);
	}, 200);
}

function onClickSettingsButtonCallBack(callerGraphType)
{
	loadSettingsButtonOriginal();
	//DO ALL WORK HERE!
	document.getElementById("graph-settings-modal").style.display = "block";
}

function settingsButtonOnClose()
{
	document.getElementById('graph-settings-modal').style.display = "none";
}

//loading images of Settings button
function loadSettingsButtonOriginal()
{
	document.getElementById('graph-settings-icon').src='images/icons/settings-icon.png';
}

function loadSettingsButtonPressed()
{
	document.getElementById('graph-settings-icon').src='images/icons/settings-icon-pressed.ico';
}


//		ERROR BUTTON functions

function onMouseDownErrorButton(callerGraphType)
{
	loadErrorButtonPressed();
}

function onMouseUpErrorButton(callerGraphType)
{
	loadErrorButtonOriginal();
}

function onClickErrorButton(callerGraphType)
{
	/*loadErrorButtonPressed();
	setTimeout(function()
	{
		onClickErrorButtonCallBack(callerGraphType);
	}, 200);*/
}

function onClickErrorButtonCallBack()
{
	loadErrorButtonOriginal();

//TODO: get and display all errors
	
	document.getElementById("error-modal").style.display = "inline";
}

function onBlurErrorButton()
{
	document.getElementById("error-modal").style.display = "none";
}

//loading images of Error button
function loadErrorButtonOriginal()
{
	document.getElementById('error-button').src='images/icons/error-icon.ico';
}

function loadErrorButtonPressed()
{
	document.getElementById('error-button').src='images/icons/error-icon-pressed.ico';
}