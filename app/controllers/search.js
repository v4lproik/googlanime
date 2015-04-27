import Ember from 'ember';

export default Ember.Controller.extend({

  queryParams: ['query', 'type'],
  query: null,
  type: null,

  layoutList: false,

  actions: {
    search: function() {
      this.set('query', this.get('searchValue'));
      console.debug("changing search value");
      window.location.replace("/search?query=" + this.get('searchValue'));
    }
  }
});
