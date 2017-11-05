/**
 *  Angular JS for Board fragment
 */

angular.module("scrumApp")

.controller("boardCtrl", function($scope, $rootScope, dataService) {
	$scope.board = null;
	$scope.newStory = {
				title: "",
				description: "",
				points: 1,
				swimLane: null
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
	
	
	
	$scope.setNewStorySwimLane = function(swimLane) {
		$scope.newStory.swimLane = swimLane;
	}
	
	$scope.$watch("newStory.points", function(newValue, oldValue) {
		var points = Math.max(1, Math.min(newValue, 10));
		$scope.newStory.points = isNaN(points) ? 1 : points;
	})
	
	$scope.createStory = function() {
		if ($scope.newStory.title && $scope.newStory.description) {
			dataService.createStory($scope.newStory,
				response => {
					console.log("success add!");
					$scope.newStory.swimLane.stories.push(response.data);
					$scope.resetNewStory();
				},
				response => console.log("could not add!"));
		}
	}
	
	$scope.resetNewStory = function() {
		$scope.newStory.title = "";
		$scope.newStory.desc = "";
		$scope.newStory.points = 1;
	}

})