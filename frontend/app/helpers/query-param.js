import Ember from 'ember';

export function queryParam(input, controller) {
  return controller.get(input);
};

export default Ember.Handlebars.makeBoundHelper(queryParam);
