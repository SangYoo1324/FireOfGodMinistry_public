
let title = document.querySelector('.event_title_wrap');
let event_box = document.querySelectorAll('.event_box');
event_box.forEach((e)=>{
    observer.observe(e); // 변경된 부분
});
    observer.observe(title);
