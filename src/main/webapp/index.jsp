<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="ex" uri="avatar" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="resources"/>
<!doctype html>
<html lang="en">

<head>
    <%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core"%>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Meteor Goods</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.8.1/css/all.css">
    <link rel="stylesheet" href="css/main.css">
</head>

<body>
 <div class="lang lang2">
  	<web-store-tag:language/>
    <!--start header-->
    <header>
        <div class="custom-nav-container d-flex align-items-center justify-content-between px-2 py-3 shadow-sm">
            <a class="text-clipped  navbar-brand" href="#">
                <i class="fal fa-meteor d-flex">
                    <span class="m-auto">Meteor</span>
                </i>
            </a>
            <div class="main-nav-outer d-flex">
                <i class="fal fa-times tet-clipped menu-close-icon d-lg-none"></i>
                <nav class="main-nav navbar navbar-light navbar-expand-lg text-center m-auto">
                    <ul class="navbar-nav d-flex">
                        <li class="nav-item">
                            <a class="nav-link" href="/ShowProduct">Shop Product</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/showBasket">Basket</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Women</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Kids</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">New arrivals</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Sale</a>
                        </li>
                    </ul>
                </nav>
            </div>
            <div class="nav-icons-container d-flex
        justify-content-between">
                <div class="nav-icons">
                    <a href="">
                        <i class="fal fa-search text-clipped"></i>
                    </a>
                </div>
                <div class="nav-icons">
                    <a href="/showBasket">
                        <i class="fal fa-shopping-bag bag-item-count text-clipped"></i>
                        <span class="badge">${basketSize}</span>
                    </a>
                </div>

                <c:set var="user" scope="session" value="${user}" />
                <c:choose>
                    <c:when test="${not empty user}">
                        <div>
                            <ex:Avatar />
                        </div>
                        <h5>${user.login}</h5>
                        <form action="logout" class=" " method="post">
                            <input type="hidden" name="command" value="logout">

                            <button type="submit" class="btn">
                                Logout
                            </button>

                        </form>
                        <!--<img alt="captcha" src="data:image/png;base64,${captchaValue}"><br>-->
                    </c:when>
                    <c:otherwise>

                        <div class="nav-icons">
                            <div class="dropdown">
                                <a class="dropdown-toggle" href="" data-toggle="dropdown">
                                    <i class="fal fa-user  text-clipped"></i>
                                </a>
                                <div class="dropdown-menu">
                                    <form action="login" class="p-2 text-secondary" method="post">
                                        <input type="hidden" name="command" value="login">
                                        <div class="form-group">
                                            <input type="text" class="form-control" placeholder="Enter login" name="userLogin" required>
                                        </div>
                                        <div class="form-group">
                                            <input type="password" class="form-control" placeholder="Enter password" name="userPassword" required>
                                        </div>
                                        <div class="form-check">
                                            <input type="checkbox" class="form-check-input" id="dropdown-checkbox">
                                            <label class="form-check-label" for="dropdown-checkbox">
                                                Remember me
                                            </label>
                                        </div>
                                        <div class="d-flex justify-content-between">
                                            <button type="submit" class="btn btn-secondary btn-bg btn-block my-2">
                                                Login
                                            </button>
                                        </div>
                                    </form>
                                    <form action="Registration" class="p-2 text-secondary" method="post">
                                        <button type="submit" class="btn btn-secondary btn-bg btn-block my-2">
                                            <input type="hidden" name="command" value="getCaptcha">
                                            Registration
                                        </button>
                                    </form>
                                    <div class="dropdown-divider mt-0"></div>
                                    <a class="dropdown-item" href="#">
                                        Dont't have an account? Sign up</a>
                                    <a class="dropdown-item" href="#">
                                        Forgot your password? Reset now
                                    </a>
                                </div>
                            </div>
                        </div>
                        <!--<img alt="captcha" src="data:image/png;base64,${captchaValue}"><br>-->
                    </c:otherwise>
                </c:choose>
                <div class="nav-icons d-lg-none">
                    <i class="fal fa-bars open-menu-icon text-clipped"></i>
                </div>
            </div>
        </div>
        <!--start promotions-->
        <div class="promotions text-white text-center py-2 mb-5">
            <span>
                <span class="text-uppercase font-weight-bold">Free shopping</span> on orders or $100 more!
            </span>
        </div>
        <!--end promotions-->
        <!--start carousel-->
        <div class="container">
            <div class="carousel slide" id="carousel-indicators" data-ride="carousel">
                <ol class="carousel-indicators">
                    <li class="active" data-target="#carousel-indicators" data-slide-to="0"></li>
                    <li data-target="#carousel-indicators" data-slide-to="1"></li>
                    <li data-target="#carousel-indicators" data-slide-to="2"></li>
                </ol>
                <div class="carousel-inner">
                    <div class="carousel-item active">
                        <a href="#">
                            <img class="img-fluid d-md-none" src="images/carousel/carousel-img-1-mobile.jpeg" alt="">
                            <img class="img-fluid d-none d-md-block" src="images/carousel/carousel-img-1.jpeg" alt="">
                        </a>
                    </div>


                    <div class="carousel-item">
                        <a href="#">
                            <img class="img-fluid d-md-none" src="images/carousel/carousel-img-2-mobile.jpg" alt="">
                            <img class="img-fluid d-none d-md-block" src="images/carousel/carousel-img-2.jpg" alt="">
                        </a>
                    </div>


                    <div class="carousel-item">
                        <a href="#">
                            <img class="img-fluid d-md-none" src="images/carousel/carousel-img-3-mobile.jpeg" alt="">
                            <img class="img-fluid d-none d-md-block" src="images/carousel/carousel-img-3.jpeg" alt="">
                        </a>
                    </div>
                </div>

                <a class="carousel-control-prev" href="#carousel-indicators" role="button" data-slide="prev">
                    <i class="fal fa-chevron-left text-clipped"></i>
                </a>
                <a class="carousel-control-next" href="#carousel-indicators" role="button" data-slide="next">
                    <i class="fal fa-chevron-right text-clipped"></i>
                </a>
            </div>

        </div>
        <!--end carousel-->
    </header>
    <!--end header-->

    <!--brands-->
    <section class="featured-brands">
        <div class="container">
            <div class="row align-items-center text-center">
                <div class="col-12 col-sm-6 col-md-2 mb-5">
                    <a href="#">
                        <img src="images/brands/nike.svg" alt="">
                    </a>
                </div>
                <div class="col-12 col-sm-6 col-md-2 mb-5">
                    <a href="#">
                        <img src="images/brands/jordan.svg" alt="">
                    </a>
                </div>
                <div class="col-12 col-sm-6 col-md-2 mb-5">
                    <a href="#">
                        <img src="images/brands/adidas.svg" alt="">
                    </a>
                </div>
                <div class="col-12 col-sm-6 col-md-2 mb-5">
                    <a href="#">
                        <img src="images/brands/under-armour.svg" alt="">
                    </a>
                </div>
                <div class="col-12 col-sm-6 col-md-2 mb-5">
                    <a href="#">
                        <img src="images/brands/vans.svg" alt="">
                    </a>
                </div>
                <div class="col-12 col-sm-6 col-md-2 mb-5">
                    <a href="#">
                        <img src="images/brands/puma.svg" alt="">
                    </a>
                </div>
            </div>
        </div>
    </section>
    <!--end-brands-->

    <!--W collections-->
    <section class="women-collection pt-0">
        <div class="container">
            <div class="section-heading text-center mb-5">
                <h1 class="text-clipped text-uppercase">Women's collection</h1>
                <p class="text-secondary">Shop the newest trends</p>
            </div>
            <div class="row">
                <div class="col-12 col-md-6 hero-img">
                    <a href="#">
                        <img src="images/hero/hero1.jpg" alt="">
                    </a>
                    <div class="mt-2">
                        <a href="#">
                            <span class="text-uppercase font-weight-bold">Air Max</span><br>
                            <span>Shop now</span><i class="fas fa-caret-right"></i>
                        </a>
                    </div>
                </div>
                <div class="col-12 col-md-6  hero-img">
                    <a href="#">
                        <img src="images/hero/hero2.jpg" alt="">
                    </a>
                    <div class="mt-2">
                        <a href="#">
                            <span class="text-uppercase font-weight-bold">Air Max</span><br>
                            <span>Shop now</span><i class="fas fa-caret-right"></i>
                        </a>
                    </div>
                </div>
                <div class="col-12 col-md-6 hero-img">
                    <a href="#">
                        <img src="images/hero/hero3.jpg" alt="">
                    </a>
                    <div class="mt-2">
                        <a href="#">
                            <span class="text-uppercase font-weight-bold">Air Max</span><br>
                            <span>Shop now</span><i class="fas fa-caret-right"></i>
                        </a>
                    </div>
                </div>
                <div class="col-12 col-md-6  hero-img">
                    <a href="#">
                        <img src="images/hero/hero4.jpg" alt="">
                    </a>
                    <div class="mt-2">
                        <a href="#">
                            <span class="text-uppercase font-weight-bold">Air Max</span><br>
                            <span>Shop now</span><i class="fas fa-caret-right"></i>
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!--end W collections-->

    <!--new arrivals-->
    <section class="our-services">
        <div class="container">
            <div class="section-heading text-center mb-5">
                <h1 class="text-clipped text-uppercase">The latest and greatest shoes</h1>
                <p class="text-secondary">releases</p>
            </div>
            <div class="row text-center">
                <div class="col-12 col-sm-6 col-md-2 mb-2">
                    <div class="new-arrival-img mb-3">
                        <img src="images/shoes/shoe1.jpg" alt="">
                    </div>
                    <a href="#">
                        <span class="text-uppercase font-weight-bold">Nike Air Max</span><br>
                        <span>Shop now</span>
                        <l class="fas fa-caret-right"></l>
                    </a>
                </div>
                <div class="col-12 col-sm-6 col-md-2 mb-2">
                    <div class="new-arrival-img mb-3">
                        <img src="images/shoes/shoe2.jpg" alt="">
                    </div>
                    <a href="#">
                        <span class="text-uppercase font-weight-bold">Nike Air Max</span><br>
                        <span>Shop now</span>
                        <l class="fas fa-caret-right"></l>
                    </a>
                </div>
                <div class="col-12 col-sm-6 col-md-2 mb-2">
                    <div class="new-arrival-img mb-3">
                        <img src="images/shoes/shoe3.jpg" alt="">
                    </div>
                    <a href="#">
                        <span class="text-uppercase font-weight-bold">Nike Air Max</span><br>
                        <span>Shop now</span>
                        <l class="fas fa-caret-right"></l>
                    </a>
                </div>
                <div class="col-12 col-sm-6 col-md-2 mb-2">
                    <div class="new-arrival-img mb-3">
                        <img src="images/shoes/shoe4.jpg" alt="">
                    </div>
                    <a href="#">
                        <span class="text-uppercase font-weight-bold">Nike Air Max</span><br>
                        <span>Shop now</span>
                        <l class="fas fa-caret-right"></l>
                    </a>
                </div>
                <div class="col-12 col-sm-6 col-md-2 mb-2">
                    <div class="new-arrival-img mb-3">
                        <img src="images/shoes/shoe5.jpg" alt="">
                    </div>
                    <a href="#">
                        <span class="text-uppercase font-weight-bold">Nike Air Max</span><br>
                        <span>Shop now</span>
                        <l class="fas fa-caret-right"></l>
                    </a>
                </div>
                <div class="col-12 col-sm-6 col-md-2 mb-2">
                    <div class="new-arrival-img mb-3">
                        <img src="images/shoes/shoe6.jpg" alt="">
                    </div>
                    <a href="#">
                        <span class="text-uppercase font-weight-bold">Nike Air Max</span><br>
                        <span>Shop now</span>
                        <l class="fas fa-caret-right"></l>
                    </a>
                </div>
            </div>
        </div>
    </section>
    <!--end new arrivals-->


    <!--Services-->
    <section class="arrivals">
        <div class="container">
            <div class="row text-center">
                <div class="col-12 col-sm-6 col-md-3 md-5">
                    <div class="our-services-icon d-flex mb-3 mx-auto">
                        <i class="fal fa-shipping-fast text-clipped m-auto "></i>
                    </div>
                    <div class="our-services-description">
                        <h5 class="text-uppercase font-weight-bold">Free world wide shipping</h5>
                        <p>Free shipping for all orders over 100$</p>
                    </div>
                </div>
                <div class="col-12 col-sm-6 col-md-3 md-5">
                    <div class="our-services-icon d-flex mb-3 mx-auto">
                        <i class="fal fa-sync text-clipped m-auto"></i>
                    </div>
                    <div class="our-services-description">
                        <h5 class="text-uppercase font-weight-bold">Money back</h5>
                        <p>We return money within 30 days of purchase</p>
                    </div>
                </div>
                <div class="col-12 col-sm-6 col-md-3 md-5">
                    <div class="our-services-icon d-flex mb-3 mx-auto">
                        <i class="fal fa-headset text-clipped m-auto"></i>
                    </div>
                    <div class="our-services-description">
                        <h5 class="text-uppercase font-weight-bold">27/7 customer</h5>
                        <p>On demand 24/7 customer support</p>
                    </div>
                </div>
                <div class="col-12 col-sm-6 col-md-3 md-5">
                    <div class="our-services-icon d-flex mb-3 mx-auto">
                        <i class="fal fa-credit-card text-clipped m-auto"></i>
                    </div>
                    <div class="our-services-description">
                        <h5 class="text-uppercase font-weight-bold">Secure online payments</h5>
                        <p>We use to of th line data encryption</p>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!--end Services-->

    <!--Start footer-->
    <footer class="py-5">
        <div class="container">
            <div class="row">
                <div class="col-12 col-lg-6">
                    <address>
                        <strong class="h3 font-weight-normal">Meteor Goods</strong><br>
                        12345 Example Street<br>
                        Anratctica, Example 0987
                        Phone:(0980)8978-4646
                    </address>
                </div>
                <div class="col-12 col-sm-6 col-lg-3">
                    <div class="footer-title">
                        <h5>Our Mission</h5>
                        <span class="custom-border"></span>
                    </div>
                    <ul class="footer-list">
                        <li>
                            <a href="#" class="footer-link">Lorem</a>
                        </li>
                        <li>
                            <a href="#" class="footer-link">Lorem</a>
                        </li>
                        <li>
                            <a href="#" class="footer-link">Lorem</a>
                        </li>
                        <li>
                            <a href="#" class="footer-link">Lorem</a>
                        </li>
                        <li>
                            <a href="#" class="footer-link">Lorem</a>
                        </li>
                        <li>
                            <a href="#" class="footer-link">Lorem</a>
                        </li>
                    </ul>
                </div>

                <div class="col-12 col-sm-6 col-lg-3">
                    <div class="footer-title">
                        <h5>Customer Service</h5>
                        <span class="custom-border"></span>
                    </div>
                    <ul class="footer-list">
                        <li>
                            <a href="#" class="footer-link">Lorem</a>
                        </li>
                        <li>
                            <a href="#" class="footer-link">Lorem</a>
                        </li>
                        <li>
                            <a href="#" class="footer-link">Lorem</a>
                        </li>
                        <li>
                            <a href="#" class="footer-link">Lorem</a>
                        </li>
                        <li>
                            <a href="#" class="footer-link">Lorem</a>
                        </li>
                        <li>
                            <a href="#" class="footer-link">Lorem</a>
                        </li>
                    </ul>
                </div>
                <hr class="line mt-5">
                <div class="w-100 d-md-flex justify-content-between align-items-center text-center">
                    <div class="order-2">
                        <a class="footer-link" href="#">Lorem</a>
                        <a class="footer-link ml-3" href="#">Lorem</a>
                        <a class="footer-link ml-3" href="#">Lorem</a>
                    </div>
                    <p class="m-0 text-sm order-1">
                        &copy; 2019 Meteor Goods. All right reserved
                    </p>
                </div>
            </div>
        </div>
    </footer>
    <!--end footer-->
    <script src="js/jquery-3.4.1.slim.min.js"></script>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/main.js"></script>
</body>

</html>
