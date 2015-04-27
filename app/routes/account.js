import Ember from 'ember';
import ApplicationRouteMixin from 'simple-auth/mixins/application-route-mixin';

export default Ember.Route.extend(ApplicationRouteMixin,{
  actions: {
    signUpWithGoogle: function() {
      var session = this.get('session');
      var controller = this.controllerFor('account');
      session.authenticate('simple-auth-authenticator:torii', 'google-token').then(function (data) {
      });
      return;
    }
  }
});
