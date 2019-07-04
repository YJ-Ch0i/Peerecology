$(function() {
    $("#addlist").click(function() {
      var str = $("#playlist_add").serialize();
      
      $.ajax({
        type:"POST",
        url:"/YongTube_Project/AddPlaylist.pl",
        contentType: "application/x-www-form-urlencoded",
        data: str,
        datatype:"json",
        success: function(data) {
          alert(data.result);			
        },
        error: function(e) {
          alert("에러발생");
        }			
      });
    });
});