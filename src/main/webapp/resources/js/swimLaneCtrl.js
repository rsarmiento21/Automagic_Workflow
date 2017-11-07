/**
 *  Angular JS for Swim Lane Controller
 */

angular.module("scrumApp")

.directive("scrumSwimLane", function() {
	return {
		restrict: 'E',
		scope: { swimLane: "=", board: "=" },
		templateUrl: 'resources/html/swimLane.html',
	};
})

.controller("swimLaneCtrl", function($scope, dataService) {
	$scope.editing = false;
	$scope.newStory = {
			title: "",
			description: "",
			points: 1,
			swimLane: $scope.swimLane
		};
	if ($scope.swimLane.stories) {
		$scope.swimLane.stories.sort(function compare(a,b) {
		  if (a.order < b.order)
			     return -1;
			  if (a.order > b.order)
			    return 1;
			  return 0;
			})
	}

	$scope.deleteSwimLane = function() {
		dataService.deleteSwimLane($scope.swimLane, response => {
			console.log("success delete!");
			$scope.board.swimLanes = $scope.board.swimLanes.filter(function(obj) {
				return obj.id !== $scope.swimLane.id;
			});
			$scope.resolveSwimLaneOrdering();
		},
		response => console.log("could not delete!"));
	}
	
	$scope.editSwimLane = function(updatedName) {
		if ($scope.swimLane.name !== updatedName) {
			$scope.swimLane.name = updatedName;
			$scope.save();
		}
	}
	
	$scope.save = function() {
		console.log("Saving swimLane changes");
		var swimlaneDTO = {};
		Object.assign(swimlaneDTO, $scope.swimLane);
		swimlaneDTO.board = $scope.board;
		dataService.editSwimLane(swimlaneDTO,
				response => {
					console.log("success edit!");
				},
				response => console.log("could not edit!"));
	}
	
	$scope.$watch("newStory.points", function(newValue, oldValue) {
		var points = Math.max(1, Math.min(newValue, 10));
		$scope.newStory.points = isNaN(points) ? 1 : points;
	})
	
	$scope.createStory = function() {
		if ($scope.newStory.title) {
			$scope.newStory.order = ($scope.swimLane.stories) ? $scope.swimLane.stories.length : 0;
			dataService.createStory($scope.newStory,
				response => {
					console.log("success add!");
					if ($scope.newStory.swimLane.stories) {
						$scope.newStory.swimLane.stories.push(response.data);
					} else {
						$scope.newStory.swimLane.stories = [response.data];
					}
					$scope.resetNewStory();
				},
				response => console.log("could not add!"));
		}
	}
	
	$scope.resetNewStory = function() {
		$scope.newStory.title = "";
		$scope.newStory.description = "";
		$scope.newStory.points = 1;
		$scope.newStory.order = ($scope.swimLane.stories) ? $scope.swimLane.stories.length : 0;
	}
	
	$scope.moveLeft = function() {
		var swimLanes = $scope.board.swimLanes;
		var index = swimLanes.findIndex(function(obj) {
			return obj.order == $scope.swimLane.order;
		});
		if (index > 0) {
			dataService.swapOrders(swimLanes, index, index-1);
			
			var toSave = [swimLanes[index], swimLanes[index-1]];
			$scope.saveSwimLanes(toSave, response => {
					swimLanes.sort(function compare(a,b) {
					  if (a.order < b.order)
						     return -1;
						  if (a.order > b.order)
						    return 1;
						  return 0;
						})
				}, response => {
					console.log("could not update tasks!");
					dataService.swapOrders(swimLanes, index, index-1);
			});
		}
	}
	
	$scope.moveRight = function() {
		var swimLanes = $scope.board.swimLanes;
		var index = swimLanes.findIndex(function(obj) {
			return obj.order == $scope.swimLane.order;
		});
		if (index > -1 && index < swimLanes.length - 1) {
			dataService.swapOrders(swimLanes, index, index+1);
			
			var toSave = [swimLanes[index], swimLanes[index+1]];
			$scope.saveSwimLanes(toSave, response => {
					swimLanes.sort(function compare(a,b) {
					  if (a.order < b.order)
						     return -1;
						  if (a.order > b.order)
						    return 1;
						  return 0;
						})
				}, response => {
					console.log("could not update tasks!");
					dataService.swapOrders(swimLanes, index, index+1);
			});
		}
	}
	
	$scope.saveSwimLanes = function(swimLanes, success, failure) {
		var swimlanesDTO = [];
		swimLanes.forEach(function(sl) {
			var slDTO = {};
			Object.assign(slDTO, sl);
			slDTO.board = $scope.board;
			swimlanesDTO.push(slDTO);
		})
		dataService.editSwimLanes(swimlanesDTO, success, failure);
	}
	
	$scope.resolveSwimLaneOrdering = function() {
		if ($scope.board.swimLanes) {
			$scope.board.swimLanes.forEach( function(swimLane) {
				swimLane.order = $scope.board.swimLanes.findIndex(function(obj) {
					return obj == swimLane;
				});
			});
		
			$scope.saveSwimLanes($scope.board.swimLanes,
					response=>{}, response=>console.log("error"));
		}
	}
})