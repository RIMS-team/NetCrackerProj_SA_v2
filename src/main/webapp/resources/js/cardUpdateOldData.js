/**
 * Created by Admin on 13.01.2017.
 */
$(document).ready(function () {
    $(document).on('click', '.updateCardButton', function(){
        var data = $(this).parent().siblings();

        var id = data.eq(0).html();
        var statusName = data.eq(1).html();
        var inventoryNum = data.eq(2).html();

        $('#update-card-id').val(id).trigger('input');
        $('#update-card-statusName').val(statusName).trigger('input');
        $('#update-card-inventoryNum').val(inventoryNum).trigger('input');;


        $(document).on('click', '#add-card-button', function() {
            $('#addCard').find('input').val('');
        });

    });
});