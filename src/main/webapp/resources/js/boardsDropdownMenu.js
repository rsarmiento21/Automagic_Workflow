angular.module("scrumApp")

.controller("dropdownCtrl",function($scope,dataService){
	$scope.populateDropdown = function(){
		var boards = dataService.getBoards();
		console.log(boards);
	}
})