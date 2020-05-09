function sendMessage(bandId, text) {
    let body = {
        bandId: bandId,
        text: text
    };

    $.ajax({
        url: "/WebApplicationForMetallists_war/chat?band_id=" + bandId,
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
        url: "/WebApplicationForMetallists_war/chat?band_id=" + bandId,
        method: "GET",
        dataType: "json",
        contentType: "application/json",
        success: function (response) {
            $('#messages').first().after('<li>' + response[0]['text'] + '</li>');
            receiveMessage(bandId);
        }
    })
}