console.log("Donation JS");
// donation_contents

$('.head_wrap>ul>li').on('click',function (){
    if($(this).hasClass('active')){
        $(this).removeClass('active');
    }
    else{
        $(this).siblings().removeClass('active');
        $(this).addClass('active');
        $('.main_wrap>ul>li').eq($(this).index()).addClass('active');
        $('.main_wrap>ul>li').eq($(this).index()).siblings().removeClass('active');
    }

});
