<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="resources"/>
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
 <div class="lang lang2">
  	<web-store-tag:language/>
<section class="jumbotron text-center">
    <div class="container">
        <h1 class="jumbotron-heading">BASKET</h1>
    </div>
</section>

<div class="container mb-4">
    <div class="row">
        <div class="col-12">
            <div class="table-responsive">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th scope="col"> </th>
                            <th scope="col">Product</th>
                            <th scope="col">Available</th>
                            <th scope="col" class="text-center">Quantity</th>
                            <th scope="col" class="text-right">Price</th>
                            <th> </th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="product" items="${basket}">
                            <tr>
                                <c:catch var="myWebSiteAccessException">
                                    <c:import url="${product.key.pictures}" var="imageData" />
                                </c:catch>
                                <c:choose>
                                    <c:when test="${empty myWebSiteAccessException}">
                                        <td><img alt="avatar" src="${product.key.pictures}" style="max-width: 70px;height: auto;"></td>
                                        </c:when>
                                        <c:otherwise>
                                        <td><img alt="avatar" src="images\shoes\default.png"></td>
                                        </c:otherwise>
                                    </c:choose>
                                <td>${product.key.nameOfProduct}</td>
                                <td>${product.key.manufacturer}</td>
                                <td><input class="form-control product-value" type="text" value="${product.value}" /></td>
                                <td class="text-right p-cost">${product.key.cost}</td>
                                <td class="text-right"><button class="btn btn-sm btn-danger"><i class="fa fa-trash"></i> </button> </td>
                            </tr>
                        </c:forEach>

                        <tr>
                            <td><img src="https://dummyimage.com/50x50/55595c/fff" /> </td>
                            <td>Product Name Dada</td>
                            <td>In stock</td>
                            <td><input class="form-control" type="text" value="1" /></td>
                            <td class="text-right">124,90 €</td>
                            <td class="text-right"><button class="btn btn-sm btn-danger"><i class="fa fa-trash"></i> </button> </td>
                        </tr>
                        <tr>
                            <td><img src="https://dummyimage.com/50x50/55595c/fff" /> </td>
                            <td>Product Name Dada</td>
                            <td>In stock</td>
                            <td><input class="form-control" type="text" value="1" /></td>
                            <td class="text-right">124,90 €</td>
                            <td class="text-right"><button class="btn btn-sm btn-danger"><i class="fa fa-trash"></i> </button> </td>
                        </tr>
                        <tr>
                            <td><img src="https://dummyimage.com/50x50/55595c/fff" /> </td>
                            <td>Product Name Toto</td>
                            <td>In stock</td>
                            <td><input class="form-control" type="text" value="1" /></td>
                            <td class="text-right">33,90 €</td>
                            <td class="text-right"><button class="btn btn-sm btn-danger"><i class="fa fa-trash"></i> </button> </td>
                        </tr>
                        <tr>
                            <td><img src="https://dummyimage.com/50x50/55595c/fff" /> </td>
                            <td>Product Name Titi</td>
                            <td>In stock</td>
                            <td><input class="form-control" type="text" value="1" /></td>
                            <td class="text-right">70,00 €</td>
                            <td class="text-right"><button class="btn btn-sm btn-danger"><i class="fa fa-trash"></i> </button> </td>
                        </tr>
                        <tr>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td><strong>Total</strong></td>
                            <td class="text-right"><strong id="caunt">1</strong></td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <script>
            let i = 0;
            let result = 0;
            let cost = document.getElementsByClassName('p-cost');
            let val = document.getElementsByClassName('product-value');
            console.log(cost[1].innerHTML);
            for (let e of cost) {
                result += parseFloat(val[i].value) * parseFloat(e.innerHTML);
                console.log(val[i++].value);
                console.log(e.innerHTML);
                 console.log(result);
            }
            let res = document.getElementById('caunt');
            res.innerHTML = result;

        </script>
        <div class="col mb-2">
            <div class="row">
                <div class="col-sm-12  col-md-6">
                    <button class="btn btn-block btn-light">Continue Shopping</button>
                </div>
                <div class="col-sm-12 col-md-6 text-right">
                    <button href="makeOrder" class="btn btn-lg btn-block btn-success text-uppercase">Checkout</button>
                </div>
            </div>
        </div>
    </div>
</div>
