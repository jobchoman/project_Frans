<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Gentelella Alela! | </title>
<link href="../vendors/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="../vendors/font-awesome/css/font-awesome.min.css" rel="stylesheet">
<link href="../vendors/nprogress/nprogress.css" rel="stylesheet">
<link href="../vendors/animate.css/animate.min.css" rel="stylesheet">
<link href="../build/css/custom.min.css" rel="stylesheet">
<meta name="robots" content="noindex, follow">
<style type="text/css">
.btn{
	background-color:#2A3F54;
    border-color:#2A3F54;
    font-size: 8pt;
}
</style>
<script nonce="53f85bef-5610-4a52-9bdf-0282be37d229">(function(w,d){!function(eK,eL,eM,eN){eK.zarazData=eK.zarazData||{};eK.zarazData.executed=[];eK.zaraz={deferred:[],listeners:[]};eK.zaraz.q=[];eK.zaraz._f=function(eO){return function(){var eP=Array.prototype.slice.call(arguments);eK.zaraz.q.push({m:eO,a:eP})}};for(const eQ of["track","set","debug"])eK.zaraz[eQ]=eK.zaraz._f(eQ);eK.zaraz.init=()=>{var eR=eL.getElementsByTagName(eN)[0],eS=eL.createElement(eN),eT=eL.getElementsByTagName("title")[0];eT&&(eK.zarazData.t=eL.getElementsByTagName("title")[0].text);eK.zarazData.x=Math.random();eK.zarazData.w=eK.screen.width;eK.zarazData.h=eK.screen.height;eK.zarazData.j=eK.innerHeight;eK.zarazData.e=eK.innerWidth;eK.zarazData.l=eK.location.href;eK.zarazData.r=eL.referrer;eK.zarazData.k=eK.screen.colorDepth;eK.zarazData.n=eL.characterSet;eK.zarazData.o=(new Date).getTimezoneOffset();if(eK.dataLayer)for(const eX of Object.entries(Object.entries(dataLayer).reduce(((eY,eZ)=>({...eY[1],...eZ[1]})))))zaraz.set(eX[0],eX[1],{scope:"page"});eK.zarazData.q=[];for(;eK.zaraz.q.length;){const e_=eK.zaraz.q.shift();eK.zarazData.q.push(e_)}eS.defer=!0;for(const fa of[localStorage,sessionStorage])Object.keys(fa||{}).filter((fc=>fc.startsWith("_zaraz_"))).forEach((fb=>{try{eK.zarazData["z_"+fb.slice(7)]=JSON.parse(fa.getItem(fb))}catch{eK.zarazData["z_"+fb.slice(7)]=fa.getItem(fb)}}));eS.referrerPolicy="origin";eS.src="/cdn-cgi/zaraz/s.js?z="+btoa(encodeURIComponent(JSON.stringify(eK.zarazData)));eR.parentNode.insertBefore(eS,eR)};["complete","interactive"].includes(eL.readyState)?zaraz.init():eK.addEventListener("DOMContentLoaded",zaraz.init)}(w,d,0,"script");})(window,document);</script></head>
<body class="login">
<div>
<a class="hiddenanchor" id="signup"></a>
<a class="hiddenanchor" id="signin"></a>
<div class="login_wrapper">
<div class="animate form login_form">
<section class="login_content">
<form action="memberLogin.do" method="POST">
<h1>Login Form</h1>
<div>
<input type="text" class="form-control" name="emp_id" placeholder="Username" required="" />
</div>
<div>
<input type="password" class="form-control" name="emp_pw" placeholder="Password" required="" />
</div>
<div>
<button class="btn btn-round btn-info">LOGIN</button>
<!-- <a class="btn btn-default submit" href="memberLogin.do">Log in</a> -->
<!-- <a class="reset_pass" href="#">Lost your password?</a> -->
</div>
<div class="clearfix"></div>

</form>
</section>
</div>
<div id="register" class="animate form registration_form">
<section class="login_content">
<form>
<h1>Create Account</h1>
<div>
<input type="text" class="form-control" placeholder="Username" required="" />
</div>
<div>
<input type="email" class="form-control" placeholder="Email" required="" />
</div>
<div>
<input type="password" class="form-control" placeholder="Password" required="" />
</div>
<div>
<a class="btn btn-default submit" href="index.html">Submit</a>
</div>
<div class="clearfix"></div>
<div class="separator">
<p class="change_link">Already a member ?
<a href="#signin" class="to_register"> Log in </a>
</p>
<div class="clearfix"></div>
<br />
<div>
<h1><i class="fa fa-paw"></i> Gentelella Alela!</h1>
<p>©2016 All Rights Reserved. Gentelella Alela! is a Bootstrap 3 template. Privacy and Terms</p>
</div>
</div>
</form>
</section>
</div>
</div>
</div>
<script defer src="https://static.cloudflareinsights.com/beacon.min.js/vaafb692b2aea4879b33c060e79fe94621666317369993" integrity="sha512-0ahDYl866UMhKuYcW078ScMalXqtFJggm7TmlUtp0UlD4eQk0Ixfnm5ykXKvGJNFjLMoortdseTfsRT8oCfgGA==" data-cf-beacon='{"rayId":"785abbc27f5b1a36","token":"cd0b4b3a733644fc843ef0b185f98241","version":"2022.11.3","si":100}' crossorigin="anonymous"></script>
</body>
<script>
var msg = "${msg}";

if(msg != ""){
	alert(msg);
}
</script>
</html>
