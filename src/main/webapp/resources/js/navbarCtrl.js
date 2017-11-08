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
		dataService.registerBoardsObserver(boards => {
			$scope.boards = boards;
		})
		dataService.checkLogin();
	}
	
	$scope.$watch(function(){ return dataService.boards; }, function(value) {
		$scope.boards = value;
	})
	
	$scope.logout = function() {
		dataService.logout(response => {}, response => alert("Error logging out!"));
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
			response => alert("Error fetching boards!")
		);
	}
	
	$scope.loadBoard = function(board) {
		dataService.setBoard(board);
	}


	$scope.createBoard = function() {
		if ($scope.b.name) {
			dataService.createBoard($scope.b, response => {
				$scope.b.name = "";
			});
		}		
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
		dataService.register($scope.newUser,
				response => $rootScope.$emit("updateFragment","login"),
				response => alert("Could not register new user"));
	}

})