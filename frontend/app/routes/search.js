import Ember from 'ember';

export default Ember.Route.extend({

  queryParams: {
    category: {
      refreshModel: true
    }
  },

  model: function(params) {

    this.controllerFor("search").set("query", params.query);
    this.controllerFor("search").set("type", params.type);
    this.controllerFor("search").set("fields", params.fields);
    this.controllerFor("search").set("render", params.render);


    //set the first object of the list to selectedAnime so we won't have an ugly empty display
    var controller = this.controllerFor("search");

    return this.store.find('anime', {query : params.query, type: params.type, fields: params.fields, render: params.render}).then(
      function(animes){
        console.log("Parsing result from backend");

        console.debug(Ember.inspect(animes.get("firstObject")));
        controller.set("animeSelected", animes.get("firstObject"));

        return animes;
      });
  },

  actions: {
    callBackend: function (valueQuery) {

      if(valueQuery != "")
        this.refresh();
    }
  }

});
