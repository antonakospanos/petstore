(function () {
  "use strict";
  angular
  .module("PetStoreUi")
  .controller("PetsReviewCtrl",
      ["$rootScope", "$scope", "$http", "$state", PetsReviewCtrl]);

  function PetsReviewCtrl($rootScope, $scope, $http, $state) {
    var ctrl = this;
    var petsUrl = $rootScope.backend_api + "/pets";

    // Initialization
    ctrl.init = function () {
      ctrl.listPets();
    }

    ctrl.filterDogs = function () {
      // TODO: Filter dogs
    }

    ctrl.filterCats = function () {
      // TODO: Filter cats
    }

    ctrl.filterFish = function () {
      // TODO: Filter fish
    }

    ctrl.sortPrices = function () {
      // TODO: Sort pets by ASC or DESC price
    }

    ctrl.sortDates = function () {
      // TODO: Sort pets by ASC or DESC date
    }

    /**
     *  List Pet Store pets!
     */
    ctrl.listPets = function () {
      $http({
        url: petsUrl
      }).then(function successCallback(response) {
        $scope.model = {data: response.data};
        console.log(response.data)
      }, function errorCallback(response) {
        $scope.model = {data: response.data};
      });
    }

    /**
     *  Hide buy button to un-subscribed users!
     *
     * @param item
     */
    ctrl.hideButton = function (item) {
      return !$http.defaults.headers.common.Authorization
    }

    /**
     * Navigate to Buy form!
     *
     * @param item
     */
    ctrl.buy = function (item) {
      $state.go("pets_buy", {
        pet: item
      });
    }
  }
}());
