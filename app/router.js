import Ember from 'ember';
import config from './config/environment';

var Router = Ember.Router.extend({
  location: config.locationType
});

Router.map(function() {
  this.resource('clients', function() {});
  this.resource('manga', function() {});
  this.resource('anime', function() {});
  this.resource('character', function() {});
  this.resource('author', function() {});
  this.resource('users', function() {});
  this.resource('animes', function() {});
  this.resource('signup', function() {});
  this.resource('profile', function() {});
  this.resource('logout', function() {});
  this.resource('account', function() {
    this.route('signin');
    this.route('login');
    this.route('logout');
    this.route('signup');
  });

  this.resource('protected', function() {
    this.route('page', function() {});
  });
  this.resource('search', {queryParams: ['q']}, function() {});
});

export default Router;
