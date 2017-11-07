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
	
	var notifyLoginObservers = function() {
		loginObservers.forEach(function(callback) {
			callback(loggedIn);
		})
	}
	
	// BoardObserver
	var board = {};
	var boardObserver = null;
	
	this.registerBoardObserver = function(callback) {
		boardObserver = callback;
	}
	
	var notifyBoardObserver = function() {
		boardObserver(board);
	}
	
	// LoadingObserver
	var loading = false;
	var loadingObserver = null;
	
	this.registerLoadingObserver = function(callback) {
		loadingObserver = callback;
	}
	
	var toggleLoading = function() {
		loading = !loading;
		loadingObserver(loading);
	}
	
	
	this.login = function(bu, success, failure) {
		toggleLoading();
		$http.post("login", bu).then(response => {
				loggedIn = true;
				notifyLoginObservers();
				toggleLoading();
			}, response => {
				failure(response);
				toggleLoading();
			});
	}

	this.checkLogin = function() {
		toggleLoading();
		$http.get("ajax/isLoggedIn").then(
				response => {
					loggedIn = response.data;
					notifyLoginObservers();
					toggleLoading();
				});
	}
	
	this.logout = function(success, failure) {
		toggleLoading();
		$http.get("logout").then(response => {
				loggedIn = false;
				notifyLoginObservers();
				toggleLoading();
		}, response => {
			failure(response);
			toggleLoading();
		});
	}
	
	this.getBoards = function(success, failure) {
		toggleLoading();
		$http.get("ajax/boards").then(response => {
			success(response);
			toggleLoading();
		}, response => {
			failure(response);
			toggleLoading();
		});
	}
	
	this.setBoard = function(newBoard) {
		board = newBoard;
		notifyBoardObserver();
	}
	
	this.editBoard = function(board, success, failure){
		toggleLoading();
		$http.post("ajax/board/edit", board).then(response => {
			success(response);
			toggleLoading();
		}, response => {
			failure(response);
			toggleLoading();
		});
	}
	
	this.register = function(newUser,success,failure){
		toggleLoading();
		$http.post("ajax/register",newUser).then(response => {
			success(response);
			toggleLoading();
		}, response => {
			failure(response);
			toggleLoading();
		});
	}
	


	//create board methods
	
	this.createBoard = function(b, success){
		toggleLoading();
		console.log("Creating stuff");
		var promise = $http.post("createBoard", b).then(response => {
			success(response);
			toggleLoading();
		});
	}
	
	//deleteboard methods
	
	
	
	this.deleteBoard = function(b, success){
		toggleLoading();
		console.log("Deleting stuff");
		var promise = $http.post("deleteBoard", b).then(response => {
			success(response);
			toggleLoading();
		});
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
		toggleLoading();
		$http.post("ajax/swimlane/new", sl).then(response => {
			success(response);
			toggleLoading();
		}, response => {
			failure(response);
			toggleLoading();
		});
	}
	
	this.getSwimLanes = function(callback) {
		callback(board.swimLanes);
	}
	
	this.editSwimLane = function(sl, success, failure) {
		toggleLoading();
		$http.post("ajax/swimlane/edit", sl).then(response => {
			success(response);
			toggleLoading();
		}, response => {
			failure(response);
			toggleLoading();
		});
	}
	
	this.editSwimLanes = function(sls, success, failure) {
		toggleLoading();
		$http.post("ajax/swimlane/editAll", sls).then(response => {
			success(response);
			toggleLoading();
		}, response => {
			failure(response);
			toggleLoading();
		});
	}
	
	this.deleteSwimLane = function(sl, success, failure) {
		toggleLoading();
		$http.post("ajax/swimlane/delete", sl).then(response => {
			success(response);
			toggleLoading();
		}, response => {
			failure(response);
			toggleLoading();
		});
	}

	
	this.createTask = function(task, success, failure) {
		toggleLoading();
		$http.post("ajax/task/new", task).then(response => {
			success(response);
			toggleLoading();
		}, response => {
			failure(response);
			toggleLoading();
		});
	}
	
	this.editTask = function(task, success, failure) {
		toggleLoading();
		$http.post("ajax/task/edit", task).then(response => {
			success(response);
			toggleLoading();
		}, response => {
			failure(response);
			toggleLoading();
		});
	}
	
	this.editTasks = function(tasks, success, failure) {
		toggleLoading();
		$http.post("ajax/task/editAll", tasks).then(response => {
			success(response);
			toggleLoading();
		}, response => {
			failure(response);
			toggleLoading();
		});
	}

	
	this.deleteTask = function(task, success, failure) {
		toggleLoading();
		$http.post("ajax/task/delete", task).then(response => {
			success(response);
			toggleLoading();
		}, response => {
			failure(response);
			toggleLoading();
		});
	}
	
	
	this.createStory = function(story, success, failure) {
		toggleLoading();
		$http.post("ajax/story/new", story).then(response => {
			success(response);
			toggleLoading();
		}, response => {
			failure(response);
			toggleLoading();
		});
	}
	
	this.editStory = function(story, success, failure) {
		toggleLoading();
		$http.post("ajax/story/edit", story).then(response => {
			success(response);
			toggleLoading();
		}, response => {
			failure(response);
			toggleLoading();
		});
	}
	
	this.editStories = function(stories, success, failure) {
		toggleLoading();
		$http.post("ajax/story/editAll", stories).then(response => {
			success(response);
			toggleLoading();
		}, response => {
			failure(response);
			toggleLoading();
		});
	}
	
	this.deleteStory = function(story, success, failure) {
		toggleLoading();
		$http.post("ajax/story/delete", story).then(response => {
			success(response);
			toggleLoading();
		}, response => {
			failure(response);
			toggleLoading();
		});
	}
	
	this.swapOrders = function(array, idx1, idx2) {
		array[idx1].order = array[idx1].order + array[idx2].order;
		array[idx2].order = array[idx1].order - array[idx2].order;
		array[idx1].order = array[idx1].order - array[idx2].order;
	}

	
})