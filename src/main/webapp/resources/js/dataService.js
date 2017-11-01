/**
 * 
 */

angular.module("scrumApp")

.service("dataService", function($http, $window, $rootScope) {
	this.login = function(bu, $scope) {
		var promise = $http.post("login", bu).then(
				function success(response) {
					$rootScope.$emit("toggleLogin", {});
					$rootScope.$emit("updateFragment", "dummy");
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
	
	this.isLoggedIn = function() {
		var promise = $http.get("ajaxIsLoggedIn").then(
				function success(response) {
					return true;
				},
				function error(response) {
					return false;
				}
		)
	}
	
	this.logout = function() {
		var promise = $http.get("logout").then(
				function success(response) {
					$rootScope.$emit("toggleLogin", {});
					$rootScope.$emit("updateFragment", "login");
				},
				function error(response) {
					console.log(response.data[0]);
				}
		)
	}
	
	this.getBoards = function() {
		var promise = $http.get("dropdown").then(
				function success(response) {
					console.log(response.data);
				},
				function error(response) {
					
				}
		)
	}
})