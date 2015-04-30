import Ember from 'ember';

export default Ember.Route.extend({

  model: function() {
    return this.store.findAll('anime');
  },

  actions: {
    changeAnimeLayoutToList: function(){
      this.controllerFor("animes").set('layoutAnimeList', true);
    }.observes('controller.layoutAnimeList'),

    changeAnimeLayoutToThumbnail: function(){
      this.controllerFor("animes").set('layoutAnimeList', false);
    }.observes('controller.layoutAnimeList')
  }

});
