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
	$scope.newUser = {
			username: "",
			password: ""
	};
	
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
//		$rootScope.$emit("setBoard", board);
		dataService.setBoard(board);
	}
	
	$rootScope.$on("getBoardById", function(event, id) {
		return $scope.boards.find(function(board) {
			return board.id == id;
		});
    });
	
	$scope.loadRegisterFragment = function(success,failure){
		$rootScope.$emit("updateFragment","registerUser");
	}
	$scope.register = function(uname,pword){
		$scope.newUser.username = uname;
		$scope.newUser.password = pword;
		dataService.register($scope.newUser);
		$rootScope.$emit("updateFragment","login");
	}
})