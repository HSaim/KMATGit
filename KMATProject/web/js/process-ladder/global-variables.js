/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var LadderType = {
			PROCESS : "PROCESS",
			COMPOSITION: "COMPOSITION",
			ROLES: "ROLES"
};

var nodeType = {
			PROCESS : "PROCESS",
			COMPOSITION: "COMPOSITION",
			ROLES: "ROLES",
			CONCEPT: "CONCEPT"
};

var edgeType = {
			EMPTY: "EMPTY",
			PROCESS : "PROCESS",
			COMPOSITION: "COMPOSITION",
			ROLES: "ROLES",
			CONCEPT_LINK: "CONCEPT_LINK",
			CONCEPT_PARENT_TO_CHILD: "CONCEPT_PARENT_TO_CHILD"
};

var currentLadderType = null;
