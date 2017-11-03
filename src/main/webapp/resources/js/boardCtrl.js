/**
 *  Angular JS for Board fragment
 */

angular.module("scrumApp")

.controller("boardCtrl", function($scope, $rootScope, dataService) {
	$scope.id = 26;
	$scope.board = null;
	
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
	
//	$scope.loadBoard = function(json) {
//		console.log(json);
//		$scope.board = json;
//	}
})