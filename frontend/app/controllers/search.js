import Ember from 'ember';

export default Ember.Controller.extend({

  // search parameters
  queryParams: ['query', 'type', 'fields', 'render'],
  search: false,
  query: null,
  type: '',
  fields: "title",
  render: "mal",

  // value of error filed
  error: "",

  // delay between each keyup on reseatch bar
  timeout: 0,

  layoutList: false,

  animeSelected: null,

  //checkbox type
  isAnimeType: false,
  watchIsAnimeType: function(key, value){

    var types = this.get('type').split(',');
    var index = types.indexOf("anime");

    if (value){
      if(index > -1){
        types.removeObject('anime');
      }else{
        types.addObject('anime');
      }
    }

    this.set("type", types.join(','));
  }.observes('isAnimeType'),

  //checkbox type
  isMangaType: false,
  watchIsMangaType: function(key, value){

    var types = this.get('type').split(',');
    var index = types.indexOf("manga");

    if (value){
      if(index > -1){
        types.removeObject('manga');
      }else{
        types.addObject('manga');
      }
    }

    this.set("type", types.join(','));
  }.observes('isMangaType'),

  //checkbox type
  isMangakaType: false,
  watchIsMangakaType: function(key, value){

    var types = this.get('type').split(',');
    var index = types.indexOf("mangaka");

    if (value){
      if(index > -1){
        types.removeObject('mangaka');
      }else{
        types.addObject('mangaka');
      }
    }

    this.set("type", types.join(','));
  }.observes('isMangakaType'),

  //checkbox type
  isCharacterType: false,
  watchIsCharacterType: function(key, value){

    var types = this.get('type').split(',');
    var index = types.indexOf("character");

    if (value){
      if(index > -1){
        types.removeObject('character');
      }else{
        types.addObject('character');
      }
    }

    this.set("type", types.join(','));
  }.observes('isCharacterType'),

  //checkbox type
  isPodcastType: false,
  watchIsPodcastType: function(key, value){

    var types = this.get('type').split(',');
    var index = types.indexOf("podcast");

    if (value){
      if(index > -1){
        types.removeObject('podcast');
      }else{
        types.addObject('podcast');
      }
    }

    this.set("type", types.join(','));
  }.observes('isPodcastType'),

  actions: {
    search: function () {
      this.set('query', this.get('searchValue'));
      console.debug("changing search value");
      window.location.replace("/search?query=" + this.get('searchValue'));
    },

    updateSeriePanel: function (anime) {
      this.set("animeSelected", anime);
    },

    changeLayoutToList: function(){
      this.set('layoutList', true);
    }.observes('controller.layoutList'),

    changeLayoutToThumbnail: function(){
      this.set('layoutList', false);
    }.observes('controller.layoutList'),

  }
});
