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