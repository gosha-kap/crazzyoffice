
$(function () {
    $('table td button').click(function () {
        var str = $(this).attr('data-target');
        alert(str+"111");
        if(str == "#deleteModal")        {
            $('#del_name').text($(this).attr('data-content'));
            $('#del_name').attr('data-id',$(this).attr('data-id'));
            alert("3");
        }
        else if (str == "#modalEdit"){
            $('#edit_name').attr('data-id',$(this).attr('data-id'));
            alert("2");
        }


       })
});




$(function () {
    $('#modalEdit').on('show.bs.modal', function () {
        var strId =  $('#edit_name').attr('data-id');
        alert("4");
        $.ajax({
            url: '/pologaya/'+strId,
            type: 'get',
            dataType: 'json',
            statusCode: {
                200: function(response) {
                    alert(response.toString());
                }
            }
        });
    })
})


$(function () {
    $('#del_butt').click(function () {
         var strId =  $('#del_name').attr('data-id');
         $.ajax({
            url: '/pologaya/'+strId,
            type: 'delete',
            dataType: 'json',
             statusCode: {
                 204: function() {
                     location.reload();
                 }
             }
        });
    })
});


