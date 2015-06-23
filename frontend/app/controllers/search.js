import Ember from 'ember';

export default Ember.Controller.extend({

  queryParams: ['query', 'type', 'fields', 'render'],
  query: null,
  type: null,
  fields: "title",
  render: "mal",
  error: "",

  layoutList: false,

  animeSelected: null,

  actions: {
    search: function () {
      this.set('query', this.get('searchValue'));
      console.debug("changing search value");
      window.location.replace("/search?query=" + this.get('searchValue'));
    },

    updateSeriePanel: function (anime) {
      console.debug("changing serie panel value" + anime.get("titles.romaji"));
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
