/**
 *  Angular JS for Board fragment
 */

angular.module("scrumApp")

.controller("boardCtrl", function($scope, dataService) {
	$scope.oldBoardName = "";
	$scope.board = null;
	
	$scope.newSwimLane = {
			name: "",
			board: null,
			order: 0
		};

	
	$scope.init = function() {
		dataService.registerBoardObserver(function(board) {
			$scope.board = board;
			if ($scope.board && $scope.board.swimLanes) {
				$scope.board.swimLanes.sort(function compare(a,b) {
					  if (a.order < b.order)
						     return -1;
						  if (a.order > b.order)
						    return 1;
						  return 0;
						});
			}
			$scope.newSwimLane.board = $scope.board;
		});
	}
	
	$scope.renameBoard = function(){
		$scope.oldBoardName = jQuery.extend(true, {}, $scope.board.name);
	}
	
	$scope.editBoard = function() {
		console.log($scope.oldBoardName + " " + $scope.board.name);
		if ($scope.board.name !== $scope.oldBoardName){
			$scope.saveBoard();
		}	
	}
	
	$scope.resetEditBoard = function() {
		$scope.board.name = jQuery.extend(true, {}, $scope.oldBoardName);
	}
	
	$scope.saveBoard = function(){
		console.log("Saving Board Changes")
		var boardDTO = {};
		Object.assign(boardDTO, $scope.board);
		boardDTO.owner = null;
		dataService.editBoard(boardDTO,
				response => {
					console.log("success edit!");
				},
				response => console.log("could not edit!"));
	}
	
	
	
	$scope.createSwimLane = function() {
		if ($scope.newSwimLane.name) {
			$scope.newSwimLane.order = ($scope.board.swimLanes) ? $scope.board.swimLanes.length : 0;
			dataService.createSwimLane($scope.newSwimLane, 
				response => {
					console.log("success add!");
					if ($scope.board.swimLanes) {
						$scope.board.swimLanes.push(response.data);
					} else {
						$scope.board.swimLanes = [response.data];
					}
					$scope.newSwimLane.name = "";
				},
				response => console.log("could not add swimLane!"));
		}
	}

	
//	$scope.goToDeleteBoard=function(){
//		dataService.goToDeleteBoard();
//	}
	
	$scope.deleteBoard = function(b) {
		console.log("deleting board");
		dataService.deleteBoard(b, response => {
		    $(".modal-backdrop").remove();
		});
	}
	
	
	$scope.createChart = function(bo){
		var holdStories = [];
		console.log(bo.swimLanes.length);
		for(var i = 0; i < bo.swimLanes.length;i++){
			var sl = bo.swimLanes[i];//get individual swimlanes
			console.log(sl);
			for(var k = 0; k < sl.stories.length; k++){
				holdStories.push(sl.stories[k]);
				
			}
		}
		
		domloaded(holdStories, bo);
		
		
	}
	
})