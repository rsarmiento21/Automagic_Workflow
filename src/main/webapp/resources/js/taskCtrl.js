/**
 *  Angular JS for Task Controller
 */

angular.module("scrumApp")

.directive("scrumTask", function() {
	return {
		restrict: 'E',
		scope: { task: "=", story: "=" },
		templateUrl: 'resources/html/task.html'
	};
})

.controller("taskCtrl", function($scope, $rootScope, dataService) {
	$scope.editing = false;
	
	$scope.edit = function() {
		$scope.oldTask = {};
		Object.assign($scope.oldTask, $scope.task);
		$scope.editing = true;
	}
	
	$scope.check = function() {
		if (!$scope.editing) {
			$scope.save();
		}
	}
	
	$scope.update = function() {
		if ($scope.task.name !== $scope.oldTask.name ||
				$scope.task.taskCompleted !== $scope.oldTask.taskCompleted) {
			$scope.save();
		}
		$scope.editing = false;
	}
	
	$scope.reset = function() {
		$scope.task = {};
		Object.assign($scope.task, $scope.oldTask);
		$scope.editing = false;
	}
	
	$scope.save = function() {
		console.log("Saving task changes");
		var taskDTO = {};
		Object.assign(taskDTO, $scope.task);
		taskDTO.story = $scope.story;
		dataService.editTask(taskDTO,
				response => {
					console.log("success edit!");
				},
				response => console.log("could not edit!"));
	}
	
	$scope.delete = function() {
		console.log("Deleting task");
		dataService.deleteTask($scope.task,
				response => {
					console.log("success delete!");
					$scope.story.tasks = $scope.story.tasks.filter(function(obj) {
						return obj.id !== $scope.task.id;
					});
					$rootScope.$emit("resolveTaskOrdering", $scope.story.id);
				},
				response => console.log("could not delete!"));
	}
	
	$scope.moveUp = function() {
		$rootScope.$emit("moveUpTask", [$scope.story.id, $scope.task.order]);
	}
	
	$scope.moveDown = function() {
		$rootScope.$emit("moveDownTask", [$scope.story.id, $scope.task.order]);
	}
})