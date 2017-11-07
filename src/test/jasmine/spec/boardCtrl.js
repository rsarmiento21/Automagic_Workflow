/*
 * This is the Suite of test cases for boardCtrl.js, written to work with Jasmine
 */

//This is the Suite, or collection of test cases
describe("Board Controller Test Suite",function(){
	
	//This is an instance of the class being tested
	var board;
	
	//This will be called before each spec runs
	beforeEach(function(){
		board = new boardCtrl();
	})
	
	describe("When board Controller is called to manipulate board objects", function(){
		
		//This is a Spec, essentially a single test case 
		it("it should be able to toggle the rename boolean value",renameBoard(){
			expect($scope.rename).toEqual(true);
		});
		
	})
	
});