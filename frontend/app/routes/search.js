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

    this.controllerFor("search").set("query", params.query);
    this.controllerFor("search").set("type", params.type);

    switch(params.type) {
      case "anime":
        //set the first object of the list to selectedAnime so we won't have an ugly empty display
        var controller = this.controllerFor("search");
        return this.store.find('anime', {n : params.query}).then(
          function(animes){

            //item = animes.get("firstObject").get("titles");
            //for(key in item) {
            //  console.log(key + ", " + item[key]);
            //};
            console.debug(Ember.inspect(animes.get("firstObject")));
            controller.set("animeSelected", animes.get("firstObject"));
            return animes;
        });

        break;
    }
  }
});
