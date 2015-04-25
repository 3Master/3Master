/**
 * Created by harttle on 15/4/25.
 */

$(function(){
    $('[data-id]').click(function(){
        var $user = $(this),
            userId = $user.data('id');

        $user.html('<i class="spinner spin"></i> 正在处理');

        $.post( '/request/' + userId)
            .done(function(){
                $user.removeClass('btn-default');
                $user.addClass('btn-success');
                $user.html('已请求拜师');
            })
            .fail(function(){
                $user.removeClass('btn-default');
                $user.addClass('btn-warning');
                $user.html('请求失败');
            })
    });
});