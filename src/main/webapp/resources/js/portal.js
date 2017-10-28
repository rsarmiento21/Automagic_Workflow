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
	$scope.bu = {
		username: "",
		password: ""
	};
	
	$scope.login = function(bu) {
		console.log("Preparing to login");
		$scope.updateTask = dataService.login(bu);
	}
})

.service("dataService", function($http, $q) {
	this.login = function(bu) {
		var promise = $http.post("login", bu).then(
				function(response) {
					console.log(response.data);
				},
				function(error) {
					console.log( $q.reject(error) );
				}
		)
	}
})