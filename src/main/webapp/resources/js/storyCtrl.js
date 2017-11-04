/**
 *  Angular JS for Task Controller
 */

angular.module("scrumApp")

.directive("scrumStory", function() {
	return {
		restrict: 'E',
		scope: { story: "=", swimLane: "=" },
		templateUrl: 'resources/html/story.html',
	};
})

.controller("storyCtrl", function($scope, $rootScope, dataService) {
	$scope.editing = false;
	$scope.newTask = {
				name: "",
				taskCompleted: false,
				story: $scope.story
			};

	$scope.createTask = function() {
		if ($scope.newTask.name !== "") {
			dataService.createTask($scope.newTask,
				response => {
					console.log("success add!");
					$scope.story.tasks.push(response.data);
					$scope.resetNewTask();
				},
				response => console.log("could not add!"));
		}
	}
	
	$scope.resetNewTask = function() {
		$scope.newTask.name = "";
	}
	
	$scope.edit = function() {
		$scope.oldStory = {};
		Object.assign($scope.oldStory, $scope.story);
		$scope.editing = true;
	}
	
	$scope.$watch("story.points", function(newValue, oldValue) {
		var points = Math.max(1, Math.min(newValue, 10));
		$scope.story.points = isNaN(points) ? 1 : points;
	})
	
	$scope.update = function() {
		if ($scope.story.title !== $scope.oldStory.title ||
				$scope.story.description !== $scope.oldStory.description ||
				$scope.story.points !== $scope.oldStory.points) {
			$scope.save();
		}
		$scope.editing = false;
	}
	
	$scope.reset = function() {
		$scope.story = {};
		Object.assign($scope.story, $scope.oldStory);
		$scope.editing = false;
	}
	
	$scope.save = function() {
		console.log("Saving story changes");
		var storyDTO = {};
		Object.assign(storyDTO, $scope.story);
		storyDTO.swimLane = $scope.swimLane;
		dataService.editStory(storyDTO,
				response => {
					console.log("success edit!");
				},
				response => console.log("could not edit!"));
	}
	
	$scope.delete = function() {
		console.log("Deleting story");
		dataService.deleteStory($scope.story,
				response => {
					console.log("success delete!");
					$scope.swimLane.stories = $scope.swimLane.stories.filter(function(obj) {
						return obj.id !== $scope.story.id;
					});
				},
				response => console.log("could not delete!"));
	}
})