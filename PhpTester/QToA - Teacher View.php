<!DOCTYPE html><html class=''>
<?php
require_once("coreL.php");
submit();
header( "refresh:10;" );
?>
<head>
    <link type="text/css" rel="stylesheet" href="css/materialize.css"  media="screen,projection"/>
    <link type="text/css" rel="stylesheet" href="css/style.css"/>
</head>
<body>
<div id="frame">
    <div id="sidepanel">
        <div id="profile">
            <div class="wrap">
          <span><img id="profile-img" src="http://emilcarlsson.se/assets/mikeross.png" alt="" />
          <h6>Building IT Systems</h6></span>
            </div>
        </div>
        <div id="courses">
            <ul>
                <li class="contact active">
                    <div class="wrap">
                        <img src="http://emilcarlsson.se/assets/louislitt.png" alt="" />
                        <div class="meta">
                            <p class="name">Lecture Theater Name</p>
                        </div>
                    </div>
                </li>
            </ul>
        </div>
    </div>
    <div class="content">
        <div class="contact-profile">
            <div class="wrap"><span><img id="profile-img" src="http://emilcarlsson.se/assets/mikeross.png" alt="" />
          <h6>Lecture Theater Name</h6></span></div>
        </div>
        <div class="row">
            <div class="col m12">
                <div class="card">
                    <div class="card-title" style="margin-left:15px!important;padding-top:12px;">
                        Student ID
                    </div>
                    <div class="card-content">
                      <?php echo getQuestion(); ?>
                    </div>
                    <div class="card-action">
                <form method="post">
                            <input type="text" name="lMsg" placeholder="Write your reply...">
                            <button type="submit" name="btnLSubmit">Send</button>
                </form>
                        </div>
                    </div>

            </div>
        </div>
    </div>
</div>
<script src='https://code.jquery.com/jquery-2.2.4.min.js'></script>
<script >$(".messages").animate({ scrollTop: $(document).height() }, "fast");
    $("#profile-img").click(function() {
        $("#status-options").toggleClass("active");
    });

    $(".expand-button").click(function() {
        $("#profile").toggleClass("expanded");
        $("#courses").toggleClass("expanded");
    });

    function newMessage() {
        message = $(".message-input input").val();
        if($.trim(message) == '') {
            return false;
        }
        $('<li class="sent"><img src="http://emilcarlsson.se/assets/mikeross.png" alt="" /><p>' + message + '</p></li>').appendTo($('.messages ul'));
        $('.message-input input').val(null);
        $('.contact.active .preview').html('<span>You: </span>' + message);
        $(".messages").animate({ scrollTop: $(document).height() }, "fast");
    };

    $('.submit').click(function() {
        newMessage();
    });

    $(window).on('keydown', function(e) {
        if (e.which == 13) {
            newMessage();
            return false;
        }
    });
</script>
</body></html>