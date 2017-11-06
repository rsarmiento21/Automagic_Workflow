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

.controller("swimLaneCtrl", function($scope, $rootScope, dataService) {
	$scope.editing = false;
	$scope.newStory = {
			title: "",
			description: "",
			points: 1,
			swimLane: $scope.swimLane,
			order: ($scope.swimLane.stories) ? $scope.swimLane.stories.length : 0
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
			$rootScope.$emit("resolveSwimLaneOrdering", $scope.board.id);
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
		$scope.newStory.description = "";
		$scope.newStory.points = 1;
		$scope.newStory.order = ($scope.swimLane.stories) ? $scope.swimLane.stories.length : 0;
	}
	

	
	$rootScope.$on("resolveStoryOrdering", function(event, id) {
		if (id == $scope.swimLane.id) {
			$scope.swimLane.stories.forEach( function(story) {
				story.order = $scope.swimLane.stories.findIndex(function(obj) {
					return obj == story;
				});
			});
			$scope.save();
		}
	})

	$rootScope.$on("moveUpStory", function (event, args) {
		if (args[0] == $scope.swimLane.id) {
			var stories = $scope.swimLane.stories;
			var index = stories.findIndex(function(obj) { return obj.order == args[1]; });
			if (index > 0) {
				stories[index].order = stories[index].order + stories[index-1].order;
				stories[index-1].order = stories[index].order - stories[index-1].order;
				stories[index].order = stories[index].order - stories[index-1].order;
				stories.sort(function compare(a,b) {
					  if (a.order < b.order)
						     return -1;
						  if (a.order > b.order)
						    return 1;
						  return 0;
						})
				$scope.swimLane.stories = stories;
				$scope.save();
			}
		}
	})
	
	$rootScope.$on("moveDownStory", function (event, args) {
		if (args[0] == $scope.swimLane.id) {
			var stories = $scope.swimLane.stories;
			var index = stories.findIndex(function(obj) { return obj.order == args[1]; });
			if (index > -1 && index < stories.length - 1) {
				stories[index].order = stories[index].order + stories[index+1].order;
				stories[index+1].order = stories[index].order - stories[index+1].order;
				stories[index].order = stories[index].order - stories[index+1].order;
				
				stories.sort(function compare(a,b) {
					  if (a.order < b.order)
						     return -1;
						  if (a.order > b.order)
						    return 1;
						  return 0;
						})
				$scope.swimLane.stories = stories;
				$scope.save();
			}
		}
	})
	
	$scope.moveLeft = function() {
		$rootScope.$emit("moveLeftSwimLane", [$scope.board.id, $scope.swimLane.order]);
	}
	
	$scope.moveRight = function() {
		$rootScope.$emit("moveRightSwimLane", [$scope.board.id, $scope.swimLane.order]);
	}
})