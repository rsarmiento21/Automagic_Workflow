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
	
	$scope.b = {
			id: 0
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
	
//	$scope.goToDeleteBoard=function(){
//		dataService.goToDeleteBoard();
//	}
	
	$scope.deleteBoard = function(b) {
		if (!$scope.submitted) {
			console.log("deleting board");
			$scope.submitted = true;
			dataService.deleteBoard(b,  response => {
				
				$rootScope.$emit("loadDropdown", {});
				$rootScope.$emit("updateFragment", "board");
			});
		}
	}
	
	
	
})