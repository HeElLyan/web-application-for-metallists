function sendMessage(bandId, text) {
    let body = {
        band_id: bandId,
        text: text
    };

    $.ajax({
        url: "/WebApplicationForMetallists_war/chat",
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
        url: "/WebApplicationForMetallists_war/chat?bandId=" + bandId,
        method: "GET",
        dataType: "json",
        contentType: "application/json",
        success: function (response) {
            $('#messages').first().after('<li>' + response[0]['text'] + '</li>');
            receiveMessage(bandId);
        }
    })
}