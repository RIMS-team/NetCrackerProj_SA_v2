/**
 * Created by Kristina on 18.01.2017.
 */
$(document).ready(function () {
    var $attach = $('#attach-project-file'),
        $remove = $('#remove-project-file'),
        $name = $('#attached-project-file'),
        $logs = $('#import-logs');

    $remove.hide();

    $attach.on('change', function() {
        var val = $(this).val().replace("C:\\fakepath\\", "");
        if (val !== '') {
            $name
                .hide()
                .text(val)
                .fadeIn();
            $remove.fadeIn();
        } else {
            $name
                .hide()
                .text('Click to select a file')
                .fadeIn();
            $remove.fadeOut();
        }
    });

    $remove.on('click', function(e) {
        e.preventDefault();
        e.stopPropagation();

        NProgress.remove();

        $attach
            .val('')
            .change();
    });

    $logs.animate({
        scrollTop: $logs[ 0].scrollHeight
    }, 'slow');

});

var myApp = angular.module('AngularSpringApp');

myApp.directive('fileModel', ['$parse', function ($parse) {
    return {
        restrict: 'A',
        link: function(scope, element, attrs) {
            var model = $parse(attrs.fileModel);
            var modelSetter = model.assign;

            element.bind('change', function(){
                scope.$apply(function(){
                    modelSetter(scope, element[0].files[0]);
                });
            });
        }
    };
}]);

myApp.service('fileUpload', ['$http', function ($http) {
    this.uploadFileToUrl = function(scope, file, uploadUrl){
        NProgress.start();
        var fd = new FormData();
        fd.append('file', file);
        $http.post(uploadUrl, fd, {
            transformRequest: angular.identity,
            headers: {'Content-Type': undefined}
        })
            .success(function(data){
                var dataArr = data.split('|');
                for (i = 0; i < dataArr.length; i++) {
                    dataArr[i] = dataArr[i].replace('*', '<span class="glyphicon glyphicon-ok icon-green" aria-hidden="true"></span>');
                    dataArr[i] = dataArr[i].replace('!', '<span class="glyphicon glyphicon-remove icon-red" aria-hidden="true"></span>');
                    dataArr[i] = dataArr[i].replace('?', '<span class="glyphicon glyphicon-info-sign icon-yellow" aria-hidden="true"></span>');
                }
                NProgress.done();
                scope.data = dataArr;
            })
            .error(function(data){
                NProgress.done();
                scope.data = ['<span class="glyphicon glyphicon-remove icon-red" aria-hidden="true"></span> Please choose the CSV-file.'];
            });
    }
}]);

myApp.controller('fileUploadController', ['$scope', 'fileUpload', function($scope, fileUpload){
    $scope.uploadFile = function(id, type){
        var file = $scope.myFile;
        console.log('file is ' );
        console.dir(file);
        var uploadUrl = "/import-csv/" + id + "/" + type;
        fileUpload.uploadFileToUrl($scope, file, uploadUrl);
    };

}]);