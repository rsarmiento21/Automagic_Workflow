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
	$scope.loggedIn = false;

	$scope.init = function() {
		dataService.isLoggedIn(response => $scope.loggedIn = response.data);
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
})