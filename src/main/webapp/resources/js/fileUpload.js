/**
 * Created by Kristina on 18.01.2017.
 */
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
    this.uploadFileToUrl = function(file, uploadUrl){
        var fd = new FormData();
        fd.append('file', file);
        $http.post(uploadUrl, fd, {
            transformRequest: angular.identity,
            headers: {'Content-Type': undefined}
        })
            .success(function(){
            })
            .error(function(){
            });
    }
}]);

myApp.controller('fileUploadController', ['$scope', 'fileUpload', function($scope, fileUpload){

    $scope.uploadFile = function(type){
        var file = $scope.myFile;
        console.log('file is ' );
        console.dir(file);
        var uploadUrl = "/import-csv/" + type;
        fileUpload.uploadFileToUrl(file, uploadUrl);
    };

}]);