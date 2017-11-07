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
	this.editBoard = function(board, success, failure){
		$http.post("ajax/board/edit", board).then(success,failure);
	}
	this.register = function(newUser,success,failure){
		$http.post("ajax/register",newUser).then(success,failure);
	}
	


	//create board methods
	
	this.createBoard = function(b, success){
		console.log("Creating stuff");
		var promise = $http.post("createBoard", b).then(success);
		
	}
	
	//deleteboard methods
	
	
	
	this.deleteBoard = function(b, success){
		console.log("Deleting stuff");
		var promise = $http.post("deleteBoard", b).then(success);
		
	}
	
	
//	ds.setLoadState = function() {
//		if (ds.isLoggedIn()) {
//			root.$emit("toggleLogin", {});
//			root.$emit("updateFragment", "board");
//		} else {
//			root.$emit("updateFragment", "login");
//		}
//	}

	this.addNewTask = function(newTask, success, failure) {
		$http.post("ajax/task/new", newTask).then(success, failure);
	}
	
	//Swimlane functions
	this.createSwimLane = function(sl, success, failure) {
		$http.post("ajax/swimlane/new", sl).then(success, failure);
	}
	
	this.editSwimLane = function(sl, success, failure) {
		$http.post("ajax/swimlane/edit", sl).then(success, failure);
	}
	
	this.deleteSwimLane = function(sl, success, failure) {
		$http.post("ajax/swimlane/delete", sl).then(success, failure);
	}

	
	this.createTask = function(task, success, failure) {
		$http.post("ajax/task/new", task).then(success, failure);
	}
	
	this.editTask = function(task, success, failure) {
		$http.post("ajax/task/edit", task).then(success, failure);

	}

	
	this.deleteTask = function(task, success, failure) {
		$http.post("ajax/task/delete", task).then(success, failure);
	}
	
	
	this.createStory = function(story, success, failure) {
		$http.post("ajax/story/new", story).then(success, failure);
	}
	
	this.editStory = function(story, success, failure) {
		$http.post("ajax/story/edit", story).then(success, failure);
	}
	
	this.deleteStory = function(story, success, failure) {
		$http.post("ajax/story/delete", story).then(success, failure);
	}

	
})