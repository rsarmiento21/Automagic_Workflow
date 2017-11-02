/**
 *  Angular JS for DataService service
 */

angular.module("scrumApp")

.service("dataService", function($http, $q) {
	this.login = function(bu, success, failure) {
		$http.post("login", bu).then(success, failure);
	}

	this.isLoggedIn = function(callback) {
		$http.get("ajaxIsLoggedIn").then(callback);
	}
	
	this.logout = function(success, failure) {
		$http.get("logout").then(success, failure);
	}
	
	this.getBoards = function() {
		var promise = $http.get("dropdown").then(
			function success(response) {
				console.log(response.data);
			},
			function error(response) {}
		)
	}
	
	this.getBoard = function(id, success, failure) {
		$http.get("ajax/board/" + id).then(success, failure);
	}
	
//	ds.setLoadState = function() {
//		if (ds.isLoggedIn()) {
//			root.$emit("toggleLogin", {});
//			root.$emit("updateFragment", "board");
//		} else {
//			root.$emit("updateFragment", "login");
//		}
//	}
	
})