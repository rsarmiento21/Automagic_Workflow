/**
 *  Angular JS for Board fragment
 */

angular.module("scrumApp")

.controller("boardCtrl", function($scope, $rootScope, dataService) {
	$scope.oldBoard = null;
	$scope.board = null;
	$scope.rename = false;
	$scope.listOfBoards = null;
	
	$scope.b = {
			id: 0
		};
		
	
	$scope.getBoard = function(id) {
		dataService.getBoard(id,
			response => {
				$scope.board = response.data;
			},
			response => alert("Error! Board " + id + " not found!"));
	}
	
	$rootScope.$on("loadBoard", function(event, id) {
        $scope.getBoard(id);
	});
	
	

	$rootScope.$on("setBoard", function(event, json) {
        $scope.board = json;
		$scope.board.swimLanes.sort(function compare(a,b) {
			  if (a.order < b.order)
				     return -1;
				  if (a.order > b.order)
				    return 1;
				  return 0;
				});
	});
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
		boardDTO.board = $scope.board;
		dataService.editBoard(boardDTO,
				response => {
					console.log("success edit!");
				},
				response => console.log("could not edit!"));
	}
	
	
	
	$scope.createSwimLane = function(name, boardId) {
		dataService.createSwimLane(name, boardId);
	}
	

	
//	$scope.goToDeleteBoard=function(){
//		dataService.goToDeleteBoard();
//	}
	
	$scope.deleteBoard = function(b) {
		if (!$scope.submitted) {
			console.log("deleting board");
			$scope.submitted = true;
			dataService.deleteBoard(b,  response => {
				
				$rootScope.$emit("loadDropdown", {});
				$rootScope.$emit("updateFragment", "board");
			});
		}
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
		console.log(holdStories);
		
		domloaded(holdStories);
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