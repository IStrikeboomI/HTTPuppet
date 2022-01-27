function sendOperation(JSONData) {
    const xhttp = new XMLHttpRequest();
    xhttp.open("POST", "/handleoperation");
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send(JSONData);
}