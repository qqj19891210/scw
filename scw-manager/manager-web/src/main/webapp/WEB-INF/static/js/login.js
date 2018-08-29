$(function () {
   dologin();
});

function dologin() {
    var type = $(":selected").val();
    if (type == "user") {
        window.location.href = "main.html";
    } else {
        window.location.href = "index.html";
    }
}