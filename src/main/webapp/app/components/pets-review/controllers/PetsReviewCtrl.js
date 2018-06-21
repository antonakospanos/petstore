(function () {
  "use strict";
  angular
  .module("PetStoreUi")
  .controller("PetsReviewCtrl",
      ["$rootScope", "$scope", "$http", "$state", PetsReviewCtrl]);

  function PetsReviewCtrl($rootScope, $scope, $http, $state) {
    var ctrl = this;
    var listPetsUrl = $rootScope.backend_protocol + "://"  + $rootScope.backend_ip + ":" + $rootScope.backend_port + "/"  + $rootScope.backend_context_path + "/pets";

    if ($state.params.publisher !== undefined && $state.params.publisher.id !== undefined) {
      listPetsUrl = listPetsUrl + "?userId=" + $state.params.publisher.id;
      $scope.publisher = $state.params.publisher.name
    }

    // Initialization
    ctrl.init = function () {
      ctrl.listPets();
    }

    // Sorting states
    var sortedByLikesDesc = false;
    var sortedByDatesDesc = false;

    ctrl.sortNames = function () {
      if (ctrl.sortedByLikesDesc === true) {
        ctrl.sortNamesAsc();
      } else {
        ctrl.sortNamesDesc();
      }
    }

    ctrl.sortDates = function () {
      if (ctrl.sortedByDatesDesc === true) {
        ctrl.sortDatesAsc();
      } else {
        ctrl.sortDatesDesc();
      }
    }

    ctrl.sortNamesDesc = function () {
      ctrl.sortedByLikesDesc = true;
      ctrl.sortedByHatesDesc = false;
      ctrl.sortedByDatesDesc = false;
      $scope.model.data.sort(function (a, b) {
        var likesA = a && a.likes ? a.likes : 0;
        var likesB = b && b.likes ? b.likes : 0;
        if (likesA < likesB) {
          return 1;
        }
        if (likesB < likesA) {
          return -1;
        }
        return 0;
      });
    }

    ctrl.sortDatesDesc = function () {
      ctrl.sortedByLikesDesc = false;
      ctrl.sortedByHatesDesc = false;
      ctrl.sortedByDatesDesc = true;
      $scope.model.data.sort(function (a, b) {
        var dateA = a && a.publicationDate ? a.publicationDate : 0;
        var dateB = b && b.publicationDate ? b.publicationDate : 0;
        if (dateA < dateB) {
          return 1;
        }
        if (dateB < dateA) {
          return -1;
        }
        return 0;
      });
    }

    ctrl.sortNamesAsc = function () {
      ctrl.sortedByLikesDesc = false;
      ctrl.sortedByHatesDesc = false;
      ctrl.sortedByDatesDesc = false;
      $scope.model.data.sort(function (a, b) {
        var likesA = a && a.likes ? a.likes : 0;
        var likesB = b && b.likes ? b.likes : 0;
        if (likesA < likesB) {
          return -1;
        }
        if (likesB < likesA) {
          return 1;
        }
        return 0;
      });
    }

    ctrl.sortDatesAsc = function () {
      ctrl.sortedByDatesDesc = false;
      ctrl.sortedByHatesDesc = false;
      ctrl.sortedByDatesDesc = false;
      $scope.model.data.sort(function (a, b) {
        var dateA = a && a.publicationDate ? a.publicationDate : 0;
        var dateB = b && b.publicationDate ? b.publicationDate : 0;
        if (dateA < dateB) {
          return -1;
        }
        if (dateB < dateA) {
          return 1;
        }
        return 0;
      });
    }

    /**
     *  List Pet Store pets!
     */
    ctrl.listPets = function () {
      $http({
        url: listPetsUrl
      }).then(function successCallback(response) {
        $scope.model = {data: response.data};
        console.log(response.data)
        ctrl.sortNamesDesc();
      }, function errorCallback(response) {
        $scope.model = {data: response.data};
      });
    }

    /**
     *  Hide voting buttons to un-subscribed users!
     *
     * @param item
     */
    ctrl.hideButtons = function (item) {
      return !$http.defaults.headers.common.Authorization
    }
  }
}());