window.onload = function(){
	loadLoginFragment();
}
/**
 * 
 */

function loadLoginFragment(){
	var xhr = new XMLHttpRequest();
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
				loginForm = xhr.responseText;
				document.getElementById("loginPlaceholder").innerHTML = loginForm;
				document.getElementById('loginButton').addEventListener("click",login,false);
			}
		
	}
	xhr.open("GET","resources/html/login.html",true);
	xhr.send();

}
function login(user){
	var xhr = new XMLHttpRequest();
	
	var username = document.getElementById("username").value;
	var password = document.getElementById("password").value;
	
	var user = {username: username, password: password};
	var json = JSON.stringify(user);
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
				var loggedIn = xhr.responseText;
				document.getElementById("login").innerHTML = loggedIn;
			}
		
	}
	xhr.open("POST","loginCtrl",true);
	xhr.setRequestHeader('key',json);
	xhr.setRequestHeader('Content-type','application/x-www-form-urlencoded')
	xhr.send(json);
}