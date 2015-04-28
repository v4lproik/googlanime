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
        return this.store.findAll('anime');
        break;
    }
  }
});
