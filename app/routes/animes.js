import Ember from 'ember';

export default Ember.Route.extend({

//  App.ApplicationAdapter = DS.RESTAdapter.extend({
//  host: 'https://cors-test.appspot.com',
//  ajax: function(url, method, hash) {
//    hash.crossDomain = true;
//    hash.xhrFields = {withCredentials: true};
//    return this._super(url, method, hash).then(function(json) {
//      // Massage this to look like RESTAdapter expects.
//      return { things: [json] };
//    });
//  }
//});

  model: function() {
    //var url = "htps://hummingbird.me/api/v2/anime/2";
    return this.store.findAll('anime');
    /*
      .then(function(data) {





         var res = data.titles;
         var array = [];

         if(!Ember.isArray(res.canonical)){

         }
        res.forEach(function(item, index){
          console.log('Player %@: %@'.fmt(index+1, item));
        });
        */
        /*
        for (var i = 0; i < titles.titles.length; i++){
          console.debug(titles.titles[i]);
        }

      });
  */
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
