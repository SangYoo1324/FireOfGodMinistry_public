console.log("admin page");

// delete btn
$('.delete_btn').on("click",function(e){
    console.log(e.target);
    let data = {
        id : $(this).parent().siblings().eq(0).text()
    }
    console.log(data.id);

    $.ajax({
        type: "DELETE",
        url:"/api/users",
        data: JSON.stringify(data),
        contentType:"application/json; charset=utf-8",
        // dataType: "json"
    }).done(function(resp){
        alert(`Successfully Deleted Data!`);
        window.location.reload();
    }).fail(function(err){
        alert("Oops something went wrong")
    });
});

//update btn

$('.update_btn').on("click",function(e){
    let data = {
        subType: $(this).attr("data-subType"),
        emailAddr: $(this).attr("data-emailAddr")
    }

    if(data.subType =="TYPE_SUBSCRIPTION"){
        $(".update_category").val(1);
        $(".update_emailAddr").val(data.emailAddr);
    }else{
        $(".update_category").val(2);
        $(".update_emailAddr").val(data.emailAddr);
    }
    console.log(data.subType+"////"+data.emailAddr);

});
$('.save_update_sub').click(function (){
    let data = {
        subType: $('.update_category').val(),
        emailAddr: $('.update_emailAddr').val()
    }
   console.log(data.subType,+"///////////"+ data.emailAddr);
    $.ajax({
        type: "PATCH",
        url:"/api/users",
        data: JSON.stringify(data),
        contentType:"application/json; charset=utf-8",
        // dataType: "json"
    }).done(function(resp){
        alert(`Successfully Updated Data!`);
        window.location.reload();
    }).fail(function(err){
        alert("Oops something went wrong")
    });
});
//save update btn_modal

//Thumbnail snapshot
function setThumbnail(event){
    let reader = new FileReader();

    reader.onload = function(event){
        let img = document.createElement("img");
        img.setAttribute("src",event.target.result);
        img.style.position= 'absolute';
        let imgs = document.querySelectorAll('.img_wrap>img');
        if(imgs.length>1){
            imgs[1].remove();
        }

        document.querySelector(".img_wrap").appendChild(img);
    };



    reader.readAsDataURL(event.target.files[0]);
}


// Sending EventPost
$('.submit-btn').on('click',function(){
    let form_data_with_pic = new FormData();

    form_data_with_pic.append("title",$('.post_title').val());
    form_data_with_pic.append("subTitle",$('.post_sub_title').val());
    form_data_with_pic.append("body",editor.getData());
    form_data_with_pic.append("date",$('#date').val());

    //append thumbnail data
    let file = $('#file')[0].files[0];
    form_data_with_pic.append("file",file);
    console.log(file);

    console.log(form_data_with_pic.get("title"));
    console.log(form_data_with_pic.get("subTitle"));
    console.log(form_data_with_pic.get("body"));
    console.log(form_data_with_pic.get("date"));
    console.log(form_data_with_pic.get("file"));

    $.ajax({
        data: form_data_with_pic,
        type: "POST",
        url: "/api/article",
        cache: false,
        contentType: false,
        enctype: 'multipart/form-data',
        processData: false,
        success: function (url) {
            alert("Success uploading event POST :::::" );
            window.location.reload();
        },
        error: function (err) {
            alert("error occured. Please check if you uploaded the pic or pushed no image button");
        }
    });
});





// CK5 Editor
// CKEditor
let editor;
ClassicEditor
    .create( document.querySelector( '#writeEditor' ) , {
        mediaEmbed: {
            previewsInData:true
        },

        fontsize: {
            options: [
                10,
                11,
                12
            ]
        }
        // toolbar: {
        //     items:[
        //         'undo', 'redo',
        //         '|', 'heading',
        //         '|', 'fontfamily', 'fontsize', 'fontColor', 'fontBackgroundColor',
        //         '|', 'bold', 'italic', 'strikethrough', 'subscript', 'superscript', 'code',
        //         '|', 'link', 'uploadImage', 'blockQuote', 'codeBlock',
        //         '|', 'bulletedList', 'numberedList', 'todoList', 'outdent', 'indent'
        //     ]
        // }


        ,

        ckfinder: {
            uploadUrl: '/api/image'
        }

    } )
    .then( newEditor => {
        editor = newEditor;
    } )

    .then( editor => {
        window.editor = editor;

    } )
    .catch( error => {
        console.error( 'Oops, something went wrong!' );
        console.warn( 'Build id: g64ljk55ssvc-goqlohse75uw' );
        console.error( error );
    } );