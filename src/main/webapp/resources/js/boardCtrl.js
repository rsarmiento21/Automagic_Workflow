/**
 *  Angular JS for Board fragment
 */

angular.module("scrumApp")

.controller("boardCtrl", function($scope, $rootScope, dataService) {
	$scope.id = 26;
	$scope.board = {};
	
	$scope.getBoard = function(id) {
		dataService.getBoard(id);
	}
	
	$rootScope.$on("loadBoard", function(event, json) {
        $scope.loadBoard(json);
	});
	
	$scope.loadBoard = function(json) {
		console.log(json);
		$scope.board = json;
	}
	
	$scope.createSwimLane = function(name, boardId) {
		console.log("TRYING TO CREATE SWIM LANE");
		dataService.createSwimLane(name, boardId);
	}
})