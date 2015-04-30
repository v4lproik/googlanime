import DS from 'ember-data';
import App from 'ember';
import config from '../config/environment';

export default DS.RESTAdapter.extend({
  host: config.backend.host,
  namespace: config.backend.namespace
});

