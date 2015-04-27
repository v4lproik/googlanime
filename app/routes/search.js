import Ember from 'ember';

export default Ember.Route.extend({

  queryParams: {
    category: {
      refreshModel: true
    }
  },

  model: function(params) {
    console.debug(params);
    this.controllerFor("search").set("query", params.query);
    this.controllerFor("search").set("type", params.type);
  }
});
