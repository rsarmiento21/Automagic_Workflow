/**
 *  Angular JS for Task Controller
 */

angular.module("scrumApp")

.controller("taskCtrl", function($scope, $rootScope, dataService) {
	$scope.editing = false;
	$scope.thisTask = null;
	$scope.oldTask = null;
	
	$scope.taskInit = function(task) {
		$scope.thisTask = task;
	}
	
	$scope.edit = function() {
		$scope.oldTask = {};
		Object.assign($scope.oldTask, $scope.thisTask);
		$scope.editing = true;
	}
	
	$scope.reset = function() {
		$scope.thisTask = {};
		Object.assign($scope.thisTask, $scope.oldTask);
		$scope.editing = false;
	}
	
	$scope.save = function() {
		console.log($scope.thisTask);
		$scope.editing = false;
	}
	
	$scope.check = function() {
		if (!$scope.editing) {
			$scope.save($scope.thisTask);
		}
	}
})