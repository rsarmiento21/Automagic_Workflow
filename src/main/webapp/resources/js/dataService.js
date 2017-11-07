/**
 *  Angular JS for DataService service
 */

angular.module("scrumApp")

.service("dataService", function($http) {
	// Login variable and listeners
	var loggedIn = false;
	var loginObservers = [];
	
	this.registerLoginObserver = function(callback) {
		loginObservers.push(callback);
	}
	
	var notifyObservers = function() {
		loginObservers.forEach(function(callback) {
			callback(loggedIn);
		})
	}
	
	var fragment = "dummy";
	
	this.login = function(bu, success, failure) {
		$http.post("login", bu).then(response => {
				loggedIn = true;
				notifyObservers();
			}, failure);
	}

	this.checkLogin = function() {
		$http.get("ajax/isLoggedIn").then(
				response => {
					loggedIn = response.data;
					notifyObservers();
				});
	}
	
	this.logout = function(success, failure) {
		$http.get("logout").then(response => {
				loggedIn = false;
				notifyObservers();
			}, failure);
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
	
	this.editTasks = function(tasks, success, failure) {
		$http.post("ajax/task/editAll", tasks).then(success, failure);
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
	
	this.editStories = function(stories, success, failure) {
		$http.post("ajax/story/editAll", stories).then(success, failure);
	}
	
	this.deleteStory = function(story, success, failure) {
		$http.post("ajax/story/delete", story).then(success, failure);
	}
	
	this.swapOrders = function(array, idx1, idx2) {
		array[idx1].order = array[idx1].order + array[idx2].order;
		array[idx2].order = array[idx1].order - array[idx2].order;
		array[idx1].order = array[idx1].order - array[idx2].order;
	}

	
})