/**
 *  Angular controller for the SCRUM application portal
 */

angular.module("scrumApp", [])

.directive("scrumBody", function() {
	return {
		restrict: 'E',
		scope: { fragment: "=" },
	    link: function($scope) {
	      $scope.$watch("fragment", function(fragment) {
	        if (fragment && fragment.length) {
	            $scope.dynamicTemplateUrl = 'resources/html/' + fragment + '.html';
	        }
	      });
	    },
	    template: '<ng-include src="dynamicTemplateUrl"></ng-include>'
	};
})

.controller("mainCtrl", function($scope, $rootScope, dataService){
	$scope.fragment = "start";
	$scope.loading = false;
	
	$scope.init = function() {
		dataService.registerLoginObserver(isLoggedIn =>
			$scope.fragment = isLoggedIn ? "board" : "login");
		dataService.registerLoadingObserver(function(loading) {
			$scope.loading = loading;
		})
	}
	
	$rootScope.$on("updateFragment", function(event, fragment) {
        $scope.updateFragment(fragment);
    });
	
	$scope.updateFragment = function(fragment) {
		$scope.fragment = fragment;
	}
})