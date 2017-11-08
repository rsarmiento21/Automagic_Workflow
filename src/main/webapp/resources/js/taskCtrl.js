/**
 *  Angular JS for Task Controller
 */

angular.module("scrumApp")

.directive("scrumTask", function() {
	return {
		restrict: 'E',
		scope: { task: "=", story: "=", storyCtrl: "=" },
		templateUrl: 'resources/html/task.html'
	};
})

.controller("taskCtrl", function($scope, dataService) {
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
		var taskDTO = {};
		Object.assign(taskDTO, $scope.task);
		taskDTO.story = $scope.story;
		dataService.editTask(taskDTO,
				response => {},
				response => alert("Could not save task changes!"));
	}
	
	$scope.delete = function() {
		dataService.deleteTask($scope.task,
				response => {
				    $(".modal-backdrop").remove();
					$scope.story.tasks = $scope.story.tasks.filter(function(obj) {
						return obj.id !== $scope.task.id;
					});
					$scope.resolveTaskOrdering();
				},
				response => alert("Could not delete task!"));
	}
	
	$scope.moveUp = function() {
		var tasks = $scope.story.tasks;
		var index = tasks.findIndex(function(obj) {
			return obj.order == $scope.task.order;
		});
		if (index > 0) {
			dataService.swapOrders(tasks, index, index-1);
			
			var toSave = [tasks[index], tasks[index-1]];
			$scope.saveTasks(toSave, response => {
					tasks.sort(function compare(a,b) {
					  if (a.order < b.order)
						     return -1;
						  if (a.order > b.order)
						    return 1;
						  return 0;
						})
				}, response => {
					alert("Could not save task changes!");
					dataService.swapOrders(tasks, index, index-1);
			});
		}
	}
	
	$scope.moveDown = function() {
		var tasks = $scope.story.tasks;
		var index = tasks.findIndex(function(obj) {
			return obj.order == $scope.task.order;
		});
		if (index > -1 && index < tasks.length - 1) {
			dataService.swapOrders(tasks, index, index+1);
			
			var toSave = [tasks[index], tasks[index+1]];
			$scope.saveTasks(toSave, response => {
					tasks.sort(function compare(a,b) {
					  if (a.order < b.order)
						     return -1;
						  if (a.order > b.order)
						    return 1;
						  return 0;
						})
				}, response => {
					alert("Could not save task changes!");
					dataService.swapOrders(tasks, index, index+1);
			});
		}
	}
	
	$scope.saveTasks = function(tasks, success, failure) {
		var tasksDTO = [];
		tasks.forEach(function(task) {
			var taskDTO = {};
			Object.assign(taskDTO, task);
			taskDTO.story = $scope.story;
			tasksDTO.push(taskDTO);
		})
		dataService.editTasks(tasksDTO, success, failure);
	}
	
	$scope.resolveTaskOrdering = function() {
		if ($scope.story.tasks) {
			$scope.story.tasks.forEach( function(task) {
				task.order = $scope.story.tasks.findIndex(function(obj) {
					return obj == task;
				});
			});
		
			$scope.saveTasks($scope.story.tasks,
					response=>{}, response=>alert("Could not save new task order!"));
		}
	}
})