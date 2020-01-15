<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <script src="https://apis.google.com/js/platform.js" async defer></script>
    <meta name="google-signin-client_id" content="273138075636-a4r5k7hv9aa9uqb1fphcutatajt8na3l.apps.googleusercontent.com">
 
    <script>
    function onSuccess(googleUser) {
        console.log('Logged in as: ' + googleUser.getBasicProfile().getName());
      }
      function onFailure(error) {
        console.log(error);
      }
      function renderButton() {
        gapi.signin2.render('my-signin2', {
          'scope': 'profile email',
          'width': 240,
          'height': 50,
          'longtitle': true,
          'theme': 'dark',
          'onsuccess': onSuccess,
          'onfailure': onFailure
        });
      }
      function onSignIn(googleUser) {
          var profile = googleUser.getBasicProfile();
          console.log('ID: ' + profile.getId()); // Do not send to your backend! Use an ID token instead.
          console.log('Name: ' + profile.getName());
          console.log('Image URL: ' + profile.getImageUrl());
          console.log('Email: ' + profile.getEmail()); // This is null if the 'email' scope is not present.
      }
      function signOut() { 
              var auth2 = gapi.auth2.getAuthInstance();
              auth2.signOut().then(function () {
                      console.log('User signed out.');
                  });
              auth2.disconnect(); 
          }
    </script>

    <script src="https://apis.google.com/js/platform.js?onload=renderButton" async defer></script>
 
    <title>Document</title>
</head>
<body>
    <h1>Google Login test</h1>
    <div class="g-signin2" data-onsuccess="onSignIn"></div>
    
</body>
</html>


