/**
 *  Angular JS for Board fragment
 */

angular.module("scrumApp")

.controller("boardCtrl", function($scope, $rootScope, dataService) {
	$scope.oldBoard = null;
	$scope.board = null;
	$scope.rename = false;
	$scope.newSwimLane = {
			name: "",
			board: null,
			order: 0
		};
	
//	$scope.getBoard = function(id) {
//		dataService.getBoard(id,
//			response => {
//				$scope.board = response.data;
//			},
//			response => alert("Error! Board " + id + " not found!"));
//	}
//	
//	$rootScope.$on("loadBoard", function(event, id) {
//        $scope.getBoard(id);
//	});
//	
	
	$rootScope.$on("setBoard", function(event, json) {
        $scope.board = json;
		$scope.board.swimLanes.sort(function compare(a,b) {
			  if (a.order < b.order)
				     return -1;
				  if (a.order > b.order)
				    return 1;
				  return 0;
				});
		$scope.newSwimLane.board = $scope.board;
		$scope.newSwimLane.order = ($scope.board.swimLanes) ? $scope.board.swimLanes.length : 0;
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
		dataService.editBoard(boardDTO,
				response => {
					console.log("success edit!");
				},
				response => console.log("could not edit!"));
	}
	
	
	
	$scope.createSwimLane = function() {

		if ($scope.newSwimLane.name) {
			dataService.createSwimLane($scope.newSwimLane, 
				response => {
					console.log("success add!");
					if ($scope.board.swimLanes) {
						$scope.board.swimLanes.push(response.data);
					} else {
						$scope.board.swimLanes = [response.data];
					}
					$scope.newSwimLane.name = "";
					$scope.newSwimLane.order = ($scope.board.swimLanes) ? $scope.board.swimLanes.length : 0;
				},
				response => console.log("could not add swimLane!"));
		}
	}
	
	$rootScope.$on("resolveSwimLaneOrdering", function() {
		$scope.board.swimLanes.forEach( function(sl) {
			sl.order = $scope.board.swimLanes.findIndex(function(obj) {
				return obj == sl;
			});
		});
		$scope.newSwimLane.order = ($scope.board.swimLanes) ? $scope.board.swimLanes.length : 0;
		$scope.saveBoard();
	})
	
	$rootScope.$on("getSwimLanes", function(event, callback) {
		callback($scope.board.swimLanes);
	})
	
	$rootScope.$on("moveLeftSwimLane", function (event, args) {
		if (args[0] == $scope.board.id) {
			var swimLanes = $scope.board.swimLanes;
			var index = swimLanes.findIndex(function(obj) { return obj.order == args[1]; });
			if (index > 0) {
				swimLanes[index].order = swimLanes[index].order + swimLanes[index-1].order;
				swimLanes[index-1].order = swimLanes[index].order - swimLanes[index-1].order;
				swimLanes[index].order = swimLanes[index].order - swimLanes[index-1].order;
				swimLanes.sort(function compare(a,b) {
					  if (a.order < b.order)
						     return -1;
						  if (a.order > b.order)
						    return 1;
						  return 0;
						})
				$scope.board.swimLanes = swimLanes;
				$scope.saveBoard();
			}
		}
	})
	
	$rootScope.$on("moveRightSwimLane", function (event, args) {
		if (args[0] == $scope.board.id) {
			var swimLanes = $scope.board.swimLanes;
			var index = swimLanes.findIndex(function(obj) { return obj.order == args[1]; });
			if (index > -1 && index < swimLanes.length - 1) {
				swimLanes[index].order = swimLanes[index].order + swimLanes[index+1].order;
				swimLanes[index+1].order = swimLanes[index].order - swimLanes[index+1].order;
				swimLanes[index].order = swimLanes[index].order - swimLanes[index+1].order;
				
				swimLanes.sort(function compare(a,b) {
					  if (a.order < b.order)
						     return -1;
						  if (a.order > b.order)
						    return 1;
						  return 0;
						})
				$scope.board.swimLanes = swimLanes;
				$scope.saveBoard();
			}
		}
	})
	

})