'use strict';

/**
 * NotebookController
 * @constructor
 */


(function () {
    var app = angular.module("notifications", ["ngSanitize","angularUtils.directives.dirPagination", "ui.bootstrap", "ui.grid", "ui.grid.selection", "ui.select", "ui.grid.autoResize"]);

    app.controller("NotificationController", function ($scope, $http, $uibModal, ordNotifyService) {
        var _this = this;

        $scope.fetchNotificationList = function() {
            $http.get('notification/all').success(function(notificationList){
                $scope.notifications = notificationList;
            });
        };

        $scope.fetchNotificationList();


        $scope.sort = function (keyname) {
            $scope.sortKey=keyname;
            $scope.reverse=!$scope.reverse;
        };

        _this.openEditor = function (templ) {
            var editRec = {};
            if (templ) {
                // TODO: editRec add fields from templ
                alert(ordNotifyService.getEmployeeIdByOrderId(templ.order.id));
            }

            var uibModalInstance = $uibModal.open({
                animation: true,
                ariaLabelledBy: 'modal-title',
                ariaDescribedBy: 'modal-body',
                templateUrl: 'updateNotifTemplate.html',
                controller: 'updateNotifTemplateController',
                resolve: {
                    editRecord: function () {
                        return editRec;
                    }
                }
            });

            uibModalInstance.result.then(function (editRec) {
                //modal ok
                if (editRec) {
                    // TODO: Call update function()
                }
                else {
                    // TODO: Call insert function()
                }
            }, function () {
                // modal cancel
            });
        };

        $scope.openUpdateEditor = function (templ) {
            _this.openEditor(templ);
        };


    });


    app.directive("notificationsList", function () {
        return {
            //restrict: "E",
            templateUrl: "notification/layout.html"
        }
    });

    app.controller('updateNotifTemplateController', ['$scope','$uibModalInstance', 'editRecord', function ($scope, uibModalInstance, editRec) {
        $scope.editRecord = editRec;

        $scope.ok = function () {
            // if (validation)
            uibModalInstance.close($scope.editRecord);
            // else
            //   show error msg
        };

        $scope.close = function () {
            uibModalInstance.dismiss('cancel');
        };
    }]);

})();


