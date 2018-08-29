$(function () {
    alert(1);
    $(".thumbnail img").css("cursor", "pointer");
    $(".thumbnail img").click(function () {
        window.location.href = "project.html";
    });
});