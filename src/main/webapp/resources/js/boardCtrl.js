/**
 *  Angular JS for Board fragment
 */

angular.module("scrumApp")

.controller("boardCtrl", function($scope, $rootScope, dataService) {
	$scope.board = null;
	$scope.newTask = {
				name: "",
				taskCompleted: 0,
				story: null
			};
	
	$scope.getBoard = function(id) {
		dataService.getBoard(id,
			response => $scope.board = response.data,
			response => alert("Error! Board " + id + " not found!"));
	}
	
	$rootScope.$on("loadBoard", function(event, id) {
        $scope.getBoard(id);
	});
	
	

	$rootScope.$on("setBoard", function(event, json) {
        $scope.board = json;
	});
	

//	$scope.loadBoard = function(json) {
//		console.log(json);
//		$scope.board = json;
//	}
	
	//still need to set success/failure variables for createswimlane?
	$scope.createSwimLane = function(name, boardId) {
		dataService.createSwimLane(name, boardId);
	}
	
	$scope.deleteSwimLane = function(swimLaneId) {
		dataService.deleteSwimLane(swimLaneId);
	}

	$scope.addNewTask = function(newTask, story) {
		dataService.addNewTask(newTask,
				response => {
					console.log("success!");
					console.log(response.data);
					story.tasks.push(response.data);
					$scope.resetNewTask();
				},
				response => console.log("oh nooo!"));
	}
	
	$scope.setNewTaskStory = function(story) {
		$scope.newTask.story = story;
	}
	
	$scope.resetNewTask = function() {
		$scope.newTask.name = "";
		$scope.newTask.story = null;

	}
})