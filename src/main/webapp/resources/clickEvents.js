
$(function () {
    $('table td button').click(function () {
        var str = $(this).attr('data-target');

        if(str == "#deleteModal")        {
            $('#del_name').text($(this).attr('data-content'));
            $('#del_name').attr('data-id',$(this).attr('data-id'));
            alert("3");
        }
        else if (str == "#modalEdit"){
            $('#edit_name').attr('data-id',$(this).attr('data-id'));

        }


       })
});




$(function () {
    $('#modalEdit').on('show.bs.modal', function () {
        var strId =  $('#edit_name').attr('data-id');
        var checked;

        $.ajax({
            url: '/pologaya/'+strId,
            type: 'get',
            dataType: 'json',
            statusCode: {
                200: function(response) {

                    if(response.autorised) checked = "checked";
                    $('#first').val(response.first);
                    $('#last').val(response.last);
                    $('#autorised').attr("checked", response.autorised);
                    $('#employee_id').val(response.id);

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

$(function () {
    $('#edit_butt').click(function () {
        $.ajax({
            url:'/pologaya',
            method:"POST",
            data:$('#edit_form').serialize(),

            statusCode: {
                200: function() {
                    location.reload();
                }
            }
        });
    })
});




