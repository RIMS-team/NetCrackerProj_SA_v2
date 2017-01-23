'use strict';

/**
 * NotebookController
 * @constructor
 */


(function () {
    var app = angular.module("notifications", ["ngSanitize","angularUtils.directives.dirPagination", "ui.bootstrap", "ui.grid", "ui.grid.selection", "ui.select", "ui.grid.autoResize"]);

    app.controller("NotificationController", function ($scope, $http, $uibModal, ordNotifyService) {
        var _this = this;

        ordNotifyService.loadUserList();

        $scope.fetchNotificationList = function() {
            $http.get('notification/all').success(function(notificationList){
                $scope.notifications = notificationList;
            });
        };

        $scope.fetchNotificationList();

        $scope.insertNewTemplete = function (editRec) {
            $http.post('notification/insert', editRec).success(function () {
            }).error(function () {
                console.log("Error sending insert request!");
            });
        };

        $scope.sort = function (keyname) {
            $scope.sortKey=keyname;
            $scope.reverse=!$scope.reverse;
        };


        _this.openEditor = function (templ) {
            var editRec = {};
            if (templ) {
                editRec.notif_num = templ.id;
                editRec.user_id = ordNotifyService.getUserIdByOrderId(templ.order.id);
                editRec.template = templ.template;
                //alert(ordNotifyService.getEmployeeIdByOrderId(templ.order.id));
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
                    $scope.insertNewTemplete(editRec);
                }
                else {
                    $scope.insertNewTemplete(editRec);
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