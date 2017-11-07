/**
 *  Angular JS for Story Controller
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
				story: $scope.story,
				order: ($scope.story.tasks) ? $scope.story.tasks.length : 0
			};
	if ($scope.story.tasks) {
		$scope.story.tasks.sort(function compare(a,b) {
		  if (a.order < b.order)
			     return -1;
			  if (a.order > b.order)
			    return 1;
			  return 0;
		})
	}
	
	$rootScope.$emit("getSwimLanes", function(swimLanes) {
		$scope.otherSwimLanes = swimLanes.filter(function(obj) {
			return obj.id != $scope.swimLane.id;
		})
	})
	

	$scope.createTask = function() {
		if ($scope.newTask.name) {
			dataService.createTask($scope.newTask,
				response => {
					console.log("success add!");
					if ($scope.story.tasks) {
						$scope.story.tasks.push(response.data);
					} else {
						$scope.story.tasks = [response.data];
					}
					$scope.resetNewTask();
				},
				response => console.log("could not add!"));
		}
	}
	
	$rootScope.$on("resolveTaskOrdering", function(event, id) {
		if (id == $scope.story.id) {
			$scope.story.tasks.forEach( function(task) {
				task.order = $scope.story.tasks.findIndex(function(obj) {
					return obj == task;
				});
			});
			$scope.save();
		}
	})
	
	$scope.resetNewTask = function() {
		$scope.newTask.name = "";
		$scope.newTask.order =  ($scope.story.tasks) ? $scope.story.tasks.length : 0;
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
	
	$scope.closeModal = function() {
		$scope.resetNewTask();
		if ($scope.editing) {
			$scope.reset();
		}
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
					$rootScope.$emit("resolveSwimLaneOrdering", $scope.swimLane.id);
				},
				response => console.log("could not delete!"));
	}
	
	$rootScope.$on("moveUpTask", function (event, args) {
		if (args[0] == $scope.story.id) {
			var tasks = $scope.story.tasks;
			var index = tasks.findIndex(function(obj) { return obj.order == args[1]; });
			if (index > 0) {
				tasks[index].order = tasks[index].order + tasks[index-1].order;
				tasks[index-1].order = tasks[index].order - tasks[index-1].order;
				tasks[index].order = tasks[index].order - tasks[index-1].order;
				tasks.sort(function compare(a,b) {
					  if (a.order < b.order)
						     return -1;
						  if (a.order > b.order)
						    return 1;
						  return 0;
						})
				$scope.story.tasks = tasks;
				$scope.save();
			}
		}
	})
	
	$rootScope.$on("moveDownTask", function (event, args) {
		if (args[0] == $scope.story.id) {
			var tasks = $scope.story.tasks;
			var index = tasks.findIndex(function(obj) { return obj.order == args[1]; });
			if (index > -1 && index < tasks.length - 1) {
				tasks[index].order = tasks[index].order + tasks[index+1].order;
				tasks[index+1].order = tasks[index].order - tasks[index+1].order;
				tasks[index].order = tasks[index].order - tasks[index+1].order;
				
				tasks.sort(function compare(a,b) {
					  if (a.order < b.order)
						     return -1;
						  if (a.order > b.order)
						    return 1;
						  return 0;
						})
				$scope.story.tasks = tasks;
				$scope.save();
			}
		}
	})
	
	$scope.moveUp = function() {
		$rootScope.$emit("moveUpStory", [$scope.swimLane.id, $scope.story.order]);
	}
	
	$scope.moveDown = function() {
		$rootScope.$emit("moveDownStory", [$scope.swimLane.id, $scope.story.order]);
	}
	
	$scope.moveTo = function(otherSwimLane) {
	    $("#modal" + $scope.story.id).removeClass("in");
	    $(".modal-backdrop").remove();
	    $("#modal" + $scope.story.id).hide();
		$scope.swimLane.stories = $scope.swimLane.stories.filter(function(obj) {
			return obj.id !== $scope.story.id;
		});
		if (otherSwimLane.stories) {
			$scope.story.order = otherSwimLane.stories.length;
			otherSwimLane.stories.push($scope.story);
		} else {
			$scope.story.order = 0;
			otherSwimLane.stories = [$scope.story];
		}
		$rootScope.$emit("resolveSwimLaneOrdering", $scope.swimLane.id);
		
	}
})