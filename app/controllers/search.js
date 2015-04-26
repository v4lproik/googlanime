import Ember from 'ember';

export default Ember.Controller.extend({

  queryParams: ['q'],
  query: null,

  actions: {
    search: function() {
      this.set('query', this.get('searchValue'));
      console.debug("changing search value");
      window.location.replace("/search?q=" + this.get('searchValue'));
    }
  }
});
