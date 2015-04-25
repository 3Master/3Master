/**
 * Created by harttle on 15/4/25.
 */

function onRequest(){

    var $user = $(this),
        userId = $user.data('id');

    $.post( '/request/' + userId)
        .done(function(){

        })
        .fail(function(){
            console.error('request failed:', userId)
        })
}