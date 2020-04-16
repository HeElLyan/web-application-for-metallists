function sendMessage(bandId, text) {
    let body = {
        pageId: bandId,
        text: text
    };

    $.ajax({
        url: "/WebApplicationForMetallists_war/messages",
        method: "POST",
        data: JSON.stringify(body),
        contentType: "application/json",
        dataType: "json",
        complete: function () {
            if (text === 'Login') {
                receiveMessage(bandId)
            }
        }
    });
}

// LONG POLLING
function receiveMessage(bandId) {
    $.ajax({
        url: "/WebApplicationForMetallists_war/messages?bandId=" + bandId,
        method: "GET",
        dataType: "json",
        contentType: "application/json",
        success: function (response) {
            $('#messages').first().after('<li>' + response[0]['text'] + '</li>');
            receiveMessage(bandId);
        }
    })
}