$(document).ready(function () {
    $('.prod-id').click(function () {
        var prodId = $(this).attr('id');
        console.log(event.target.getAttribute('id'));
        console.log($(this).attr('id'));
        var data = {'product-id': prodId};
        $.ajax({
            type: 'POST',
            url: '/addProduct',
            data: data,
            dataType: 'json',
            success: function () {
                console.log('success !!!!!!!!!!!!!!!!');
            }
        });
//        $.ajax({
//            type: 'POST',
//            url: '/addProduct',
//            data: data,
//            contentType: 'application/json; charset=utf-8',
//            dataType: 'json',
//            success: function () {
//                console.log('success !!!!!!!!!!!!!!!!')
//            }
//        });
//        $.ajax()({
//            method: 'POST',
//            url: '/addProduct',
//            data: data,
//            contentType: 'application/json; charset=utf-8',
//            dataType: 'json',
//            success:function () {
//                console.log('success !!!!!!!!!!!!!!!!')
//            }
//        })
    });
});


// <input class='product-id' value='${product.cost}' type='hidden'/>







