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

.controller("storyCtrl", function($scope, dataService) {
	$scope.editing = false;
	$scope.newTask = {
				name: "",
				taskCompleted: false,
				story: $scope.story
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
	
	dataService.getSwimLanes(function(swimLanes) {
		$scope.otherSwimLanes = swimLanes.filter(function(obj) {
			return obj.id != $scope.swimLane.id;
		})
	});

	$scope.createTask = function() {
		if ($scope.newTask.name) {
			$scope.newTask.order = ($scope.story.tasks) ? $scope.story.tasks.length : 0;
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
					$scope.resolveStoryOrdering();
				},
				response => console.log("could not delete!"));
	}
	
	
	
	
	$scope.moveUp = function() {
		var stories = $scope.swimLane.stories;
		var index = stories.findIndex(function(obj) {
			return obj.order == $scope.story.order;
		});
		if (index > 0) {
			dataService.swapOrders(stories, index, index-1);
			
			var toSave = [stories[index], stories[index-1]];
			$scope.saveStories(toSave, response => {
					stories.sort(function compare(a,b) {
					  if (a.order < b.order)
						     return -1;
						  if (a.order > b.order)
						    return 1;
						  return 0;
						})
				}, response => {
					console.log("could not update stories!");
					dataService.swapOrders(stories, index, index-1);
			});
		}
	}
	
	$scope.moveDown = function() {
		var stories = $scope.swimLane.stories;
		var index = stories.findIndex(function(obj) {
			return obj.order == $scope.story.order;
		});
		if (index > -1 && index < stories.length - 1) {
			dataService.swapOrders(stories, index, index+1);
			
			var toSave = [stories[index], stories[index+1]];
			$scope.saveStories(toSave, response => {
					stories.sort(function compare(a,b) {
					  if (a.order < b.order)
						     return -1;
						  if (a.order > b.order)
						    return 1;
						  return 0;
						})
				}, response => {
					console.log("could not update stories!");
					dataService.swapOrders(stories, index, index+1);
			});
		}
	}
	
	$scope.saveStories = function(stories, success, failure) {
		var storiesDTO = [];
		stories.forEach(function(story) {
			var storyDTO = {};
			Object.assign(storyDTO, story);
			storyDTO.swimLane = $scope.swimLane;
			storiesDTO.push(storyDTO);
		})
		dataService.editStories(storiesDTO, success, failure);
	}
	
	
	
	$scope.moveTo = function(otherSwimLane) {
		toSave = [];
		
		// Adding story to move
		var storyDTO = {};
		Object.assign(storyDTO, $scope.story);
		storyDTO.swimLane = otherSwimLane;
		toSave.push(storyDTO);
		
		// Grabbing all stories in current swimlane to update their orders
		var array = $scope.swimLane.stories.find(function(obj) {
				return obj != $scope.story;
			});
		if (array) {
			array.forEach(function(story) {
				var storyDTO = {};
				Object.assign(storyDTO, story);
				storyDTO.order = $scope.swimLane.stories.findIndex(function(obj) {
					return obj == story;
				});
				storyDTO.swimLane = $scope.swimLane;
				toSave.push(storyDTO);
			})
		}
		
		dataService.editStories(toSave, response => {
			$scope.swimLane.stories = $scope.swimLane.stories.filter(function(obj) {
				return obj.id !== $scope.story.id;
			});
			
			if ($scope.swimLane.stories) {
				$scope.swimLane.stories.forEach( function(story) {
					story.order = $scope.swimLane.stories.findIndex(function(obj) {
						return obj == story;
					});
				});
			}
			
			if (otherSwimLane.stories) {
				$scope.story.order = otherSwimLane.stories.length;
				otherSwimLane.stories.push($scope.story);
			} else {
				$scope.story.order = 0;
				otherSwimLane.stories = [$scope.story];
			}
			
			$scope.swimLane = otherSwimLane;
		    $(".modal-backdrop").remove();
		}, response => console.log("oops"));
		
		
//		$scope.swimLane.stories = $scope.swimLane.stories.filter(function(obj) {
//			return obj.id !== $scope.story.id;
//		});
//		if (otherSwimLane.stories) {
//			$scope.story.order = otherSwimLane.stories.length;
//			otherSwimLane.stories.push($scope.story);
//		} else {
//			$scope.story.order = 0;
//			otherSwimLane.stories = [$scope.story];
//		}
//		$scope.resolveStoryOrdering();
//		$scope.swimLane = otherSwimLane;
//		$scope.save();
		
	}
	

	
	$scope.resolveStoryOrdering = function() {
		if ($scope.swimLane.stories) {
			$scope.swimLane.stories.forEach( function(story) {
				story.order = $scope.swimLane.stories.findIndex(function(obj) {
					return obj == story;
				});
			});
			
			$scope.saveStories($scope.swimLane.stories,
					response=>{}, response=>console.log("error"));
		}
	}
	
	//get current time with Date.now() if you want to make the JS to put date into the Story instead of the controller
	$scope.markAsFinsihed = function() {
		console.log("This story is finished!");
		dataService.markStoryAsFinished($scope.story,
				response => {
					console.log("marking complete!");
					$scope.swimLane.stories = $scope.swimLane.stories.filter(function(obj) {
						return obj.id !== $scope.story.id;
					});
					$scope.resolveStoryOrdering();
				},
				response => console.log("could not mark as finished!"));
	}
	
})