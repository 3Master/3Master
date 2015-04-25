/**
 * Created by harttle on 15/4/25.
 */

$(function(){
    var socket = new SockJS('/portfolio', {debug: true}),
        stompClient = Stomp.over(socket);

    stompClient.connect({}, function(frame) {
        console.info('Connected ' + frame);

        stompClient.subscribe('/positions', function(message) {
            console.log(message.body);
        });
    }, function(error) {
        console.error('STOMP protocol error: ' + error);
    });

    var $msgList = $('.comment-list');
    $('.btn-send').click(function(){
        var $this = $(this),
            $form = $this.closest('.msg-form'),
            $text = $form.find('msg-input'),
            msg   = $text.val();

        $.post('/chat/:userId')
            .done(function(data){
                $msgList.append(data)
            })
    });
});