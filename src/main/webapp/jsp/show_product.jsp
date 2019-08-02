<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="resources"/>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Registration</title>
        <link rel="stylesheet" href="../css/bootstrap.min.css">
        <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
        <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.0.8/css/all.css">
        <link rel="stylesheet" href="../css/main.css">
    </head>
    <body>
     <div class="lang lang2">
      	<web-store-tag:language/>
        <form class="container row" action="/ShowProduct" id="registration-form" method="get">
            <input type="hidden" name="command" value="show-product">
            <div id="filter-param" class="row border "
                 style="padding-left: 25px;padding-right: 20px;background-color: #bbbbbb;">
                <div>
                    <section>
                        <label for="shoes-type">Shoes type</label>
                        <select class="form-control" name="category" id="category" >
                            <option value="1">Sneakers</option>
                            <option value="2">Shoes</option>
                            <option value="3">Slippers</option>
                        </select>
                        <br>
                        <script>

                        </script>
                        <h6 class="text-center">Brand</h6>
                        <fieldset>
                            <input class="checkbox" type="checkbox" name="manufacturer" value="11" >Nike<br>
                            <input class="checkbox" type="checkbox" name="manufacturer" value="12">New Balance<br>
                            <input class="checkbox" type="checkbox" name="manufacturer" value="13">Converse<br>
                        </fieldset>
                        <br>
                        <h6 class="text-center">Cost</h6>
                        <div class="row" style="padding-left: 10px">
                            <label for="low-cost">from:</label>
                            <input value="${repositoryBean.loverCost}" size="3" type="text" name="cost-from" class="text-dark" id="low-cost">
                            <label for="high-cost">to:</label>
                            <input value="${repositoryBean.upperCost}" size="3" type="text" class="text-dark" name="cost-to" id="high-cost">
                        </div>
                        <br>
                        <input class="d-none" id="current-page" name="current-page" value="1">
                        <button id='sub' class="btn-block" type="submit" value="Submit">Submit</button>
                    </section>
                </div>
            </div>
            <div class="col">
                <div class="d-flex flex-row-reverse">
                    <div class="form-group">
                        <label for="order-by" style="padding-left:50px;">Order by</label>
                        <select class="form-control" id="order-by" name="order-by" style="margin-left:50px;">
                            <option value="21">Natural order</option>
                            <option value="22">Natural order</option>
                            <option value="23">Low cost first</option>
                            <option value="24">High cost first</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="amount" style="text-align: center;">Amount</label>
                        <select class="form-control" id="amount" name="amount">
                            <option value="31">5</option>
                            <option value="32">25</option>
                            <option value="33">50</option>
                            <option value="34">75</option>
                            <option value="35">100</option>
                        </select>
                    </div>
                </div>
                <section>
                    <div class="text-center">
                        <div class="section-heading text-center">
                            <h1 class="text-clipped text-uppercase">Shoes collection</h1>
                            <p class="text-secondary">Shop the newest trends</p>
                        </div>
                        <div class="row">
                            <c:forEach var="product" items="${repositoryBean.productList}">
                                <div class="col-12 col-md-3 hero-img product-card">
                                    <a href="#">
                                        <c:catch var="myWebSiteAccessException">
                                            <c:import url="${product.pictures}" var="imageData" /> 
                                        </c:catch>
                                        <c:choose>
                                            <c:when test="${empty myWebSiteAccessException}"> 
                                                <img alt="avatar" src="${product.pictures}"><br>
                                            </c:when>
                                            <c:otherwise>
                                                <img alt="avatar" src="images\shoes\default.png"><br>
                                            </c:otherwise>
                                        </c:choose>
                                    </a>
                                    <div class="mt-2">
                                        <a href="#">
                                            <span>Cost:"${product.cost}"</span><br>
                                            <span class="text-uppercase font-weight-bold">${product.nameOfProduct}</span><br>
                                            <br/>
                                            <span class="prod-id" id="${product.id}">Shop now</span><i class="fas fa-caret-right"></i>
                                        </a>
                                    </div>
                                </div>

                            </c:forEach>

                            <div class="col-12 col-md-3 hero-img">
                                <a href="#">
                                    <img src="../images/hero/hero1.jpg" alt="">
                                </a>
                                <div class="mt-2">
                                    <a href="#">
                                        <span class="text-uppercase font-weight-bold">Air Max</span><br>
                                        <span>Shop now</span><i class="fas fa-caret-right"></i>
                                    </a>
                                </div>
                            </div>
                            <div class="col-12 col-md-3  hero-img">
                                <a href="#">
                                    <img src="../images/hero/hero4.jpg" alt="">
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
                <div class="d-flex align-item-center justify-content-center">

                    <ul class="pagination">
                        <li class="page-item"><a class="page-link" href="#">Previous</a></li>
                            <c:forEach var="i" begin="1" end="${repositoryBean.pageAmount}"> 
                            <li class="page-item">
                                <label class="page-link" for="sub">${i}</label>
                            </li>
                        </c:forEach>
                        <li class="page-item"><a class="page-link" href="#">Next</a></li>
                    </ul>
                </div>
            </div>
            <script>


                let currentPage = ${repositoryBean.page};
                const querySelectorPagination = document.querySelector('.pagination');
                let currentPageClient = document.getElementById('current-page');
                querySelectorPagination.addEventListener('click', function (e) {
                    let target = e.target;
                    let b = target.textContent;
                    if (b === undefined) {
                        b = currentPage;
                    } else {
                        currentPage = b;
                    }
                    currentPageClient.value = b;
                });

                function f() {
                    const test = "filter.manufacturer}";
                    let index = 0;
                <c:forEach var="i" items="${repositoryBean.filter['manufacturer']}">
                    console.log("${i}");
                    setChacked("${i}");
                </c:forEach>
                    console.log("${repositoryBean.filter['manufacturer']}");
                    const testPage = document.getElementsByClassName("form-control");
                    for (let e of testPage) {
                        for (let e2 of e) {
                            if ("${repositoryBean.filter['category'][0]}" === e2.value) {
                                e2.setAttribute('selected', true);
                            }
                            console.log("${repositoryBean.elementPerPage}");
                            if ("${repositoryBean.elementPerPage}" === e2.value) {
                                e2.setAttribute('selected', true);
                            }
                            console.log("${repositoryBean.orderBy}");
                            if ("${repositoryBean.orderBy}" === e2.value) {
                                e2.setAttribute('selected', true);
                            }
                        }
                        console.log(e);
                    }
                    const addActive = document.getElementsByClassName("page-item");
                    for (let el of addActive) {
                        if (Number(el.textContent) === currentPage) {
                            el.classList.add("active");
                        }
                    }
                }
                function setChacked(input) {
                    const testF = document.getElementsByClassName("checkbox");
                    for (let e of testF) {
                        console.log(e.value + " ==//== " + input);
                        console.log(input === e.value);
                        if (input === e.value) {
                            console.log(e.checked = true);
                        }
                    }
                }
                f();
            </script>
        </form>
        <script src="../js/add_to_basket.js"></script>
    </body>
</html>