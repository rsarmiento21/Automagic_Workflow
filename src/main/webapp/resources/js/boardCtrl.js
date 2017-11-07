/**
 *  Angular JS for Board fragment
 */

angular.module("scrumApp")

.controller("boardCtrl", function($scope, dataService) {
	$scope.oldBoard = null;
	$scope.board = null;
	$scope.rename = false;

	$scope.listOfBoards = null;
	
	$scope.b = {
			id: 0
		};
		

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
		$scope.oldBoard = jQuery.extend(true, {}, $scope.board);
		$scope.rename = true;
	}
	
	$scope.editBoard = function(updatedName){
		$scope.board.name = updatedName;
		console.log($scope.oldBoard.name + " " + $scope.board.name);
		if ($scope.board.name !== $scope.oldBoard.name){
			$scope.saveBoard();
		}
		$scope.rename = false;		
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
		dataService.deleteBoard(b, response => {});
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
//		console.log("Creating chart from with " + bo.id);
//		//var arrayOfBoards = dataService.getAllStories(bo.id);
//		console.log("TRY AGAIN IDIOT");
//		//console.log(arrayOfBoards);
//		dataService.getAllStories(bo.id, response => {
//			$scope.listOfBoards = response.data;
//		});
//		console.log($scope.listOfBoards);
		//var listOfSwimlanes = 
		
		
	}
	
})