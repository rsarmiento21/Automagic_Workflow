/**
 *  Angular JS for DataService service
 */

angular.module("scrumApp")

.service("dataService", function($http, $window, $rootScope) {
	var ds = this;
	var root = $rootScope;
	
	ds.login = function(bu, $scope) {
		var promise = $http.post("login", bu).then(
			function success(response) {
				root.$emit("toggleLogin", {});
				root.$emit("updateFragment", "board");
			},
			function error(response) {
				if (response.status == 409) {
					$scope.errorMessage = response.data[0];
				} else {
					$scope.errorMessage = "Error adding user.";
				}
				$scope.error = true;
				$scope.submitted = false;
			}
		)
	}
	
	ds.isLoggedIn = function() {
		var promise = $http.get("ajaxIsLoggedIn").then(
			function success(response) {
				return response.data;
			},
			function error(response) {
				return false;
			}
		)
	}
	
	ds.logout = function() {
		var promise = $http.get("logout").then(
			function success(response) {
				root.$emit("toggleLogin", {});
				root.$emit("updateFragment", "login");
			},
			function error(response) {
				console.log(response.data[0]);
			}
		)
	}
	
	ds.getBoards = function($scope) {
		var promise = $http.get("dropdown").then(
			function success(response) {
				console.log(response.data);
				root.$emit(response.data);
			},
			function error(response) {
				console.log(response.data);
			}
		)
	}
	
	ds.getBoard = function(id) {
		var promise = $http.get("ajax/board/" + id).then(
			function success(response) {
				root.$emit("loadBoard", response.data);
			},
			function error(response) {
				console.log("Error! Board " + id + " not found!");
				return null;
			}
		)
	}
	
	ds.setLoadState = function() {
		if (ds.isLoggedIn()) {
			root.$emit("toggleLogin", {});
			root.$emit("updateFragment", "board");
		} else {
			root.$emit("updateFragment", "login");
		}
	}
	
	ds.createSwimLane = function(name, boardId) {
		console.log("BEFORE PROMISE");
		var promise = $http.get("ajax/swimlane/create/" + name).then(
			function success(response) {
//				root.$emit("")
				//just log for now, testing
				console.log("success:test create swimlane");
			},
			function error(response) {
				console.log("fail:test create swimlane");
			}
		)
	}
})