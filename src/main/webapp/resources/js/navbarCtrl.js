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
	$scope.loggedIn = dataService.isLoggedIn();
	
	$scope.logout = function() {
		dataService.logout();
	}
	
	$rootScope.$on("toggleLogin", function() {
        $scope.toggleLogin();
     });
	
	$scope.toggleLogin = function() {
		$scope.loggedIn = !$scope.loggedIn;
	}
})