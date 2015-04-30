import Ember from 'ember';

export default Ember.Route.extend({

  queryParams: {
    category: {
      refreshModel: true
    }
  },

  actions: {
    changeLayoutToList: function(){
      this.controllerFor("search").set('layoutList', true);
    }.observes('controller.layoutList'),

    changeLayoutToThumbnail: function(){
      this.controllerFor("search").set('layoutList', false);
    }.observes('controller.layoutList')

  },

  model: function(params) {
    console.debug(params);

    this.controllerFor("search").set("query", params.query);
    this.controllerFor("search").set("type", params.type);

    switch(params.type) {
      case "anime":
        //set the first object of the list to selectedAnime so we won't have an ugly empty display
        var controller = this.controllerFor("search");
        return this.store.find('anime', {}).then(
          function(animes){
            controller.set("animeSelected", animes.get("firstObject"));
            return animes;
        });

        break;
    }
  }
});
