/**
 *  Angular JS for Loading Modal
 */

angular.module("scrumApp")

.directive("loadingToggle",function(){
	return function(scope, element, attrs){
		scope.$watch(function(){ return scope.loading; }, function(value) {
	    	if (value) {
	    		element.modal('show');
	    	} else {
	    		element.modal('hide');
	    	}
		});
	};
})

.directive("loadingModal", function() {
	return {
		restrict: 'E',
		templateUrl: 'resources/html/loading.html'
	};
})

.controller("loadingCtrl", function($scope, dataService) {
	$scope.loading = false;
	
	$scope.init = function() {
		dataService.registerLoadingObserver(function(loading) {
			$scope.loading = loading;
		})
	}
	
})