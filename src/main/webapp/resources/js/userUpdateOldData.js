/**
 * Created by trvler135 on 12.01.2017.
 */
$(document).ready(function () {
    $(document).on('click', '.updateUserButton', function(){
        var data = $(this).parent().siblings();

        var id = data.eq(0).html();
        var fullName = data.eq(1).html();
        var telephone = data.eq(2).html();
        var email = data.eq(3).html();

        $('#update-user-old-email').val(email);

        $('#update-user-id').val(id).trigger('input');
        $('#update-user-full-name').val(fullName).trigger('input');
        $('#update-user-telephone').val(telephone).trigger('input');
        $('#update-user-email').val(email).trigger('input');
        $('#update-user-password').val('');
        $('#update-user-repeat-password').val('');
    });

    $(document).on('click', '#addUserForm-button', function() {
        $('#addUser').find('input').val('');
    });
});