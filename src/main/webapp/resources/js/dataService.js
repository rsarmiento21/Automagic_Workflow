/**
 *  Angular JS for DataService service
 */

angular.module("scrumApp")

.service("dataService", function($http, $q) {
	this.login = function(bu, success, failure) {
		$http.post("login", bu).then(success, failure);
	}

	this.isLoggedIn = function(callback) {
		$http.get("ajax/isLoggedIn").then(callback);
	}
	
	this.logout = function(success, failure) {
		$http.get("logout").then(success, failure);
	}
	
	this.getBoards = function(success, failure) {
		var promise = $http.get("ajax/boards").then(success, failure);
	}
	
	this.getBoard = function(id, success, failure) {
		$http.get("ajax/board/" + id).then(success, failure);
	}
	
	
	this.createSwimLane = function(name, boardId, success, failure) {
		$http.get("ajax/swimlane/create/"+name+"/"+boardId).then(success, failure);
	}
	
	this.deleteSwimLane = function(swimLaneId, success, failure) {
		$http.get("ajax/swimlane/delete/"+swimLaneId).then(success,failure);
	}

//	ds.setLoadState = function() {
//		if (ds.isLoggedIn()) {
//			root.$emit("toggleLogin", {});
//			root.$emit("updateFragment", "board");
//		} else {
//			root.$emit("updateFragment", "login");
//		}
//	}
	
})