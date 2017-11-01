/**
 * 
 */

angular.module("scrumApp")

.controller("loginCtrl", function($scope, dataService) {
	$scope.error = false;
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
			dataService.login(bu, $scope);
		}
	}
})