

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


// Bell Btn slide
let connect = document.querySelector('.connect>.container>p');
let connectTitle = document.querySelector('.connect>.container>h1');
let subscribeTitle = document.querySelector('.title_wrap>h4');
let subscribePtag = document.querySelector('.title_wrap>p');
let observer = new IntersectionObserver((e)=>{
    console.log(e);
    e.forEach((ele)=>{
        if(ele.isIntersecting){
            ele.target.className += ' active';
        }
        else
            ele.target.classList.remove('active');
    });
});

observer.observe(connectTitle);
observer.observe(connect);
observer.observe(subscribeTitle);
observer.observe(subscribePtag);