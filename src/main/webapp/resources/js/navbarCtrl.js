/**
 *  Angular controller for the SCRUM Navbar
 */

angular.module("scrumApp")

.directive("scrumNavbar", function() {
	return {
		templateUrl: "resources/html/navbarDummy.html",
	};
})

.controller("navbarCtrl", function($scope) {
	$scope.loggedIn = false;
})