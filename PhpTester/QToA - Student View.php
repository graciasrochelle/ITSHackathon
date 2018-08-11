<?php
require_once("coreS.php");
submit();
?>

<!DOCTYPE html>
<html class=''>
<head>
  <link type="text/css" rel="stylesheet" href="css/materialize.css"  media="screen,projection"/>
  <link type="text/css" rel="stylesheet" href="css/style.css"/>
</head><body>

  <div id="frame">
    <div id="sidepanel">
      <div id="profile">
        <div class="wrap">
          <img id="profile-img" src="http://emilcarlsson.se/assets/mikeross.png" class="lecAc" alt="" />
          <h6>Mike Ross</h6>
        </div>
      </div>
      <div id="courses">
        <ul>
          <li class="contact active">
            <div class="wrap">
              <img src="http://emilcarlsson.se/assets/louislitt.png" alt="" />
              <div class="meta">
                <h6 class="name">Lecture Theater Name</h6>
              </div>
            </div>
          </li>
        </ul>
      </div>
      <div id="bottom-bar" >
        <button id="addcourse" onclick="window.location.href='http://hackathon.gayurug.com'">
          <i class="fa fa-user-plus fa-fw" aria-hidden="true"></i>
          <span>Log Out</span></button>

        </div>
      </div>
      <div class="content">
        <div class="contact-profile">
          <div class="wrap"><span><img id="profile-img" src="http://emilcarlsson.se/assets/mikeross.png" alt="" />
            <h6>Lecture: Building IT Systems.</h6></span></div>
          </div>
          <div class="row">
            <div class="col m12">
              <div class="card">
                <div class="card-title" style="margin-left:15px!important;padding-top:12px;">
                  Course Name
                </div>
                <div class="card-content">
                  <p><?php echo getQuestion();?></p>
                  </div>
                  <div class="card-action">
                    <form method="post">
                      <input type="text" name="sMsg" placeholder="Write your reply...">
                        <select style="width:20%; margin-right: 2%;" name="sLang">
                            <option value="en">English</option>
                            <option value="zh">Chinese</option>
                        </select>
                      <button style="width: 15%;" type="submit" name="btnSubmit">Send</button>
                    </form>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <script src='https://production-assets.codepen.io/assets/common/stopExecutionOnTimeout-b2a7b3fe212eaa732349046d8416e00a9dec26eb7fd347590fbced3ab38af52e.js'></script><script src='https://code.jquery.com/jquery-2.2.4.min.js'></script>
        <script >
        $(".messages").animate({ scrollTop: $(document).height() }, "fast");

        $("#profile-img").click(function() {
          $("#status-options").toggleClass("active");
        });

        $(".expand-button").click(function() {
          $("#profile").toggleClass("expanded");
          $("#courses").toggleClass("expanded");
        });

        $("#status-options ul li").click(function() {
          $("#profile-img").removeClass();
          $("#status-lecAc").removeClass("active");
          $("#status-away").removeClass("active");
          $("#status-busy").removeClass("active");
          $("#status-lecInAc").removeClass("active");
          $(this).addClass("active");

          if($("#status-lecAc").hasClass("active")) {
            $("#profile-img").addClass("lecAc");
          } else if ($("#status-away").hasClass("active")) {
            $("#profile-img").addClass("away");
          } else if ($("#status-busy").hasClass("active")) {
            $("#profile-img").addClass("busy");
          } else if ($("#status-lecInAc").hasClass("active")) {
            $("#profile-img").addClass("lecInAc");
          } else {
            $("#profile-img").removeClass();
          };

          $("#status-options").removeClass("active");
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

        function boot(){
          bootbox.prompt({
            title: "This is a prompt with select!",
            inputType: 'select',
            inputOptions: [
              {
                text: 'Choose one...',
                value: '',
              },
              {
                text: 'Choice One',
                value: '1',
              },
              {
                text: 'Choice Two',
                value: '2',
              },
              {
                text: 'Choice Three',
                value: '3',
              }
            ],
            callback: function (result) {
              console.log(result);
            }
          })
        };
        </script>
      </body></html>
