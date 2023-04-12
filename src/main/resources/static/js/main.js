

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
        },4000*i);

    }

}



 function main_page_slideshow_animation(){
    let slides = $('.slide_wrap>*');
    // let dots = $('.main-page__slide_dot >*');
    slide_looping(slides,null);

    setInterval(function(){
        slide_looping(slides,null);
    },12000);

}

main_page_slideshow_animation();


// Bell Btn slide