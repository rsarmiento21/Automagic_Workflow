/**
 *  Angular controller for the SCRUM Navbar
 */

angular.module("scrumApp")

.directive("scrumNavbar", function() {
	return {
		templateUrl: "resources/html/navbar.html",
	};
})

.controller("navbarCtrl", function($scope, $rootScope, dataService) {
	$scope.boards = {};
	$scope.loggedIn = false;

	$scope.init = function() {
		dataService.registerLoginObserver(isLoggedIn => {
			$scope.loggedIn = isLoggedIn;
			if (isLoggedIn) {
				$scope.getBoards();
			}
		})
		dataService.checkLogin();
	}
	
	$scope.logout = function() {
		dataService.logout(response => {}, response => console.log(response.data[0]));
	}
	
//	$rootScope.$on("toggleLogin", function() {
//		$scope.loggedIn = !$scope.loggedIn;
//    });
//	
//	$rootScope.$on("loadDropdown", function() {
//		$scope.getBoards();
//    });
//	
//	$rootScope.$on("setBoards", function(event, boards) {
//		$scope.boards = boards;
//    });
	
	$scope.getBoards = function() {
		dataService.getBoards(
			response => {
				$scope.boards = response.data;
				$scope.boards.sort(function compare(a,b) {
					  if (a.id < b.id)
					     return -1;
					  if (a.id > b.id)
					    return 1;
					  return 0;
					})
			},
			response => console.log(response.data)
		);
	}
	
	$scope.loadBoard = function(board) {
		$rootScope.$emit("setBoard", board);
	}
	
	$rootScope.$on("getBoardById", function(event, id) {
		return $scope.boards.find(function(board) {
			return board.id == id;
		});
    });
})