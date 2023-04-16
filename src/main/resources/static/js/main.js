

// slideshow_animation(sub-method)   dots can be null
function slide_looping(slides,dots){
    for(let i = 0; i<slides.length; i++){
        setTimeout(function(){
            if(!$(slides.eq(i)).hasClass('active')){
                $(slides.eq(i)).addClass('active');
                console.log(`slide-${i}activated`);
                // for dots
                console.log(dots!=null);
                if(dots!=null){
                    console.log(i+"th slide for testimonial");
                    dots.eq(i).addClass('active');
                    dots.eq(i).siblings().removeClass('active');
                }

            }
            $(slides.eq(i)).siblings().removeClass('active');
            // console.log(`slide-${i}removed`);
        },6000*i);

    }

}



 function main_page_slideshow_animation(){
    let slides = $('.slide_wrap>*');
    let dots = $('.dots>ul>li');
    slide_looping(slides,dots);

    setInterval(function(){
        slide_looping(slides,dots);
    },18000);

}

main_page_slideshow_animation();


// Observation slide
let connect = document.querySelector('.connect>.container>p');
let connectTitle = document.querySelector('.connect>.container>h1');
let subscribeTitle = document.querySelector('.title_wrap>h4');
let subscribePtag = document.querySelector('.title_wrap>p');
let event_title = document.querySelector('.event_title_wrap');
let event_box = document.querySelectorAll('.event_box_wrap>.event_box');



let observer = new IntersectionObserver((e)=>{
    console.log(e);
    e.forEach((ele)=>{
        if(ele.isIntersecting){
            console.log(ele.isIntersecting);
            ele.target.className += ' active';
        }
        else
            ele.target.classList.remove('active');
                console.log("remove");
    });
});

observer.observe(connectTitle);
observer.observe(connect);
observer.observe(subscribeTitle);
observer.observe(subscribePtag);
event_box.forEach((e)=>{
    observer.observe(e);
});
observer.observe(event_title);

//subscribe btn
$('.subscribe_btn').on('click',function (){

    let data = {
        email : $('#subscribe_input').val()
    }

    console.log(JSON.stringify(data));

    $.ajax({
        type: "POST",
        url:"/api/subscribe",
        data: JSON.stringify(data),
        contentType:"application/json; charset=utf-8",
        // dataType: "json",
        success: function(){
            alert(`Subscription complete!`);
            window.location.reload();
        },
        error: function(err){
            console.log(err);
            alert("Please input valid email address!"+err);
       }
    });
});

//Event Modal
$('.view_detail').on('click',function() {
    console.log($(this).siblings().eq(1).children().eq(1).html());
    let title = $(this).siblings().eq(1).children().eq(0).html()
    let body = $(this).siblings().eq(3).html()
    console.log(body);
    $('.modal-title').html(title);
    $('.modal_con>.content_wrap').html(body);
});