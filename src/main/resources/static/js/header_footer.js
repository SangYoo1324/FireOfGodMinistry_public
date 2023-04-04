console.log("main js");

//pc mode more btn
$('.pc_more_btn').on('click',function (){

    if($(this).hasClass('active'))
    $(this).removeClass('active');
    else
        $(this).addClass('active');
});


// sideBar trigger
$('.menu_expansion_burger>i').on('click',function(){
   $('.mobile_side_bar').addClass('active');
});
//sideBar X btn
$('.mobile_side_bar,.mobile_side_bar__head>i').on('click',function(){
    $('.mobile_side_bar').removeClass('active');
});

$('.mobile_side_bar__contents').on('click',function (e){
        e.stopPropagation();
});

$('.more_btn').on('click',function (e){

    console.log($(this).hasClass('active'));
    if($(this).hasClass('active')){
        $(this).removeClass('active');

    } else{
        $(this).addClass('active');
    }

});


//bell & search btn
$('.fa-bell').on('mouseover',function (){
    console.log("click");
    $(this).addClass('fa-shake');

});
$('.fa-magnifying-glass').on('mouseover',function (){
    console.log("click");
    $(this).addClass('fa-beat-fade');
});

$('.fa-bell').on('mouseout',function (){
    console.log("click");
    $(this).removeClass('fa-shake');

});
$('.fa-magnifying-glass').on('mouseout',function (){
    console.log("click");
    $(this).removeClass('fa-beat-fade');
});


