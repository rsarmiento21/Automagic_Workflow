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
		if ($scope.board.name !== $scope.oldBoardName){
			$scope.saveBoard();
		}	
	}
	
	$scope.resetEditBoard = function() {
		$scope.board.name = jQuery.extend(true, {}, $scope.oldBoardName);
	}
	
	$scope.saveBoard = function(){
		var boardDTO = {};
		Object.assign(boardDTO, $scope.board);
		boardDTO.owner = null;
		dataService.editBoard(boardDTO,
				response => {},
				response => alert("Could not save board changes!"));
	}
	
	
	
	$scope.createSwimLane = function() {
		if ($scope.newSwimLane.name) {
			$scope.newSwimLane.order = ($scope.board.swimLanes) ? $scope.board.swimLanes.length : 0;
			dataService.createSwimLane($scope.newSwimLane, 
				response => {
					if ($scope.board.swimLanes) {
						$scope.board.swimLanes.push(response.data);
					} else {
						$scope.board.swimLanes = [response.data];
					}
					$scope.newSwimLane.name = "";
				},
				response => alert("Could not save new SwimLane!"));
		}
	}

	
//	$scope.goToDeleteBoard=function(){
//		dataService.goToDeleteBoard();
//	}
	
	$scope.deleteBoard = function(b) {
		dataService.deleteBoard(b, response => {
		    $(".modal-backdrop").remove();
		}, response => alert("Could not delete board!"));
	}
	
	
	$scope.createChart = function(bo){
		var holdStories = [];
		for(var i = 0; i < bo.swimLanes.length;i++){
			var sl = bo.swimLanes[i];//get individual swimlanes
			for(var k = 0; k < sl.stories.length; k++){
				holdStories.push(sl.stories[k]);
				
			}
		}
		
		domloaded(holdStories, bo);
		
		
	}
	
})