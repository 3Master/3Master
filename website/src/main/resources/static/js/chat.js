/**
 * Created by harttle on 15/4/25.
 */

$(function() {
    var $msgList     = $('.comment-list'),
        remoteId     = $('input#remote-id').val(),
        remoteName   = $('input#remote-name').val(),
        remoteAvatar = $('input#remote-avatar').val(),
        localId      = $('input#local-id').val(),
        localName    = $('input#local-name').val(),
        localAvatar  = $('input#local-avatar').val();

    $('.btn-send').click(function() {
        var $this = $(this),
            $form = $this.closest('.msg-form'),
            $text = $form.find('.msg-input'),
            msg   = $text.val();
        $text.val('').focus();
        render(msg, true);
        $.post('/chat/' + remoteId, {
            content: msg
        });
    });

    function render(content, isMine) {
        var avatarUrl = isMine ? localAvatar : remoteAvatar;
        avatarUrl = avatarUrl || '/img/default-avatar.jpeg';

        var avatar = '<div class="avatar"><a><img src="' + avatarUrl + '"></a></div>',
            body   = '<div class="body"><p class="content">' + content + '</p></div>';

        var str = '<div class="comment-list-item' +
            (isMine ? ' inverse' : '')
            +'">' +
            (isMine ? body + avatar : avatar + body) +
            '</div>';

        $msgList.append($(str))
    }

    poll();

    function poll() {
        $.get('/chat/' + remoteId + '/messages')
            .done(function(messages) {
                if(!messages || !messages.length) return;

                console.log('msg received:', messages);
                messages.map(function(m) {
                    render(m, false);
                });
            })
            .always(function(){
                setTimeout(poll, 2000)
            });
    }
});

function webSocket(){
    //var enabled = window.WebSocket && true;
    //if (!enabled) {
    //    console.error('websocket not supported,');
    //    return;
    //}
    //var self = this,
    //    conn = new WebSocket(encodeURI('ws://' + location.host + '/chat'));
    //
    //self.connect = function() {
    //    conn.onopen = function() {
    //        console.log('websocket conn opened');
    //        self.send(location.href);
    //    };
    //    conn.onerror = function() {
    //        console.error('websocket error');
    //    };
    //    conn.onclose = function(event) {
    //        console.log('websocket conn closed:' + event.code);
    //        if (event.code !== 1006) {
    //            console.log('reconnecting...');
    //            self.connect();
    //        }
    //    };
    //    conn.onmessage = function(message) {
    //        console.log(message.data);
    //        render(message.data, false);
    //    };
    //};
    //
    //self.send = function(msg) {
    //    self.conn && self.conn.send(typeof msg === "string"
    //        ? msg
    //        : JSON.stringify(msg));
    //};
    //
    //self.connect();
}