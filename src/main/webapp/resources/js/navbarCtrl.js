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
	//set board to create
	$scope.b = {
			name: ""
	};
	
	$scope.init = function() {
		dataService.isLoggedIn(response => {
			if (response.data) {
				$rootScope.$emit("toggleLogin", {});
				$rootScope.$emit("loadDropdown", {});
			}
		});
	}
	
	$scope.logout = function() {
		dataService.logout(
				response => {
					$scope.loggedIn = !$scope.loggedIn;
					$rootScope.$emit("updateFragment", "login");
				},
				response => console.log(response.data[0])
		);
	}
	
	$rootScope.$on("toggleLogin", function() {
		$scope.loggedIn = !$scope.loggedIn;
    });
	
	$rootScope.$on("loadDropdown", function() {
		$scope.getBoards();
    });
	
	$scope.getBoards = function() {
		dataService.getBoards(
			response => $scope.boards = response.data,
			response => console.log(response.data)
		);
	}
	
	$scope.loadBoard = function(board) {
		$rootScope.$emit("setBoard", board);
	}
	$scope.loadBoard = function(id){
		console.log(id);
		$rootScope.$emit("loadBoard", id);
	}
	//create board
	$scope.createBoard = function(b) {
		if (!$scope.submitted) {
			//console.log("creating board");
			$scope.submitted = true;
			dataService.createBoard(b, $scope);
		}
	}
})