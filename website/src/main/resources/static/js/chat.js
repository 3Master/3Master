/**
 * Created by harttle on 15/4/25.
 */

$(function() {
    var enabled = window.WebSocket && true;
    if (!enabled) {
        console.error('websocket not supported,');
        return;
    }
    var self = this,
        conn = new WebSocket(encodeURI('ws://' + location.host + '/websocket'));

    self.connect = function(){
        conn.onopen = function() {
            console.log('websocket conn opened');
            send(location.href);
        };
        conn.onerror = function() {
            console.error('websocket error');
        };
        conn.onclose = function(event) {
            console.log('websocket conn closed:' + event.code);
            if (event.code !== 1006){
                console.log('reconnecting...');
                self.connect();
            }
        };
        this.conn.onmessage = function(message) {
            console.log(message.data);
            $msgList.append(message.data)
        };
    };

    self.send = function(msg) {
        self.conn && self.conn.send(typeof msg === "string" 
            ? msg 
            : JSON.stringify(msg));
    };

    self.connect();

    var $msgList = $('.comment-list');
    $('.btn-send').click(function() {
        var $this = $(this),
            $form = $this.closest('.msg-form'),
            $text = $form.find('.msg-input'),
            msg   = $text.val();

        $.post('/chat/:userId')
            .done(function(data) {
                $msgList.append(data)
            })
    });
});