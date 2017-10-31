/**
 *  Angular controller for the SCRUM application portal
 */

angular.module("scrumApp", [])

.controller("mainCtrl", function($scope, $rootScope){
	$scope.fragment = "login";
	
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
			dataService.login(bu, $scope);
		}
	}
})

.service("dataService", function($http, $window, $rootScope) {
	this.toggleLogin = 
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
					$scope.submitted = false;
				}
		)
	}
})