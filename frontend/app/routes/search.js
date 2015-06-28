import Ember from 'ember';

export default Ember.Route.extend({

  queryParams: {
    category: {
      refreshModel: true
    }
  },

  model: function (params) {

    if (!params.query)
      return


    var controller = this.controllerFor("search");

    controller.set("search", true);

    controller.set("query", params.query);

    var types = params.type.split(',');

    if(types.indexOf("anime") > -1){
      controller.set("isAnimeType", true);
    }
    if(types.indexOf("manga") > -1){
      controller.set("isMangaType", true);
    }

    if(types.indexOf("character") > -1){
      controller.set("isCharacterType", true);
    }

    if(types.indexOf("podcast") > -1){
      controller.set("isPodcastType", true);
    }

    if(types.indexOf("mangaka") > -1){
      controller.set("isMangakaType", true);
    }

    controller.set("type", params.type);

    controller.set("fields", params.fields);

    controller.set("render", params.render);


    return this.store.find('anime', {
      query: params.query,
      type: params.type,
      fields: params.fields,
      render: params.render
    }).then(
      function (animes) {

        if (animes.get("firstObject")) {
          controller.set("animeSelected", animes.get("firstObject"));
          controller.set("error", null);
        } else {
          controller.set("error", "We were not able to find any entries with the criterias you provided.");
        }

        return animes;
      }, function () {
        controller.set("error", "The server seems to be down... Please try once more or come back later.");
      }
    );
  },

  actions: {
    callBackend: function (valueQuery) {

      var timeout = this.controllerFor("search").get("timeout");
      var callback = this;

      if (timeout) {
        clearTimeout(timeout);
      }

      timeout = setTimeout(function () {
        if (valueQuery != "") {
          callback.refresh();
        }
      }, 800);

      this.controllerFor("search").set("timeout", timeout);
    }
  }
});
