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
	
	$rootScope.$on("setBoards", function(event, boards) {
		$scope.boards = boards;
    });
	
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



	//create board
	$scope.createBoard = function(b) {
			dataService.createBoard(b, response => {
				
				$rootScope.$emit("loadDropdown", {});
				$rootScope.$emit("updateFragment", "board");
			});
		
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