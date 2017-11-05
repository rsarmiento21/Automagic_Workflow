/**
 *  Angular JS for Board fragment
 */

angular.module("scrumApp")

.controller("boardCtrl", function($scope, $rootScope, dataService) {
	$scope.board = null;
	$scope.rename = false;
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
	$scope.renameBoard = function(event){
		$scope.rename = true;
	}
	$scope.editBoard = function(event,id){
		$scope.board.name = id;
		$scope.rename = false;
		
	}
	
	
	
	$scope.createSwimLane = function(name, boardId) {
		dataService.createSwimLane(name, boardId);
	}
	
	$scope.deleteSwimLane = function(swimLaneId) {
		dataService.deleteSwimLane(swimLaneId);
	}
	
	$scope.editSwimLane = function(swimLaneId, updatedName) {
		dataService.editSwimLane(swimLaneId, updatedName);
	}

})