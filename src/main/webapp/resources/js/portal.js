/**
 *  Angular controller for the SCRUM application portal
 */

angular.module("scrumApp", [])

.controller("mainCtrl", function($scope, $rootScope, dataService){
	$scope.fragment = "dummy";
	
	$scope.init = function() {
		dataService.setLoadState();
	}
	
	$rootScope.$on("updateFragment", function(event, fragment) {
        $scope.updateFragment(fragment);
    });
	
	$scope.updateFragment = function(fragment) {
		$scope.fragment = fragment;
	}
})

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