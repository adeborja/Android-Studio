/*BLOG JS*/

var $grid;
var $contact;

$(window).load(function(){

    //$('[data-toggle="tooltip"]').tooltip();

    $( ".blog-post-body .download" ).click(function(event) {
        event.preventDefault();
       $( ".blog-post-body .wpcf7 form" ).slideDown( "slow", function() {
         // console.log('Animation complete');
       });

      /*if ( $( ".blog-post-body .wpcf7" ).is( ":hidden" ) ) {
        $( ".blog-post-body .wpcf7 form" ).slideDown( "slow" );
      } */
   

    });

});

var conter = 2;
var do_it = true;
var obj;

//
//PRELOADING IMAGES
function pastePostsOnce($preload, json, callType){

    var $images = $preload.find('img');
    var totalImages = $images.length;
    var imagesLoaded = 0;
    var timer = setInterval(function(){
        if(totalImages == 0){
            clearInterval(timer);
            do_it = true;
            $preload.empty();
            doGrid(json, callType);
        }else{
            for(var i = 0; i < totalImages; i++){
                if($images[i].complete){
                    imagesLoaded++;
                    if(imagesLoaded == totalImages){
                        clearInterval(timer);
                        do_it = true;
                        $preload.empty();
                        doGrid(json, callType);
                    }
                }
            }
        }
    }, 300);

}

function doGrid(json, callType){
    if(callType == 'filter'){
        //FOR FILTERS
        $.each(json, function(key, item){
            $item = $(item);
            var blog_grid = document.querySelector('.blogs-container');
            salvattore.appendElements(blog_grid,  $item);
        });
    }else if(callType == 'scroll'){
        //FOR SCROLLING BLOG
        conter++;
        $.each(json, function(key, item){
            $item = $(item);
            var blog_grid = document.querySelector('.blogs-container');
            salvattore.appendElements(blog_grid,  $item);
        });
    }
    $('.no-posts-to-show').remove();
    $('#blog-loader').hide();
}

function requestPosts(data, callType){
    $('#blog-loader').show();
    $.post( mobomo_admin_ajax_url , data, function(response) {

        var response_data = JSON && JSON.parse(response) || $.parseJSON(response);
        var posts_list    = response_data.posts;

        if(posts_list ){
            //Images Preloading
            $.each(posts_list, function(key, item) {
                $item = $(item);
                $('#preload').append($item.find('img'));
            });
            //Paste posts once
            pastePostsOnce($('#preload'), posts_list, callType);
        }else{
            $('.no-results-to-show').remove();
            $('.blogs-container').after('<div class="no-results-to-show">Sorry, no results...</div>');
            setTimeout(function(){ $('.no-results-to-show').fadeOut() }, 5000);
            $('#blog-loader').hide();
        }
    });
}

$(window).load(function(){

    $contact = $('#contact-area');
    //WOW EFFECT
    /*new WOW({
        offset: 50
    }).init();*/

});

$(window).scroll(function(){

    var scroll = $(this).scrollTop();
    $contact   = $('#contact-area');
    var end_position = $contact.position() || $('#footer').position();
    if( end_position.top-$(window).height() < scroll && do_it == true){

        do_it = false;
        var search = (typeof search_params != 'undefined')?search_params:false;
        // Information of our Request
        var data = {
            'action': 'bordoni_query_posts',
            'qty'   : content_load_quantity,
            'paged' : conter,
            'load_content_type' : load_content_type,
            'load_content_type_value' : load_content_type_value,
            'date_query_type'         : date_query_type,
            'search_params' : search
        };
        requestPosts(data, 'scroll');
    }
});
