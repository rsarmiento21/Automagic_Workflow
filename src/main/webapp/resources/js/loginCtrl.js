/**
 * 
 */

angular.module("scrumApp")

.controller("loginCtrl", function($scope, $rootScope, dataService) {
	
	$scope.error = false;
	$scope.errorMessage = "";
	$scope.submitted = false;
	$scope.bu = {
		username: "",
		password: ""
	};
	
	$scope.login = function(bu) {
		if (!$scope.submitted) {
			$scope.submitted = true;
			
			dataService.login(bu,
				response => {
					$rootScope.$emit("updateFragment", "board");
				},
				response => {
					$scope.errorMessage = (response.status == 409) ?
							response.data[0] : "Error fetching user. Please try again.";
					$scope.error = true;
					$scope.submitted = false;
				}
			);
		}
	}

})