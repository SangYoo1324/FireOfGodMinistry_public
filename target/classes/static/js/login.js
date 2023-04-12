//Admin Login
$('.login_btn').on("click",()=>{
    let data = {
        username: $('#id_input').val(),
        password: $('#pw_input').val()
    }
    console.log(data);

    //connecting to rest controller
    $.ajax({
        type: "POST",
        url:"/api/login",
        data: JSON.stringify(data),
        contentType:"application/json; charset=utf-8",
        dataType: "json"
    }).done(function(resp){
        alert("Log-in Success");
        location.href="/page/main";
    }).fail(function(error){
        alert("Failed to Login"+JSON.stringify(error));
        console.log(JSON.stringify(error));
    });
});