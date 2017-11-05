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
	
	
	
	$scope.createSwimLane = function(name, boardId) {
		dataService.createSwimLane(name, boardId);
	}
	
	$scope.deleteSwimLane = function(swimLaneId) {
		dataService.deleteSwimLane(swimLaneId);
	}
	
	$scope.editSwimLane = function(swimLaneId, updatedName) {
		dataService.editSwimLane(swimLaneId, updatedName);
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