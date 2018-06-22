(function () {
	"use strict";
	angular
		.module("PetStoreUi")
		.controller("PetsSalesCtrl", ["$rootScope", "$scope", "$http", "$state", PetsSalesCtrl]);

	function PetsSalesCtrl($rootScope, $scope, $http, $state) {
        var ctrl = this;
        ctrl.salesUrl = $rootScope.backend_protocol + "://" + $rootScope.backend_ip + ":" + $rootScope.backend_port + "/" + $rootScope.backend_context_path + "/sales";

        if ($state.params.pet !== undefined) {
          $scope.pet = $state.params.pet
        }

        ctrl.init = function() {
            $scope.sale = {};
            $scope.initialModel = angular.copy($scope.sale);
        }

        ctrl.reset = function() {
            var message = "This will reset the form. Proceed anyway?";
            $scope.modalWarning(message, "RESET")
                .then(function (response) {
                    if (response === true) {
                        $scope.sale = angular.copy($scope.initialModel);
                        // location.reload();
                        $scope.scrollTop();
                    }
                });
        }

        ctrl.cancel = function() {
            var message = "Your work will be lost. Proceed anyway?";
            $scope.modalWarning(message, "PROCEED")
                .then(function (response) {
                    if (response === true) {
                        $state.go("pets_review");
                        $scope.scrollTop();
                    }
                });
        }

        ctrl.getFormCtrl = function() {
            var retval = $scope.$$childHead
            if (retval) {
                retval = retval.formCtrl;
            }
            return retval;
        }

        ctrl.isValid = function() {
            var formCtrl = ctrl.getFormCtrl();
            if (formCtrl && formCtrl.$valid) {
                return true;
            }

            return false;
        }

    /**
     *  Sends a sale request to the backend!
     */
    ctrl.buy = function() {
          $scope.modalAlert("Are you sure you want to buy '" + $scope.pet.name+ "' ?", "BUY")
          .then(function (response) {
            if (response === true) {

              $scope.sale.pet = $scope.pet.id;
              var body = $scope.sale;
              var config = {
                headers: {
                  'Content-Type': 'application/json;charset=utf-8;'
                },
              }
              $http.post(ctrl.salesUrl, body, config)
              .then(function successCallback(response) {
                $state.go("pets_review");
                $scope.createToast(response.data.result + "! " + response.data.description)
              }, function errorCallback(response) {
                $scope.createToast(response.data.result + "! " + response.data.description)
              });
            }
          });
        }
  }
}());
