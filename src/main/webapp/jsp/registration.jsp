<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="ex" uri="custom" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="resources"/>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Registration</title>
        <link rel="stylesheet" href="../css/bootstrap.min.css">
        <script src="../js/jquery-3.4.1.slim.min.js"></script>
        <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.0.8/css/all.css">

    </head>
    <body style="background-color: #454d55">
     <div class="lang lang2">
      	<web-store-tag:language/>
        <div class="container">
            <div class="card bg-dark">
                <article class="card-body mx-auto" style="max-width: 400px;">
                    <h4 class="card-text mt-3 text-center text-white">Crate Account</h4>
                    <p class="text-center text-white">Get started with your free account</p>
                    <form action="/Registration" id="registration-form" method="post"
                          enctype="multipart/form-data" novalidate>
                        <input type="hidden" name="command" value="registration">
                        <div class="form-group input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text"><i class="fa fa-user-circle"></i></span>
                            </div>
                            <input name="userLogin" id="user-login" class="form-control" placeholder="Login" type="text" value="${loginResponse}">
                            <div class="valid-feedback">
                                Ok!
                            </div>
                            <div class="invalid-feedback">
                                To short or already exist!
                            </div>
                        </div>
                        <div class="form-group input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text"><i class="fa fa-user"></i></span>
                            </div>
                            <input name="userName" id="user-name" class="form-control" placeholder="Full name" type="text" value="${nameResponse}">

                            <div class="valid-feedback">
                                Ok!
                            </div>
                            <div class="invalid-feedback">
                                To short!
                            </div>
                        </div>
                        <div class="form-group input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text"><i class="fa fa-phone"></i></span>
                            </div>

                            <select class="custom-select" style="max-width: 25%">
                                <option selected="">+380</option>
                                <option value="0">+971</option>
                                <option value="1">+972</option>
                                <option value="2">+198</option>
                                <option value="3">+701</option>
                            </select>

                            <input type="text" name="userPhone" id="user-phone" class="form-control" placeholder="Phone number" value="${phoneResponse}">

                            <div class="valid-feedback">
                                Ok!
                            </div>
                            <div class="invalid-feedback">
                                incorrect phone number!
                            </div>
                        </div>
                        <div class="form-group input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text"> <i class="fa fa-envelope"></i> </span>
                            </div>

                            <input name="userEmail" id="user-email" class="form-control" placeholder="Email address"
                                   type="email" value="${emailResponse}">

                            <div class="valid-feedback">
                                Ok!
                            </div>
                            <div class="invalid-feedback">
                                incorrect Email address!
                            </div>
                        </div>
                        <div class="form-group input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text"><i class="fa fa-lock"></i></span>
                            </div>

                            <input name="userPassword" id="user-password" type="password" class="form-control"
                                   placeholder="Crate password">

                            <div class="valid-feedback">
                                Ok!
                            </div>
                            <div class="invalid-feedback">
                                incorrect Password!
                            </div>
                        </div>
                        <div class="form-group input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text"><i class="fa fa-lock"></i></span>
                            </div>

                            <input name="userPassword2" id="user-password2" type="password" class="form-control"
                                   placeholder="Repeat password">

                            <div class="valid-feedback">
                                Ok!
                            </div>
                            <div class="invalid-feedback">
                                Password don't match
                            </div>
                        </div>
                        <div>

                            <h2 style="color: white">File upload:</h2>
                            <input type = "file" name = "userAvatar" size = "50000" accept="image/*"/>
                            <div>

                                <div class="form-group input-group">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text"><i class="fa fa-lock"></i></span>
                                    </div>

                                    <input name="userCaptcha" id="user-captcha" type="text" class="form-control"
                                           placeholder="Enter captcha">

                                    <div class="valid-feedback">
                                        Ok!
                                    </div>
                                    <div class="invalid-feedback">
                                        to short
                                    </div>
                                </div>

                                <!--                **********************************************************************************************************************************************************-->
                                <br>
                                <div>
                                    <ex:Captcha/>
                                </div>


                                <div class="form-group">
                                    <button id="submitForm" type="submit"
                                            class="btn btn-primary btn-dark btn-block border-1 border-light">
                                        Create account
                                    </button>
                                </div>
                                <p class="text-center text-white">Have an account?<a href="#" class="">Log In</a></p>
                                </form>
                                </article>
                            </div>
                            <script>

                            </script>
                            <script src="../js/validation.js"></script>
                            <!--      <script src="js/jquery-validation.js"></script>-->

                        </div>
                        </body>
                        </html>

<!--<img class="captcha" src="${context_path}/captcha" id="captcha_img">-->
<!--<a href="#" onclick="$('#captcha_img').attr('src', '${context_path}/captcha?t=' + new Date().getTime());">Reset captcha</a>-->
