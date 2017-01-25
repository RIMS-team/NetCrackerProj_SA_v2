'use strict';

/**
 * NotebookController
 * @constructor
 */


(function () {
    var app = angular.module("notifications", ["ngSanitize","angularUtils.directives.dirPagination", "ui.bootstrap"]);

    app.controller("NotificationController", function ($scope, $http, $uibModal, ordNotifyService) {
        var _this = this;

        ordNotifyService.loadNotifiTempList();

        $scope.pageSize = 11;
        $scope.temp_1=ordNotifyService.findNotifiTemp1(1);
        $scope.id=ordNotifyService.findNotifiTemp1(2);
        $scope.num=ordNotifyService.findNotifiTemp1(3);


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

        $scope.isSortKey = function(keyname) {
            return $scope.sortKey == keyname;
        }

        _this.openEditor = function (templ) {
            var editRec = {};
            if (templ) {
                editRec.notif_num = templ.id;
                editRec.user_id = ordNotifyService.getUserIdByOrderId(templ.order.id);
                editRec.template = ordNotifyService.findNotifiTemp1(1);
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

        _this.openEditorDef = function () {
                var template_1 = ordNotifyService.findNotifiTemp1(1);
                var template_2 = ordNotifyService.findNotifiTemp1(2);
                var template_3 = ordNotifyService.findNotifiTemp1(3);
                var editRec={}
                editRec.temp_1=template_1;
                editRec.id=template_2;
                editRec.num=template_3;
                $scope.edit=editRec;

            var uibModalInstance = $uibModal.open({
                animation: true,
                ariaLabelledBy: 'modal-title',
                ariaDescribedBy: 'modal-body',
                templateUrl: 'updateNotifTemplateDef.html',
                controller: 'updateNotifTemplateControllerTemp',
                resolve: {
                    editRecord: function () {
                        return $scope.edit;
                    }
                }
            });

            uibModalInstance.result.then(function (editRec) {
                //modal ok
                if (editRec) {
                   // $scope.insertNewTemplete(editRec);
                }
                else {
                    //$scope.insertNewTemplete(editRec);
                    // TODO: Call insert function()
                }
            }, function () {
                // modal cancel
            });
        };

        $scope.openUpdateEditor = function (templ) {
            _this.openEditor(templ);
        };
        $scope.openUpdateEditorDef = function () {
            _this.openEditorDef();
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

    app.controller('updateNotifTemplateControllerTemp', ['$scope','$uibModalInstance', 'editRecord', function ($scope, uibModalInstance, editRec) {
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


