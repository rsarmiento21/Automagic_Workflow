window.onload = function(){
	loadLoginFragment();
}
/**
 * 	On the page load, bring up the login fragment.
 */

//This function sends an ajax request to fetch the login page fragment, then
//adds an EventListener to the loginButton

function loadLoginFragment(){
	var xhr = new XMLHttpRequest();
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
				var loginForm = xhr.responseText;
				document.getElementById("loginPlaceholder").innerHTML = loginForm;
				document.getElementById('loginButton').addEventListener("click",login,false);
			}
		
	}
	xhr.open("GET","html/login.html",true);
	xhr.send();

}

//This function triggers once the user hits the login button. It takes the values of the 
//username and password fields, then passes them in an ajax request to the Login controller

function login(user){
	var xhr = new XMLHttpRequest();
	
	var username = document.getElementById("u_name").value;
	var password = document.getElementById("u_pass").value;
	
	var user = {username: username, password: password};
	var json = JSON.stringify(user);
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
				var loggedIn = xhr.responseText;
				document.getElementById("boardPlaceHolder").innerHTML = loggedIn;
				loadLogoutFragment();	//load the logout button
			}
		
	}
	xhr.open("POST","../login",true);
//	xhr.setRequestHeader('key',json);
	xhr.setRequestHeader('Content-type','application/x-www-form-urlencoded')
	xhr.send(json);
}

//This function is called when the user successfully logs in. It loads the logout button in the place
//where the login fields were.

function loadLogoutFragment(){
	var xhr = new XMLHttpRequest();
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
				loginForm = xhr.responseText;
				document.getElementById("loginPlaceholder").innerHTML = loginForm;
		}
		
	}
	xhr.open("GET","resources/html/logout.html",true);
	xhr.send();
}