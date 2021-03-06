function sendMessage(pageId, text) {
    let body = {
        pageId: pageId,
        text: text
    };

    $.ajax({
        url: "/WebApplicationForMetallists_war/messages",
        method: "POST",
        data: JSON.stringify(body),
        contentType: "application/json",
        dataType: "json",
        complete: function () {
            if (text === 'New user') {
                receiveMessage(pageId)
            }
        }
    });
}

// LONG POLLING
function receiveMessage(pageId) {
    $.ajax({
        url: "/WebApplicationForMetallists_war/messages?pageId=" + pageId,
        method: "GET",
        dataType: "json",
        contentType: "application/json",
        success: function (response) {
            $('#messages').first().after('<li>' + response[0]['text'] + '</li>');
            receiveMessage(pageId);
        }
    })
}