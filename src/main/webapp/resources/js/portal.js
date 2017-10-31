/**
 *  Angular controller for the SCRUM application portal
 */

angular.module("scrumApp", [])

.directive("scrumBody", function() {
	return {
		templateUrl: "resources/html/loginAngular.html",
	};
})

.controller("loginCtrl", function($scope, dataService) {
	$scope.errorMessage = "";
	$scope.submitted = false;
	$scope.bu = {
		username: "",
		password: ""
	};
	
	$scope.login = function(bu) {
		if (!$scope.submitted) {
			console.log("Preparing to login");
			$scope.submitted = true;
			setTimeout(dataService.login(bu, $scope), 3000);
		}
	}
})

.service("dataService", function($http, $q, $window) {
	this.login = function(bu, $scope) {
		var promise = $http.post("login", bu).then(
				function success(response) {
					$window.location.href = "dummy";
				},
				function error(response) {
					if (response.status == 409) {
						$scope.errorMessage = response.data[0];
					} else {
						$scope.errorMessage = "Error adding user.";
					}
					$scope.submitted = false;
				}
		)
	}
})